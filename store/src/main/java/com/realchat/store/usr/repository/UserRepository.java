package com.realchat.store.usr.repository;

import org.apache.ibatis.annotations.Mapper;
import com.realchat.store.usr.dto.User;
import com.realchat.store.usr.dto.UserAuth;
import com.realchat.store.usr.dto.UserQuery;

import java.util.List;

@Mapper
public interface UserRepository {
	public int registerUser(User User) throws Exception;
	public User updateUser(User User) throws Exception;
	public int deleteUser(User User) throws Exception;
	public List<User> listUser(UserQuery UserQuery) throws Exception;
	public boolean authUser(UserAuth UserAuth) throws Exception;
}


