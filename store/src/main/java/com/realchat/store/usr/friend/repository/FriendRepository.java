package com.realchat.store.usr.friend.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.realchat.store.usr.friend.dto.UserFriend;

import java.util.List;

@Mapper
public interface FriendRepository {
	
	//Friend 
	public int addFriend(@Param("user_id") String user_id, @Param("friend_id") String friend_id) throws Exception;
	public UserFriend getFriendRelationship(@Param("sender_id") String sender_id, @Param("receiver_id") String receiver_id) throws Exception;
	public int deleteFriend(@Param("user_id") String user_id, @Param("friend_id") String friend_id) throws Exception;
	public UserFriend getFriend(@Param("user_id") String user_id, @Param("friend_id") String friend_id) throws Exception;
	public List<UserFriend> listFriends(@Param("user_id") String user_id) throws Exception;
	
	
}


