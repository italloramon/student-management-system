package com.ramon.utils;

import com.ramon.exception.InvalidFieldException;

public class CheckAllFields {
    public static boolean checkAllFields(String fullName, String cpf, String email) throws InvalidFieldException {
        if (!CheckName.isValidName(fullName)) {
            throw new InvalidFieldException("Name", fullName);
        } else if (!CheckCPF.isCPF(cpf)) {
            throw  new InvalidFieldException("CPF", cpf);
        } else if (!CheckEmail.isEmail(email)) {
            throw new InvalidFieldException("Email", email);
        }
        return true;
    }
}
