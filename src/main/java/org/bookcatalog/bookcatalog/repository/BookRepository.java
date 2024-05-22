package org.bookcatalog.bookcatalog.repository;

import org.bookcatalog.bookcatalog.entity.Book;
import org.bookcatalog.bookcatalog.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String bookName);
}
