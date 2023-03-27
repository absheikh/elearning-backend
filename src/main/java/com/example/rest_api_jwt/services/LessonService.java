package com.example.rest_api_jwt.services;

import com.example.rest_api_jwt.entities.Lesson;
import com.example.rest_api_jwt.entities.User;
import com.example.rest_api_jwt.pojos.LessonRequest;
import com.example.rest_api_jwt.repositories.CourseRepository;
import com.example.rest_api_jwt.repositories.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    Logger logger = LoggerFactory.getLogger(LessonService.class);

    public ResponseEntity<?> addLesson(String course, User currentUser, LessonRequest request) {

        if(request.getTitle()==null || request.getTitle().isEmpty() || request.getDescription()==null || request.getDescription().isEmpty() || request.getContent_type()==null || request.getContent_type().isEmpty() || request.getContent_url()==null || request.getContent_url().isEmpty() || request.getLesson_order()==null || request.getLesson_order() ==0 )
            return new ResponseEntity<>(new ApiResponse<>(false, "Invalid Request", 400,  null),
                    HttpStatus.BAD_REQUEST);

        if(!courseRepository.existsByUuid(course))
            return new ResponseEntity<>(new ApiResponse<>(false, "Course not found", 404,  null),
                            HttpStatus.NOT_FOUND);

        if(!courseRepository.existsByAuthorAndUuid(currentUser.getUuid(),course))
            return new ResponseEntity<>(new ApiResponse<>(false, "You are not the instructor of this course", 403,  null),
                            HttpStatus.FORBIDDEN);



        var lesson = Lesson.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .content_type(request.getContent_type())
                .content_url(request.getContent_url())
                .lesson_order(request.getLesson_order())
                .course_id(course)
                .build();

        try {
                lessonRepository.save(lesson);
                logger.info("Lesson added successfully");
                return ResponseEntity.ok(new ApiResponse(true, "Lesson added successfully", 200, lesson));

        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponse(false, "Something went wrong", 500,  null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
