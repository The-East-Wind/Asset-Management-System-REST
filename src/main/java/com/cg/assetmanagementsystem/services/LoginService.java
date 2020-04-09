package com.cg.assetmanagementsystem.services;

import com.cg.assetmanagementsystem.entities.Credential;
import com.cg.assetmanagementsystem.entities.Employee;
import com.cg.assetmanagementsystem.exceptions.EmployeeNotFoundException;
import com.cg.assetmanagementsystem.exceptions.LoginException;

public interface LoginService {
	Employee authenticateUser(Credential enteredCredentials) throws LoginException, EmployeeNotFoundException;
}
