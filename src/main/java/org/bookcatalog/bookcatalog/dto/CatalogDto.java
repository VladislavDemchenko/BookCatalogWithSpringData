package org.bookcatalog.bookcatalog.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

public record CatalogDto(@NotNull(message = "field Catalog.name cannot be null")
                         @NotEmpty(message = "field Catalog.name cannot be empty")
                         @Column(unique = true)
                         String catalogName,
                         String catalogDescription) {
}
