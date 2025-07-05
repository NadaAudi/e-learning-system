package com.example.subscription_service.repository;

import com.example.subscription_service.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUsername(String username);
    List<Subscription> findByCourseId(Long courseId);
    Optional<Subscription> findByUsernameAndCourseId(String username, Long courseId);
}
