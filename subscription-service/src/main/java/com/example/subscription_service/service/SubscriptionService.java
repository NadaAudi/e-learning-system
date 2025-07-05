package com.example.subscription_service.service;

import com.example.subscription_service.model.Subscription;
import com.example.subscription_service.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository repository;

    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    public Subscription subscribe(String username, Long courseId) {
        // منع الاشتراك المكرر
        repository.findByUsernameAndCourseId(username, courseId)
                .ifPresent(sub -> {
                    throw new RuntimeException("أنت مشترك مسبقًا بهذه الدورة.");
                });

        // إنشاء الاشتراك يدويًا (بدون Builder)
        Subscription subscription = new Subscription();
        subscription.setUsername(username);
        subscription.setCourseId(courseId);
        subscription.setSubscribedAt(LocalDateTime.now());

        return repository.save(subscription);
    }

    public void unsubscribe(Long id, String username) {
        Subscription subscription = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("الاشتراك غير موجود."));

        if (!subscription.getUsername().equals(username)) {
            throw new RuntimeException("لا يمكنك إلغاء اشتراك ليس لك.");
        }

        repository.delete(subscription);
    }

    public List<Subscription> getUserSubscriptions(String username) {
        return repository.findByUsername(username);
    }

    public List<Subscription> getSubscriptionsByCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }
}
