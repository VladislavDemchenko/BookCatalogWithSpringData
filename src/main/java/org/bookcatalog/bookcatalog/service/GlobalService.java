package org.bookcatalog.bookcatalog.service;

import org.springframework.validation.BindingResult;

public interface GlobalService<T> {
    String save(T dto, BindingResult bindingResult);

    String delete(Long id);

    String findAll();

}
