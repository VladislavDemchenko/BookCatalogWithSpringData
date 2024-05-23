package org.bookcatalog.bookcatalog.service.impl;

import lombok.RequiredArgsConstructor;
import org.bookcatalog.bookcatalog.dto.BookDto;
import org.bookcatalog.bookcatalog.entity.Book;
import org.bookcatalog.bookcatalog.entity.Catalog;
import org.bookcatalog.bookcatalog.exceptions.InvalidRequestException;
import org.bookcatalog.bookcatalog.exceptions.NotFoundContentException;
import org.bookcatalog.bookcatalog.repository.BookRepository;
import org.bookcatalog.bookcatalog.repository.CatalogRepository;
import org.bookcatalog.bookcatalog.service.BookService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final CatalogRepository catalogRepository;

    @Override
    public String save(BookDto bookDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult.getAllErrors().toString());
        }

        Catalog catalog = catalogRepository.findById(bookDto.catalogId())
                .orElseThrow(() -> new NotFoundContentException("Catalog not found with id: " + bookDto.catalogId()));

        Book book = new Book();
        book.setName(bookDto.name());
        book.setBody(bookDto.body());
        book.setCatalog(catalog);

        return saveBookWithCatchUniqueException(book).toString();
    }

    @Override
    public String delete(Long id) {
        bookRepository.findById(id)
                .ifPresentOrElse(bookRepository::delete,
                        () -> {throw new InvalidRequestException("Not found book with id - " + id + " when trying to delete");});
        return "Book with id - " + id + " safely removed";
    }

    @Override
    public String findByName(String bookName) {
        return bookRepository.findByName(bookName)
                .orElseThrow(()-> new NotFoundContentException("Not found book by name <" + bookName + ">"))
                .toString();
    }

    @Override
    public String findAll() {
        List<Book> book = bookRepository.findAll();
        if(book.isEmpty()){
            throw new NotFoundContentException("Not found any book");
        }
        return book.toString();
    }

    @Override
    public String updateBookName(Long id, String bookName) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundContentException("Book with id " + id + " hasn't been created"));

        if (bookName.isEmpty()) {
            throw new InvalidRequestException("New name of book can't be empty");
        }

        book.setName(bookName);
        return saveBookWithCatchUniqueException(book).toString();
    }

    @Override
    public String updateBookBody(Long id, String body) {
        bookRepository.findById(id).ifPresentOrElse(book -> {
            book.setBody(body);
            bookRepository.save(book);
        },
        () -> { throw new NotFoundContentException("Book with id " + id + " hasn't been created"); });
        return "Updated";
    }

    private Book saveBookWithCatchUniqueException(Book book) {
        try {
            return bookRepository.save(book);
        }catch(DataIntegrityViolationException e){
            throw new InvalidRequestException("A book with this name already exist");
        }
    }
}
