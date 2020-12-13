package com.integration.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.integration.app.dao.EmployeeDao;
import com.integration.app.dao.entity.Employee;

@Repository("employeeHibernateDao")
public class EmployeeHibernateDao implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<Employee> getAll() {
		
		Session session = sessionFactory.openSession(); 
		
		Query<Employee> query = session.createQuery("SELECT emp FROM Employee emp", Employee.class);
		List<Employee> result = query.getResultList();
		
		session.close();
		return result;
	}
	
	public Long findMaxEmpNo() {
		
		Session session = sessionFactory.openSession(); 
		Query<Long> query = session.createQuery("SELECT MAX(emp.empNo) FROM Employee emp", Long.class);
		Long maxEmpNo = query.getSingleResult();
		session.close();
		
		return maxEmpNo;
	}
	
	public Employee findByEmpNo(Long empNo) {
		
		Session session = sessionFactory.openSession(); 
		Query<Employee> query = session.createQuery("SELECT emp FROM Employee emp WHERE emp.empNo = :empId", Employee.class);
		query.setParameter("empId", empNo);
		
		Employee employee = query.getSingleResult();
		session.close();
		
		return employee;
	}
	
	public Employee save(Employee employee) {
		
		Session session = sessionFactory.openSession(); 
		session.save(employee);
		session.close();
		return employee;
	}
	
	public Employee update(Employee employee) {
		
		Session session = sessionFactory.openSession(); 
		session.update(employee);
		session.close();
		return employee;
	}
	
	
	/*
	 * Transactional management in DAO, 
	 * Biz transaction yönetimini Service katmanında yapmak istiyoruz
	 * ve bu sorumluluğu yazılımcıdan alıp bir Aspect ile yönetiyoruz.
	public Employee save(Employee employee) {
		
		Session session = sessionFactory.openSession(); 

		try {
			session.beginTransaction();
			
			employee = session.save(employee);
			
			session.getTransaction().commit();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
		}
		finally {
			session.close();
		}
		
		return employee;
	}
	
	public Employee update(Employee employee) {
		
		Session session = sessionFactory.openSession(); 

		try {
			session.beginTransaction();
			
			employee = session.update(employee);
			
			session.getTransaction().commit();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
		}
		finally {
			session.close();
		}
		return employee;
	}
	*/

}
