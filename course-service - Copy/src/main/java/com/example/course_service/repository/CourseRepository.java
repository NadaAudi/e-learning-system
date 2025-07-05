package com.example.course_service.repository;

import com.example.course_service.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // 📦 دورات أنشأها المدرّب
    List<Course> findByCreatedBy(String createdBy);

    // ✅ فقط الدورات المعتمدة للنشر
    List<Course> findByApprovedTrue();
}
