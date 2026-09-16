package com.debate.backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ArgumentDto {

    private UUID id;
    private UUID debateId;
    private UUID userId;
    private String messageText;
    private String argumentType;
    private Double nlpConfidence;
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDebateId() {
        return debateId;
    }

    public void setDebateId(UUID debateId) {
        this.debateId = debateId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getArgumentType() {
        return argumentType;
    }

    public void setArgumentType(String argumentType) {
        this.argumentType = argumentType;
    }

    public Double getNlpConfidence() {
        return nlpConfidence;
    }

    public void setNlpConfidence(Double nlpConfidence) {
        this.nlpConfidence = nlpConfidence;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}