package com.minibank.cst.cust.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minibank.cst.cust.dto.RegisterCustInDto;
import com.minibank.cst.cust.dto.RetrieveCustListInDto;
import com.minibank.cst.cust.dto.RetrieveCustListOutDto;
import com.minibank.cst.cust.dto.RetrieveCustOutDto;
import com.minibank.cst.cust.dto.UpdateCustInDto;
import com.minibank.cst.cust.repository.CustRepository;
import com.minibank.exception.BusinessException;

@Service("custService")
public class CustService {
	@Autowired
	private CustRepository custRepository;
	
	// Register Customer
	@Transactional(rollbackFor = Exception.class)
	public int registerCust(RegisterCustInDto registerCustInDto) throws Exception {
		
		// Required field check
		if (registerCustInDto.getCustNo() == null || "".equals(registerCustInDto.getCustNo()))
			throw new BusinessException("Please enter the customer number.");
		if (registerCustInDto.getCustNm() == null || "".equals(registerCustInDto.getCustNm()))
			throw new BusinessException("Please enter the customer's name.");

		return custRepository.registerCust(registerCustInDto);
	}
	
	// Update Customer
	@Transactional(rollbackFor = Exception.class)
	public int updateCust(UpdateCustInDto updateCustInDto) throws Exception {
		// Required field check
		if(updateCustInDto.getCustNo() == null || "".equals(updateCustInDto.getCustNo())) 
			throw new BusinessException("Please enter the customer number.");
		if(updateCustInDto.getCustNm() == null || "".equals(updateCustInDto.getCustNm())) 
			throw new BusinessException("Please enter the customer's name.");
		
    	return custRepository.updateCust(updateCustInDto);
    }
    
	// Delete Customer
	@Transactional(rollbackFor = Exception.class)
	public int deleteCust(String custNo) throws Exception {
		// Required field check
		if(custNo == null || custNo.trim().isEmpty() ) 
			throw new BusinessException("Please enter the customer number.");
		
    	return custRepository.deleteCust(custNo);
    }
    
	// Retrieve Customer
	public RetrieveCustOutDto retrieveCust(String custNo) throws Exception {
		// Required field check
		if(custNo == null || custNo.trim().isEmpty()) {
			throw new BusinessException("Please enter the customer number.");
		}
		RetrieveCustOutDto retrieveCustOutDto = custRepository.retrieveCust(custNo);
		    
		 // Null check for retrieveCustOutDto
	    /*
	      if(retrieveCustOutDto == null) {	  
	        throw new BusinessException("Customer not found for the given customer number.");
	    }
	     */
	    
	    if(retrieveCustOutDto.getCustNo() == null || "".equals(retrieveCustOutDto.getCustNo())) {
	    	throw new BusinessException("Failed to retrieve customer data..");
	    }
		
    	return retrieveCustOutDto;		
    }
    
    // Retrieve Customer List
    public List<RetrieveCustListOutDto> retrieveCustList(RetrieveCustListInDto retrieveCustListInDto) throws Exception {
		// Required field check
		if(retrieveCustListInDto.getCustNm() == null || "".equals(retrieveCustListInDto.getCustNm()) ) 
			throw new BusinessException("Please enter the customer's name.");
    	
    	return custRepository.retrieveCustList(retrieveCustListInDto);

    }
}
