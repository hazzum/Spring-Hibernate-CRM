package com.hazzum.hibernateDemo.testJDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jdbcUrl = "jdbc:postgresql://localhost:5432/hb_student_tracker?useSSL=false";
		String user = "hbstudent";
		try {
			System.out.println("Connecting to database... " + jdbcUrl);
			@SuppressWarnings("unused")
			Connection myConnection = DriverManager.getConnection(jdbcUrl, user, user);
			System.out.println("Connection Success!!");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}

}
