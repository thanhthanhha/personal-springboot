package com.realchat.store.usr.user.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAuth implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String email;         
    private String user_id;       
    private String password;      // optional
    
    @Builder
    public UserAuth(String email, String user_id, String password) {
        super();
        this.email = email;
        this.user_id = user_id;
        this.password = password;
    }
}