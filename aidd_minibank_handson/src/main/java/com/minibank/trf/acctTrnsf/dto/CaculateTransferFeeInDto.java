package com.minibank.trf.acctTrnsf.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CaculateTransferFeeInDto {
	private String custNo;
	private String wthdrAcno;
	private BigDecimal trnsfAmt;

	@Builder
	public CaculateTransferFeeInDto(String custNo, String wthdrAcno, BigDecimal trnsfAmt) {
		super();
		this.custNo = custNo;
		this.wthdrAcno = wthdrAcno;
		this.trnsfAmt = trnsfAmt;
	}

}
