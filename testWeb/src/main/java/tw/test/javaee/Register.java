package tw.test.javaee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.test.apis.MemberDB;

@WebServlet("/Register")
public class Register extends HttpServlet {
	
	private MemberDB db = null;
	
    public Register() {
        try {
			db = new MemberDB();
		} catch (Exception e) {
			System.out.println(e);
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(db==null) return;
		
		request.setCharacterEncoding("UTF-8");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
	
		if(!db.isAccountExist(account)) {
			try {
				if(db.addMember(account,password,name)) {
					response.sendRedirect("JSP05.jsp");
				}else {
					response.sendRedirect("JSP04.jsp?errType=3");
				}
			} catch (Exception e) {
				response.sendRedirect("JSP04.jsp?errType=2");
			}
		}else {
			response.sendRedirect("JSP04.jsp?errType=1");
		}
	}

}
