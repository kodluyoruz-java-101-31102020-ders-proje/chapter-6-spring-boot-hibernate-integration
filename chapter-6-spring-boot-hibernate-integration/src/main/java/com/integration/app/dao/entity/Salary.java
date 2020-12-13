package com.integration.app.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "salaries")
public class Salary {

	@EmbeddedId
	private SalaryPK id;
	
	@Column(name = "salary")
	private Long salary;
	
	@Column(name = "to_date")
	@Temporal(TemporalType.DATE)
	private Date toDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_no", insertable = false, updatable = false)
	private Employee employee;

	
	public SalaryPK getId() {
		return id;
	}

	public void setId(SalaryPK id) {
		this.id = id;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
