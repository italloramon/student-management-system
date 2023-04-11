package com.ramon.exception;

public class InvalidFieldException extends Exception {
    public InvalidFieldException(String field, String contentField) {
        super("The " + field + " " + contentField + " is invalid. Please type a valid " + field + "!");
    }
}
