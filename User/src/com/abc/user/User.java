package com.abc.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter writer = response.getWriter();
		
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");
		String hobbies[] = request.getParameter("hobbies").split("\\s|\\n|,|¡B|¡A");
		
		writer.println("name: " + name + "<br>");
		writer.println("birthday: " + birthday + "<br>");
		writer.println("gender: " + gender + "<br>");
		writer.println("hobbies: ");
		
		for(int i = 0 ; i < hobbies.length ; i++) {
			writer.println(hobbies[i]);
		}
		 
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
