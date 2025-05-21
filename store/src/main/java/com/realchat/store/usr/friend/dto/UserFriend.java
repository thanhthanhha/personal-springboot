package com.realchat.store.usr.friend.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserFriend implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private UUID id;            
    private String user_id;     
    private String friend_id;        
    private Timestamp created_at; 
    
    @Builder
    public UserFriend(UUID id, String user_id, String friend_id, 
    		Timestamp created_at) {
        super();
        this.id = id;
        this.user_id = user_id;
        this.friend_id = friend_id;
        this.created_at = created_at;
    }
}