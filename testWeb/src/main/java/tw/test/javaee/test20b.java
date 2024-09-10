package tw.test.javaee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import tw.test.bcrypt.BCrypt;

@WebServlet("/test20b")
public class test20b extends HttpServlet {
	
	private Connection conn;
	
	public test20b() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/brad","root","root");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(500,"none");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		if(account==null) {
			response.sendRedirect("test20f.html");
			return;
		}
		
		try {
			String sql = "select * from member where account = ?";
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1,account);
			ResultSet result = prep.executeQuery();
			if(result.next()) {
				String hashpassword = result.getString("password");
				if(BCrypt.checkpw(password,hashpassword)) {
					response.sendRedirect("main.html");
				}else {
					response.sendRedirect("test20f.html");
				}
			}else {
				response.sendRedirect("test20f.html");
			}
		} catch (Exception e) {
			response.sendRedirect("test20f.html");
		}
	}

}
