package com.minibank.trf.userCntr.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VerifyTrnsfLimitAmtInDto {
	private String custNo;
	private BigDecimal trnsfAmt;
	
	@Builder
	public VerifyTrnsfLimitAmtInDto(String custNo, BigDecimal trnsfAmt) {
		super();
		this.custNo = custNo;
		this.trnsfAmt = trnsfAmt;
	}
}
