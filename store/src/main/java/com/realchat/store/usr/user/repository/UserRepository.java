package com.realchat.store.usr.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.realchat.store.usr.user.dto.User;
import com.realchat.store.usr.user.dto.UserAuth;
import com.realchat.store.usr.user.dto.UserQuery;

import java.util.List;

@Mapper
public interface UserRepository {
	
	//User
	public int registerUser(User User) throws Exception;
	public int updateUser(User user) throws Exception;
	public int deleteUser(String user_id) throws Exception;
	public List<User> listUser(UserQuery UserQuery) throws Exception;
	public User getUserByKey(User User) throws Exception;
	public boolean existsByUserId(String user_id) throws Exception;
	public boolean existsByUserEmail(String email) throws Exception;
	//Auth
	public boolean authUser(UserAuth UserAuth) throws Exception;
}


