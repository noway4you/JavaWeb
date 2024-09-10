package tw.test.javaee;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.test.apis.MyModel;

@WebServlet("/test15b")
public class test15b extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// get value
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		// evaluate
		try {
			MyModel myModel = new MyModel(x,y);
			String result = myModel.plus();
			request.setAttribute("result",result);
			request.setAttribute("x",x);
			request.setAttribute("y",y);
			request.setAttribute("view","view1");
		} catch (Exception e) {
			request.setAttribute("result","");
			request.setAttribute("x",x==null?"":x);
			request.setAttribute("y",y==null?"":y);
			request.setAttribute("view","view1");
		}
		
		// show on view
		response.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("test16b");
		dispatcher.forward(request,response);
		
	}

}
