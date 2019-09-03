package com.abc.register.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

import com.abc.config.ERROR;

import net.javaguides.usermanagement.dao.AccDAO;
import net.javaguides.usermanagement.model.Account;

/**
 * Servlet Filter implementation class _r02_VerifyExistence
 */
@WebFilter("/RegisterServlet")
public class _r02_VerifyExistence implements Filter {

	private AccDAO accDAO;
	
    /**
     * Default constructor. 
     */
    public _r02_VerifyExistence() {
        // TODO Auto-generated constructor stub
    	accDAO = new AccDAO();
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		ArrayList<Account> accounts = (ArrayList<Account>) accDAO.selectAllUsers();
		// pass the request along the filter chain
		for(int i = 0 ; i < accounts.size() ; i++) {
			
			if(accounts.get(i).getAccount().equals(request.getParameter("account"))) {
				
				System.out.println("Double signup: " + accounts.get(i).getAccount());
				out.print("<div align='center'>" + ERROR.ERR_VERYFY_SIGN_ACCOUNT + "</div>");
				request.getRequestDispatcher("register.jsp").include(request, response);
				return;
			}
		}
		
		if(!request.getParameter("password").equals(request.getParameter("password_verify"))) {
			
			out.print("<div align='center'>" + ERROR.ERR_VERIFY_SIGN_PASSWORD + "</div>");
			request.getRequestDispatcher("register.jsp").include(request, response);
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
