package com.etim.sms.sms;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sms.jpa.service.StudentService;
import com.sms.util.SmsHibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    	
//    	System.out.println( "Hello World!" );
    	StudentService stuServ = new StudentService();
    	stuServ.login();
    	stuServ.runService();
    	
    	 
    	 
    	 
    }
}
