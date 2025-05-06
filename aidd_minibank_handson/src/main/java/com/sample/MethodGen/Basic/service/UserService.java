//package com.minibank.cmm.user.service;
package com.sample.MethodGen.Basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minibank.exception.BusinessException;
import com.sample.MethodGen.Basic.dto.RegisterUserInDto;
import com.sample.MethodGen.Basic.dto.RetrieveUserListInDto;
import com.sample.MethodGen.Basic.dto.RetrieveUserListOutDto;
import com.sample.MethodGen.Basic.dto.RetrieveUserOutDto;
import com.sample.MethodGen.Basic.dto.UpdateUserInDto;
import com.sample.MethodGen.Basic.repository.UserRepository;

@Service("userService")
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
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
		// Required field check
		if (userNo == null || userNo.trim().isEmpty())
			throw new BusinessException("Please enter the user number.");
		
		RetrieveUserOutDto retrieveUserOutDto = userRepository.retrieveUser(userNo);
		
		return retrieveUserOutDto;
    }
    
    // Retrieve User List
	@Transactional(readOnly = true)
    public List<RetrieveUserListOutDto> retrieveUserList(RetrieveUserListInDto retrieveUserListInDto) throws Exception {   	
    	List<RetrieveUserListOutDto> result = userRepository.retrieveUserList(retrieveUserListInDto);
    	
    	return result;
    }
}
