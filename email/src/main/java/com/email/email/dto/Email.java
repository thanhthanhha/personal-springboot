package com.email.email.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Email {
    private Long id;
    private String subject;
    private String body;
    private Long senderId;
    private Long receiverId;
    private LocalDateTime sentAt;
    private Boolean isDeleted;

    @Builder
    public Email(Long id, String subject, String body, Long senderId, Long receiverId, LocalDateTime sentAt, Boolean isDeleted) {
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.sentAt = sentAt;
        this.isDeleted = isDeleted;
    }
}
