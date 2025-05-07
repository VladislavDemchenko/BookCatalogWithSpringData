package org.bookcatalog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bookcatalog.dto.BookDto;
import org.bookcatalog.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    @PostMapping("/create")
    public ResponseEntity<?> addBook(@RequestBody @Valid BookDto bookDto, BindingResult bindingResult){
        return new ResponseEntity<>(bookService.save(bookDto, bindingResult), HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return new ResponseEntity<>(bookService.delete(id), HttpStatus.OK);
    }


    @GetMapping("/findByName")
    public ResponseEntity<?> findByName(@RequestParam String bookName){
        return new ResponseEntity<>(bookService.findByBookName(bookName), HttpStatus.OK);
    }


    @GetMapping("/findAll")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/updateName/{id}")
    public ResponseEntity<?> updateName(@PathVariable Long id, @RequestParam String name){
        return new ResponseEntity<>(bookService.updateBookName(id, name), HttpStatus.OK);
    }

    @PutMapping("/updateBody/{id}")
    public ResponseEntity<?> updateBody(@PathVariable Long id, @RequestParam String body){
        return new ResponseEntity<>(bookService.updateBookBody(id, body), HttpStatus.OK);
    }
}
