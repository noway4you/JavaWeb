package tw.test.javaee;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test02b")
public class test02b extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> headers = request.getHeaderNames();
		while(headers.hasMoreElements()) {
			String name = headers.nextElement();
			String value = request.getHeader(name);
			System.out.println(name+" = "+value);
		}
	}

}
