package org.bookcatalog.bookcatalog.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bookcatalog.bookcatalog.dto.NoteDto;
import org.bookcatalog.bookcatalog.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    @PostMapping("/create")
    public ResponseEntity<?> addNote(@RequestBody @Valid NoteDto noteDto, BindingResult bindingResult){
        return new ResponseEntity<>(noteService.save(noteDto, bindingResult), HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return new ResponseEntity<>(noteService.delete(id), HttpStatus.OK);
    }


    @GetMapping("/findByBody")
    public ResponseEntity<?> findByBody(@RequestParam String body){
        return new ResponseEntity<>(noteService.findByBody(body), HttpStatus.OK);
    }


    @GetMapping("/findAll")
    public ResponseEntity<?> getAllNotes(){
        return new ResponseEntity<>(noteService.findAll(), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/updateBody/{id}")
    public ResponseEntity<?> updateBody(@PathVariable Long id, @RequestParam String body){
        return new ResponseEntity<>(noteService.updateNoteBody(id, body), HttpStatus.OK);
    }

}
