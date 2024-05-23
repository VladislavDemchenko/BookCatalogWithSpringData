package org.bookcatalog.bookcatalog.repository;

import org.bookcatalog.bookcatalog.dto.CatalogDto;
import org.bookcatalog.bookcatalog.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    Optional<Catalog> findByCatalogName(String catalogName);

}
