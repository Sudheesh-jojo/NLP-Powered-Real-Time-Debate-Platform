package com.debate.backend.dto;

import java.util.UUID;

public class CreateArgumentRequest {

    private UUID debateId;
    private String messageText;

    public UUID getDebateId() {
        return debateId;
    }

    public void setDebateId(UUID debateId) {
        this.debateId = debateId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}