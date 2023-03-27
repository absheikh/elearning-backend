package com.example.rest_api_jwt.Controllers;

import com.example.rest_api_jwt.Security.CurrentUser;
import com.example.rest_api_jwt.entities.User;
import com.example.rest_api_jwt.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {


    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private  final UserService userService;


    @GetMapping("/user")
    public ResponseEntity<?> getUser(@CurrentUser User currentUser) {
        return userService.getUserByUuid(currentUser);
    }



}