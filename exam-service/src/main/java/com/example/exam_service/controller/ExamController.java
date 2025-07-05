package com.example.exam_service.controller;

import com.example.exam_service.model.Exam;
import com.example.exam_service.model.ExamSubmission;
import com.example.exam_service.service.ExamService;
import com.example.exam_service.service.ExamSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;
    private final ExamSubmissionService submissionService;

    public ExamController(ExamService examService, ExamSubmissionService submissionService) {
        this.examService = examService;
        this.submissionService = submissionService;
    }

    // 🔹 إنشاء امتحان (INSTRUCTOR)
    @PostMapping
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        return ResponseEntity.ok(examService.createExam(exam));
    }
    // 🔍 عرض الامتحانات الخاصة بدورة
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Exam>> getCourseExams(@PathVariable Long courseId) {
        return ResponseEntity.ok(examService.getExamsByCourseId(courseId));
    }
    // ✅ تقديم إجابة امتحان (LEARNER)
    @PostMapping("/{examId}/submit")
    @PreAuthorize("hasRole('LEARNER')")
    public ResponseEntity<ExamSubmission> submitAnswer(@PathVariable Long examId, @RequestBody String answer) {
        String username = getCurrentUsername();
        return ResponseEntity.ok(submissionService.submitAnswer(examId, username, answer));
    }
    // 🧾 عرض إجابات الطالب الحالي
    @GetMapping("/submissions/me")
    @PreAuthorize("hasRole('LEARNER')")
    public ResponseEntity<List<ExamSubmission>> getMySubmissions() {
        String username = getCurrentUsername();
        return ResponseEntity.ok(submissionService.getUserSubmissions(username));
    }
    // 🧾 عرض إجابات كل الطلاب لامتحان (INSTRUCTOR فقط)
    @GetMapping("/submissions/exam/{examId}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<List<ExamSubmission>> getExamSubmissions(@PathVariable Long examId) {
        return ResponseEntity.ok(submissionService.getExamSubmissions(examId));
    }

    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
