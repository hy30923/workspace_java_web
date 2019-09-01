package com.abc.login.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.abc.config.Config;
import com.abc.config.ERROR;
import com.mysql.cj.Session;

import net.javaguides.usermanagement.dao.AccDAO;
import net.javaguides.usermanagement.model.Account;

/**
 * Servlet Filter implementation class VerifyAccount
 */
@WebFilter("/LoginServlet")
public class _l02_VerifyAccount implements Filter {

	ArrayList<String> logged_accounts = new ArrayList<String>();
	AccDAO accDAO;
    /**
     * Default constructor. 
     */
    public _l02_VerifyAccount() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		ServletContext context = request.getServletContext();
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		ArrayList<Account> accounts = (ArrayList<Account>) accDAO.selectAllUsers();
		
		// pass the request along the filter chain
		for(int i = 0 ; i < accounts.size() ; i++) {
			
			if(accounts.get(i).getAccount().equals(account) && logged_accounts.contains(account)){
				
				out.print("<script>alert(" + ERROR.ERR_RE_LOG + ")</script>");
				
				request.getRequestDispatcher("index.jsp").include(request, response);
			}
				
			else if(accounts.get(i).getAccount().equals(account) && accounts.get(i).getPassword().equals(password)) {
				
				HttpSession session = ((HttpServletRequest) request).getSession();
				
				System.out.println("get session id " + session.getId());			
				logged_accounts.add(account);
				context.setAttribute("logged_accounts", logged_accounts);
				session.setAttribute("logged_account", account);
				chain.doFilter(request, response);	
			}
		
			else {
				
				out.print("<div align='center'>" + ERROR.ERR_LOGIN + "</div>");		
				request.getRequestDispatcher("index.jsp").include(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
