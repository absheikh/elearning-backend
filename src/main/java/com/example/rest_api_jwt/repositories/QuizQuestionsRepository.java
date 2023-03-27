package com.example.rest_api_jwt.repositories;

import com.example.rest_api_jwt.entities.QuizQuestions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizQuestionsRepository extends JpaRepository<QuizQuestions, Long> {
}
