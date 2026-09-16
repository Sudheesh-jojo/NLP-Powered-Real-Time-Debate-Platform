package com.debate.backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class DebateDto {

    private UUID id;
    private String title;
    private String description;
    private String createdByUsername;
    private LocalDateTime createdAt;
    private int argumentCount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getArgumentCount() {
        return argumentCount;
    }

    public void setArgumentCount(int argumentCount) {
        this.argumentCount = argumentCount;
    }
}