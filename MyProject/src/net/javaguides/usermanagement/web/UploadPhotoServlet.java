package net.javaguides.usermanagement.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.abc.config.Config;

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
					
					// get stream...  
					if(item.getName() != null && !item.getName().equals("")) {
						
						String strId = null;
							
						if("id".equals(item.getName())) {
							
							StringBuilder stringBuilder = new StringBuilder();
							String line = null;
							
							try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(item.getInputStream()))){
								
								while((line = bufferedReader.readLine()) != null) {
									
									stringBuilder.append(line);
								}
							}
							
							strId = stringBuilder.toString();
						}

						int id = Integer.parseInt(strId);
						System.out.println("id: " + id);
						System.out.println("size: " + item.getSize());
						System.out.println("type: " + item.getContentType());
						System.out.println("name: " + item.getName());			
						System.out.println("path: " + savePath);
						
						File file = new File(sc.getRealPath("/") + savePath, new File(item.getName()).getName());
						item.write(file);
						
						User setUrl = new User();
						setUrl.setId(id);
						setUrl.setUrl(savePath + item.getName());
						userDAO.updateUserUrl(setUrl);
						
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
