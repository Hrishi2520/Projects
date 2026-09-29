package com.example.util;

import java.util.*;
import java.util.stream.Collectors;

public class EMPService {


    public static void create(int id, String name, double sal, String dept, List<Employee> employees) {
        Optional<Employee> optional = employees.stream().filter(e -> e.getId() == id).findFirst();
        if (optional.isPresent()) {
            System.out.println("Employee Already Exist for given Id: "+id+"\n");
        } else {
            Employee employee = new Employee(id, name, sal, dept);
            employees.add(employee);
        }
    }

    public static Employee update(int id, String name, double sal, String dept, List<Employee> employees) {
        Employee emp = employees.stream().filter(e -> e.getId() == id).findFirst().orElseThrow(
                () -> new RuntimeException("Employee Not Found.")
        );
        emp.setId(id);
        emp.setName(name);
        emp.setSal(sal);
        emp.setDepartment(dept);
        return emp;
    }
    public static Employee find(String name, List<Employee> employees) {
        return employees.stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Employee Not Found.");
                    return null;
                });
    }

    public static boolean delete(String name, List<Employee> employees) {
        Employee employee = employees.stream().filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseGet(
                        () -> {
                            System.out.println("Employee Not Found.");
                            return null;
                        }
                );
        if (employee == null) {
            return  false;
        }
        employees.remove(employee);
        return true;
    }

    public static Employee highestSalary(List<Employee> employees) {
        return employees.stream().max(Comparator.comparing(Employee::getSal))
                .orElseThrow(() -> new RuntimeException("Employee Search Failed."));
    }

    public static Employee secondHighestSalary(List<Employee> employees) {
        Optional<Employee> first = employees.stream().sorted(Comparator.comparing(Employee::getSal).reversed()).skip(1).findFirst();
        return first.orElse(null);
    }

    public static Map<String, List<Employee>> findEmpByDept(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }


}
