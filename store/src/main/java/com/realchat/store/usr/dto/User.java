package com.realchat.store.usr.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;            
    private String name;          
    private String email;         
    private String user_id;       
    private String image;         // optional since not in schema
    private String password;      // optional
    private String password_salt; // optional
    
    @Builder
    public User(String id, String name, String email, String user_id, 
               String image, String password, String password_salt) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.user_id = user_id;
        this.image = image;
        this.password = password;
        this.password_salt = password_salt;
    }
}