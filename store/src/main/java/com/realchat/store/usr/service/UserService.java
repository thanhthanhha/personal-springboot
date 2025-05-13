package com.realchat.store.usr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.NoSuchAlgorithmException;

import com.realchat.store.usr.dto.User;
import com.realchat.store.usr.dto.UserAuth;
import com.realchat.store.usr.dto.UserQuery;
import com.realchat.store.usr.dto.UserFriend;
import com.realchat.store.usr.dto.UserFriendRequest;
import com.realchat.store.usr.repository.UserRepository;
import com.realchat.store.exception.BusinessException;

import com.realchat.store.utils.StringUtils;
import com.realchat.store.utils.SecurityUtils;

@Service("userService")
public class UserService {
	@Autowired
	private UserRepository UserRepository;
	
	@Transactional(rollbackFor = Exception.class)
	public int registerUser(User user) throws Exception {
	    // Validate mandatory fields
	    if (user.getName() == null || user.getName().trim().isEmpty()) {
	        throw new BusinessException("User name is required");
	    }
	    
	    if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
	        throw new BusinessException("Email is required");
	    }
	    
	    // Password is optional in the schema but typically required for registration
	    if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
	        throw new BusinessException("Password is required for registration");
	    }
	    
	    // Generate 6-character user_id
	    String generatedUserId = StringUtils.generateRandomAlphanumeric(6);
	    user.setUser_id(generatedUserId);
	    
        try {
            // Generate a random salt (16 bytes)
            String salt = SecurityUtils.generateSalt(16);
            user.setPassword_salt(salt);
            
            // Hash the password with the salt
            String hashedPassword = SecurityUtils.hashPassword(user.getPassword(), salt);
            user.setPassword(hashedPassword);
            user.setPassword_salt(salt);
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException("Error during password hashing: " + e.getMessage());
        }
	    
	    // Call repository method to register user
	    return UserRepository.registerUser(user);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public User updateUser(User user) throws Exception {
	    // Validate mandatory fields
	    if (user.getUser_id() == null || user.getUser_id().trim().isEmpty()) {
	        throw new BusinessException("User ID is required");
	    }
	    
	    // Call repository method to update user
	    return UserRepository.updateUser(user);
	}
	
	
}
