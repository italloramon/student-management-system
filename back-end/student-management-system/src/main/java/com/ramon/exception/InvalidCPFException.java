package com.ramon.exception;

public class InvalidCPFException extends Exception {
    public InvalidCPFException(String cpf) {
        super("The CPF " + cpf + " is invalid. Please type a valid CPF!");
    }
}
