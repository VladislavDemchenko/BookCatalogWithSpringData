package org.bookcatalog.bookcatalog.service;

import org.bookcatalog.bookcatalog.dto.BookDto;
import org.springframework.validation.BindingResult;

public interface GlobalService<T> {
    String save(T dto, BindingResult bindingResult);

    String delete(Long id);

    String findByName(String name);

    String findAll();

}
