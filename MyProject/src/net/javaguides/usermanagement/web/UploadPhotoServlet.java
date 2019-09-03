package net.javaguides.usermanagement.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.jsp.PageContext;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.abc.config.Config;
import com.abc.config.ERROR;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import net.javaguides.usermanagement.dao.UserDAO;
import net.javaguides.usermanagement.model.User;

/**
 * Servlet implementation class UploadPhotoServlet
 */
//@WebServlet("/UploadPhotoServlet")
public class UploadPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletContext sc;
    private String savePath;
	private UserDAO userDAO;
	
	public void init(ServletConfig config) {
		
		savePath = Config.IMG_PATH;
		//savePath = config.getInitParameter("savePath");
		sc = config.getServletContext();
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadPhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
        userDAO = new UserDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("id: " + request.getParameter("id"));
		
		try {
			
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			
			while(itr.hasNext()) {
				
				FileItem item = (FileItem) itr.next();
				if(item.isFormField()) {
					
					System.out.println(item.getFieldName() + " " + item.getString("utf-8"));
				}
				
				else {
					
					if(item.getName() != null && !item.getName().equals("")) {
						
						//int id = Integer.parseInt(strId);
						//System.out.println("id: " + id);
						System.out.println("size: " + item.getSize());
						System.out.println("type: " + item.getContentType());
						System.out.println("name: " + item.getName());			
						System.out.println("path: " + savePath);
						
						File file = new File(sc.getRealPath("/") + savePath, new File(item.getName()).getName());
						item.write(file);
						
						User setUrl = new User();
						//setUrl.setId(id);
						setUrl.setUrl("uploads/" + item.getName());
						//userDAO.updateUserUrl(setUrl);
						
						out.print("<script>alert('upload success');</script>");
						request.setAttribute("upload.message", "upload success");
					}
					
					else {
						
						request.setAttribute("upload.message", "no choosen file");
					}
				}
			}
			
		} catch (FileUploadException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("upload.message", "failed to upload");
		}
		
		request.getRequestDispatcher("ListServlet").forward(request, response);
	}
	*/
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println("id: " + request.getParameter("id"));
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		
		User setUrl = new User();
		setUrl.setId(id);
		setUrl.setUrl("uploads/" + name);
		try {
			userDAO.updateUserUrl(setUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("ListServlet").forward(request, response);
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
