package com.secure.mandateapp.repo;

import com.secure.mandateapp.dto.CustomerRes;
import com.secure.mandateapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByCustomerRef(String custRef);
}
