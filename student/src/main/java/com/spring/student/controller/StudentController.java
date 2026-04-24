package com.spring.student.controller;

import com.spring.student.dto.StudentDto;
import com.spring.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public StudentDto create(@RequestBody StudentDto dto) {
        return studentService.create(dto);
    }

    @GetMapping
    public List<StudentDto> getAll(){
        return studentService.getAll();
    }

    @GetMapping("/ok")
    public String ok(){
        return "hoii";
    }

}
