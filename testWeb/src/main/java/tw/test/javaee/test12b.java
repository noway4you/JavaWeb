package tw.test.javaee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.test.apis.bike;

@WebServlet("/test12b")
public class test12b extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String x = request.getParameter("name");
		int intX = (Integer)request.getAttribute("a");
		String name = (String)request.getAttribute("name");
		bike b1 = (bike)request.getAttribute("bike");
		
		response.getWriter().print("Welcome , " + x + " , " + intX + " , " + name + " , " + b1);
	}

}
