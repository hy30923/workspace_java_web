package net.javaguides.usermanagement.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.abc.config.Config;

/**
 * Servlet implementation class UploadPhotoServlet
 */
//@WebServlet("/UploadPhotoServlet")
public class UploadPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletContext sc;
    private String savePath;
	
	
	public void init(ServletConfig config) {
		
		savePath = config.getInitParameter("savePath");
		sc = config.getServletContext();
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadPhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
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
						
						String rename = request.getParameter("id") + ".jpg";
						
						File tempFile = new File(item.getName());
						System.out.println(tempFile.renameTo(new File(rename)));
						System.out.println("size: " + item.getSize());
						System.out.println("type: " + item.getContentType());
						System.out.println("name: " + item.getName());
						
						File file = new File(sc.getRealPath(savePath), tempFile.getName());
						
						item.write(file);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
