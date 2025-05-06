package com.minibank.trf.userCntr.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUserCntrInDto {
	private String userId;
	private String custNo;
	private String userPswd;
	private BigDecimal d1TrnsfLimtAmt;
	private BigDecimal ti1TrnsfLimtAmt;
	
	@Builder
	public RegisterUserCntrInDto(String userId, String custNo, String userPswd, BigDecimal d1TrnsfLimtAmt, BigDecimal ti1TrnsfLimtAmt) {
		super();
		this.userId = userId;
		this.custNo = custNo;
		this.userPswd = userPswd;
		this.d1TrnsfLimtAmt = d1TrnsfLimtAmt;
		this.ti1TrnsfLimtAmt = ti1TrnsfLimtAmt;
	}
}
