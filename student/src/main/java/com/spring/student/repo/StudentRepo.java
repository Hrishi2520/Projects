package com.spring.student.repo;

import com.spring.student.entity.Student;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    Optional<Student> findByName(@NonNull String name);
}
