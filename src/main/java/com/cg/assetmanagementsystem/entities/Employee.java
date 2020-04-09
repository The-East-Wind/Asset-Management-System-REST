package com.cg.assetmanagementsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="employee")
public class Employee implements Serializable {
	@Id
	@Column(name="emp_id")
	private Integer employeeId;
	@Column(name="emp_name")
	private String employeeName;
	@Column(name="username")
	private String username;
	@Column(name="emp_dept")
	private String employeeDepartment;
	@Column(name="emp_design")
	private String employeeDesignation;
	public Employee(){
	}
	public Employee(Integer employeeId, String employeeName, String username, String employeeDepartment,
					String employeeDesignation) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.username = username;
		this.employeeDepartment = employeeDepartment;
		this.employeeDesignation = employeeDesignation;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmployeeDepartment() {
		return employeeDepartment;
	}
	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}
	public String getEmployeeDesignation() {
		return employeeDesignation;
	}
	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}
}
