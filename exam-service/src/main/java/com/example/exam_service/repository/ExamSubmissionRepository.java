package com.example.exam_service.repository;

import com.example.exam_service.model.ExamSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamSubmissionRepository extends JpaRepository<ExamSubmission, Long> {
    List<ExamSubmission> findByUsername(String username);
    List<ExamSubmission> findByExamId(Long examId);
}
