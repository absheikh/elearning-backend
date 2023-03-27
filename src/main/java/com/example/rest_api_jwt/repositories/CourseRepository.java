package com.example.rest_api_jwt.repositories;

import com.example.rest_api_jwt.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByUuid(String course);

    boolean existsByAuthorAndUuid(String author, String course);

    List findAllByAuthor(String uuid);
}
