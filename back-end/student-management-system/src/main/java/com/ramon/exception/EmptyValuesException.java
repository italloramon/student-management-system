package com.ramon.exception;

public class EmptyValuesException extends Exception {
    public EmptyValuesException() {
        super("You cannot leave empty fields!");
    }
}
