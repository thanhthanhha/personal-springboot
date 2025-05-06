package com.minibank.trf.userCntr.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserCntrInDto {
	private String userId;
	private BigDecimal d1TrnsfLimtAmt;
	private BigDecimal ti1TrnsfLimtAmt;
	
	@Builder
	public UpdateUserCntrInDto(String userId, BigDecimal d1TrnsfLimtAmt, BigDecimal ti1TrnsfLimtAmt) {
		super();
		this.userId = userId;
		this.d1TrnsfLimtAmt = d1TrnsfLimtAmt;
		this.ti1TrnsfLimtAmt = ti1TrnsfLimtAmt;
	}
}
