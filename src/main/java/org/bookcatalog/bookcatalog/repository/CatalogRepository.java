package org.bookcatalog.bookcatalog.repository;

import org.bookcatalog.bookcatalog.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    Optional<Catalog> findByCatalogName(String catalogName);

}
