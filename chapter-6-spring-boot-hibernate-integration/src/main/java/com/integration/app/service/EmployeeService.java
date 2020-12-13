package com.integration.app.service;

import java.util.List;

import com.integration.app.dao.entity.Employee;

public interface EmployeeService {

	public List<Employee> getAll();
	public Long findMaxEmpNo();
	public Employee findByEmpNo(Long empNo);
	public Employee save(Employee employee);
	public Employee update(Employee employee);
}
