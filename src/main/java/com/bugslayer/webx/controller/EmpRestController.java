package com.bugslayer.webx.controller;


import com.bugslayer.webx.entity.Employee;
import com.bugslayer.webx.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.apache.tomcat.jni.SSLConf.apply;

@RestController
@RequestMapping("api")

public class EmpRestController {

   private EmployeeService employeeService;
    private  ObjectMapper objectMapper;

    @Autowired
    EmpRestController(EmployeeService employeeService,ObjectMapper objectMapper){
        this.employeeService=employeeService;
        this.objectMapper=objectMapper;

    }

    @GetMapping("/employees")
    private List<Employee>  findAllEmpData(){
        System.out.println(""+getClass().getSimpleName());
        return  employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    private Employee  findId(@PathVariable  int id){
        System.out.println(""+getClass().getSimpleName());
        Employee employee= employeeService.findId(id);

        if (employee==null){
            throw new RuntimeException( "Id not Found "+id);
        }
        return employee;
    }

    @PostMapping("/employees")
    private Employee addEmployee(@RequestBody Employee employee){

        employee.setId(0);
        Employee addEmployee=employeeService.save(employee);
        return addEmployee;
    }


    @PutMapping("/employees")
    private Employee updateEmployee(@RequestBody Employee employee){
        System.out.println("employee "+employee);
        Employee addEmployee=employeeService.save(employee);

        System.out.println("Updated  put : "+addEmployee);
        return addEmployee;
    }

    @PatchMapping("/employees/{id}")
    private Employee  findId(@PathVariable  int id, @RequestBody Map<String,Object> patchload){
        System.out.println(""+getClass().getSimpleName());
        Employee employee= employeeService.findId(id);

        if (employee==null){
            throw new RuntimeException( "Id not Found "+id);
        }

        if (patchload.containsKey("id")){
            throw new RuntimeException( "Employee id not allowed in request Body "+id);
        }

        Employee patchedEmployee=apply(patchload,employee);
        Employee dbemp=employeeService.save(patchedEmployee);

        return employee;
    }

    private Employee apply(Map<String, Object> patchload, Employee employee) {

        // Convert the existing employee to an ObjectNode (JSON tree)
        ObjectNode empNode = objectMapper.convertValue(employee, ObjectNode.class);

        // Convert the patch map to an ObjectNode
        ObjectNode patchNode = objectMapper.convertValue(patchload, ObjectNode.class);

        // Merge patchNode into empNode
        empNode.setAll(patchNode);

        // Convert the merged ObjectNode back to an Employee object
        return objectMapper.convertValue(empNode, Employee.class);

    }

    @DeleteMapping("/employees/{id}")
    private void removeEmp(@PathVariable("id") int id ){

        Employee employee= employeeService.findId(id);

        if (employee==null){
            throw new RuntimeException( "Id not Found "+id);
        }
        employeeService.deleteById(id);
    }


}
