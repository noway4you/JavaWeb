package tw.test.javaee;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/test10b")
@MultipartConfig(location = "D:\\JavaPractice\\testWeb\\src\\main\\webapp\\upload")
public class test10b extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String account = request.getParameter("account");
		
		int count = 0;
		Collection<Part> parts = request.getParts();
		for(Part part:parts){
			if(part.getName().equals("upload")) {
				if(part.getSize()>0) {
					String filename = account + "_" +part.getSubmittedFileName();
					try {
						part.write(filename);
						count++;
					}catch(Exception e) {
						
					}
				}
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print("success " + count + " files");
	}

}
