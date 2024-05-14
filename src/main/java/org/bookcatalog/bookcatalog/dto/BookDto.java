package org.bookcatalog.bookcatalog.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookDto (@NotNull(message = "field Catalog.name cannot be null")
                       @NotEmpty(message = "field Catalog.name cannot be empty")
                       @Column(unique = true) String name,
                       String body){}