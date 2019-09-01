package com.abc.register.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.abc.config.ERROR;

/**
 * Servlet Filter implementation class _r01_VerifyNumber
 */
@WebFilter("/RegisterServlet")
public class _r01_VerifyNumber implements Filter {

    /**
     * Default constructor. 
     */
    public _r01_VerifyNumber() {
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
		// pass the request along the filter chain
		String number = request.getParameter("number");
		String correct_number = request.getParameter("correct_number");
		
		if(number.equals(correct_number)) {

			chain.doFilter(request, response);
		}
		
		else {
			
			out.print("<div align='center'>" + ERROR.ERR_VERIFY_NUMBER + "</div>");
			request.getRequestDispatcher("register.jsp").include(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
