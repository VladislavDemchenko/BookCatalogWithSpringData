package org.bookcatalog.bookcatalog.service;

import org.bookcatalog.bookcatalog.dto.CatalogDto;
import org.springframework.validation.BindingResult;

public interface CatalogService extends GlobalService<CatalogDto>{

     String updateCatalogName(Long id, String catalogName);

     String updateCatalogDescription(Long id, String descriptionName);
}
