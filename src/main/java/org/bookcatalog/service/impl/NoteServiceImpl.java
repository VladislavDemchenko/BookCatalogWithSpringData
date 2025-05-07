package org.bookcatalog.service.impl;

import lombok.RequiredArgsConstructor;
import org.bookcatalog.dto.NoteDto;
import org.bookcatalog.entity.Book;
import org.bookcatalog.entity.Note;
import org.bookcatalog.exceptions.InvalidRequestException;
import org.bookcatalog.exceptions.NotFoundContentException;
import org.bookcatalog.repository.BookRepository;
import org.bookcatalog.repository.NoteRepository;
import org.bookcatalog.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    private final BookRepository bookRepository;
    @Override
    @Transactional
    public String save(NoteDto noteDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult.getAllErrors().toString());
        }

        Book book = bookRepository.findById(noteDto.bookId())
                .orElseThrow(() -> new NotFoundContentException("Not found book with id - " + noteDto.bookId()));

        return noteRepository.save(new Note(noteDto, book)).toString();
    }
    @Override
    @Transactional
    public String delete(Long id){
        noteRepository.findById(id)
                .ifPresentOrElse(noteRepository::delete,
                        () -> {throw new InvalidRequestException("Not found Note with id - " + id + " when trying to delete");});
        return "Note with id - " + id + " safely removed";
    }

    @Override
    public String findByBody(String body){
        return noteRepository.findByBody(body).stream()
                .map(list -> list.orElseThrow(() -> new NotFoundContentException("Not found note")))
                .toList()
                .toString();
    }
    @Override
    public String findAll() {
        List<Note> note = noteRepository.findAll();
        if(note.isEmpty()){
            throw new NotFoundContentException("Not found any notes");
        }
        return note.toString();
    }

    @Override
    @Transactional
    public String updateNoteBody(Long id, String body) {
        noteRepository.findById(id).ifPresentOrElse(note -> {
                    note.setBody(body);
                },
                () -> { throw new NotFoundContentException("Not found note with id - " + id); });
        return "Updated";
    }

}
