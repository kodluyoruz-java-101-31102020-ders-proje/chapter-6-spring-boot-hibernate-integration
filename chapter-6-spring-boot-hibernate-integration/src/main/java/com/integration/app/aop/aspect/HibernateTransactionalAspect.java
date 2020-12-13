package com.integration.app.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.integration.app.aop.annotation.HibernateTransactional;

@Aspect
@Component
public class HibernateTransactionalAspect {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Around("@annotation(hTransactional)")
	public Object execute(ProceedingJoinPoint joinPoint, HibernateTransactional hTransactional) throws Throwable {
		
		if(!hTransactional.active()) {
			return joinPoint.proceed();
		}
		
		Session session = sessionFactory.openSession(); 
		Object result = null;
		
		try {
			session.beginTransaction();
			
			result = joinPoint.proceed();
			
			session.merge(result);
			
			session.getTransaction().commit();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
		}
		finally {
			session.close();
		}
		
		return result;
	}
	
}
