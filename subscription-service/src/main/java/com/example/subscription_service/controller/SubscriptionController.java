package com.example.subscription_service.controller;

import com.example.subscription_service.model.Subscription;
import com.example.subscription_service.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    // ✅ اشتراك الطالب في دورة
    @PostMapping
    public ResponseEntity<Subscription> subscribeToCourse(@RequestParam Long courseId) {
        String username = getCurrentUsername();
        Subscription subscription = service.subscribe(username, courseId);
        return ResponseEntity.ok(subscription);
    }

    // ✅ إلغاء اشتراك
    @DeleteMapping("/{id}")
    public ResponseEntity<String> unsubscribeFromCourse(@PathVariable Long id) {
        String username = getCurrentUsername();
        service.unsubscribe(id, username);
        return ResponseEntity.ok("✅ تم إلغاء الاشتراك بنجاح.");
    }

    // ✅ عرض اشتراكات المستخدم الحالي
    @GetMapping("/me")
    public ResponseEntity<List<Subscription>> getMySubscriptions() {
        String username = getCurrentUsername();
        return ResponseEntity.ok(service.getUserSubscriptions(username));
    }

    // ✅ عرض مشتركي دورة معينة (للمشرف أو المدرّب)
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Subscription>> getCourseSubscribers(@PathVariable Long courseId) {
        return ResponseEntity.ok(service.getSubscriptionsByCourseId(courseId));
    }

    // 🧠 استخراج اسم المستخدم من التوكن
    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
