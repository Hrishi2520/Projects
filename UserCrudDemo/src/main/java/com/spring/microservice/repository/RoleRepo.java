package com.spring.microservice.repository;

import com.spring.microservice.entity.Role;
import com.spring.microservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
}
