package com.grabtable.circuitbreaker.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CircuitBreakerController {
    private static final String SUCCESS_URI = "TODO";
    private static final String FAILURE_URI = "TODO";

    private final WebClient webClient = WebClient.create();

    @GetMapping("/api/v1/call-success-api")
    @CircuitBreaker(name = "default")
    public ResponseEntity<String> callSuccess() {
        return webClient
                .get()
                .uri(SUCCESS_URI)
                .retrieve()
                .toEntity(String.class)
                .block();
    }

    @GetMapping("/api/v1/call-failure-api")
    @CircuitBreaker(name = "default")
    public ResponseEntity<String> callFailure() {
        return webClient
                .get()
                .uri(FAILURE_URI)
                .retrieve()
                .toEntity(String.class)
                .block();
    }
}
