//package com.minibank.cmm.user.repository;
package com.sample.MethodGen.Basic.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sample.MethodGen.Basic.dto.RegisterUserInDto;
import com.sample.MethodGen.Basic.dto.RetrieveUserListInDto;
import com.sample.MethodGen.Basic.dto.RetrieveUserListOutDto;
import com.sample.MethodGen.Basic.dto.RetrieveUserOutDto;
import com.sample.MethodGen.Basic.dto.UpdateUserInDto;

@Mapper
public interface UserRepository {
	public int registerUser(RegisterUserInDto registerUserInDto) throws Exception;
    public int updateUser(UpdateUserInDto updateUserInDto) throws Exception;
    public int deleteUser(String userNo) throws Exception;
    public RetrieveUserOutDto retrieveUser(String userNo) throws Exception;
    public List<RetrieveUserListOutDto> retrieveUserList(RetrieveUserListInDto retrieveUserListInDto) throws Exception;
}
