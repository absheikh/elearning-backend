package com.example.rest_api_jwt.repositories;

import com.example.rest_api_jwt.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List findAllByCourseId(String course);
}
