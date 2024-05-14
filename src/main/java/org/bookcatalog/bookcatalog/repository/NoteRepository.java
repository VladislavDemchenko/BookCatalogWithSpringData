package org.bookcatalog.bookcatalog.repository;

import org.bookcatalog.bookcatalog.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
