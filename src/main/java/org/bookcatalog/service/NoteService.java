package org.bookcatalog.service;

import org.bookcatalog.dto.NoteDto;

public interface NoteService extends GlobalService<NoteDto>{

    String findByBody(String body);
    String updateNoteBody(Long id, String bodyName);
}
