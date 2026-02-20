package com.spring.jwt.dao;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class ResponseWithData extends ApiResponse {
    private Object data;

    public ResponseWithData(HttpStatus statusCode, String description, String message, Object data) {
        super(statusCode, description, message);
        this.data = data;
    }
}