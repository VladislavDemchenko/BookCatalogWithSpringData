package org.bookcatalog.bookcatalog.service;

import org.bookcatalog.bookcatalog.dto.CatalogDto;
import org.springframework.validation.BindingResult;

public interface CatalogService {

     String save(CatalogDto catalogDto, BindingResult bindingResult);

     String delete(Long id);

     String findByCatalogName(String catalogName);

     String findAll();

     String updateCatalogName(Long id, String catalogName);

     String updateCatalogDescription(Long id, String descriptionName);
}
