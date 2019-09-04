package com.abc;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PowerNumber extends TagSupport {

	private int number;
	private int power;
	private static int counter = 0;
	private static int result = 1;
	
	public void setNumber(int number) {
		this.number = number;
	}

	public void setPower(int power) {
		this.power = power;
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		return EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doAfterBody() throws JspException {
		// TODO Auto-generated method stub
		counter++;
		result *= number;
		
		if(counter == power) {
			
			return SKIP_BODY;
		}
		
		else {
			
			return EVAL_BODY_AGAIN;
		}
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		JspWriter out = pageContext.getOut();
		
		try {
		
			out.print(result + " ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	

}
