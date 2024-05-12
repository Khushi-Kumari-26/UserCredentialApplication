package com.user;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/DisplayServlet")
//public class DisplayServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	Connection con= null;
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		con= Dbconnection.get();
//		PrintWriter out=response.getWriter();
//		
//		try {
//            String query = "SELECT * FROM createaccount";
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//
//            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Balance</th></tr>");
//
//            while (rs.next()) {
//                int id = rs.getInt("num");
//                String name = rs.getString("name");
//                double balance = rs.getDouble("balance");
//
//                out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>" + balance + "</td></tr>");
//            }
//
//            out.println("</table>");
//        } catch (ClassNotFoundException | SQLException e) {
//            out.println("<h2>Error: " + e.getMessage() + "</h2>");
//        }
//	}
//
//}


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	Connection con= null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	con= Dbconnection.get();
		PrintWriter out=response.getWriter();
		int Acc_Num=Integer.parseInt(request.getParameter("Acc_Num"));
        try {

            String query = "SELECT * FROM createaccount WHERE num=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Acc_Num);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                out.println("<h2>Account details</h2>");
                out.println("<h3>Acc_Num: " + rs.getInt("Acc_Num") + "</h3>");
                out.println("<h3>Acc_HolderName: " + rs.getString("Acc_HolderName") + "</h3>");
                out.println("<h3>age: " + rs.getInt("age") + "</h3>");
                out.println("<h3>address: " + rs.getInt("address") + "</h3>");
               
                RequestDispatcher rd = request.getRequestDispatcher("Sucess.html");
				rd.include(request, response);

            } else {
            	out.println("<h1 style='color:red'>Display Failed- Try Again</h4>");
				RequestDispatcher rd = request.getRequestDispatcher("Display.html");
				rd.include(request, response);
            }

            
        }catch (Exception e) {
            out.println("<h2>Exception: " + e.getMessage() + "</h2>");
        }
    }
}
