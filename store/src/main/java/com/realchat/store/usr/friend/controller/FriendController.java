package com.realchat.store.usr.friend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private FriendService friendService;
    
    //Friend
    
    @Operation(summary = "Find Friendship", description = "Find a specific friendship between two users")
    @RequestMapping(method = RequestMethod.GET, path = "/rest/v1/friend/{user_id}/{friend_id}")
    public ResponseEntity<UserFriend> getFriend(@PathVariable String user_id, @PathVariable String friend_id) throws Exception {
        UserFriend friendship = friendService.getFriend(user_id, friend_id);
        return new ResponseEntity<>(friendship, HttpStatus.OK);
    }
    
    @Operation(summary = "List User Friends", description = "List all friends for a user")
    @RequestMapping(method = RequestMethod.GET, path = "/rest/v1/friend/{user_id}")
    public ResponseEntity<List<UserFriend>> listFriends(@PathVariable String user_id) throws Exception {
        List<UserFriend> friends = friendService.listFriends(user_id);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }
    
    @Operation(summary = "Delete Friend", description = "Delete Friend")
    @RequestMapping(method = RequestMethod.DELETE, path = "/rest/v1/friend/{user_id}/{friend_id}")
    public ResponseEntity<Integer> deleteFriend(@PathVariable String user_id, @PathVariable String friend_id) throws Exception {
        int result = friendService.deleteFriend(user_id, friend_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}