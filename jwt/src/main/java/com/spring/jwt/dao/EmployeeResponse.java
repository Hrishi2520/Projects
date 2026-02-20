package com.spring.jwt.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class EmployeeResponse {
    private int empId;

    private String name;

    private String designation;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate doj;
}
