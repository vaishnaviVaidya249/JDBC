package Banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
	private Connection con;
	private Scanner sc;
	public User(Connection con,Scanner sc) {
		this.con=con;
		this.sc=sc;
	}
	
	public void register() {
		sc.nextLine();
		System.out.print("Full Name: ");
		String full_name=sc.nextLine();
		System.out.print("Email: ");
		String email=sc.nextLine();
		System.out.print("Password: ");
		String password=sc.nextLine();
		
		if(user_exist(email)) {
			System.out.println("User Already Exsists for this Email Address!");
			return;
		}
		
		String register_query="INSERT INTO user(full_name,email,password) VALUES(?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(register_query);
			ps.setString(1, full_name);
			ps.setString(2, email);;
			ps.setString(3,password);
			int rowsAffected=ps.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Registration Successful!");
				
			}else {
				System.out.println("Registration failed!");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public String login() {
		sc.nextLine();
		System.out.print("Email: ");
		String email=sc.nextLine();
		System.out.print("Password: ");
		String password=sc.nextLine();
		String login_query="SELECT * FROM user WHERE  email=? AND password=?";
		
		try {
			PreparedStatement ps=con.prepareStatement(login_query);
			
			ps.setString(1, email);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return email;
			}else {
				return null;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	
	public boolean user_exist(String email){
		String query="SELECT * FROM user WHERE email=?";
		try {
			PreparedStatement ps=con.prepareStatement(query);
			
			ps.setString(1, email);;
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
		
	}

}
