package org.bookcatalog.bookcatalog.service;

import org.bookcatalog.bookcatalog.dto.BookDto;
import org.bookcatalog.bookcatalog.dto.CatalogDto;
import org.springframework.validation.BindingResult;

public interface BookService {

    String save(BookDto bookDto, BindingResult bindingResult);

    String delete(Long id);

    String findByBookName(String bookName);

    String findAll();

    String updateBookName(Long id, String bookName);

    String updateBookBody(Long id, String descriptionName);
}
