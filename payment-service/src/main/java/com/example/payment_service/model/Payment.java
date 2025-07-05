package com.example.payment_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private Long courseId;
    private Double amount;
    private LocalDateTime paidAt;

    // Getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public Long getCourseId() { return courseId; }
    public Double getAmount() { return amount; }
    public LocalDateTime getPaidAt() { return paidAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setPaidAt(LocalDateTime paidAt) { this.paidAt = paidAt; }
}
