package com.abc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyLink
 */
@WebServlet("/MyLink")
public class MyLink extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyLink() {
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
		
		Cookie ck[] = request.getCookies();
		String name = "";
		for(Cookie c: ck) {
			
			if(c.getName().equals("name")) {
					
				name = c.getValue();
				break;
			}
		}
		
		if(!name.equals("")) {
			
			out.print("<a href=\"LogoutServlet\">Logout</a> | ");
		}
		
		else {
			
			out.print("<a href=\"login.html\">Login</a> | ");
		}
		
		out.print("<a href=\"ProfileServlet\">Profile</a><hr>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
