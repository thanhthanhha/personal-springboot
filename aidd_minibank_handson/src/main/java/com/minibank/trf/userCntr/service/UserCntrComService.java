package com.minibank.trf.userCntr.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minibank.trf.userCntr.dto.RetrieveUserCntrOutDto;
import com.minibank.trf.userCntr.dto.VerifyTrnsfLimitAmtInDto;
import com.minibank.trf.userCntr.repository.UserCntrMRepository;

@Service("userCntrComService")
public class UserCntrComService {
	@Autowired
	private UserCntrMRepository userCntrMRepository;
	
	// Check If User Contract Exists
	public boolean existUserCntr(String custNo) throws Exception {
		RetrieveUserCntrOutDto retrieveUserCntrOutDto = userCntrMRepository.retrieveUserCntrByCustNo(custNo);
		if(retrieveUserCntrOutDto == null) return false;
		
		return true;
	}
	
	// Validate Transfer Amount Limit
	public boolean verifyTrnsfLimitAmt(VerifyTrnsfLimitAmtInDto verifyTrnsfLimitAmtInDto) throws Exception {
		BigDecimal trnsfAmt = verifyTrnsfLimitAmtInDto.getTrnsfAmt();
		BigDecimal totTrnsfAmt = trnsfAmt.add(this.getTotTrnsfAmt(verifyTrnsfLimitAmtInDto.getCustNo()));
		
		RetrieveUserCntrOutDto retrieveUserCntrOutDto = userCntrMRepository.retrieveUserCntrByCustNo(verifyTrnsfLimitAmtInDto.getCustNo());
		
		BigDecimal ti1TrnsfLimtAmt = retrieveUserCntrOutDto.getTi1TrnsfLimtAmt();
		BigDecimal d1TrnsfLimtAmt = retrieveUserCntrOutDto.getD1TrnsfLimtAmt();
		
		if(trnsfAmt.compareTo(ti1TrnsfLimtAmt) <= 0 || totTrnsfAmt.compareTo(d1TrnsfLimtAmt) <= 0)
			return false;
		
		return true;
	}
	
	//Get Total Transfers for the Day
	private BigDecimal getTotTrnsfAmt(String custNo) throws Exception {
		BigDecimal totTrnsfAmt;
		
		totTrnsfAmt = new BigDecimal(0);
		return totTrnsfAmt;
	}
}
