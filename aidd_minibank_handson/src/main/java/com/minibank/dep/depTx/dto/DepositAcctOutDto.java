package com.minibank.dep.depTx.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepositAcctOutDto {
	private String acno;
	private BigDecimal txAmt;
	private BigDecimal preAccoBal;
	private BigDecimal accoBal;
	
	@Builder
	public DepositAcctOutDto(String acno, BigDecimal txAmt, BigDecimal preAccoBal, BigDecimal accoBal) {
		super();
		this.acno = acno;
		this.txAmt = txAmt;
		this.preAccoBal = preAccoBal;
		this.accoBal = accoBal;
	}
}
