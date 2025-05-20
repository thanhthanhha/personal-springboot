package com.realchat.store.usr.request.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.realchat.store.usr.request.service.RequestService;
import com.realchat.store.usr.request.dto.UserFriendRequest;
import com.realchat.store.utils.StringUtils;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/user")
public class RequestController {
    @Autowired
    private RequestService requestService;
    
    @Operation(summary = "Add Friend Request", description = "Add Friend Request")
    @RequestMapping(method = RequestMethod.POST, path = "/rest/v1/request/{sender_id}/{receiver_id}")
    public ResponseEntity<UserFriendRequest> addFriendRequest(@PathVariable String sender_id, @PathVariable String receiver_id) throws Exception {
        UserFriendRequest request = requestService.addFriendRequest(sender_id, receiver_id);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Accept Friend Request", description = "Accept Friend Request")
    @RequestMapping(method = RequestMethod.PUT, path = "/rest/v1/request/{sender_id}/{receiver_id}")
    public ResponseEntity<UserFriendRequest> acceptFriendRequest(@PathVariable String sender_id, @PathVariable String receiver_id) throws Exception {
        UserFriendRequest request = requestService.acceptFriendRequest(sender_id, receiver_id);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
    
    @Operation(summary = "Find Friend Request", description = "Find a specific friend request between sender and receiver")
    @RequestMapping(method = RequestMethod.GET, path = "/rest/v1/request/{sender_id}/{receiver_id}")
    public ResponseEntity<UserFriendRequest> getFriendRequest(@PathVariable String sender_id, @PathVariable String receiver_id) throws Exception {
        UserFriendRequest request = requestService.getFriendRequest(sender_id, receiver_id);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
    
    @Operation(summary = "List Pending Friend Requests", description = "List all pending friend requests for a user")
    @RequestMapping(method = RequestMethod.GET, path = "/rest/v1/request/pending/{user_id}")
    public ResponseEntity<List<UserFriendRequest>> listPendingFriendRequests(@PathVariable String user_id) throws Exception {
        List<UserFriendRequest> requests = requestService.listPendingFriendRequests(user_id);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    
    @Operation(summary = "List Sent Friend Requests", description = "List all friend requests sent by a user")
    @RequestMapping(method = RequestMethod.GET, path = "/rest/v1/request/sent/{user_id}")
    public ResponseEntity<List<UserFriendRequest>> listSentFriendRequests(@PathVariable String user_id) throws Exception {
        List<UserFriendRequest> requests = requestService.listSentFriendRequests(user_id);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    
    @Operation(summary = "Delete Friend Request", description = "Delete Friend Request")
    @RequestMapping(method = RequestMethod.DELETE, path = "/rest/v1/request/{sender_id}/{receiver_id}")
    public ResponseEntity<Integer> deleteFriendRequest(@PathVariable String sender_id, @PathVariable String receiver_id) throws Exception {
        int result = requestService.deleteFriendRequest(sender_id, receiver_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}