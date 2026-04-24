package com.spring.student.service;

import com.spring.student.dto.UserPrinciple;
import com.spring.student.entity.Student;
import com.spring.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepo.findByName(username).orElseThrow(() -> new UsernameNotFoundException("Student Not Found: "+username));
        return new UserPrinciple(student);
    }
}
