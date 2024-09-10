package tw.test.javaee;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import tw.test.bcrypt.BCrypt;

@WebServlet("/test21b")
@MultipartConfig
public class test21b extends HttpServlet {
	
	private static final String user = "root";
	private static final String user_password = "root";
	private static final String url = "jdbc:mysql://localhost/brad";
	private static final String sql_insert = "insert into member (account,password,name,icon) values (?,?,?,?) ";
	private Connection conn;
	
	public test21b() {
		Properties prop = new Properties();
		prop.put("user",user);
		prop.put("password",user_password);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,prop);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Part icon = request.getPart("icon");
		InputStream in = icon.getInputStream();
		String account = request.getParameter("account");
		String password = BCrypt.hashpw(request.getParameter("password"),BCrypt.gensalt());
		String name = request.getParameter("name");
		
		try {
			PreparedStatement prep = conn.prepareStatement(sql_insert);
			prep.setString(1,account);
			prep.setString(2,password);
			prep.setString(3,name);
			prep.setBinaryStream(4,in);
			prep.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
		
//		byte[] buffer = in.readAllBytes();
//		String base64String = Base64.getEncoder().encodeToString(buffer);
//		
//		response.setContentType("text/html; charset=UTF-8");
//		response.getWriter().print(String.format("<img src='data:image/png;base64,%s'>",base64String));
	
		response.sendRedirect("JSP33_MainPage.jsp");
	}

}
