package org.bookcatalog.bookcatalog.service;


import org.bookcatalog.bookcatalog.dto.BookDto;

public interface BookService extends GlobalService<BookDto>{

    String findByBookName(String name);
    String updateBookName(Long id, String bookName);

    String updateBookBody(Long id, String descriptionName);
}
