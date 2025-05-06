package com.sample.QueryToCode.Basic.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.QueryToCode.Basic.dto.RegisterUserInDto;
import com.sample.QueryToCode.Basic.dto.RetrieveUserListInDto;
import com.sample.QueryToCode.Basic.dto.RetrieveUserListOutDto;
import com.sample.QueryToCode.Basic.dto.RetrieveUserOutDto;
import com.sample.QueryToCode.Basic.dto.UpdateUserInDto;
import com.sample.QueryToCode.Basic.repository.UserRepository;

@Service("userService")
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	// Register User
	@Transactional(rollbackFor = Exception.class)
	public int registerUser(RegisterUserInDto registerUserInDto) throws Exception {		
		return userRepository.registerUser(registerUserInDto);
	}
	
	// Update User
	@Transactional(rollbackFor = Exception.class)
	public int updateUser(UpdateUserInDto updateUserInDto) throws Exception {	
    	return userRepository.updateUser(updateUserInDto);
    }
    
	// Delete User
	@Transactional(rollbackFor = Exception.class)
	public int deleteUser(String userNo) throws Exception {
    	return userRepository.deleteUser(userNo);
    }
    
	// Retrieve User
	@Transactional(readOnly = true)
	public RetrieveUserOutDto retrieveUser(String userNo) throws Exception {
		return  userRepository.retrieveUser(userNo);
    }
    
    // Retrieve User List
	@Transactional(readOnly = true)
    public List<RetrieveUserListOutDto> retrieveUserList(RetrieveUserListInDto retrieveUserListInDto) throws Exception {   	
    	return userRepository.retrieveUserList(retrieveUserListInDto);

    }
}
