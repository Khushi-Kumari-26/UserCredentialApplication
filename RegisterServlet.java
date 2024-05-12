package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con= null;
       
    
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
 {
	 con= Dbconnection.get();
	 PrintWriter out=response.getWriter();
	 String username= request.getParameter("username");
	 String password= request.getParameter("password");	
	
	 try
	 {
		 String query="insert into register values(?,?)";
		 PreparedStatement ps= con.prepareStatement(query);
		 ps.setString(1,username);
		 ps.setString(2, password);
		
		 int count=ps.executeUpdate();
		 if(count>0)
		 {
			 out.println("Successfully Register");
			 RequestDispatcher rd=request.getRequestDispatcher("Login.html");
			 rd.include(request,response);
		 }
		 else
		 {
			 out.println("Registration failed...");
			 
			 RequestDispatcher rd=request.getRequestDispatcher("register.html");
			 rd.include(request,response);
		 }
	 }
	 catch(Exception e)
	 {
		 out.println("Exception::"+e);
	 }
 }

}