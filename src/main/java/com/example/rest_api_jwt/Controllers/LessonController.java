package com.example.rest_api_jwt.Controllers;

import com.example.rest_api_jwt.Security.CurrentUser;
import com.example.rest_api_jwt.entities.User;
import com.example.rest_api_jwt.pojos.LessonRequest;
import com.example.rest_api_jwt.services.LessonService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lessons")
@SecurityRequirement(name = "bearerAuth")
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/addNew/{courseId}")
    public ResponseEntity<?> addLesson(@PathVariable("courseId") String course, @CurrentUser User currentUser, @RequestBody LessonRequest request) {
        return lessonService.addLesson(course,currentUser, request);
    }


}
