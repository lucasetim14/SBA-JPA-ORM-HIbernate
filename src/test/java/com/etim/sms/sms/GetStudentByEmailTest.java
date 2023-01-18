package com.etim.sms.sms;

import static org.junit.Assert.*;

import org.junit.Test;

import com.etim.sms.jpa.entitymodels.Student;
import com.sms.jpa.service.StudentService;

public class GetStudentByEmailTest {
	
	@Test
	public void testGetStudentByEmail() {
		//Given
		String email = "cstartin3@flickr.com";
		StudentService stuServ = new StudentService();
		Student student = new Student();
		String expectedEmail = email;
		Student expectedStudent = stuServ.getStudentByEmail(expectedEmail);
		
		//When
	    student.setsEmail("cstartin3@flickr.com");
	    String actualEmail = student.getsEmail();
	    Student actualStudent = stuServ.getStudentByEmail(actualEmail);
		
		//Then
		assertEquals(expectedStudent.getsEmail(), actualStudent.getsEmail());
	}

}
