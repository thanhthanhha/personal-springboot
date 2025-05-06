package com.sample.NextCode.Basic.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveUserOutDto {
	private String userNo;
	private String userNm;
	private String userEnnm;
	private String cpno;
	private String emaddr;
	
	@Builder
	public RetrieveUserOutDto(String userNo, String userNm, String userEnnm, String cpno, String emaddr) {
		super();
		this.userNo = userNo;
		this.userNm = userNm;
		this.userEnnm = userEnnm;
		this.cpno = cpno;
		this.emaddr = emaddr;
	}
}
