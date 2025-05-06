package com.minibank.dep.depTx.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterDepTxInDto {
	private String txSeqno;
	private String acno;
	private String txDt;
	private String txTime;
	private String txCd;
	private BigDecimal txAmt;
	
	@Builder
	public RegisterDepTxInDto(String txSeqno, String acno, String txDt, String txTime, String txCd, BigDecimal txAmt) {
		super();
		this.txSeqno = txSeqno;
		this.acno = acno;
		this.txDt = txDt;
		this.txTime = txTime;
		this.txCd = txCd;
		this.txAmt = txAmt;
	}
}
