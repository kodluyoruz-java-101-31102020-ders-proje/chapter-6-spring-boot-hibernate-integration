package com.integration.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.integration.app.aop.annotation.HibernateTransactional;
import com.integration.app.dao.EmployeeDao;
import com.integration.app.dao.entity.Employee;
import com.integration.app.service.EmployeeService;

@Service("employeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	@Qualifier("employeeHibernateDao")
	private EmployeeDao employeeHibernateDao;
	
	
	public List<Employee> getAll() {
		
		return employeeHibernateDao.getAll();
	}

	@Override
	public Long findMaxEmpNo() {
		return employeeHibernateDao.findMaxEmpNo();
	}

	@Override
	public Employee findByEmpNo(Long empNo) {
		
		return employeeHibernateDao.findByEmpNo(empNo);
	}
	
	
	@HibernateTransactional(active = true)
	@Override
	public Employee save(Employee employee) {
		
		Employee insertedEmp = employeeHibernateDao.save(employee);
		return insertedEmp;
	}

	@HibernateTransactional(active = true)
	@Override
	public Employee update(Employee employee) {
		
		Employee modifiedEmp = employeeHibernateDao.update(employee);
		return modifiedEmp;
	}
}
