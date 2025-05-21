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
import com.realchat.store.utils.LoggerUtils;
import com.realchat.store.exception.BusinessException;
import com.realchat.store.exception.SystemException;
import org.springframework.http.HttpStatus;

import com.realchat.store.utils.StringUtils;
import com.realchat.store.utils.SecurityUtils;
import java.util.UUID;

@Service("userService")
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	private static final LoggerUtils logger = LoggerUtils.getLogger(UserService.class);
	
	
	//User
	
	@Transactional(rollbackFor = Exception.class)
	public User registerUser(User user) throws Exception {
	    // Validate mandatory fields
	    if (user.getName() == null || user.getName().trim().isEmpty()) {
	        throw new BusinessException("User name is required", HttpStatus.BAD_REQUEST);
	    }
	    
	    if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
	        throw new BusinessException("Email is required", HttpStatus.BAD_REQUEST);
	    }
	    
	    // Password is optional in the schema but typically required for registration
	    if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
	        throw new BusinessException("Password is required for registration", HttpStatus.BAD_REQUEST);
	    }
	    
	    if (userRepository.existsByUserEmail(user.getEmail())) {
	    	throw new BusinessException("Email already exist", HttpStatus.CONFLICT);
	    }
	    
	    // Generate 6-character user_id
	    String generatedUserId = StringUtils.generateRandomAlphanumeric(6);
	    user.setUser_id(generatedUserId);
	    user.setId(UUID.randomUUID());
	    
        try {
            // Generate a random salt (16 bytes)
            String salt = SecurityUtils.generateSalt(16);
            user.setPasswordSalt(salt);
            
            // Hash the password with the salt
            String hashedPassword = SecurityUtils.hashPassword(user.getPassword(), salt);
            user.setPassword(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException("Error during password hashing: " + e.getMessage());
        }
	    
        logger.info(String.format("Saving user with ID: %s", user.getId()));
        userRepository.registerUser(user);
       
	    // Call repository method to register user
	    return user;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public User updateUser(User user, String user_id) throws Exception {
	    // Validate mandatory fields
	    if (user.getUser_id() == null || user.getUser_id().trim().isEmpty()) {
	        throw new BusinessException("User ID is required");
	    }
	    
	    User existUser = this.getUser(user_id, null);
	    
	    if (existUser == null) {
	    	throw new BusinessException("User don't exist", HttpStatus.NOT_FOUND);
	    }
	    
	    if (user.getPassword() != null) {
		    String password_salt = existUser.getPasswordSalt();
		    logger.info(String.format("User %s Salt is %s", existUser.getName(), existUser.getPasswordSalt()));
	        try {
	            // Hash the password with the salt
	            String hashedPassword = SecurityUtils.hashPassword(user.getPassword(), password_salt);
	            user.setPassword(hashedPassword);
	        } catch (NoSuchAlgorithmException e) {
	            throw new SystemException("Error during password hashing: " + e.getMessage());
	        }
	    }
	    
	    userRepository.updateUser(user);
	    
	    // Call repository method to update user
	    return user;
	}
	
	@Transactional(readOnly = true)
	public List<User> listUser(UserQuery userQuery) throws Exception {
	    // Validate mandatory fields
	    if (userQuery == null) {
	        userQuery = new UserQuery();
	    }
	    
	    // Call repository method to get user
	    return userRepository.listUser(userQuery);
	}
	
	@Transactional(readOnly = true)
	public User getUser(String user_id, String email) throws Exception {
	    // Validate mandatory fields
	    if ((user_id == null || user_id.isEmpty()) &&
	        (email == null || email.isEmpty())) {
	        throw new BusinessException(String.format("At least one search criteria is required: user id %s email %s", user_id, email));
	    }

	    logger.info(String.format("Querying user %s %s", user_id, email));
	    
	    User user = new User();
	    
	    if  (email != null && !email.isEmpty()) {
	    	user.setEmail(email);
	    } else if (user_id != null && !user_id.isEmpty()) {
	    	user.setUser_id(user_id);
	    }
	    
	    logger.info(String.format("Querying user after object creation %s %s", user.getEmail(), user.getUser_id()));
	    
	    // Return the first user found
	    return userRepository.getUserByKey(user);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int deleteUser(String user_id) throws Exception {
	    // Validate mandatory fields
	    if (user_id == null || user_id.trim().isEmpty()) {
	        throw new BusinessException("User ID is required");
	    }
	    
	    if (!userRepository.existsByUserId(user_id)) {
	    	throw new BusinessException("User ID is required", HttpStatus.NOT_FOUND);
	    }
	    
	    // Call repository method to delete user
	    return userRepository.deleteUser(user_id);
	}	 
	
	@Transactional(readOnly = true)
	public boolean existsByUserEmail(String email) throws Exception {
	    // Validate mandatory fields
	    if (email == null || email.trim().isEmpty()) {
	        throw new BusinessException("User Email is required");
	    }
	    
	    return userRepository.existsByUserEmail(email);
	}
	
	@Transactional(readOnly = true)
	public boolean existsByUserId(String user_id) throws Exception {
	    // Validate mandatory fields
	    if (user_id == null || user_id.trim().isEmpty()) {
	        throw new BusinessException("User ID is required");
	    }
	    
	    return userRepository.existsByUserId(user_id);
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
        
        User user = this.getUser(userAuth.getUser_id(), userAuth.getEmail());
        
        if (user == null) {
        	throw new BusinessException(String.format("User don't exist in auth %s and %s", userAuth.getEmail(), userAuth.getUser_id()), HttpStatus.NOT_FOUND);
        }
        
        try {
            // Hash the provided password with the stored salt
            String hashedPassword = SecurityUtils.hashPassword(userAuth.getPassword(), user.getPasswordSalt());
            
            logger.info(String.format("Compare password %s and %s", hashedPassword, user.getPassword()));
            // Set the hashed password in the userAuth object
            userAuth.setPassword(hashedPassword);
            
            // Call repository method to authenticate user
            return userRepository.authUser(userAuth);
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException("Error during password hashing: " + e.getMessage());
        }
    }
}
