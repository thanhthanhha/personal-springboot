package com.minibank.dep.depTx.repository;

import org.apache.ibatis.annotations.Mapper;

import com.minibank.dep.depTx.dto.RegisterDepTxInDto;

@Mapper
public interface DepTxLRepository {
	public int registerDepTx(RegisterDepTxInDto registerDepTxInDto) throws Exception;
}
