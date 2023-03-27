package com.example.rest_api_jwt.services;

import com.example.rest_api_jwt.entities.Quiz;
import com.example.rest_api_jwt.entities.User;
import com.example.rest_api_jwt.pojos.LessonRequest;
import com.example.rest_api_jwt.pojos.QuizQuestionRequest;
import com.example.rest_api_jwt.pojos.QuizRequest;
import com.example.rest_api_jwt.repositories.CourseRepository;
import com.example.rest_api_jwt.repositories.QuizQuestionsRepository;
import com.example.rest_api_jwt.repositories.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizQuestionsRepository quizQuestionsRepository;
    private final CourseRepository courseRepository;


    Logger logger = LoggerFactory.getLogger(QuizService.class);

    public ResponseEntity<?> addQuiz(String course, User currentUser, QuizRequest request){
        if(request.getTitle()==null || request.getTitle().isEmpty())
            return new ResponseEntity<>(new ApiResponse<>(false, "Invalid Request", 400,  null),
                    HttpStatus.BAD_REQUEST);
        if(!courseRepository.existsByUuid(course))
            return new ResponseEntity<>(new ApiResponse<>(false, "Course not found", 404,  null),
                    HttpStatus.NOT_FOUND);

        if(!courseRepository.existsByAuthorAndUuid(currentUser.getUuid(),course))
            return new ResponseEntity<>(new ApiResponse<>(false, "Not allowed", 403,  null),
                    HttpStatus.FORBIDDEN);

        var quiz = Quiz.builder()
                .title(request.getTitle())
                .courseId(course)
                .build();
        try{
            quizRepository.save(quiz);
            logger.info("Quiz created");
            return ResponseEntity.ok(new ApiResponse(true, "Quiz created successfully", 200, null));
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponse(false, "Something went wrong", 500,  null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public ResponseEntity<?> getQuizzes(String course, User currentUser) {
        if(!courseRepository.existsByUuid(course))
            return new ResponseEntity<>(new ApiResponse<>(false, "Course not found", 404,  null),
                    HttpStatus.NOT_FOUND);

        if(!courseRepository.existsByAuthorAndUuid(currentUser.getUuid(),course))
            return new ResponseEntity<>(new ApiResponse<>(false, "Not allowed", 403,  null),
                    HttpStatus.FORBIDDEN);

        try{
            List quizzes = quizRepository.findAllByCourseId(course);
            logger.info("Quizzes fetched");
            return new ResponseEntity<>(new ApiResponse<>(true, "Quizzes fetched", 200,  quizzes),
                    HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(new ApiResponse<>(false, "Something went wrong", 500,  null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> addQuestion(String quizId, User currentUser, QuizQuestionRequest request) {
        return new ResponseEntity<>(new ApiResponse<>(false, "Not allowed", 403,  request),
                HttpStatus.FORBIDDEN);

    }
}
