package com.integration.app.console.runner;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.integration.app.dao.entity.Employee;
import com.integration.app.service.EmployeeService;

@Component
public class ConsoleRunner implements CommandLineRunner {

	@Autowired
	@Qualifier("employeeServiceImpl")
	private EmployeeService employeeService;
	
	
	public void run(String... args) throws Exception {
		
		// listAllEmployees();
		insertEmployee();
		// updateEmployee();
	}
	
	private void updateEmployee() {
		
		Long empNo = 10056l;
		Employee employee = employeeService.findByEmpNo(empNo);
		employee.setName("Ayşe1");
		employee.setLastName("Ertaç1");
		
		employeeService.update(employee);
	}
	
	private void insertEmployee() {
		
		Long maxEmpNo = employeeService.findMaxEmpNo();
		
		Employee newEmp = new Employee();
		newEmp.setEmpNo(maxEmpNo + 1);
		newEmp.setName("Fatma");
		newEmp.setLastName("Ayçiçek");
		newEmp.setGender("F");
		newEmp.setBirthDate(new Date());
		newEmp.setHireDate(new Date());
		
		employeeService.save(newEmp);
	}
	
	private void listAllEmployees() {
		
		List<Employee> employeeList = employeeService.getAll();
		for(Employee emp : employeeList) {
			System.out.println(emp.getName() + " " + emp.getLastName());
		}
	}
}
