package tw.test.apis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import tw.test.bcrypt.BCrypt;

public class MemberDB {
	private static final String Driver = "com.mysql.cj.jdbc.Driver";
	private static final String user = "root";
	private static final String user_password = "root";
	private static final String url = "jdbc:mysql://localhost/brad";
	private static final String sql_check = "select count(account) count from member where account = ?";
	private static final int insert_account = 1;
	private static final int insert_password = 2;
	private static final int insert_name = 3;
	private static final String sql_insert = "insert into member (account,password,name) values (?,?,?)";
	
	private Connection conn;
	
	public MemberDB() throws Exception {
		Properties prop = new Properties();
		prop.put("user",user);
		prop.put("password",user_password);
		
		Class.forName(Driver);
		conn = DriverManager.getConnection(url,prop);
	}
	
	public boolean isAccountExist(String account) {
		try {
			PreparedStatement prep = conn.prepareStatement(sql_check);
			prep.setString(1,account);
			ResultSet result = prep.executeQuery();
			result.next();
			return result.getInt("count") > 0;
		} catch (Exception e) {
			System.out.println("error1");
		}
		return true;
	}
	
	public boolean addMember(String account,String password,String name) throws Exception {
		
		PreparedStatement prep = conn.prepareStatement(sql_insert);	
		prep.setString(insert_account,account);
		prep.setString(insert_password,BCrypt.hashpw(password,BCrypt.gensalt()));
		prep.setString(insert_name,name);
		
		return prep.executeUpdate() > 0;
	}
}
