package com.example.course_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private double price;

    private String createdBy; // اسم المدرّب أو الـ username
    private boolean approved = false;
    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


}
