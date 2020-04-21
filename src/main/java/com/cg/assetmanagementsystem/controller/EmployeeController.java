package com.cg.assetmanagementsystem.controller;

import com.cg.assetmanagementsystem.entities.Employee;
import com.cg.assetmanagementsystem.exceptions.EmployeeNotFoundException;
import com.cg.assetmanagementsystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(
            value="/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    @ResponseBody
    public Employee getEmployeeWithId(@PathVariable("id") Integer employeeId) throws EmployeeNotFoundException {
        return employeeService.getEmployeeWithId(employeeId);
    }
}
