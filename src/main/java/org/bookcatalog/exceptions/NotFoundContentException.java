package org.bookcatalog.exceptions;

public class NotFoundContentException extends RuntimeException{
    public NotFoundContentException(String message) {
        super(message);
    }
}
