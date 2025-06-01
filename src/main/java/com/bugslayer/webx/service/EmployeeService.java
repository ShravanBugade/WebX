package com.bugslayer.webx.service;


import com.bugslayer.webx.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findId(int id );

    Employee save(Employee employee );

    void deleteById(int id );

}
