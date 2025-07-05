package com.example.course_service.service;

import com.example.course_service.model.Course;
import com.example.course_service.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public List<Course> getApprovedCourses() {
        return courseRepository.findByApprovedTrue();
    }

    public Course approveCourse(Long id) {
        Course course = getCourseById(id);
        course.setApproved(true);
        return courseRepository.save(course);
    }
    public List<Course> getCoursesByCreatedBy(String createdBy) {
        return courseRepository.findByCreatedBy(createdBy);
    }
    public Course rejectCourse(Long id) {
        Course course = getCourseById(id);

        if (!course.isApproved()) {
            throw new IllegalStateException("الدورة مرفوضة مسبقًا.");
        }

        course.setApproved(false);
        return courseRepository.save(course);
    }


}
