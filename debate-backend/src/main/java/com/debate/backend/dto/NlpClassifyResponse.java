package com.debate.backend.dto;

public class NlpClassifyResponse {

    private String argument_type;
    private Double confidence;

    public String getArgument_type() {
        return argument_type;
    }

    public void setArgument_type(String argument_type) {
        this.argument_type = argument_type;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }
}