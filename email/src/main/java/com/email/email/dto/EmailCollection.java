package com.email.email.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class EmailCollection {
    private Long id;
    private String subject;
    private String body;
    private Long senderId;
    private LocalDateTime sentAt;
    private Boolean isDeleted;
    private String senderUsername;
    private List<Long> receiverIds;

    @Builder
    public EmailCollection(Long id, String subject, String body, Long senderId, List<Long> receiverIds, LocalDateTime sentAt, Boolean isDeleted, String senderUsername) {
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.senderId = senderId;
        this.receiverIds = receiverIds;
        this.sentAt = sentAt;
        this.isDeleted = isDeleted;
        this.senderUsername = senderUsername;
    }
}
