package org.bookcatalog;

import org.bookcatalog.dto.NoteDto;
import org.bookcatalog.service.NoteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NoteTest {

    @Mock
    private NoteService noteService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private NoteController noteController;

    public NoteTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addNoteTest() {
        NoteDto dto = new NoteDto("body", 1L);
        when(noteService.save(dto, bindingResult)).thenReturn("created");
        ResponseEntity<?> response = noteController.addNote(dto, bindingResult);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("created", response.getBody());
    }

    @Test
    void deleteTest() {
        when(noteService.delete(1L)).thenReturn("deleted");
        ResponseEntity<?> response = noteController.delete(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("deleted", response.getBody());
    }

    @Test
    void findByBodyTest() {
        when(noteService.findByBody("body")).thenReturn("found");
        ResponseEntity<?> response = noteController.findByBody("body");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("found", response.getBody());
    }

    @Test
    void getAllNotesTest() {
        when(noteService.findAll()).thenReturn("all");
        ResponseEntity<?> response = noteController.getAllNotes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("all", response.getBody());
    }

    @Test
    void updateBodyTest() {
        when(noteService.updateNoteBody(1L, "newBody")).thenReturn("body updated");
        ResponseEntity<?> response = noteController.updateBody(1L, "newBody");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("body updated", response.getBody());
    }
} 