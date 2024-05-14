package org.bookcatalog.bookcatalog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bookcatalog.bookcatalog.entity.Book;
import org.bookcatalog.bookcatalog.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @PostMapping("/create")
    public Book addBook(@RequestBody @Valid Book book){
        return bookRepository.save(book);
    }


//    @DeleteMapping("/deleteBook{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id){
//        return bookService.delete(id, Book.class);
//    }
//
//
//    @GetMapping("/getBook/{id}")
//    public ResponseEntity<?> findById(@PathVariable Long id){
//        return bookService.findById(id, Book.class);
//    }
//
//    @GetMapping("/findByName")
//    public ResponseEntity<?> findByName(@RequestParam String bookName){
//        return bookService.findByName(bookName, Book.class);
//    }
//
//    @GetMapping("/allBook")
//    public ResponseEntity<?> getAllUsers(){
//        return bookService.findAll(Book.class);
//    }
}
