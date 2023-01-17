package com.hazzum.CRMDemo.TestJDBC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestJDBC
 */
public class TestJDBC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String urlString = "jdbc:postgresql://localhost:5432/customer_tracker?useSSL=false", driverString="org.postgresql.Driver";
		
		try {
			PrintWriter outPrintWriter = response.getWriter();
			outPrintWriter.print("Connecting to database: " + urlString);
			Class.forName(driverString);
			Connection myConnection = DriverManager.getConnection(urlString, "postgres", "admin");
			outPrintWriter.print("Success!!");
			myConnection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServletException();
		}
	}

}
