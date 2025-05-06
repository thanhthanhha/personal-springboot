package com.minibank.trf.acctTrnsf.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.minibank.trf.acctTrnsf.dto.RetrieveAcctTrnsfListOutDto;

@Mapper
public interface AcctTrnsfRepository {
	public List<RetrieveAcctTrnsfListOutDto> retrieveAcctTrnsfList(String wthdrAcno) throws Exception;
}
