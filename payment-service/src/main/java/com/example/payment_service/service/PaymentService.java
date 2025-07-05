package com.example.payment_service.service;

import com.example.payment_service.model.Payment;
import com.example.payment_service.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public Payment makePayment(String username, Long courseId, Double amount) {
        repository.findByUsernameAndCourseId(username, courseId).ifPresent(p -> {
            throw new RuntimeException("❌ تم الدفع مسبقًا لهذه الدورة.");
        });

        Payment payment = new Payment();
        payment.setUsername(username);
        payment.setCourseId(courseId);
        payment.setAmount(amount);
        payment.setPaidAt(LocalDateTime.now());


        return repository.save(payment);
    }

    public List<Payment> getUserPayments(String username) {
        return repository.findByUsername(username);
    }
}
