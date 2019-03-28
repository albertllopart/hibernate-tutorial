package com.albertllopart.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.albertllopart.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//create the student object
			System.out.println("Creating a new student object...");
			Student theStudent = new Student("Donald", "Duck", "donald@asdfg.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student...");
			session.save(theStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			//NEW CODE
			
			//find out the student's id: primary key
			System.out.println("Saved student. Generated id: " + theStudent.getId());
			
			//get a new session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + theStudent.getId());
			
			Student myStudent = session.get(Student.class, theStudent.getId());
			
			System.out.println("Get complete: " + myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			
			factory.close();
			
		}
	}

}
