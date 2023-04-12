package com.ramon.utils;

import java.util.regex.Pattern;

public class CheckName {
    public static boolean isValidName(String fullName) {
        String fullNameCapitalize = CheckName.capitalizeWords(fullName);
        // Regex pattern to validate name
        String regexPattern = "^[A-Za-z]+(\\s[A-Za-z]+)*$";
        return Pattern.compile(regexPattern)
                .matcher(fullNameCapitalize)
                .matches();
    }

    public static String capitalizeWords(String name) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;
        for (char ch : name.toCharArray()) {
            if (Character.isWhitespace(ch)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                ch = Character.toUpperCase(ch);
                capitalizeNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            result.append(ch);
        }
        return result.toString();
    }
}
