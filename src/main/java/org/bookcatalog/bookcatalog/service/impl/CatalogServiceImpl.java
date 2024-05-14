package org.bookcatalog.bookcatalog.service.impl;

import lombok.RequiredArgsConstructor;
import org.bookcatalog.bookcatalog.dto.CatalogDto;
import org.bookcatalog.bookcatalog.entity.Catalog;
import org.bookcatalog.bookcatalog.exceptions.InvalidRequestException;
import org.bookcatalog.bookcatalog.exceptions.NotFoundContentException;
import org.bookcatalog.bookcatalog.repository.CatalogRepository;
import org.bookcatalog.bookcatalog.service.CatalogService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    @Override
    public String save(CatalogDto catalogDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult.getGlobalErrors().toString());
        }
        return catalogRepository.save(new Catalog(catalogDto)).toString();
    }
    @Override
    public String delete(Long id){
        catalogRepository.findById(id)
                .ifPresentOrElse(catalogRepository::delete,
                        () -> {throw new InvalidRequestException("Not found Catalog with id - " + id + " when trying to delete");});
        return "Catalog with id - " + id + " safely removed";
    }
    @Override
    public String findByCatalogName(String catalogName) {
        return catalogRepository.findByCatalogName(catalogName)
                .orElseThrow(()-> new NotFoundContentException("Not found catalog by name <" + catalogName + ">"))
                .toString();
    }
    @Override
    public String findAll() {
        List<Catalog> catalogs = catalogRepository.findAll();
        if(catalogs.isEmpty()){
            throw new NotFoundContentException("Not found any catalogs");
        }
        return catalogs.toString();
    }
    @Override
    public String updateCatalogName(Long id, String catalogName) {
        catalogRepository.findById(id).ifPresentOrElse(
                catalog -> {
                    if (catalogRepository.findByCatalogName(catalogName).isEmpty()) {
                        catalog.setCatalogName(catalogName);
                        catalogRepository.save(catalog);
                    }else {
                        throw new InvalidRequestException("This name already exist");
                    }
                },
                () -> { throw new NotFoundContentException("Book with id - "+ id + " haven't been created"); }
        );
        return "Updated";
    }
    @Override
    public String updateCatalogDescription(Long id, String descriptionName) {
        catalogRepository.findById(id).ifPresentOrElse(catalog -> {
            catalog.setDescription(descriptionName);
            catalogRepository.save(catalog);
        },
        () -> { throw new NotFoundContentException("Not found catalog with id - " + id); });
        return "Updated";
    }
}
