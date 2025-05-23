package com.realchat.store.usr.request.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realchat.store.usr.request.dto.UserFriendRequest;
import com.realchat.store.usr.request.repository.RequestRepository;
import com.realchat.store.usr.user.service.UserService;
import com.realchat.store.exception.BusinessException;
import com.realchat.store.usr.friend.service.FriendService;
import com.realchat.store.usr.friend.dto.UserFriend;
import com.realchat.store.usr.friend.repository.FriendRepository;
import com.realchat.store.usr.user.repository.UserRepository;
import com.realchat.store.usr.user.dto.User;
import com.realchat.store.utils.LoggerUtils;

import org.springframework.http.HttpStatus;

@Service("userRequest")
public class RequestService {
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private FriendRepository friendRepository;
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	UserRepository userRepository;
	
	private static final LoggerUtils logger = LoggerUtils.getLogger(UserService.class);
	
    //Friend Request
    
    /**
     * Send a friend request to another user
     * @param sender_id The user sending the friend request
     * @param receiver_id The user receiving the friend request
     * @return The created UserFriendRequest object
     * @throws Exception If there's an error during the process
     */
    @Transactional(rollbackFor = Exception.class)
    public UserFriendRequest addFriendRequest(String sender_id, String receiver_id) throws Exception {
        // Validate mandatory fields
        if (sender_id == null || sender_id.trim().isEmpty()) {
            throw new BusinessException("Sender ID is required", HttpStatus.BAD_REQUEST);
        }
        
        if (receiver_id == null || receiver_id.trim().isEmpty()) {
            throw new BusinessException("Receiver ID is required", HttpStatus.BAD_REQUEST);
        }
        
        if (sender_id.equals(receiver_id)) {
            throw new BusinessException("User cannot send a friend request to themselves", HttpStatus.CONFLICT);
        }
        
        logger.info(String.format("Check sender id %s and receiver id %s", sender_id, receiver_id));
        
        UserFriendRequest existingRequest = requestRepository.getFriendRequest(sender_id.trim(), receiver_id.trim());
	    if (existingRequest != null) {
	    	throw new BusinessException(String.format("Friend request between user %s and friend %s exist", sender_id, receiver_id), HttpStatus.CONFLICT);
	    }
	    
	    if (!userRepository.existsByUserId(sender_id) || !userRepository.existsByUserId(receiver_id)) {
	    	throw new BusinessException(String.format("Sender or Reciever ID does not exist"), HttpStatus.NOT_FOUND);
	    }
	    
	    UserFriend existingFriendship = friendRepository.getFriend(sender_id, receiver_id);
	    
	    if (existingFriendship != null) {
	    	throw new BusinessException(String.format("User %s is already friend with %s",sender_id, receiver_id), HttpStatus.CONFLICT);
	    }
	    
	    UserFriendRequest friendRequest = UserFriendRequest.builder()
	    		.id(UUID.randomUUID())
	    		.receiver_id(receiver_id)
	    		.sender_id(sender_id)
	    		.approved(false)
	    		.build();
	    
        // Call repository method to add friend request
        requestRepository.addFriendRequest(friendRequest);
        
        return friendRequest;
    }
    
    /**
     * Find a specific friend request between sender and receiver
     * @param sender_id The sender ID
     * @param receiver_id The receiver ID
     * @return The UserFriendRequest object
     * @throws Exception If there's an error or the request doesn't exist
     */
    @Transactional(readOnly = true)
    public UserFriendRequest getFriendRequest(String sender_id, String receiver_id) throws Exception {
        // Validate mandatory fields
        if (sender_id == null || sender_id.trim().isEmpty()) {
            throw new BusinessException("Sender ID is required");
        }
        
        if (receiver_id == null || receiver_id.trim().isEmpty()) {
            throw new BusinessException("Receiver ID is required");
        }
        
        // Trim the input parameters
        String trimmedSenderId = sender_id.trim();
        String trimmedReceiverId = receiver_id.trim();
        
        // Call repository method to find the friend request
        UserFriendRequest friendRequest = requestRepository.getFriendRequest(trimmedSenderId, trimmedReceiverId);
        
        if (friendRequest == null) {
            throw new BusinessException(String.format("Friend request from sender '%s' to receiver '%s' does not exist", 
                                       trimmedSenderId, trimmedReceiverId));
        }
        
        return friendRequest;
    }

    /**
     * List all pending friend requests for a user
     * @param user_id The user ID
     * @return List of pending friend requests
     * @throws Exception If there's an error during the process
     */
    @Transactional(readOnly = true)
    public List<UserFriendRequest> listPendingFriendRequests(String user_id) throws Exception {
        // Validate mandatory fields
        if (user_id == null || user_id.trim().isEmpty()) {
            throw new BusinessException("User ID is required");
        }
        
        // Trim the input parameter
        String trimmedUserId = user_id.trim();
        
        // Call repository method to list pending friend requests
        List<UserFriendRequest> pendingRequests = requestRepository.listPendingFriendRequests(trimmedUserId);
        
        return pendingRequests;
    }

    /**
     * List all friend requests sent by a user
     * @param user_id The user ID
     * @return List of sent friend requests
     * @throws Exception If there's an error during the process
     */
    @Transactional(readOnly = true)
    public List<UserFriendRequest> listSentFriendRequests(String user_id) throws Exception {
        // Validate mandatory fields
        if (user_id == null || user_id.trim().isEmpty()) {
            throw new BusinessException("User ID is required");
        }
        
        // Trim the input parameter
        String trimmedUserId = user_id.trim();
        
        // Call repository method to list sent friend requests
        List<UserFriendRequest> sentRequests = requestRepository.listSentFriendRequests(trimmedUserId);
        
        return sentRequests;
    }
    
	@Transactional(rollbackFor = Exception.class)
	public UserFriendRequest acceptFriendRequest(String sender_id, String receiver_id) throws Exception {
	    // Check for null before trimming
	    if (sender_id == null) {
	        throw new BusinessException("Sender ID is required");
	    }
	    
	    if (receiver_id == null) {
	        throw new BusinessException("Receiver ID is required");
	    }
	    
	    String trimmedSenderId = sender_id.trim();
	    String trimmedReceiverId = receiver_id.trim();
	            
	    // Validate fields after trimming
	    if (trimmedSenderId.isEmpty()) {
	        throw new BusinessException("Sender ID is required");
	    }
	    
	    if (trimmedReceiverId.isEmpty()) {
	        throw new BusinessException("Receiver ID is required");
	    }
	    
	    // First, get the existing friend request
	    // You'll need to add a method to find the request
	    UserFriendRequest existingRequest = requestRepository.getFriendRequest(trimmedSenderId, trimmedReceiverId);
	    
	    if (existingRequest == null) {
	    	throw new BusinessException(String.format("Friend request between user %s and friend %s does not exist", sender_id, receiver_id));
	    }
	    
	    // Update the request
	    existingRequest.setApproved(true);
	    
	    // Add the friendship relationship
	    friendService.addFriend(trimmedSenderId, trimmedReceiverId);
	    
	    // Update the friend request
	    // You need to add this method to your repository
	    requestRepository.updateFriendRequest(existingRequest);
	    
	    return existingRequest;
	}
    
    /**
     * Delete a friend request
     * @param sender_id The user who sent the friend request
     * @param receiver_id The user who received the friend request
     * @throws Exception If there's an error during the process
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteFriendRequest(String sender_id, String receiver_id) throws Exception {
        // Validate mandatory fields
        if (sender_id == null || sender_id.trim().isEmpty()) {
            throw new BusinessException("Sender ID is required");
        }
        
        if (receiver_id == null || receiver_id.trim().isEmpty()) {
            throw new BusinessException("Receiver ID is required");
        }
        
        // Call repository method to delete friend request
        return requestRepository.deleteFriendRequest(sender_id, receiver_id);
    }
    
}
