package com.debate.backend.service;
import com.debate.backend.dto.HealthResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.debate.backend.dto.NlpClassifyRequest;
import com.debate.backend.dto.NlpClassifyResponse;
import com.debate.backend.dto.NlpSimilarityRequest;
import com.debate.backend.dto.NlpSimilarityResponse;

@Service
public class NlpService {
    private final WebClient webClient;
    public NlpService(WebClient webClient){
        this.webClient=webClient;
    }
    public HealthResponse checkNlpHealth(){
        return webClient
                .get()
                .uri("/health")
                .retrieve()
                .bodyToMono(HealthResponse.class)
                .block();
    }
    public NlpClassifyResponse classifyArgument(String text) {

        NlpClassifyRequest request = new NlpClassifyRequest();
        request.setText(text);

        return webClient
                .post()
                .uri("/classify")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(NlpClassifyResponse.class)
                .block();
    }
    public NlpSimilarityResponse calculateSimilarity(
            String text1,
            String text2) {

        NlpSimilarityRequest request = new NlpSimilarityRequest();

        request.setText1(text1);
        request.setText2(text2);

        return webClient
                .post()
                .uri("/similarity")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(NlpSimilarityResponse.class)
                .block();
    }
}
