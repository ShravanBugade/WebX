package com.bugslayer.webx.dao;


import com.bugslayer.webx.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJPAImpl implements EmployeeDAO {
    EntityManager entityManager;

    @Autowired
    public EmployeeDAOJPAImpl(EntityManager em) {
        entityManager=em;
    }

    @Override
    public List<Employee> findALl() {
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
        List<Employee> list = query.getResultList();
//        System.out.println("findAll >> list " + list);
        System.out.println(""+getClass().getSimpleName());
        return list;
    }

    @Override
    public Employee findId(int id) {
        Employee employee=entityManager.find(Employee.class,id);
        return  employee;
    }

    @Override
    public Employee save(Employee employee) {

        Employee dbemEmployee=entityManager.merge(employee);

        return dbemEmployee;
    }

    @Override
    public void deleteById(int id) {

        Employee employee=entityManager.find(Employee.class,id);

         entityManager.remove(employee);
    }
}
