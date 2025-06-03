package org.bookcatalog;

import org.bookcatalog.dto.BookDto;
import org.bookcatalog.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookTest {

    @Mock
    private BookService bookService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private BookController bookController;

    public BookTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBookTest() {
        BookDto dto = new BookDto("book", "body", 1L);
        when(bookService.save(dto, bindingResult)).thenReturn("created");
        ResponseEntity<?> response = bookController.addBook(dto, bindingResult);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("created", response.getBody());
    }

    @Test
    void deleteTest() {
        when(bookService.delete(1L)).thenReturn("deleted");
        ResponseEntity<?> response = bookController.delete(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("deleted", response.getBody());
    }

    @Test
    void findByNameTest() {
        when(bookService.findByBookName("book")).thenReturn("found");
        ResponseEntity<?> response = bookController.findByName("book");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("found", response.getBody());
    }

    @Test
    void getAllUsersTest() {
        when(bookService.findAll()).thenReturn("all");
        ResponseEntity<?> response = bookController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("all", response.getBody());
    }

    @Test
    void updateNameTest() {
        when(bookService.updateBookName(1L, "newName")).thenReturn("updated");
        ResponseEntity<?> response = bookController.updateName(1L, "newName");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("updated", response.getBody());
    }

    @Test
    void updateBodyTest() {
        when(bookService.updateBookBody(1L, "newBody")).thenReturn("body updated");
        ResponseEntity<?> response = bookController.updateBody(1L, "newBody");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("body updated", response.getBody());
    }
} 