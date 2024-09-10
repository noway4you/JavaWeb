package tw.test.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class jdbc_practice{
	
	private static final String user = "root";
	private static final String user_password = "root";
	private static final String url = "jdbc:mysql://127.0.0.1:3306/northwind";
	private static String sql = "select UnitPrice,Quantity from orderdetails where OrderID = ";
	private static String sql2 = "select OrderID from orders where EmployeeID = (select EmployeeID from employees where EmployeeID = ";
	private static String sql3 = "select LastName,FirstName,EmployeeID from employees ";
	private static Properties prop = new Properties();
	private static String s;
	
	public jdbc_practice() {
		
		prop.put("user",user);
		prop.put("password",user_password);
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Select ID = ");
		s = sc.next();
		
		if(s.equals("*")) printAll();
		else printSelected(s);
		
		System.out.print("Again or Quit => ");
		s = sc.next();
		if(s.equals("Again")) new jdbc_practice();
		else System.out.println("Bye");
	}

	public static void main(String[] args) {
		new jdbc_practice();
	}
	
	static void printSelected(String s) {
		try {
			Connection conn = DriverManager.getConnection(url,prop);

			PreparedStatement prep3 = conn.prepareStatement(sql3+"where EmployeeID = " + s);
			ResultSet result3 = prep3.executeQuery();
			if(result3.next()) System.out.print(result3.getString("LastName")+" "+result3.getString("FirstName")+" = ");
			PreparedStatement prep2 = conn.prepareStatement(sql2+result3.getString("EmployeeID")+")");
			ResultSet result2 = prep2.executeQuery();
			int sum = 0;
			while(result2.next()) {
				PreparedStatement prep = conn.prepareStatement(sql+result2.getString("OrderID"));
				ResultSet result = prep.executeQuery();
				while(result.next()) {
					sum += result.getInt("UnitPrice") * result.getInt("Quantity");
				}
			}
			System.out.println(sum);
			
		} catch (Exception e) {
			System.out.println("ID not exist");
		}
	}
	
	static void printAll() {
		try {
			Connection conn = DriverManager.getConnection(url,prop);
			
			PreparedStatement prep3 = conn.prepareStatement(sql3);
			ResultSet result3 = prep3.executeQuery();
			while(result3.next()) {
				System.out.print(result3.getString("LastName")+" "+result3.getString("FirstName")+" = ");
				int sum = 0;
				PreparedStatement prep2 = conn.prepareStatement(sql2+result3.getString("EmployeeID")+")");
				ResultSet result2 = prep2.executeQuery();
				while(result2.next()) {
					PreparedStatement prep = conn.prepareStatement(sql+result2.getString("OrderID"));
					ResultSet result = prep.executeQuery();
					while(result.next()) {
						sum += result.getInt("UnitPrice") * result.getInt("Quantity");
					}
				}
				System.out.println(sum);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
