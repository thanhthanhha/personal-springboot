package com.realchat.store.usr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realchat.store.usr.dto.User;
import com.realchat.store.usr.dto.UserAuth;
import com.realchat.store.usr.dto.UserQuery;
import com.realchat.store.usr.repository.UserRepository;
import com.realchat.store.exception.BusinessException;

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
	    
	    if (user.getUser_id() == null || user.getUser_id().trim().isEmpty()) {
	        throw new BusinessException("User ID is required");
	    }
	    
	    // Password is optional in the schema but typically required for registration
	    if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
	        throw new BusinessException("Password is required for registration");
	    }
	    
	    // Call repository method to register user
	    return UserRepository.registerUser(user);
	}

}



