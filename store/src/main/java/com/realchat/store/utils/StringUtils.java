package com.realchat.store.utils;

import java.util.Random;

public class StringUtils {
    
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();
    
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
}