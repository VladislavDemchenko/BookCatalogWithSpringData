package org.bookcatalog;

import org.bookcatalog.dto.CatalogDto;
import org.bookcatalog.service.CatalogService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CatalogTest {

    @Mock
    private CatalogService catalogService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private CatalogController catalogController;

    public CatalogTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTest() {
        CatalogDto dto = new CatalogDto("test", "desc");
        when(catalogService.save(dto, bindingResult)).thenReturn("created");
        ResponseEntity<?> response = catalogController.create(dto, bindingResult);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("created", response.getBody());
    }

    @Test
    void deleteTest() {
        when(catalogService.delete(1L)).thenReturn("deleted");
        ResponseEntity<?> response = catalogController.delete(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("deleted", response.getBody());
    }

    @Test
    void findByNameTest() {
        when(catalogService.findByCatalogName("test")).thenReturn("found");
        ResponseEntity<?> response = catalogController.findByName("test");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("found", response.getBody());
    }

    @Test
    void findByNameContainingTest() {
        when(catalogService.findByCatalogNameContaining("test")).thenReturn("filtered");
        ResponseEntity<?> response = catalogController.findByNameContaining("test");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("filtered", response.getBody());
    }

    @Test
    void findByDescriptionTest() {
        when(catalogService.findByDescription("desc")).thenReturn("desc found");
        ResponseEntity<?> response = catalogController.findByDescription("desc");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("desc found", response.getBody());
    }

    @Test
    void findByKeywordTest() {
        when(catalogService.findByKeyword("key")).thenReturn("keyword found");
        ResponseEntity<?> response = catalogController.findByKeyword("key");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("keyword found", response.getBody());
    }

    @Test
    void findAllTest() {
        when(catalogService.findAll()).thenReturn("all");
        ResponseEntity<?> response = catalogController.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("all", response.getBody());
    }

    @Test
    void changeNameTest() {
        when(catalogService.updateCatalogName(1L, "newName")).thenReturn("updated");
        ResponseEntity<?> response = catalogController.changeName(1L, "newName");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("updated", response.getBody());
    }

    @Test
    void changeDescriptionNameTest() {
        when(catalogService.updateCatalogDescription(1L, "newDesc")).thenReturn("desc updated");
        ResponseEntity<?> response = catalogController.changeDescriptionName(1L, "newDesc");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("desc updated", response.getBody());
    }
}