package org.bookcatalog.repository;

import org.bookcatalog.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    Optional<Catalog> findByCatalogName(String catalogName);
    
    List<Catalog> findByCatalogNameContainingIgnoreCase(String catalogName);
    
    List<Catalog> findByDescriptionContainingIgnoreCase(String description);
    
    @Query("SELECT c FROM Catalog c WHERE LOWER(c.catalogName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Catalog> findByCatalogNameOrDescriptionContainingIgnoreCase(@Param("keyword") String keyword);
}
