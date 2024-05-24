package org.bookcatalog.bookcatalog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NoteDto(@NotNull(message = "note can`t be null")
                      @NotEmpty(message = "note can`t be empty")
                      String body,
                      Long bookId) {
}
