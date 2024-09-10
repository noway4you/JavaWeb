package tw.test.javaee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.test.apis.MyUtils;

@WebServlet("/test16b")
public class test16b extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get attribute
		String result = (String)request.getAttribute("result");
		String x = (String)request.getAttribute("x");
		String y = (String)request.getAttribute("y");
		
		try {
			// load view
			String webPage = MyUtils.loadView();
			
			// show view
			webPage = webPage.replaceAll("@x@",x).replaceAll("@y@",y).replaceAll("@result@",result);
			response.getWriter().print(webPage);
			response.flushBuffer();
		} catch (Exception e) {
			response.getWriter().print("error");
			response.flushBuffer();
		}
	}

}
