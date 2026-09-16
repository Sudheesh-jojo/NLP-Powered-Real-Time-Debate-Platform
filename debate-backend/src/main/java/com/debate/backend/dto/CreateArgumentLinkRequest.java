package com.debate.backend.dto;

import java.util.UUID;

public class CreateArgumentLinkRequest {

    private UUID fromArgumentId;
    private UUID toArgumentId;
    private String linkType;
    private Double similarityScore;

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
}