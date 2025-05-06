package com.minibank.dep.depTx.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepositAcctInDto {
	private String acno;
	private BigDecimal txAmt;
	
	@Builder
	public DepositAcctInDto(String acno, BigDecimal txAmt) {
		super();
		this.acno = acno;
		this.txAmt = txAmt;
	}
}
