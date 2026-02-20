package com.spring.jwt.dao;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@AllArgsConstructor
public class ApiResponse {
    private HttpStatus statusCode;
    private String description;
    private String message;
}
