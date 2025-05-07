package org.bookcatalog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bookcatalog.dto.CatalogDto;
import org.bookcatalog.service.CatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/catalogs")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CatalogDto catalogDto, BindingResult bindingResult){
        return new ResponseEntity<>(catalogService.save(catalogDto, bindingResult), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return new ResponseEntity<>(catalogService.delete(id), HttpStatus.OK);
    }


    @GetMapping("/findByName")
    public ResponseEntity<?> findByName(@RequestParam String catalogName){
        return new ResponseEntity<>(catalogService.findByCatalogName(catalogName), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(catalogService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/updateName/{id}")
    public ResponseEntity<?> changeName(@PathVariable Long id, @RequestParam @Valid String catalogName){
        return new ResponseEntity<>(catalogService.updateCatalogName(id, catalogName), HttpStatus.OK);
    }
    @PutMapping("/updateDescription/{id}")
    public ResponseEntity<?> changeDescriptionName(@PathVariable Long id, @RequestParam String description){
        return new ResponseEntity<>(catalogService.updateCatalogDescription(id, description), HttpStatus.OK);
    }
}
