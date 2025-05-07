package org.bookcatalog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CatalogDto(@NotNull(message = "field Catalog.name cannot be null")
                         @NotEmpty(message = "field Catalog.name cannot be empty")
                         String catalogName,
                         String catalogDescription) {
}
