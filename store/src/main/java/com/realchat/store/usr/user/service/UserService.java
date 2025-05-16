package com.realchat.store.usr.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.NoSuchAlgorithmException;


import com.realchat.store.usr.user.repository.UserRepository;
import com.realchat.store.usr.user.dto.User;
import com.realchat.store.usr.user.dto.UserAuth;
import com.realchat.store.usr.user.dto.UserQuery;
import com.realchat.store.exception.BusinessException;

import com.realchat.store.utils.StringUtils;
import com.realchat.store.utils.SecurityUtils;

@Service("userService")
public class UserService {
	@Autowired
	private UserRepository UserRepository;
	
	
	//User
	
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
	public User updateUser(User user, String user_id) throws Exception {
	    // Validate mandatory fields
	    if (user.getUser_id() == null || user.getUser_id().trim().isEmpty()) {
	        throw new BusinessException("User ID is required");
	    }
	    
	    // Call repository method to update user
	    return UserRepository.updateUser(user, user_id);
	}
	
	@Transactional(readOnly = true)
	public List<User> listUser(UserQuery userQuery) throws Exception {
	    // Validate mandatory fields
	    if ((userQuery.getId() == null || userQuery.getId().isEmpty()) && 
            (userQuery.getName() == null || userQuery.getName().isEmpty()) &&
            (userQuery.getEmail() == null || userQuery.getEmail().isEmpty()) &&
            (userQuery.getUser_id() == null || userQuery.getUser_id().isEmpty()) &&
            (userQuery.getImage() == null || userQuery.getImage().isEmpty())) {
            throw new BusinessException("At least one search criteria is required");
        }
	    
	    // Call repository method to get user
	    return UserRepository.listUser(userQuery);
	}
	
	@Transactional(readOnly = true)
	public User getUser(String user_id, String email) throws Exception {
	    // Validate mandatory fields
	    if ((user_id == null || user_id.isEmpty()) ||
            (email == null || email.isEmpty())) {
            throw new BusinessException("At least one search criteria is required");
        }
	    
	 // Create a UserQuery with only user_id and email in one line
	    UserQuery query = UserQuery.builder()
	    		.user_id(List.of(user_id))
	    		.email(List.of(email))
	    		.build();
	    
	    List<User> result = UserRepository.listUser(query);
	    
	    if (result == null || result.isEmpty()) {
	    	throw new BusinessException("User don't exist");
	    }
	    
	    if (!(result.get(0) instanceof User)) {
	        throw new BusinessException("Invalid data type in result list");
	    }

	    // Call repository method to get user
	    return result.get(0);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int deleteUser(String user_id) throws Exception {
	    // Validate mandatory fields
	    if (user_id == null || user_id.trim().isEmpty()) {
	        throw new BusinessException("User ID is required");
	    }
	    
	    // Call repository method to delete user
	    return UserRepository.deleteUser(user_id);
	}	  
    
    //Auth
    /**
     * Authenticate a user using email/user_id and password
     * @param userAuth The authentication details containing email/user_id and password
     * @return true if authentication is successful, false otherwise
     * @throws Exception If there's an error during the authentication process
     */
    @Transactional(readOnly = true)
    public boolean authUser(UserAuth userAuth) throws Exception {
        // Validate mandatory fields
        if ((userAuth.getEmail() == null || userAuth.getEmail().trim().isEmpty()) && 
            (userAuth.getUser_id() == null || userAuth.getUser_id().trim().isEmpty())) {
            throw new BusinessException("Either email or user_id is required for authentication");
        }
        
        if (userAuth.getPassword() == null || userAuth.getPassword().trim().isEmpty()) {
            throw new BusinessException("Password is required for authentication");
        }
        
        User user = this.getUser(userAuth.getEmail(), userAuth.getUser_id());
        
        if (user == null) {
        	throw new BusinessException("User don't exist");
        }
        
        try {
            // Hash the provided password with the stored salt
            String hashedPassword = SecurityUtils.hashPassword(userAuth.getPassword(), user.getPassword_salt());
            
            // Set the hashed password in the userAuth object
            userAuth.setPassword(hashedPassword);
            
            // Call repository method to authenticate user
            return UserRepository.authUser(userAuth);
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException("Error during password hashing: " + e.getMessage());
        }
    }
}
