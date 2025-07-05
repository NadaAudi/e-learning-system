package com.example.api_gateway.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @RequestMapping(
            value = "/auth",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public Mono<String> authFallback() {
        System.out.println("⚠️ تم تفعيل fallback لـ AUTH-SERVICE");
        return Mono.just("❌ Auth Service غير متاح مؤقتًا.");
    }

    @RequestMapping(
            value = "/course",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public Mono<String> courseFallback() {
        System.out.println("⚠️ تم تفعيل fallback لـ COURSE-SERVICE");
        return Mono.just("❌ Course Service غير متاح مؤقتًا.");
    }

    @RequestMapping(
            value = "/subscription",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public Mono<String> subscriptionFallback() {
        System.out.println("⚠️ تم تفعيل fallback لـ SUBSCRIPTION-SERVICE");
        return Mono.just("❌ Subscription Service غير متاح مؤقتًا.");
    }

    @RequestMapping(
            value = "/exam",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public Mono<String> examFallback() {
        System.out.println("⚠️ تم تفعيل fallback لـ EXAM-SERVICE");
        return Mono.just("❌ Exam Service غير متاح مؤقتًا.");
    }

    @RequestMapping(
            value = "/payment",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public Mono<String> paymentFallback() {
        System.out.println("⚠️ تم تفعيل fallback لـ PAYMENT-SERVICE");
        return Mono.just("❌ Payment Service غير متاح مؤقتًا.");
    }
}
