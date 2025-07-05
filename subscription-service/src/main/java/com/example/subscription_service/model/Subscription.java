package com.example.subscription_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private Long courseId;
    private LocalDateTime subscribedAt;

    public Subscription() {}

    public Subscription(Long id, String username, Long courseId, LocalDateTime subscribedAt) {
        this.id = id;
        this.username = username;
        this.courseId = courseId;
        this.subscribedAt = subscribedAt;
    }

    // ✅ Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Long getCourseId() {
        return courseId;
    }

    public LocalDateTime getSubscribedAt() {
        return subscribedAt;
    }

    // ✅ Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setSubscribedAt(LocalDateTime subscribedAt) {
        this.subscribedAt = subscribedAt;
    }
}
