package org.bookcatalog.bookcatalog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bookcatalog.bookcatalog.dto.CatalogDto;
import org.bookcatalog.bookcatalog.service.impl.CatalogServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/catalogs")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogServiceImpl catalogServiceImpl;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CatalogDto catalogDto, BindingResult bindingResult){
        return new ResponseEntity<>(catalogServiceImpl.save(catalogDto, bindingResult), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return new ResponseEntity<>(catalogServiceImpl.delete(id), HttpStatus.OK);
    }


    @GetMapping("/findByName")
    public ResponseEntity<?> findByName(@RequestParam String catalogName){
        return new ResponseEntity<>(catalogServiceImpl.findByName(catalogName), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(catalogServiceImpl.findAll(), HttpStatus.OK);
    }

    @PutMapping("/updateName/{id}")
    public ResponseEntity<?> changeName(@PathVariable Long id, @RequestParam @Valid String catalogName){
        return new ResponseEntity<>(catalogServiceImpl.updateCatalogName(id, catalogName), HttpStatus.OK);
    }
    @PutMapping("/updateDescription/{id}")
    public ResponseEntity<?> changeDescriptionName(@PathVariable Long id, @RequestParam String description){
        return new ResponseEntity<>(catalogServiceImpl.updateCatalogDescription(id, description), HttpStatus.OK);
    }
}
