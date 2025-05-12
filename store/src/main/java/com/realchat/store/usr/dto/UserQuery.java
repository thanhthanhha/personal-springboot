package com.realchat.store.usr.dto;
import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserQuery implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<String> id;            
    private List<String> name;          
    private List<String> email;         
    private List<String> user_id;       
    private List<String> image;         // optional since not in schema
    
    @Builder
    public UserQuery(List<String> id, List<String> name, List<String> email, List<String> user_id, 
               List<String> image, List<String> password, List<String> password_salt) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.user_id = user_id;
        this.image = image;
    }
}