package tw.test.javaee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/test09b")
@MultipartConfig(location = "D:\\JavaPractice\\testWeb\\src\\main\\webapp\\upload")
public class test09b extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("test/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Part part = request.getPart("upload");
		System.out.println(part.getName());
		System.out.println(part.getSize());
		System.out.println(part.getSubmittedFileName());
		
		String filename = "upload_" + part.getSubmittedFileName();
		System.out.println(filename);
		if(part.getSize()>0) {
			part.write(filename);
			out.print("ok");
		}
		else out.print("failure");
	}
}
