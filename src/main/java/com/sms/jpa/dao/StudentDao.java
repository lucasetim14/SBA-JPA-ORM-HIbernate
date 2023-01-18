package com.sms.jpa.dao;

import java.util.List;

import com.etim.sms.jpa.entitymodels.Course;
import com.etim.sms.jpa.entitymodels.Student;

public interface StudentDao {
	
	List<Student> getAllStudents ();
	Student getStudentByEmail (String email);
	boolean validateStudent(String email, String pass);
	Course registerStudentToCourse(Student student, int id);
	List<Course> getStudentCourses(String email);
	
	
	

}
