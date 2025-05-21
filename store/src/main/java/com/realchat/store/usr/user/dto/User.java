package com.realchat.store.usr.user.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class User implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UUID id;            
    private String name;          
    private String email;         
    private String user_id;       
    private String image;         // optional since not in schema
    private String password;      // optional
    private String passwordSalt; // optional
    
    @Builder
    public User(UUID id, String name, String email, String user_id, 
               String image, String password, String passwordSalt) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.user_id = user_id;
        this.image = image;
        this.password = password;
        this.passwordSalt = passwordSalt;
    }
}