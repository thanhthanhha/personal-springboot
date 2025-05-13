package com.realchat.store.usr.repository;

import org.apache.ibatis.annotations.Mapper;
import com.realchat.store.usr.dto.User;
import com.realchat.store.usr.dto.UserAuth;
import com.realchat.store.usr.dto.UserQuery;
import com.realchat.store.usr.dto.UserFriend;
import com.realchat.store.usr.dto.UserFriendRequest;

import java.util.List;

@Mapper
public interface UserRepository {
	public int registerUser(User User) throws Exception;
	public User updateUser(User User) throws Exception;
	public int deleteUser(User User) throws Exception;
	public UserFriend addFriend(String user_id, String friend_id) throws Exception;
	public int deleteFriend(String user_id, String friend_id) throws Exception;
	public UserFriendRequest addFriendRequest(String user_id, String friend_id) throws Exception;
	public int deleteFriendRequest(String user_id, String friend_id) throws Exception;
	public List<User> listUser(UserQuery UserQuery) throws Exception;
	public boolean authUser(UserAuth UserAuth) throws Exception;
}


