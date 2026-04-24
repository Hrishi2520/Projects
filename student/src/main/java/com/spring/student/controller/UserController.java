package com.spring.student.controller;

import com.spring.student.dto.LoginReq;
import com.spring.student.dto.LoginRes;
import com.spring.student.dto.UserDto;
import com.spring.student.dto.UserPrinciple;
import com.spring.student.entity.Student;
import com.spring.student.repo.StudentRepo;
import com.spring.student.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public UserDto signup(@RequestBody UserDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setPassword(passwordEncoder.encode(dto.getPassword()));
        student.setAge(dto.getAge());
        return convertTo(studentRepo.save(student));

    }

//    @PostMapping("/login")
//    public LoginRes login(@RequestBody LoginReq req) {
//        userService.loadUserByUsername(req.userName());
//        Authentication authentication = new UsernamePasswordAuthenticationToken(req.userName(), req.password());
//        if (authentication.isAuthenticated()) {
//            return new LoginRes("Login Success", req.userName());
//        } else {
//            throw new RuntimeException("Invalid Credentials");
//        }
//    }



    private UserDto convertTo(Student save) {
        return UserDto
                .builder()
                .id(save.getId())
                .name(save.getName())
                .password(save.getPassword())
                .age(save.getAge())
                .build();
    }
}
