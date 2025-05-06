package com.sample.NextCode.Basic.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minibank.exception.BusinessException;
import com.sample.NextCode.Basic.dto.RegisterUserInDto;
import com.sample.NextCode.Basic.dto.RetrieveUserListInDto;
import com.sample.NextCode.Basic.dto.RetrieveUserListOutDto;
import com.sample.NextCode.Basic.dto.RetrieveUserOutDto;
import com.sample.NextCode.Basic.dto.UpdateUserInDto;
import com.sample.NextCode.Basic.repository.UserRepository;

@Service("userService")
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	// Register User
	@Transactional(rollbackFor = Exception.class)
	public int registerUser(RegisterUserInDto registerUserInDto) throws Exception {
		// Required field check 
		if (registerUserInDto.getUserNo() == null || "".equals(registerUserInDto.getUserNo()))
			throw new BusinessException("Please enter the user number.");
		if (registerUserInDto.getUserNm() == null || "".equals(registerUserInDto.getUserNm()))
			throw new BusinessException("Please enter the user's name.");

		return userRepository.registerUser(registerUserInDto);
	}
	
	// Update User
	@Transactional(rollbackFor = Exception.class)
	public int updateUser(UpdateUserInDto updateUserInDto) throws Exception {
		// Required field check 
		if(updateUserInDto.getUserNo() == null || "".equals(updateUserInDto.getUserNo())) 
			throw new BusinessException("Please enter the user number.");
		if(updateUserInDto.getUserNm() == null || "".equals(updateUserInDto.getUserNm())) 
			throw new BusinessException("Please enter the user's name.");
		
    	return userRepository.updateUser(updateUserInDto);
    }
    
	// Delete User
	@Transactional(rollbackFor = Exception.class)
	public int deleteUser(String userNo) throws Exception {
		// Required field check 
		if(userNo == null || userNo.trim().isEmpty() ) 
			throw new BusinessException("Please enter the user number.");
		
    	return userRepository.deleteUser(userNo);
    }
    
	// Retrieve User
	@Transactional(readOnly = true)
	public RetrieveUserOutDto retrieveUser(String userNo) throws Exception {
		// Required field check 
		if(userNo == null || userNo.trim().isEmpty() ) 
			throw new BusinessException("Please enter the user number.");
		
		RetrieveUserOutDto retrieveUserOutDto = userRepository.retrieveUser(userNo);
		
		// User not found error handling
		if(retrieveUserOutDto == null) 
			throw new BusinessException("Failed to retrieve user data.");
		
    	return retrieveUserOutDto;		
    }
    
    // Retrieve User List
	@Transactional(readOnly = true)
    public List<RetrieveUserListOutDto> retrieveUserList(RetrieveUserListInDto retrieveUserListInDto) throws Exception {
		// Required field check 
		if(retrieveUserListInDto.getUserNm() == null || "".equals(retrieveUserListInDto.getUserNm()) ) 
			throw new BusinessException("Please enter the user's name.");
    	
    	return userRepository.retrieveUserList(retrieveUserListInDto);

    }
}
