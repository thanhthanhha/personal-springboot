package com.realchat.store.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SecurityUtils {
    
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    
    /**
     * Generates a random salt for password hashing
     * @param length The length of the salt in bytes
     * @return Base64 encoded salt string
     */
    public static String generateSalt(int length) {
        byte[] salt = new byte[length];
        SECURE_RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    
    /**
     * Hashes a password with the provided salt using SHA-256
     * @param password The password to hash
     * @param salt The salt to use for hashing
     * @return Base64 encoded hashed password
     * @throws NoSuchAlgorithmException If the hashing algorithm is not available
     */
    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        
        // Decode salt from Base64
        byte[] decodedSalt = Base64.getDecoder().decode(salt);
        
        // Add salt to digest
        md.update(decodedSalt);
        
        // Hash the password
        byte[] hashedPassword = md.digest(password.getBytes());
        
        // Encode as Base64 for storage
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}