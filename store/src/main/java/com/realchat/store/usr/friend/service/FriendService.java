package com.realchat.store.usr.friend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realchat.store.usr.friend.dto.UserFriend;
import com.realchat.store.usr.request.dto.UserFriendRequest;
import com.realchat.store.usr.friend.repository.FriendRepository;
import com.realchat.store.usr.request.repository.RequestRepository;
import com.realchat.store.exception.BusinessException;

@Service("userFriend")
public class FriendService {
	@Autowired
	private FriendRepository FriendRepository;
	
	@Autowired
	private RequestRepository RequestRepository;
	
	//Friend
	
	/**
     * Add a user as a friend
     * @param user_id The user who is adding a friend
     * @param friend_id The user being added as a friend
     * @return The created UserFriend object
     * @throws Exception If there's an error during the process
     */
    @Transactional(rollbackFor = Exception.class)
    public UserFriend addFriend(String user_id, String friend_id) throws Exception {
        // Validate mandatory fields
        if (user_id == null || user_id.trim().isEmpty()) {
            throw new BusinessException("User ID is required");
        }
        
        if (friend_id == null || friend_id.trim().isEmpty()) {
            throw new BusinessException("Friend ID is required");
        }
        
        if (user_id.equals(friend_id)) {
            throw new BusinessException("User cannot add themselves as a friend");
        }
        UserFriendRequest existingRequest = RequestRepository.getFriendRequest(user_id.trim(), friend_id.trim());
	    if (existingRequest == null) {
	    	throw new BusinessException(String.format("Friend request between user %s and friend %s does not exist", user_id, friend_id));
	    }
        
        // Call repository method to add friend
        return FriendRepository.addFriend(user_id, friend_id);
    }
    
    
    /**
     * Find a specific friendship between two users
     * @param user_id The user ID
     * @param friend_id The friend ID
     * @return The UserFriend object representing the friendship
     * @throws Exception If there's an error or the friendship doesn't exist
     */
    @Transactional(readOnly = true)
    public UserFriend getFriend(String user_id, String friend_id) throws Exception {
        // Validate mandatory fields
        if (user_id == null || user_id.trim().isEmpty()) {
            throw new BusinessException("User ID is required");
        }
        
        if (friend_id == null || friend_id.trim().isEmpty()) {
            throw new BusinessException("Friend ID is required");
        }
        
        // Trim the input parameters
        String trimmedUserId = user_id.trim();
        String trimmedFriendId = friend_id.trim();
        
        // Call repository method to find the friendship
        UserFriend friendship = FriendRepository.getFriend(trimmedUserId, trimmedFriendId);
        
        if (friendship == null) {
            throw new BusinessException(String.format("Friendship between user '%s' and friend '%s' does not exist", 
                                       trimmedUserId, trimmedFriendId));
        }
        
        return friendship;
    }

    /**
     * List all friends for a user
     * @param user_id The user ID
     * @return List of UserFriend objects
     * @throws Exception If there's an error during the process
     */
    @Transactional(readOnly = true)
    public List<UserFriend> listFriends(String user_id) throws Exception {
        // Validate mandatory fields
        if (user_id == null || user_id.trim().isEmpty()) {
            throw new BusinessException("User ID is required");
        }
        
        // Trim the input parameter
        String trimmedUserId = user_id.trim();
        
        // Call repository method to list friends
        List<UserFriend> friends = FriendRepository.listFriends(trimmedUserId);
        
        return friends;
    }

    
    /**
     * Remove a friend relationship
     * @param user_id The user who is removing a friend
     * @param friend_id The user being removed as a friend
     * @throws Exception If there's an error during the process
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteFriend(String user_id, String friend_id) throws Exception {
        // Validate mandatory fields
        if (user_id == null || user_id.trim().isEmpty()) {
            throw new BusinessException("User ID is required");
        }
        
        if (friend_id == null || friend_id.trim().isEmpty()) {
            throw new BusinessException("Friend ID is required");
        }
        
        // Call repository method to delete friend
        return FriendRepository.deleteFriend(user_id, friend_id);
    }
    
}
