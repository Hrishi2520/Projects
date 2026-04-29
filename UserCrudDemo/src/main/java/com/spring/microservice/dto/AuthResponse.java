package com.spring.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private String type;
    private String email;
    private Set<String> roles;
}