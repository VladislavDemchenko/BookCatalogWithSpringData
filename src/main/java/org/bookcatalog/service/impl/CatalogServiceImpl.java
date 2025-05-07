package org.bookcatalog.service.impl;

import lombok.RequiredArgsConstructor;
import org.bookcatalog.dto.CatalogDto;
import org.bookcatalog.entity.Catalog;
import org.bookcatalog.exceptions.InvalidRequestException;
import org.bookcatalog.exceptions.NotFoundContentException;
import org.bookcatalog.repository.CatalogRepository;
import org.bookcatalog.service.CatalogService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    @Override
    @Transactional // default readOnly false
    public String save(CatalogDto catalogDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult.getAllErrors().toString());
        }
        try{
            return catalogRepository.save(new Catalog(catalogDto)).toString();
        }catch(DataIntegrityViolationException e){
            throw new InvalidRequestException("A catalog with this name already exist");
        }
    }
    @Override
    @Transactional // default readOnly false
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
    @Transactional // default readOnly false
    public String updateCatalogName(Long id, String catalogName) {

        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new NotFoundContentException("Catalog with id " + id + " hasn't been created"));

        if (catalogName.isEmpty()) {
            throw new InvalidRequestException("New name of catalog can't be empty");
        }
        catalog.setCatalogName(catalogName);
        return catalog.toString();
    }

    @Override
    @Transactional // default readOnly false
    public String updateCatalogDescription(Long id, String descriptionName) {
        catalogRepository.findById(id).ifPresentOrElse(catalog -> {
            catalog.setDescription(descriptionName);
        },
        () -> { throw new NotFoundContentException("Not found catalog with id - " + id); });
        return "Updated on new description - " + descriptionName;
    }


}
