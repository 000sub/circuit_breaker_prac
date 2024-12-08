package com.grabtable.circuitbreaker.controller;

import com.grabtable.circuitbreaker.service.CircuitBreakerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CircuitBreakerController {
    private final CircuitBreakerService circuitBreakerService;

    @GetMapping("/api/v1/call-success-api")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackResponse")
    public String callSuccess() {
        return circuitBreakerService.callSuccess();
    }

    @GetMapping("/api/v1/call-failure-api")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackResponse")
    public String callFailure() {
        return circuitBreakerService.callFailure();
    }
}
