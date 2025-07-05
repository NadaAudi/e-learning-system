package com.example.course_service.controller;

import com.example.course_service.model.Course;
import com.example.course_service.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // ➕ إنشاء دورة مع ربط تلقائي باسم المنشئ + غير معتمدة افتراضيًا
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        String createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("✅ [COURSE-SERVICE 8086] تم استلام الطلب لإنشاء دورة.");
        System.out.println("✅ [COURSE-SERVICE] إنشاء دورة من قبل: " + createdBy);

        course.setCreatedBy(createdBy);
        course.setApproved(false); // ❌ ما بتنشر قبل موافقة المشرف
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    // 📦 عرض الدورات المعتمدة فقط (مفتوحة للجميع)
    @GetMapping
    public ResponseEntity<List<Course>> getAllApprovedCourses() {
        return ResponseEntity.ok(courseService.getApprovedCourses());
    }

    // 🔍 جلب دورة حسب ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    // 🧑‍🏫 عرض دورات المدرّب الحالي فقط (سواء معتمدة أو لأ)
    @GetMapping("/owned")
    public ResponseEntity<List<Course>> getMyCourses() {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("📥 [COURSE-SERVICE] عرض الدورات الخاصة بـ: " + currentUser);

        return ResponseEntity.ok(courseService.getCoursesByCreatedBy(currentUser));
    }

    // ✅ Endpoint خاص بـ admin للموافقة على الدورة
    @PutMapping("/{id}/approve")
    public ResponseEntity<Course> approveCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.approveCourse(id));
    }

    // ❌ رفض دورة من قبل admin
    @PutMapping("/{id}/reject")
    public ResponseEntity<Course> rejectCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.rejectCourse(id));
    }
}
