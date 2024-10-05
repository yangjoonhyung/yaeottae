package com.fit.Ya_eottae.web.review.service;

import com.fit.Ya_eottae.domain.AiModel.AiModel;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class PredictionService {

    private final WebClient webClient = WebClient.create("https://api-948724278280.asia-northeast3.run.app");

    public Mono<AiModel> getPrediction(String reviewDetail) {
        return webClient.post()
                .uri("/classifier/predict")
                .bodyValue(Collections.singletonMap("text", reviewDetail))
                .retrieve()
                .bodyToMono(AiModel.class)  // 응답을 PredictionResponse로 매핑
                .doOnNext(response -> System.out.println("Response: " + response)) // 응답 로그 출력
                .doOnError(e -> System.out.println("Error: " + e.getMessage()));
    }
}
