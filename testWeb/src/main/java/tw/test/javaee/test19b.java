package tw.test.javaee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test19b")
public class test19b extends HttpServlet {
	
	private Connection conn;
	
	public test19b() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/brad","root","root");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String key = request.getParameter("key");
		String key2 = "%" + key + "%";
		String sql = "select * from gift";
		if(key!=null) {
			sql = "select * from gift where name like ? or feature like ? or address like ?";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("test19f.html");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		dispatcher.include(request,response);
		
		out.print("<table border='1' width='100%'>");
		out.print("<tr><th>ID</th><th>Name</th><th>Feature</th><th>Address</th><th>Picture</th></tr>");
		
		try {
			PreparedStatement prep = conn.prepareStatement(sql);
			if(key!=null) {
				prep.setString(1,key2);
				prep.setString(2,key2);
				prep.setString(3,key2);
			}
			ResultSet result = prep.executeQuery();
			while(result.next()) {
				String id = result.getString("id");
				String name = result.getString("name");
				String feature = result.getString("feature");
				String address = result.getString("address");
				String picture = result.getString("picture");
				out.print("<tr><td>"+id+"</td><td>"+name+"</td><td>"+feature+"</td><td>"+address+"</td><td><img src="+picture+" width='160px' height='90px'></td><td><button id="+id+">+</button></td></tr>");
			}
		} catch (SQLException e) {
			
		}
		out.print("</table>");
		
		response.flushBuffer();
		
	}

}
