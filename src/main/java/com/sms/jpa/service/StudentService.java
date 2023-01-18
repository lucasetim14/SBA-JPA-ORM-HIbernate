package com.sms.jpa.service;

import java.util.List;
import java.util.Scanner;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.etim.sms.jpa.entitymodels.Course;
import com.etim.sms.jpa.entitymodels.Student;
import com.mysql.cj.Query;
import com.sms.jpa.dao.StudentDao;
import com.sms.util.SmsHibernateUtil;

public class StudentService extends SmsHibernateUtil implements StudentDao {
	
	Scanner input = new Scanner(System.in);

	@Override
	public List<Student> getAllStudents() {
		Session session = SmsHibernateUtil.getConnection();
		Transaction tx = session.beginTransaction();
		String hql = "FROM Student";
		TypedQuery<Student> qry = session.createQuery(hql, Student.class);
		List<Student> studentList = qry.getResultList();
		
		for(Student student : studentList) {
			System.out.println(student.toString());
		}
		tx.commit();
		session.close();
		return studentList;
	}

	@Override
	public Student getStudentByEmail(String email) {
		Session session = SmsHibernateUtil.getConnection();
		
		String hql = "FROM Student s WHERE s.email = :email";
		TypedQuery<Student> query = session.createQuery(hql, Student.class);
		query.setParameter("email", email);
		
		Student rs = query.getSingleResult();
		System.out.println(rs.getsName());
		session.close();
		return rs;
	}

	@Override
	public boolean validateStudent(String email, String pass) {
		boolean vBool;
		Session session = SmsHibernateUtil.getConnection();
		String hqlOne = "From Student s Where s.email =:email";
		TypedQuery<Student> qry1 = session.createQuery(hqlOne, Student.class);
		qry1.setParameter("email",email);
		
		Student student= qry1.getSingleResult();
		
		if (student.getsEmail().equals(email) && student.getsPass().equals(pass)) {
			vBool = true;
		}else {
			vBool = false;
		}
		session.close();
		return vBool;
		
	}


	@Override
	public Course registerStudentToCourse(Student student, int id) {
		Session session = SmsHibernateUtil.getConnection();
		Transaction tx = session.beginTransaction();
		CourseService cs = new CourseService();
		List<Course> sCourses = cs.getAllCourses();
		Course c = sCourses.get(id);
		List<Course> courseList = student.getsCourses();
		courseList.add(c);
		session.save(c);
		student.setsCourses(courseList);
		session.save(student);
		System.out.println("Here is your new academic schedule\n");
		for(Course crs :courseList)
			System.out.println(crs.toString());
		tx.commit();
		session.close();
		return c;
	}

	@Override
	public List<Course> getStudentCourses(String email) {
		Session session = SmsHibernateUtil.getConnection();
		Transaction tx = session.beginTransaction();
		Student student = (Student) session.get(Student.class, email);
		List<Course> courseList = student.getsCourses();
		 
		tx.commit();
		session.close();
		return courseList;
		
		
	}
	public void login () {
		Session session = SmsHibernateUtil.getConnection();
//		Transaction t = session.beginTransaction();
		System.out.println("Welcome to the Student Management Services!");
		System.out.println("Please enter your email");
		String email = input.nextLine();
		System.out.println("Please enter your password");
		String password = input.nextLine();
		
		
			if (validateStudent(email, password)) {
				String hql = "From Student s Where s.email =:email";
				TypedQuery<Student> qry = session.createQuery(hql, Student.class);
				qry.setParameter("email",email);
				Student student= qry.getSingleResult();
				StudentService ss1 = new StudentService();
				System.out.println(" Hello, " + student.getsName()+ ". Here are the classes you are in!");
				List<Course> cList = ss1.getStudentCourses(email);
				for(Course c:cList)
					System.out.println(c.toString());
			}else {
				System.out.println("Whoops, invalid entry run again");
			}
//			t.commit();
			session.close();
	}
	public void logout() {
		System.out.println("Logging out..,Goodbye!");
		System.exit(0);
	}
	
	public void runService() {
		Session session = SmsHibernateUtil.getConnection();
//		Transaction t = session.beginTransaction();
		int option = 0;
		System.out.println("\nPlease make a selection between 1 and 2.");
		
		while(true) {
			System.out.println("Would you like to...");
			System.out.println("1. Register for a Class?");
			System.out.println("2. Logout?");
			
			option = input.nextInt();
			switch(option) {
			case 1:
				System.out.println("Please enter your email");
				String userEmail = input.nextLine();
				input.nextLine();
				System.out.println("Please enter course ID");
				int cId = input.nextInt();
				StudentService ss2 = new StudentService();
				List<Course> courseList = ss2.getStudentCourses(userEmail);
				Student student2 = (Student) session.get(Student.class, userEmail);
				Course c = (Course) session.get(Course.class, cId);
				
				
				if (courseList.contains(c)) {
					System.out.println("You are already registered in that course! Please run again.");
				}else {
					ss2.registerStudentToCourse(student2, cId);
					System.out.println("You are now in " + c.getcName());
					for(Course eachClass:courseList)
						System.out.println(eachClass.toString());
				}
//				t.commit();
				session.close();
				continue;
			case 2:
				logout();
			}
			}
		}
		
	
	
	

}
