package com.hazzum.hibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hazzum.hibernateDemo.entities.Course;
import com.hazzum.hibernateDemo.entities.Instructor;
import com.hazzum.hibernateDemo.entities.InstructorDetail;
import com.hazzum.hibernateDemo.entities.Review;
import com.hazzum.hibernateDemo.entities.Student;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Student.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// create the objects
			Student tempStudent1 = new Student("Chad", "Darby", "darby@luv2code.com");
			Student tempStudent2 = new Student("Donald", "Duck", "theDonald@luv2code.com");
			Course tempCourse = new Course("Pacman - How to Score One Million Points");
			
			
			// start a transaction
			session.beginTransaction();
			System.out.println("Joining student #1..." + tempStudent1);
			tempStudent1.joinCourse(tempCourse);
			System.out.println("Joining student #2..." + tempStudent2);
			tempStudent2.joinCourse(tempCourse);
			
			// session save
			System.out.println("Saving course: " + tempCourse);
			session.save(tempCourse);
			System.out.println("Saving student: " + tempStudent1);
			System.out.println("Saving student: " + tempStudent2);
			session.save(tempStudent1);
			session.save(tempStudent2);
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}





