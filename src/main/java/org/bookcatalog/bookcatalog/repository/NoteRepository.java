package org.bookcatalog.bookcatalog.repository;

import org.bookcatalog.bookcatalog.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Optional<Note>> findByBody(String body);

}
