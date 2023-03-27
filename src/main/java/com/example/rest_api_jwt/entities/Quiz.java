package com.example.rest_api_jwt.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String uuid;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, name = "course_id")
    private String courseId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", referencedColumnName = "uuid")
    private List<QuizQuestions> questions;


    @PrePersist
    public void prePersist(){
        this.uuid = UUID.randomUUID().toString();
    }
}
