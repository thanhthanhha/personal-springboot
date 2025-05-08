package com.realchat.store.usr.repository;

import org.apache.ibatis.annotations.Mapper;
import com.realchat.store.usr.dto.User;
import java.util.List;

public interface UserRepository {
	public int registerUser(User User) throws Exception;
	public User updateUser(User User) throws Exception;
	public int deleteUser(User User) throws Exception;
	public List<User> listUser(UserQuery UserQuery) throws Exception;
	public boolean authUser(UserAuth UserAuth) throws Exception;
}


