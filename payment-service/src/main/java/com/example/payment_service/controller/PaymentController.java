package com.example.payment_service.controller;

import com.example.payment_service.model.Payment;
import com.example.payment_service.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // 🔹 إنشاء دفعة (LEARNER فقط)
    @PostMapping
    public ResponseEntity<Payment> pay(@RequestParam Long courseId, @RequestParam Double amount) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(service.makePayment(username, courseId, amount));
    }

    // 🔍 عرض دفعات الطالب الحالي
    @GetMapping("/me")
    public ResponseEntity<List<Payment>> getMyPayments() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(service.getUserPayments(username));
    }
}
