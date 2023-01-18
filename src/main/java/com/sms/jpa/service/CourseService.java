package com.sms.jpa.service;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.etim.sms.jpa.entitymodels.Course;
import com.sms.jpa.dao.CourseDao;
import com.sms.util.SmsHibernateUtil;

public class CourseService extends SmsHibernateUtil implements CourseDao {

	@Override
	public List<Course> getAllCourses() {
		Session session = SmsHibernateUtil.getConnection();
		String hql = "FROM Course";
		TypedQuery<Course> qry = session.createQuery(hql, Course.class);
		List<Course> courseList = qry.getResultList();
		
		for(Course course : courseList) {
			System.out.println(course.toString());
		}
		return courseList;
	}
	
		
	
	
	

}
