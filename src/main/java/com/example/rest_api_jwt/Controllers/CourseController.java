package com.example.rest_api_jwt.Controllers;

import com.example.rest_api_jwt.Security.CurrentUser;
import com.example.rest_api_jwt.entities.User;
import com.example.rest_api_jwt.pojos.CourseRequest;
import com.example.rest_api_jwt.services.CourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/new")
    public ResponseEntity<?> addCourse(@CurrentUser User currentUser,  @RequestBody CourseRequest request) {
        return courseService.addCourse(currentUser,request);
    }

    //my courses
    @GetMapping("/my-courses")
    public ResponseEntity<?> getMyCourses(@CurrentUser User currentUser) {
        return courseService.getMyCourses(currentUser);
    }
}
