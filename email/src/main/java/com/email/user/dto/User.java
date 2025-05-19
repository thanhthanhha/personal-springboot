package com.email.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private Integer inboxLimit;
    private LocalDateTime createdAt;

    @Builder
    public User(Long id, String username, String email, Integer inboxLimit, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.inboxLimit = inboxLimit;
        this.createdAt = createdAt;
    }
}
