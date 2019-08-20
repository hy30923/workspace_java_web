package com.abc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyDatabase
 */
@WebServlet("/MyDatabase")
public class MyDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/mytable?useSSL=false&serverTimezone=CST";
	
	private static final String USER = "root";
	private static final String PASS = "qaz1234567";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; setchar=utf-8");
		PrintWriter	out = response.getWriter();
		
		try {
			
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Create statement...");
			stmt = conn.createStatement();
			
			String sql = "select id, first, last, age from Employee";
			ResultSet rs = stmt.executeQuery(sql);
			
			out.print(
					"<style>" +
					"table { \r\n" + 
					"  border:1px solid #000; \r\n" + 
					"  font-family: ·L³n¥¿¶ÂÅé; \r\n" + 
					"  font-size:16px; \r\n" + 
					"  width:200px;\r\n" + 
					"  border:1px solid #000;\r\n" + 
					"  text-align:center;\r\n" + 
					"  border-collapse:collapse;\r\n" + 
					"} \r\n" + 
					"th { \r\n" + 
					"  background-color: #009FCC;\r\n" + 
					"  padding:10px;\r\n" + 
					"  border:1px solid #000;\r\n" + 
					"  color:#fff;\r\n" + 
					"} \r\n" + 
					"td { \r\n" + 
					"  border:1px solid #000;\r\n" + 
					"  padding:5px;\r\n" + 
					"} " +
					"</style>");
			
			out.print("<table> \r\n" + 
					"  <tr> \r\n" + 
					"  <th>ID</th> \r\n" + 
					"  <th>Age</th>\r\n" +
					"  <th>First Name</th>\r\n" + 
					"  <th>Last Name</th>\r\n" + 
					"  </tr> ");
			
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");
				
				out.print("<tr> \r\n" + 
						"  <td>"+ id + "</th> \r\n" + 
						"  <td>"+ age + "</td> \r\n" +
						"  <td>"+ first +"</td> \r\n" + 
						"  <td>"+ last + "</td> \r\n" + 
						"  </tr>");
			}
			
			out.print("</table>");
			
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			
			try {
				
				if(stmt != null)
					stmt.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			try {
				
				if(conn != null)
					conn.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		System.out.println("Goodbye!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
