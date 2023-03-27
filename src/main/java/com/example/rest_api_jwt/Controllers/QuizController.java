package com.example.rest_api_jwt.Controllers;

import com.example.rest_api_jwt.Security.CurrentUser;
import com.example.rest_api_jwt.entities.User;
import com.example.rest_api_jwt.pojos.QuizQuestionRequest;
import com.example.rest_api_jwt.pojos.QuizRequest;
import com.example.rest_api_jwt.services.QuizService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz")
@SecurityRequirement(name = "bearerAuth")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("new/{courseId}")
    public ResponseEntity<?> addQuiz(@PathVariable("courseId") String course, @CurrentUser User currentUser, @RequestBody QuizRequest request) {
        return quizService.addQuiz(course,currentUser, request);
    }
    @GetMapping("all/{courseId}")
    public ResponseEntity<?> getQuizzes(@PathVariable("courseId") String course, @CurrentUser User currentUser) {
        return quizService.getQuizzes(course,currentUser);
    }

    @PostMapping("{quizId}/addQuestions")
    public ResponseEntity<?> addQuestion(@PathVariable String quizId, @CurrentUser User currentUser, @RequestBody QuizQuestionRequest request){
        return quizService.addQuestion(quizId, currentUser, request);
    }




}
