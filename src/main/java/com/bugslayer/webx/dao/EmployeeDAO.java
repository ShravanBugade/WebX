package com.bugslayer.webx.dao;


import com.bugslayer.webx.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findALl();

    Employee findId(int id );

    Employee save(Employee employee );

    void deleteById(int id );

}
