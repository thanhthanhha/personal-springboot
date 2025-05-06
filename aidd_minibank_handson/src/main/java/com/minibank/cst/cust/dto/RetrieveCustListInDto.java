package com.minibank.cst.cust.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveCustListInDto {
	private String custNm;
	
	@Builder
	public RetrieveCustListInDto(String custNm) {
		super();
		this.custNm = custNm;
	}
}
