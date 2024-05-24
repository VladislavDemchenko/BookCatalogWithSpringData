package org.bookcatalog.bookcatalog.service;

import org.bookcatalog.bookcatalog.dto.CatalogDto;
import org.bookcatalog.bookcatalog.dto.NoteDto;
import org.bookcatalog.bookcatalog.entity.Note;

public interface NoteService extends GlobalService<NoteDto>{

    String findByBody(String body);
    String updateNoteBody(Long id, String bodyName);
}
