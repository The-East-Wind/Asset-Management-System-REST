package com.cg.assetmanagementsystem.services;

import com.cg.assetmanagementsystem.entities.Employee;
import com.cg.assetmanagementsystem.exceptions.EmployeeNotFoundException;

public interface EmployeeService {
	Employee getEmployeeWithUsername(String username) throws EmployeeNotFoundException;

    Employee getEmployeeWithId(int employeeId) throws EmployeeNotFoundException;
}
