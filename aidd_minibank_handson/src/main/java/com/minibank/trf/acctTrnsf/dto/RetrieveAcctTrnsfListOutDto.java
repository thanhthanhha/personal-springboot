package com.minibank.trf.acctTrnsf.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveAcctTrnsfListOutDto {
	private String trnsfDt;
	private String trnsfTime;
	private String dpstAcno;
	private String recvrNm;
	private BigDecimal trnsfAmt;
	private String dpstSumry;
	private String wthdraccoSumry;
	
	@Builder
	public RetrieveAcctTrnsfListOutDto(String trnsfDt, String trnsfTime, String dpstAcno, String recvrNm, BigDecimal trnsfAmt, String dpstSumry, String wthdraccoSumry) {
		super();
		this.trnsfDt = trnsfDt;
		this.trnsfTime = trnsfTime;
		this.dpstAcno = dpstAcno;
		this.recvrNm = recvrNm;
		this.trnsfAmt = trnsfAmt;
		this.dpstSumry = dpstSumry;
		this.wthdraccoSumry = wthdraccoSumry;
	}
}
