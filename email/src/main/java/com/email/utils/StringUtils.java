package com.email.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StringUtils {

    // Regular expression for validating an email address
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    /**
     * Validates an email address using a regular expression.
     *
     * @param email the email address to validate
     * @return true if the email address is valid, false otherwise
     */
    public static boolean validateEmail(String email) {
        if (email == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static boolean isEmpty(String text) {
    	return text == null || text.isEmpty();
    }
}
