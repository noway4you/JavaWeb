package tw.test.javaee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test22b")
@MultipartConfig
public class test22b extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// 1.handle parts
		byte[] buffer = request.getPart("icon").getInputStream().readAllBytes();
		request.setAttribute("icon",buffer);
		// 2.let jsp to handle else problem
		request.getRequestDispatcher("JSP33_insertMember.jsp").forward(request,response);
	}

}
