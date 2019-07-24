package com.myapplication.restfulwebservice.controllers;

import com.myapplication.restfulwebservice.Entities.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value="restfulwebservice")
public class employeeController {

    private ArrayList<Employee> listOfEmployee = new ArrayList<>();

    @GetMapping(value="/getEmployee/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        for (Employee employee : listOfEmployee) {
            if (employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    @GetMapping(value="/getAllEmployees")
    public ArrayList<Employee> getAllEmployees() {
        return listOfEmployee;
    }

    @PostMapping(value="/addEmployee/{firstName}/{lastName}/{employeeId}")
    public Employee addEmployee(@PathVariable String firstName, @PathVariable String lastName, @PathVariable int employeeId) {
        Employee newEmployee = new Employee(firstName, lastName, employeeId);
        listOfEmployee.add(newEmployee);
        return newEmployee;
    }

    @PutMapping(value="updateEmployee/{employeeId}/{firstName}/{lastName}")
    public Employee updateEmployeeById(@PathVariable int employeeId, @PathVariable String firstName, @PathVariable String lastName) {
        Employee oldEmployee = getEmployeeById(employeeId);
        Employee newEmployee = new Employee(firstName, lastName, employeeId);
        listOfEmployee.set(listOfEmployee.indexOf(oldEmployee), newEmployee);
        return newEmployee;
    }

    @DeleteMapping(value="/deleteEmployee/{employeeId}")
    public Employee deleteEmployeeById(@PathVariable int employeeId) {
        for (Employee employee : listOfEmployee) {
            if (employee.getEmployeeId() == employeeId) {
                listOfEmployee.remove(employee);
                return employee;
            }
        }
        return null;
    }

    @PostMapping(value="/addEmployeeByEmployeeObject", consumes = "application/json", produces = "application/json")
    public Employee addEmployeeByEmployeeObject(@RequestBody Employee newEmployee) {
        listOfEmployee.add(newEmployee);
        return newEmployee;
    }

}
