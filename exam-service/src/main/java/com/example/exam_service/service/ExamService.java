package com.example.exam_service.service;

import com.example.exam_service.model.Exam;
import com.example.exam_service.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository repository;

    public ExamService(ExamRepository repository) {
        this.repository = repository;
    }

    public Exam createExam(Exam exam) {
        return repository.save(exam);
    }

    public List<Exam> getExamsByCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }
}
