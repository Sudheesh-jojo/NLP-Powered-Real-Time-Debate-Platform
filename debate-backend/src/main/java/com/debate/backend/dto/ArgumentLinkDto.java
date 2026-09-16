package com.debate.backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ArgumentLinkDto {

    private UUID id;
    private UUID fromArgumentId;
    private UUID toArgumentId;
    private String linkType;
    private Double similarityScore;
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getFromArgumentId() {
        return fromArgumentId;
    }

    public void setFromArgumentId(UUID fromArgumentId) {
        this.fromArgumentId = fromArgumentId;
    }

    public UUID getToArgumentId() {
        return toArgumentId;
    }

    public void setToArgumentId(UUID toArgumentId) {
        this.toArgumentId = toArgumentId;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public Double getSimilarityScore() {
        return similarityScore;
    }

    public void setSimilarityScore(Double similarityScore) {
        this.similarityScore = similarityScore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}