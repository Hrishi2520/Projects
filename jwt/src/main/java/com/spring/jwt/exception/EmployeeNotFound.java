package com.spring.jwt.exception;

public class EmployeeNotFound extends RuntimeException{

    public EmployeeNotFound(String ex) {
        super(ex);
    }
}
