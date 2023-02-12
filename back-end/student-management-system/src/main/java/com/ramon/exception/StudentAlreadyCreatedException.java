package com.ramon.exception;

public class StudentAlreadyCreatedException extends RuntimeException {
    public StudentAlreadyCreatedException(String cpf) {
        super("The student with CPF: " + cpf + " already exists in our database");
    }
}
