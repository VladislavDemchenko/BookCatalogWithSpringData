package org.bookcatalog.bookcatalog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bookcatalog.bookcatalog.entity.Note;
import org.bookcatalog.bookcatalog.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepository noteRepository;

    @PostMapping("/addNote")
    public Note addNote(@RequestBody @Valid Note note){
        return noteRepository.save(note);
    }
//
//    @DeleteMapping("/deleteNote/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id){
//        return noteService.delete(id, Note.class);
//    }
//
//    @PutMapping("/updateNote/{id}")
//    public ResponseEntity<?> change(@PathVariable Long id, @RequestParam String descriptionName){
//        return noteService.updateName(id, descriptionName,new FieldDto<String>("name"), Note.class);
//    }
//
//    @GetMapping("/getNote/{id}")
//    public ResponseEntity<?> getNote(@PathVariable Long id){
//        return noteService.findById(id, Note.class);
//    }
//
//    @GetMapping("/getAllNote")
//    public ResponseEntity<?> getAllNote(){
//        return noteService.findAll(Note.class);
//    }
//
//    @Autowired
//    public NoteController(NoteService noteService) {
//        this.noteService = noteService;
//    }
}
