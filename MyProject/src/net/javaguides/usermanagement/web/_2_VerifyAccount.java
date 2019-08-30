package net.javaguides.usermanagement.web;

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

import com.mysql.cj.Session;

/**
 * Servlet Filter implementation class VerifyAccount
 */
@WebFilter("/ListServlet")
public class _2_VerifyAccount implements Filter {

	ArrayList<String> logged_accounts = new ArrayList<String>();
	
    /**
     * Default constructor. 
     */
    public _2_VerifyAccount() {
        // TODO Auto-generated constructor stub
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
		
		// pass the request along the filter chain
		if(password.equals("admin123") && !logged_accounts.isEmpty() && logged_accounts.contains(account)){
			
			out.print("<script>\r\n" + 
					"alert(\"This account has been logged in!!\")\r\n" + 
					"</script>");
			
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
			
		else if(password.equals("admin123")) {
			
			HttpSession session = ((HttpServletRequest) request).getSession();
			
			logged_accounts.add(account);
			context.setAttribute("logged_accounts", logged_accounts);
			session.setAttribute("logged_account", account);
			chain.doFilter(request, response);	
		}
		
		else {
			
			out.print("<div align=\"center\">Wrong account or password!!</div>");		
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
