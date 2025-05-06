package com.minibank.trf.userCntr.repository;

import org.apache.ibatis.annotations.Mapper;

import com.minibank.trf.userCntr.dto.RegisterUserCntrInDto;
import com.minibank.trf.userCntr.dto.RetrieveUserCntrOutDto;
import com.minibank.trf.userCntr.dto.UpdateUserCntrInDto;

@Mapper
public interface UserCntrMRepository {
	public RetrieveUserCntrOutDto retrieveUserCntr(String userId) throws Exception;
	public RetrieveUserCntrOutDto retrieveUserCntrByCustNo(String custNo) throws Exception;
	public int registerUserCntr(RegisterUserCntrInDto registerUserCntrInDto) throws Exception;
	public int updateUserCntr(UpdateUserCntrInDto updateUserCntrInDto) throws Exception;
	public int deleteUserCntr(String userId) throws Exception;
}
