package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con= null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    con = Dbconnection.get();
	    PrintWriter out = response.getWriter();
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    String query = "select * from register where username=? and password=?";
	    try {
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, username);
	        ps.setString(2, password);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
//	            out.println("<h1 style='color:blue'>Login Success</h1>");
//	            
//	            out.println("<h2 style='color:red'>Account Operation</h2>");
//	            
//	            out.println("<ul>");
//	            out.println("<li> <a href='Create.html'> Create Account </a> </li>");
//	            out.println("<li> <a href='Display.html'> Display </a> </li>");
//	            out.println("<li> <a href='Deposit.html'> Deposit </a> </li>");
//	            out.println("<li> <a href='Withdraw.html'> Withdraw </a> </li>");
//	            out.println("<li> <a href='#'> Logout </a> </li>");
//	            out.println("</ul>");
	            RequestDispatcher rd = request.getRequestDispatcher("Success.html");
	            rd.forward(request, response);
	            
	        } else {
	            out.println("<h1 style='color:red'>Login Failed- TRY AGAIN </h1>");
	            RequestDispatcher rd = request.getRequestDispatcher("login.html");
	            rd.include(request, response);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle the exception appropriately, perhaps log it
	        out.println("<h1 style='color:red'>An error occurred. Please try again later.</h1>");
	    }
	}
}
