package com.debate.backend.controller;

import com.debate.backend.dto.HealthResponse;
import com.debate.backend.dto.NlpClassifyRequest;
import com.debate.backend.dto.NlpClassifyResponse;
import com.debate.backend.service.NlpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.debate.backend.dto.NlpSimilarityRequest;
import com.debate.backend.dto.NlpSimilarityResponse;

@RestController
public class NlpController {

    private final NlpService nlpService;

    public NlpController(NlpService nlpService) {
        this.nlpService = nlpService;
    }

    @GetMapping("/api/nlp/health")
    public HealthResponse checkNlpHealth() {
        return nlpService.checkNlpHealth();
    }

    @PostMapping("/api/nlp/classify")
    public NlpClassifyResponse classify(
            @RequestBody NlpClassifyRequest request) {

        return nlpService.classifyArgument(request.getText());
    }
    @PostMapping("/api/nlp/similarity")
    public NlpSimilarityResponse similarity(
            @RequestBody NlpSimilarityRequest request) {

        return nlpService.calculateSimilarity(
                request.getText1(),
                request.getText2()
        );
    }
}