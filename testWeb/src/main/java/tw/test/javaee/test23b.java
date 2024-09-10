package tw.test.javaee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test23b")
public class test23b extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		
		if(checkAccount(account,password)) {
			if(remember!=null && remember.equals("true")) {
				Cookie cookie = new Cookie("account",account);
				cookie.setMaxAge(60);
				response.addCookie(cookie);
			}else {
				Cookie cookie = new Cookie("account",account);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				request.getSession().setAttribute("account",account);
			}
			response.sendRedirect("JSP43.jsp");
		}else {
			response.sendRedirect("test27f.html");
		}
	}
	
	private boolean checkAccount(String account,String password) {
		if(account.equals("abc") && password.equals("123")) {
			return true;
		}else {
			return false;
		}
	}
}
