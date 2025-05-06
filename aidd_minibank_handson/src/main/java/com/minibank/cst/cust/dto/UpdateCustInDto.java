package com.minibank.cst.cust.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateCustInDto {
	private String custNo;
	private String custNm;
	private String custEnnm;
	private String cpno;
	private String emaddr;
	
	@Builder
	public UpdateCustInDto(String custNo, String custNm, String custEnnm, String cpno, String emaddr) {
		super();
		this.custNo = custNo;
		this.custNm = custNm;
		this.custEnnm = custEnnm;
		this.cpno = cpno;
		this.emaddr = emaddr;
	}
}
