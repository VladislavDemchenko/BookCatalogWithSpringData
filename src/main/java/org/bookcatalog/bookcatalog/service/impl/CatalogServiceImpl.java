package org.bookcatalog.bookcatalog.service.impl;

import lombok.RequiredArgsConstructor;
import org.bookcatalog.bookcatalog.dto.CatalogDto;
import org.bookcatalog.bookcatalog.entity.Catalog;
import org.bookcatalog.bookcatalog.exceptions.InvalidRequestException;
import org.bookcatalog.bookcatalog.exceptions.NotFoundContentException;
import org.bookcatalog.bookcatalog.repository.CatalogRepository;
import org.bookcatalog.bookcatalog.service.CatalogService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    public String save(CatalogDto catalogDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult.getFieldError().getDefaultMessage());
        }
        return catalogRepository.save(new Catalog(catalogDto)).toString();
    }

    public String delete(Long id){
        catalogRepository.findById(id)
                .ifPresentOrElse(catalogRepository::delete,
                        () -> {throw new InvalidRequestException("Not found Catalog with id - " + id + " when trying to delete");});
        return "Catalog with id - " + id + " safely removed";
    }

    public String findByCatalogName(String catalogName) {
        return catalogRepository.findByCatalogName(catalogName)
                .orElseThrow(()-> new NotFoundContentException("Not found catalog by name <" + catalogName + ">"))
                .toString();
    }

    public String findAll() {
        List<Catalog> catalogs = catalogRepository.findAll();
        if(catalogs.isEmpty()){
            throw new NotFoundContentException("Not found any catalogs");
        }
        return catalogs.toString();
    }
    public String updateCatalogName(Long id, String catalogName) {
        catalogRepository.findById(id).ifPresentOrElse(
                catalog -> {
                    if (catalogRepository.searchByCatalogName(catalogName) == 0) {
                        catalog.setCatalogName(catalogName);
                        catalogRepository.save(catalog);
                    }else {
                        throw new InvalidRequestException("This name already exist");
                    }
                    //or
//                    try {
//                    }catch (DataIntegrityViolationException e){
//                    }
                },
                () -> { throw new NotFoundContentException("Catalog with name " + catalogName + " haven't been created"); }
        );
        return "Updated";
    }

    public String updateCatalogDescription(Long id, String descriptionName) {
        catalogRepository.findById(id).ifPresentOrElse(catalog -> {
            catalog.setDescription(descriptionName);
            catalogRepository.save(catalog);
        },
        () -> { throw new NotFoundContentException("Not found catalog with id - " + id); });
        return "Updated";
    }
}
