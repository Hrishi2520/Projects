package com.spring.jwt.controller;

import com.spring.jwt.constant.Constant;
import com.spring.jwt.dao.EmployeeReq;
import com.spring.jwt.dao.ResponseWithData;
import com.spring.jwt.dao.EmployeeResponse;
import com.spring.jwt.dao.ApiResponse;
import com.spring.jwt.exception.EmployeeNotFound;
import com.spring.jwt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createEmp(@RequestBody EmployeeReq req) {
        EmployeeResponse empRes = service.createEmployee(req);
        ResponseWithData res = new ResponseWithData(HttpStatus.CREATED, Constant.SUCCESS, Constant.EMPLOYEE_CREATED, empRes);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<ApiResponse> updateEmp(@PathVariable int empId, @RequestBody EmployeeReq req) {
        try{
            EmployeeResponse empRes = service.update(empId, req);
            ResponseWithData res = new ResponseWithData(HttpStatus.OK, Constant.SUCCESS, Constant.EMPLOYEE_UPDATED, empRes);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (EmployeeNotFound ex) {
            ApiResponse res = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, Constant.FAILED, Constant.EMPLOYEE_UPDATE_ERROR);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<ApiResponse> deleteEmp(@PathVariable int empId) {
        try {
            service.delete(empId);
            ApiResponse res = new ApiResponse(HttpStatus.OK, Constant.SUCCESS, Constant.EMPLOYEE_DELETED);
            return ResponseEntity.ok(res);
        } catch (EmployeeNotFound ex) {
            ApiResponse res = new ApiResponse(HttpStatus.NOT_FOUND, Constant.FAILED, Constant.EMPLOYEE_DELETED_ERROR);
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{empId}")
    public ResponseEntity<ApiResponse> getEmployee(@PathVariable int empId) {
        try {
            EmployeeResponse empRes = service.getById(empId);
            ResponseWithData res = new ResponseWithData(HttpStatus.OK, Constant.SUCCESS, Constant.EMPLOYEE_DATA_FETCHED, empRes);
            return ResponseEntity.ok(res);
        }catch (EmployeeNotFound ex) {
            ApiResponse res = new ApiResponse(HttpStatus.NOT_FOUND, Constant.FAILED, Constant.EMPLOYEE_DATA_RETRIEVAL_FAILED);
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getEmployees() {
        List<EmployeeResponse> employees = service.getAll();
        if (!employees.isEmpty()) {
            ResponseWithData res = new ResponseWithData(HttpStatus.OK, Constant.SUCCESS, Constant.EMPLOYEE_DATA_FETCHED , employees);
            return ResponseEntity.ok(res);
        } else {
            ApiResponse res = new ApiResponse(HttpStatus.OK, Constant.SUCCESS, Constant.EMPLOYEE_DATA_EMPTY);
            return ResponseEntity.ok(res);
        }
    }
}
