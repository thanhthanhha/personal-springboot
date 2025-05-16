package com.realchat.store.utils;

import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StringUtils {
    
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();
    
    // Regular expression for validating email addresses
    private static final String EMAIL_REGEX = 
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
    /**
     * Generates a random alphanumeric string of specified length
     * @param length The length of the string to generate
     * @return A random alphanumeric string
     */
    public static String generateRandomAlphanumeric(int length) {
        StringBuilder sb = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(ALPHANUMERIC.length());
            sb.append(ALPHANUMERIC.charAt(index));
        }
        
        return sb.toString();
    }
    
    /**
     * Validates if a string is in valid email format
     * @param email The string to check
     * @return true if the string is a valid email, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}