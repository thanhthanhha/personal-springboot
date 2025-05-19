package com.email.user.service;

import com.email.user.dto.User;
import com.email.user.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

import com.email.exception.BusinessException;
import com.email.exception.SystemException;
import com.email.utils.StringUtils;
import com.email.utils.LoggerUtils;

import org.springframework.http.HttpStatus;


@Service("userService")
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	private static final LoggerUtils logger = LoggerUtils.getLogger(UserService.class);
	
	@Transactional(rollbackFor = Exception.class)
	public User registerUser(User user) throws Exception {
		try {
            if (user == null || StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getUsername())) {
                throw new BusinessException("User data is invalid");
            }
            if (!StringUtils.validateEmail(user.getEmail())) {
            	logger.error("Email is not in correct format" + user.getEmail());
            	throw new BusinessException("User email is invalid");
            }
            if (userRepository.existsByEmailOrName(user.getUsername(), user.getEmail())) {
            	throw new SystemException("duplicate username/email");
            }
            logger.info("Creating user");
            user.setInboxLimit(user.getInboxLimit() != null ? user.getInboxLimit() : 100); 
            user.setCreatedAt(LocalDateTime.now());
            
            userRepository.registerUser(user);
            
            return user;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("Error during user registration", e);
        }
	}
	
    @Transactional(readOnly = true)
    public User getUserById(long user_id) throws BusinessException {
        User user;
		try {
			user = userRepository.getUserById(user_id);
	        if (user == null) {
	            throw new BusinessException("User not found with id: " + user_id, HttpStatus.NOT_FOUND);
	        }
	        return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e.getMessage(), HttpStatus.NOT_FOUND);
		}
    }
    
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        try {
			return userRepository.getUsers();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
    }
}
