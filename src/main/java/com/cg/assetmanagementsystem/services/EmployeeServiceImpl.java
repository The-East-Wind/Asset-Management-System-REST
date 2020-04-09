package com.cg.assetmanagementsystem.services;

import com.cg.assetmanagementsystem.daos.EmployeeDAO;
import com.cg.assetmanagementsystem.entities.Employee;
import com.cg.assetmanagementsystem.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeDAO employeeDAO;
	public EmployeeServiceImpl(){
	}
	@Override
	public Employee getEmployeeWithUsername(String username) throws EmployeeNotFoundException{
		Employee employeeWithId = employeeDAO.findEmployeeDTOByUsername(username);
		if(employeeWithId == null)
			throw new EmployeeNotFoundException("The Employee Id you have entered is invalid!Please enter a valid Employee Id.");
		return employeeWithId;
	}

	@Override
	public Employee getEmployeeWithId(int employeeId) throws EmployeeNotFoundException {
		Optional<Employee> employeeWithId = employeeDAO.findById(employeeId);
		if(!employeeWithId.isPresent())
			throw new EmployeeNotFoundException("The Employee Id you have entered is invalid!Please enter a valid Employee Id.");
		return employeeWithId.get();
	}
}
