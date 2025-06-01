package com.bugslayer.webx.dao;

import com.bugslayer.webx.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
