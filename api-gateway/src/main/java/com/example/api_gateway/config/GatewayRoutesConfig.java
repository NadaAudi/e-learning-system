package com.example.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                // ✅ COURSE Service
                .route("course-service", r -> r.path("/api/courses/**")
                        .filters(f -> f
                                .filter((exchange, chain) -> {
                                    var request = exchange.getRequest();
                                    var mutatedRequest = request.mutate()
                                            .header("Authorization", request.getHeaders().getFirst("Authorization"))
                                            .build();
                                    return chain.filter(exchange.mutate().request(mutatedRequest).build());
                                })
                                .circuitBreaker(c -> c
                                        .setName("courseCB")
                                        .setFallbackUri("forward:/fallback/course")))
                        .uri("lb://COURSE-SERVICE"))

                // ✅ AUTH Service
                .route("auth-service", r -> r.path("/api/auth/**")
                        .filters(f -> f
                                .circuitBreaker(c -> c
                                        .setName("authCB")
                                        .setFallbackUri("forward:/fallback/auth")))
                        .uri("lb://AUTH-SERVICE"))

                // ✅ SUBSCRIPTION Service
                .route("subscription-service", r -> r.path("/api/subscriptions/**")
                        .filters(f -> f
                                .filter((exchange, chain) -> {
                                    var request = exchange.getRequest();
                                    var mutatedRequest = request.mutate()
                                            .header("Authorization", request.getHeaders().getFirst("Authorization"))
                                            .build();
                                    return chain.filter(exchange.mutate().request(mutatedRequest).build());
                                })
                                .circuitBreaker(c -> c
                                        .setName("subCB")
                                        .setFallbackUri("forward:/fallback/subscription")))
                        .uri("lb://SUBSCRIPTION-SERVICE"))

                // ✅ EXAM Service
                .route("exam-service", r -> r.path("/api/exams/**")
                        .filters(f -> f
                                .filter((exchange, chain) -> {
                                    var request = exchange.getRequest();
                                    var mutatedRequest = request.mutate()
                                            .header("Authorization", request.getHeaders().getFirst("Authorization"))
                                            .build();
                                    return chain.filter(exchange.mutate().request(mutatedRequest).build());
                                })
                                .circuitBreaker(c -> c
                                        .setName("examCB")
                                        .setFallbackUri("forward:/fallback/exam")))
                        .uri("lb://EXAM-SERVICE"))

                // ✅ PAYMENT Service
                .route("payment-service", r -> r.path("/api/payments/**")
                        .filters(f -> f
                                .filter((exchange, chain) -> {
                                    var request = exchange.getRequest();
                                    var mutatedRequest = request.mutate()
                                            .header("Authorization", request.getHeaders().getFirst("Authorization"))
                                            .build();
                                    return chain.filter(exchange.mutate().request(mutatedRequest).build());
                                })
                                .circuitBreaker(c -> c
                                        .setName("paymentCB")
                                        .setFallbackUri("forward:/fallback/payment")))
                        .uri("lb://PAYMENT-SERVICE"))

                .build();
    }
}
