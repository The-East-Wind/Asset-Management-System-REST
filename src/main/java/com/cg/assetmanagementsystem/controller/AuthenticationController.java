package com.cg.assetmanagementsystem.controller;

import com.cg.assetmanagementsystem.entities.Credential;
import com.cg.assetmanagementsystem.entities.Employee;
import com.cg.assetmanagementsystem.exceptions.EmployeeNotFoundException;
import com.cg.assetmanagementsystem.exceptions.LoginException;
import com.cg.assetmanagementsystem.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/login")
public class AuthenticationController {
    @Autowired
    private LoginService loginService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(
            headers = "Accept=application/json",
            produces = "application/json"
    )
    @ResponseBody
    public Employee authenticateUser(@RequestBody Credential enteredCredentials) throws LoginException, EmployeeNotFoundException {
        return loginService.authenticateUser(enteredCredentials);
    }
}
