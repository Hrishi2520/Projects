package com.spring.microservice.service;

import com.spring.microservice.config.JwtUtil;
import com.spring.microservice.dto.AuthResponse;
import com.spring.microservice.dto.LoginRequest;
import com.spring.microservice.dto.RegisterReq;
import com.spring.microservice.dto.UserRes;
import com.spring.microservice.entity.Role;
import com.spring.microservice.entity.User;
import com.spring.microservice.repository.RoleRepo;
import com.spring.microservice.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private JwtUtil jwtUtil;

    private PasswordEncoder encoder;

    public UserRes register(RegisterReq req) {

        log.info("Registering user with email: {}", req.getEmail());

        // ✅ Check if user already exists
        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // ✅ Get default role
        Role userRole = roleRepo.findByName(req.getRole())
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));

        user.setRoles(Set.of(userRole));

        User saved = userRepo.save(user);

        return mapToResponse(saved);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Password check
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        Set<String> roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        String token = jwtUtil.generateToken(user.getEmail(), roles);

        return new AuthResponse(token);
    }
    // ✅ Get User Profile
    public UserRes getUser(int id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    // ✅ Update User
    public UserRes updateUser(int id, RegisterReq request) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        User updated = userRepo.save(user);

        return mapToResponse(updated);
    }

    private UserRes mapToResponse(User saved) {
        Set<String> roles = saved.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        return UserRes.builder()
                .id(saved.getId())
                .name(saved.getName())
                .email(saved.getEmail())
                .roles(roles)
                .build();
    }
}
