package com.cg.assetmanagementsystem.daos;

import com.cg.assetmanagementsystem.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDAO extends CrudRepository<Employee,Integer> {
	Employee findEmployeeDTOByUsername(String username);
}
