package com.cg.assetmanagementsystem.controller;

import com.cg.assetmanagementsystem.entities.Employee;
import com.cg.assetmanagementsystem.exceptions.EmployeeNotFoundException;
import com.cg.assetmanagementsystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(
            value="/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public Employee getEmployeeWithId(@PathVariable("id") Integer employeeId) throws EmployeeNotFoundException {
        return employeeService.getEmployeeWithId(employeeId);
    }
}
