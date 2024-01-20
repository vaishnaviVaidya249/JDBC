import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Prepared {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/mydatabasejdbc";
		String username="root";
		String password="root";
		String query="update employee set name=? where id=?";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Drivers loaded successfully");
			
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
			
		}
		
		try {
			Connection con=DriverManager.getConnection(url,username,password);
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, "vishnu");
			ps.setInt(2, 55);
			ps.executeUpdate();
		    System.out.println("success");
		    ps.close();
		    con.close();
		    
			
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		

	}

}
