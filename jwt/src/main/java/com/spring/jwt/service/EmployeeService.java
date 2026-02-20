package com.spring.jwt.service;

import com.spring.jwt.dao.EmployeeReq;
import com.spring.jwt.dao.EmployeeResponse;
import com.spring.jwt.entity.Employee;
import com.spring.jwt.exception.EmployeeNotFound;
import com.spring.jwt.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo  repo;

    public EmployeeResponse createEmployee(EmployeeReq req) {
        System.out.printf("name: %s, designation: %s, doj: %s", req.name(), req.designation(), req.doj().toString());
        Employee employee = convertToEntity(req);
        return convertToRes(repo.save(employee));
    }

    public EmployeeResponse update(int empId, EmployeeReq req) {
        Optional<Employee> existing = repo.findById(empId);
        if (existing.isEmpty()) {
            throw new EmployeeNotFound("Employee Not Found.");
        }
        Employee employee = existing.get();

        employee.setName(req.name());
        employee.setDesignation(req.designation());
        employee.setDoj(req.doj());

        return convertToRes(repo.save(employee));
    }

    public String delete(int empId) {
        Employee existing = repo.findById(empId)
                .orElseThrow(() -> new EmployeeNotFound("Employee Not Found."));
        repo.delete(existing);
        return String.format("Employee with id: %d is deleted", empId);
    }

    public EmployeeResponse getById(int empId) {
        Employee existing = repo.findById(empId)
                .orElseThrow(() -> new EmployeeNotFound("Employee Not Found."));
        return convertToRes(existing);
    }

    public List<EmployeeResponse> getAll() {
        List<Employee> employees = repo.findAll();
        return employees.stream().map(this::convertToRes).toList();
    }

    private EmployeeResponse convertToRes(Employee saved) {
        System.out.println(saved);
        System.out.println("-".repeat(30));
        return EmployeeResponse
                .builder()
                .empId(saved.getEmpId())
                .name(saved.getName())
                .designation(saved.getDesignation())
                .doj(saved.getDoj())
                .build();
    }

    private Employee convertToEntity(EmployeeReq req) {
        Employee employee = new Employee();
        employee.setName(req.name());
        employee.setDesignation(req.designation());
        employee.setDoj(req.doj());
        return employee;
    }
}
