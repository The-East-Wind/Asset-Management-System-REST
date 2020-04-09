package com.cg.assetmanagementsystem.services;

import com.cg.assetmanagementsystem.daos.LoginDAO;
import com.cg.assetmanagementsystem.entities.Credential;
import com.cg.assetmanagementsystem.entities.Employee;
import com.cg.assetmanagementsystem.exceptions.EmployeeNotFoundException;
import com.cg.assetmanagementsystem.exceptions.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDAO loginDAO;
	@Autowired
	private EmployeeService employeeService;
	public LoginServiceImpl(){
	}
	@Override
	public Employee authenticateUser(Credential enteredCredential) throws LoginException, EmployeeNotFoundException {
		Optional<Credential> existingCredential = loginDAO.findById(enteredCredential.getUsername());
		if(existingCredential.isPresent()){
			if(existingCredential.get().getPassword().equals(enteredCredential.getPassword())){
				return employeeService.getEmployeeWithUsername(enteredCredential.getUsername());
			}
			else {
				throw new LoginException("The password you entered is incorrect! Please check the password");
			}
		}
		else{
			throw new LoginException("Username does not exist! Please check the username you entered");
		}
	}
}
