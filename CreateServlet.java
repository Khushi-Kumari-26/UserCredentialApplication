package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con= null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		con= Dbconnection.get();
		PrintWriter out=response.getWriter();
		
		int Acc_Num=Integer.parseInt(request.getParameter("Acc_Num"));
		String Acc_HolderName= request.getParameter("Acc_HolderName");
		int age= Integer.parseInt(request.getParameter("age"));
		String address= request.getParameter("address");
		
		
		try
		 {
			 String query="insert into createaccount values(?,?,?,?)";
			 PreparedStatement ps= con.prepareStatement(query);
			 
			 ps.setInt(1, Acc_Num);
			 ps.setString(2, Acc_HolderName);
			 
			 ps.setInt(3, age);
			 ps.setString(4, address);
			
			 int count=ps.executeUpdate();
			 if(count>0)
			 {
				 out.println("Account Created Successfully");
				 RequestDispatcher rd=request.getRequestDispatcher("Success.html");
				 rd.forward(request,response);
			 }
			 else
			 {
				 out.println("failed.....--> Try Again");
				 
				 RequestDispatcher rd=request.getRequestDispatcher("Create.html");
				 rd.include(request,response);
			 }
		 }
		 catch(Exception e)
		 {
			 out.println("Exception::"+e);
		 }
	}

}
