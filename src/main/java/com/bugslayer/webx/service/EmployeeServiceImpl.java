package com.bugslayer.webx.service;


import com.bugslayer.webx.dao.EmployeeRepository;
import com.bugslayer.webx.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements  EmployeeService{

//    private EmployeeDAO employeeDAO;

    private EmployeeRepository employeeRepository;


    @Autowired
    EmployeeServiceImpl(EmployeeRepository employeeRepositorym )
    {
        employeeRepository=employeeRepositorym;
    }

    @Override
    public List<Employee> findAll() {
        System.out.println(""+getClass().getSimpleName());
        return employeeRepository.findAll();
    }

    @Override
    public Employee findId(int id) {
        Optional<Employee> byId = employeeRepository.findById(id);
        Employee employee=null;
        if (byId.isPresent()){
            employee=byId.get();
        }else
        {
            throw new RuntimeException("data not found for id ");
        }
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    @Override
    
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
