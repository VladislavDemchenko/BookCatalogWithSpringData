package org.bookcatalog.bookcatalog.service.impl;

import lombok.RequiredArgsConstructor;
import org.bookcatalog.bookcatalog.dto.BookDto;
import org.bookcatalog.bookcatalog.entity.Book;
import org.bookcatalog.bookcatalog.entity.Catalog;
import org.bookcatalog.bookcatalog.exceptions.InvalidRequestException;
import org.bookcatalog.bookcatalog.exceptions.NotFoundContentException;
import org.bookcatalog.bookcatalog.repository.BookRepository;
import org.bookcatalog.bookcatalog.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    @Override
    public String save(BookDto bookDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult.getGlobalErrors().toString());
        }
        return bookRepository.save(new Book(bookDto)).toString();
    }

    @Override
    public String delete(Long id) {
        bookRepository.findById(id)
                .ifPresentOrElse(bookRepository::delete,
                        () -> {throw new InvalidRequestException("Not found book with id - " + id + " when trying to delete");});
        return "Book with id - " + id + " safely removed";
    }

    @Override
    public String findByBookName(String bookName) {
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
        bookRepository.findById(id).ifPresentOrElse(
                catalog -> {
                    if (bookRepository.findByName(bookName).isEmpty()) {
                        catalog.setName(bookName);
                        bookRepository.save(catalog);
                    }else {
                        throw new InvalidRequestException("This name already exist");
                    }
                },
                () -> { throw new NotFoundContentException("Catalog with id - "+ id + " haven't been created"); }
        );
        return "Updated";
    }

    @Override
    public String updateBookBody(Long id, String descriptionName) {
        bookRepository.findById(id).ifPresentOrElse(book -> {
            book.setName(descriptionName);
            bookRepository.save(book);
        },
        () -> { throw new InvalidRequestException("This name already exist"); });
        return "Updated";
    }
}
