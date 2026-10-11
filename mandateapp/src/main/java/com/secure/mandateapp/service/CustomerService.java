package com.secure.mandateapp.service;

import com.secure.mandateapp.dto.CustomerReq;
import com.secure.mandateapp.dto.CustomerRes;
import com.secure.mandateapp.entity.Customer;
import com.secure.mandateapp.repo.CustomerRepo;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepo customerRepo;

    private static Logger log = LoggerFactory.getLogger(CustomerService.class);

    public CustomerRes create(CustomerReq req) {

        Customer cust = new Customer();
        cust.setCustomerName(req.getCustomerName());
        cust.setCustomerRef(req.getCustomerName()+ LocalDate.now());
        cust.setStatus("Created");

        log.info("Saving Customer Details ...");
        Customer saved = customerRepo.save(cust);
        log.info("Customer Created Successful...");
        return CustomerRes.builder()
                .customerId(saved.getId())
                .customerRef(saved.getCustomerRef())
                .customerName(saved.getCustomerName())
                .status(saved.getStatus())
                .build();
    }

    public CustomerRes getCustomer(String custRef) {
        Optional<Customer> byCustomerRef = customerRepo.findByCustomerRef(custRef);

        if (byCustomerRef.isEmpty() || byCustomerRef == null) {
            log.info("Customer Not Exist...");
            return null;
        }
        Customer customer = byCustomerRef.get();
        log.info("Customer Details Fetched..");
        return CustomerRes.builder()
                .customerId(customer.getId())
                .customerRef(customer.getCustomerRef())
                .customerName(customer.getCustomerName())
                .status(customer.getStatus())
                .build();

    }
}
