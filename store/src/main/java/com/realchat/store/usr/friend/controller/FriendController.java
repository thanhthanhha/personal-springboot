package com.realchat.store.usr.friend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.realchat.store.usr.friend.service.FriendService;
import com.realchat.store.usr.friend.dto.UserFriend;
import com.realchat.store.utils.StringUtils;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/user")
public class FriendController {
	@Autowired
	private FriendService FriendService;
	
	//Friend
	
	@Operation(summary = "Find Friendship", description = "Find a specific friendship between two users")
	@RequestMapping(method = RequestMethod.GET, path = "/rest/v1/friend/{user_id}/{friend_id}")
	public UserFriend getFriend(@PathVariable String user_id, @PathVariable String friend_id) throws Exception {
	    return FriendService.getFriend(user_id, friend_id);
	}

	@Operation(summary = "List User Friends", description = "List all friends for a user")
	@RequestMapping(method = RequestMethod.GET, path = "/rest/v1/friend/{user_id}")
	public List<UserFriend> listFriends(@PathVariable String user_id) throws Exception {
	    return FriendService.listFriends(user_id);
	}

	@Operation(summary = "Delete Friend", description = "Delete Friend")
	@RequestMapping(method = RequestMethod.DELETE, path = "/rest/v1/friend/{user_id}/{friend_id}")
	public int deleteFriend(@PathVariable String user_id, @PathVariable String friend_id) throws Exception {
	    return FriendService.deleteFriend(user_id, friend_id);
	}

}




