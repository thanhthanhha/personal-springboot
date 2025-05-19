package com.email.user.repository;

import com.email.user.dto.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserRepository {
	//User
	public int registerUser(User user) throws Exception;
	public User getUserById(@Param("user_id") Long user_id) throws Exception;
	public boolean existsByEmailOrName(@Param("username") String username, @Param("email") String email) throws Exception;
	public List<User> getUsers() throws Exception;
}