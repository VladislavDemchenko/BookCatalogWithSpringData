package org.bookcatalog.service;

import org.bookcatalog.dto.CatalogDto;

public interface CatalogService extends GlobalService<CatalogDto>{

     String findByCatalogName (String name);
     String updateCatalogName(Long id, String catalogName);

     String updateCatalogDescription(Long id, String descriptionName);
}
