package org.bookcatalog.bookcatalog.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.bookcatalog.bookcatalog.entity.Catalog;
import org.bookcatalog.bookcatalog.repository.CatalogRepository;

public record BookDto (@NotNull(message = "field Book.name cannot be null")
                       @NotEmpty(message = "field Book.name cannot be empty")
                       String name,
                       String body,
                       @NotNull(message = "field catalogId cannot be null")
                       @Positive(message = "field catalogId must be positive")
                       Long catalogId) {
}
