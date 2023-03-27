package com.example.rest_api_jwt.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quizzes_questions")
public class QuizQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String uuid;
    @Column(nullable = false,name = "quiz_is")
    private String quizId;
    @Column(nullable = false, columnDefinition = "text", name = "question_text")
    private String question;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", referencedColumnName = "uuid")
    private List<QuizQuestionOptions> options;

    @PrePersist
    public void prePersist(){
        this.uuid = UUID.randomUUID().toString();
    }

}
