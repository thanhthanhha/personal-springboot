package com.realchat.store.usr.user.dto;
import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserQuery implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<UUID> id;            
    private List<String> name;          
    private List<String> email;         
    private List<String> user_id;             // optional since not in schema
    
    @Builder
    public UserQuery(List<UUID> id, List<String> name, List<String> email, List<String> user_id) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.user_id = user_id;
    }
}