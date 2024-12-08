package com.grabtable.circuitbreaker.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class CircuitBreakerService {
    private static final String SUCCESS_URI = "http://localhost:8000/api/v1/delay-success/3";
    private static final String FAILURE_URI = "http://localhost:8000/api/v1/delay-failure/3";

    private final WebClient webClient;

    @CircuitBreaker(name = "default", fallbackMethod = "fallbackResponse")
    public String callSuccess() {
        return webClient.get()
                .uri(SUCCESS_URI)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 동기 블로킹
    }

    @CircuitBreaker(name = "default", fallbackMethod = "fallbackResponse")
    public String callFailure() {
        return webClient.get()
                .uri(FAILURE_URI)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 동기 블로킹
    }

    private String fallbackResponse(Throwable t) {
        return "Fallback response: External service currently unavailable.";
    }
}
