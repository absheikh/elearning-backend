package com.example.rest_api_jwt.services;

import com.example.rest_api_jwt.entities.User;
import com.example.rest_api_jwt.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<?> getUserByUuid(User currentUser) {
        return ResponseEntity.ok(userRepository.findByUuid(currentUser.getUuid()));
    }

}
