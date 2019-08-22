package com.abc;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class VerifyAccount
 */
@WebFilter("/HomePage")
public class _2_VerifyAccount implements Filter {

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
		
		String password = request.getParameter("password");
		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
		
		if(password.equals("admin123")) {
			
			Cookie ck = new Cookie("account", encryption(request.getParameter("account")));
			res.addCookie(ck);
			chain.doFilter(request, response);
		}
		
		else {
			
			out.print("wrong account or password!!");
			request.getRequestDispatcher("index.html").include(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public String encryption(String str) throws UnsupportedEncodingException {
		
		final Base64.Decoder decoder = Base64.getDecoder();
		final Base64.Encoder encoder = Base64.getEncoder();
		final byte[] bytes = str.getBytes("UTF-8");
		final String encodedText = encoder.encodeToString(bytes);
		System.out.println(encodedText);
		System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
		
		return encodedText;
	}
}
