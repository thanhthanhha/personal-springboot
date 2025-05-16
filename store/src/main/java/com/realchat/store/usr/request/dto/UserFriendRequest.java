package com.realchat.store.usr.request.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFriendRequest implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String id;            
    private String sender_id;     
    private String receiver_id;   
    private Boolean approved;     
    private Timestamp created_at; 
    
    @Builder
    public UserFriendRequest(String id, String sender_id, String receiver_id, 
                       Boolean approved, Timestamp created_at) {
        super();
        this.id = id;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.approved = approved;
        this.created_at = created_at;
    }
}