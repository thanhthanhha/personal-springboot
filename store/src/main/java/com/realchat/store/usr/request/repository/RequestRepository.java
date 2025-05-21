package com.realchat.store.usr.request.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.realchat.store.usr.request.dto.UserFriendRequest;

import java.util.List;

@Mapper
public interface RequestRepository {
	
	//Friend Request
	public int addFriendRequest(UserFriendRequest userFriendRequest) throws Exception;
	public UserFriendRequest getFriendRequest(@Param("sender_id") String sender_id, @Param("receiver_id") String receiver_id) throws Exception;
	public int updateFriendRequest(UserFriendRequest UserFriendRequest) throws Exception;
	public List<UserFriendRequest> listPendingFriendRequests(@Param("user_id") String user_id) throws Exception;
	public List<UserFriendRequest> listSentFriendRequests(@Param("user_id") String user_id) throws Exception;
	public int deleteFriendRequest(@Param("sender_id") String sender_id,@Param("receiver_id") String receiver_id) throws Exception;
	
}


