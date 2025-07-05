package com.example.exam_service.service;

import com.example.exam_service.model.ExamSubmission;
import com.example.exam_service.repository.ExamSubmissionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExamSubmissionService {

    private final ExamSubmissionRepository repository;

    public ExamSubmissionService(ExamSubmissionRepository repository) {
        this.repository = repository;
    }

    public ExamSubmission submitAnswer(Long examId, String username, String answer) {
        ExamSubmission submission = ExamSubmission.builder()
                .examId(examId)
                .username(username)
                .answer(answer)
                .submittedAt(LocalDateTime.now())
                .build();
        return repository.save(submission);
    }

    public List<ExamSubmission> getUserSubmissions(String username) {
        return repository.findByUsername(username);
    }

    public List<ExamSubmission> getExamSubmissions(Long examId) {
        return repository.findByExamId(examId);
    }
}
