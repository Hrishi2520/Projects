package com.spring.microservice.controller;

import com.spring.microservice.dto.AuthResponse;
import com.spring.microservice.dto.LoginRequest;
import com.spring.microservice.dto.RegisterReq;
import com.spring.microservice.dto.UserRes;
import com.spring.microservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRes> register(@RequestBody RegisterReq request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserRes> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRes> updateUser(
            @PathVariable int id,
            @RequestBody RegisterReq request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }
}