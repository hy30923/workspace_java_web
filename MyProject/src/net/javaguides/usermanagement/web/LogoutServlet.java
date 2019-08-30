package net.javaguides.usermanagement.web;

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
import javax.xml.ws.Response;

import net.javaguides.usermanagement.web.UserServlet;

/**
 * Servlet implementation class logoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; setchar=utf-8");
		PrintWriter	out = response.getWriter();
		
		ServletContext context = request.getServletContext();
		ArrayList<String> logged_accounts = (ArrayList<String>) context.getAttribute("logged_accounts");
		
		HttpSession session = request.getSession(false);
		if(session != null && logged_accounts != null) {
			
			String account = (String) session.getAttribute("logged_account");
			logged_accounts.remove(account);
			System.out.println(account);
			context.setAttribute("logged_accounts", logged_accounts);
			session.invalidate();

			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		else {

			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 ;// TODO Auto-generated method stub
		doGet(request, response);
	}

}
