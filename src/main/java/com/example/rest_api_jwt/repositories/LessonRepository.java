package com.example.rest_api_jwt.repositories;

import com.example.rest_api_jwt.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {



}
