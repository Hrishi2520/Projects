package com.spring.student.service;

import com.spring.student.dto.StudentDto;
import com.spring.student.entity.Student;
import com.spring.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public StudentDto create(StudentDto dto) {
        Student st = new Student();
        st.setName(dto.getName());
        st.setAge(dto.getAge());
        st.setPassword(passwordEncoder.encode(dto.getPassword()));
        return convert(studentRepo.save(st));
    }

    public List<StudentDto> getAll() {
        List<Student> students = studentRepo.findAll();
        if (students.isEmpty()) {
            return Collections.emptyList();
        }
        return students
                .stream()
                .map(this::convert)
                .toList();
    }

    private StudentDto convert(Student save) {
        return StudentDto.builder()
                .id(save.getId())
                .name(save.getName())
                .age(save.getAge())
                .password(save.getPassword())
                .build();
    }
}
