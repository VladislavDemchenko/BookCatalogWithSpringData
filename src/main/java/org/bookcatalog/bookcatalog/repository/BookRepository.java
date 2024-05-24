package org.bookcatalog.bookcatalog.repository;

import org.bookcatalog.bookcatalog.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String bookName);
}
