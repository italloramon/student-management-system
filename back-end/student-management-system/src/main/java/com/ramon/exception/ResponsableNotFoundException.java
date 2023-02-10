package com.ramon.exception;

public class ResponsableNotFoundException extends RuntimeException {
    public ResponsableNotFoundException(Long id) {
        super("Could not find responsable " + id);
    }
}
