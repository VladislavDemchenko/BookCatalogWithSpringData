package org.bookcatalog.bookcatalog.service.impl;

import lombok.RequiredArgsConstructor;
import org.bookcatalog.bookcatalog.dto.NoteDto;
import org.bookcatalog.bookcatalog.entity.Book;
import org.bookcatalog.bookcatalog.entity.Note;
import org.bookcatalog.bookcatalog.exceptions.InvalidRequestException;
import org.bookcatalog.bookcatalog.exceptions.NotFoundContentException;
import org.bookcatalog.bookcatalog.repository.BookRepository;
import org.bookcatalog.bookcatalog.repository.NoteRepository;
import org.bookcatalog.bookcatalog.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    private final BookRepository bookRepository;
    @Override
    public String save(NoteDto noteDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult.getAllErrors().toString());
        }

        Book book = bookRepository.findById(noteDto.bookId())
                .orElseThrow(() -> new NotFoundContentException("Not found book with id - " + noteDto.bookId()));

        return noteRepository.save(new Note(noteDto, book)).toString();
    }
    @Override
    public String delete(Long id){
        noteRepository.findById(id)
                .ifPresentOrElse(noteRepository::delete,
                        () -> {throw new InvalidRequestException("Not found Note with id - " + id + " when trying to delete");});
        return "Note with id - " + id + " safely removed";
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
    public String findByBody(String body){
        return noteRepository.findByBody(body)
                .orElseThrow(()-> new NotFoundContentException("Not found note"))
                .toString();
    }

    @Override
    public String updateNoteBody(Long id, String body) {
        noteRepository.findById(id).ifPresentOrElse(note -> {
                    note.setBody(body);
                    noteRepository.save(note);
                },
                () -> { throw new NotFoundContentException("Not found note with id - " + id); });
        return "Updated";
    }

}
