package com.example.rest_api_jwt.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quizzes_questions_answers")
public class QuizQuestionOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String uuid;
    @Column(nullable = false,name = "question_id")
    private String questionId;
    @Column(nullable = false, columnDefinition = "text", name = "option_text")
    private String option;
    @Column(nullable = false,  columnDefinition = "boolean",  name = "is_answer")
    private Boolean isAnswer;

    @PrePersist
    public void prePersist(){
        this.uuid = UUID.randomUUID().toString();
    }
}
