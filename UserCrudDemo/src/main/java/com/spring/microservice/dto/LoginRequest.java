package com.spring.microservice.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}