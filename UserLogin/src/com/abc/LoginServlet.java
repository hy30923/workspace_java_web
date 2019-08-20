package com.abc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ArrayList<String> logged_name = new ArrayList<String>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; setchar=utf-8");
		PrintWriter	out = response.getWriter();
		 
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		ServletContext context = getServletContext();
		
		if(password.equals("admin123") && !logged_name.isEmpty() && logged_name.contains(name)){
			
			out.print("<script>\r\n" + 
					"alert(\"this account has logged in!!\")\r\n" + 
					"</script>");
			
			request.getRequestDispatcher("login.html").include(request, response);
		}
		
		else if(password.equals("admin123")) {
			
			logged_name.add(name);
			HttpSession session = request.getSession();
			context.setAttribute("logged_names", logged_name);
			session.setAttribute("name", name);
			request.getRequestDispatcher("MyLink").include(request, response);
			
			out.print("You are successfully logged in!");
			out.print("<br/>Welcome, " + name);
		}
		
		else {

			out.print("sorry, username or password error!");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
