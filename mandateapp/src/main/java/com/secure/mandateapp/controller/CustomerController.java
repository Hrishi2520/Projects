package com.secure.mandateapp.controller;

import com.secure.mandateapp.dto.CustomerReq;
import com.secure.mandateapp.dto.CustomerRes;
import com.secure.mandateapp.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class CustomerController {

    private CustomerService service;

    @PostMapping("/save")
    public ResponseEntity<CustomerRes> Save(@RequestBody CustomerReq req) {
        CustomerRes customerRes = service.create(req);
        return new ResponseEntity<>(customerRes, HttpStatus.CREATED);
    }

    @GetMapping("/cust/{ref}")
    public ResponseEntity<CustomerRes> get(@PathVariable String ref) {
        CustomerRes customer = service.getCustomer(ref);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
