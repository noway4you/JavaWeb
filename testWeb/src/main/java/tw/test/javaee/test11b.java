package tw.test.javaee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.test.apis.bike;

@WebServlet("/test11b")
public class test11b extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("test12b");
		request.setAttribute("a",10);
		request.setAttribute("name","hi");
		bike b1 = new bike();
		request.setAttribute("bike",b1);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.append("<h1>h1</h1>").append("<hr>").append("<div>hello</div>").append("<hr>");
		dispatcher.include(request,response);
		out.append("<hr>");
		response.flushBuffer();
	}

}
