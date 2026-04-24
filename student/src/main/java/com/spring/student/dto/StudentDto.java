package com.spring.student.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private int id;

    @NonNull
    private String name;

    private int age;

    @NonNull
    private String password;
}
