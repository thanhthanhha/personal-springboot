package com.minibank.trf.userCntr.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minibank.exception.BusinessException;
import com.minibank.trf.userCntr.dto.RegisterUserCntrInDto;
import com.minibank.trf.userCntr.dto.RetrieveUserCntrOutDto;
import com.minibank.trf.userCntr.dto.UpdateUserCntrInDto;
import com.minibank.trf.userCntr.repository.UserCntrMRepository;

@Service("userCntrService")
public class UserCntrService {
	@Autowired
	private UserCntrMRepository userCntrMRepository;
	
	@Autowired
	private UserCntrComService userCntrComService;
	
	public RetrieveUserCntrOutDto retrieveUserCntr(String userId) throws Exception {
		if(!existUserId(userId))
			throw new BusinessException("User contract information does not exist.");
		
		return userCntrMRepository.retrieveUserCntr(userId);
	}
	
	public RetrieveUserCntrOutDto retrieveUserCntrByCustNo(String custNo) throws Exception {
		return userCntrMRepository.retrieveUserCntrByCustNo(custNo);
	}
	
	// Register User Contract
	@Transactional(rollbackFor = Exception.class)
	public int registerUserCntr(RegisterUserCntrInDto registerUserCntrInDto) throws Exception {
		String custNo = registerUserCntrInDto.getCustNo();
		String userId = registerUserCntrInDto.getUserId();
		String userPswd = registerUserCntrInDto.getUserPswd();
		BigDecimal d1TrnsfLimtAmt = registerUserCntrInDto.getD1TrnsfLimtAmt();
		BigDecimal ti1TrnsfLimtAmt = registerUserCntrInDto.getTi1TrnsfLimtAmt();
		
		// Input Validation
		if(custNo == null || "".equals(custNo))
			throw new BusinessException("Please enter the customer number.");
		
		if(userId == null || "".equals(userId))
			throw new BusinessException("Please enter the user ID.");
		
		if(userPswd == null || "".equals(userPswd))
			throw new BusinessException("Please enter the user password.");
		
		if(d1TrnsfLimtAmt == null)
			throw new BusinessException("Please enter the daily transfer limit amount.");
		
		if(ti1TrnsfLimtAmt == null)
			throw new BusinessException("Please enter the one-time transfer limit amount.");
		
		// Check if daily transfer limit is greater than 0
		if(d1TrnsfLimtAmt.compareTo(BigDecimal.ZERO) <= 0)
			throw new BusinessException("The daily transfer limit amount must be greater than 0.");
		
		// Check if one-time transfer limit is greater than 0
		if(ti1TrnsfLimtAmt.compareTo(BigDecimal.ZERO) <= 0)
			throw new BusinessException("The one-time transfer limit amount must be greater than 0.");
		
		// Check if user contract exists
		if(existUserId(userId))
			throw new BusinessException("A user contract with the same user ID already exists.");
		
		// Check if user contract exists
		if(userCntrComService.existUserCntr(custNo))
			throw new BusinessException("User contract information for this customer already exists.");
		
		return userCntrMRepository.registerUserCntr(registerUserCntrInDto);
	}
	
	// Update User Contract
	@Transactional(rollbackFor = Exception.class)
	public int updateUserCntr(UpdateUserCntrInDto updateUserCntrInDto) throws Exception {
		String userId = updateUserCntrInDto.getUserId();
		BigDecimal d1TrnsfLimtAmt = updateUserCntrInDto.getD1TrnsfLimtAmt();
		BigDecimal ti1TrnsfLimtAmt = updateUserCntrInDto.getTi1TrnsfLimtAmt();
		
		// Input Validation
		if(userId == null || "".equals(userId))
			throw new BusinessException("Please enter the user ID.");
		
		// Check if daily transfer limit is greater than 0
		if(d1TrnsfLimtAmt.compareTo(BigDecimal.ZERO) <= 0)
			throw new BusinessException("The daily transfer limit amount must be greater than 0.");
		
		// Check if one-time transfer limit is greater than 0
		if(ti1TrnsfLimtAmt.compareTo(BigDecimal.ZERO) <= 0)
			throw new BusinessException("The one-time transfer limit amount must be greater than 0.");
		
		return userCntrMRepository.updateUserCntr(updateUserCntrInDto);
	}
	
	// Delete User Contract
	@Transactional(rollbackFor = Exception.class)
	public int deleteUserCntr(String userId) throws Exception {
		// Input Validation
		if(userId == null || "".equals(userId))
			throw new BusinessException("Please enter the user ID.");
		
		return userCntrMRepository.deleteUserCntr(userId);
	}
	
	// Check if user ID exists
	public boolean existUserId(String userId) throws Exception {
		
		RetrieveUserCntrOutDto retrieveUserCntrOutDto = userCntrMRepository.retrieveUserCntr(userId);
		if(retrieveUserCntrOutDto == null) return false;
		
		return true;
	}
}
