package com.email.email.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailQuery {
    private Long userId;
    private String text;

    @Builder
    public EmailQuery(Long userId, String text) {
        this.userId = userId;
        this.text = text;
    }
};
