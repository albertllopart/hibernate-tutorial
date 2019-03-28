package com.albertllopart.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.albertllopart.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//display the students
			System.out.println("\nStudents:");
			displayStudents(theStudents);
			
			//query students: lastName='Doe'
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			//display the students
			System.out.println("\nStudents who have last name of Doe:");
			displayStudents(theStudents);
			
			//query students: lastName='Doe' OR firstName='Daffy'
			theStudents = session.createQuery("from Student s where"
					+ " s.lastName='Doe' OR s.firstName='Dídac'").getResultList();
			
			//display the students
			System.out.println("\nStudents who have last name of Doe OR firstName of Dídac:");
			displayStudents(theStudents);
			
			//query students where email LIKE '%29292@asdfg.com'
			theStudents = session.createQuery("from Student s where"
					+ " s.email LIKE '%29292@asdfg.com'").getResultList();
			
			//display the students
			System.out.println("\nStudents whose email ends with 29292@asdfg.com:");
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			
			factory.close();
			
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
