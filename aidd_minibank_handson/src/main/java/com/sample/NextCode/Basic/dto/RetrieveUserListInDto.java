package com.sample.NextCode.Basic.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveUserListInDto {
	private String userNm;
	
	@Builder
	public RetrieveUserListInDto(String userNm) {
		super();
		this.userNm = userNm;
	}
}
