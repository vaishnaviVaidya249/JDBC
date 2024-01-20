import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transactions {

	public static void main(String[] args) throws ClassNotFoundException{
		String url="jdbc:mysql://localhost:3306/mydatabasejdbc";
		String username="root";
		String password="root";
		String withdrawal="update account set balance=balance-? where account_number=?;";
		String deposit="update account set balance=balance+? where account_number=?;"; 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Drivers loaded successfully");
			
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
			
		}
		
		try {
			Connection con=DriverManager.getConnection(url,username,password);
			System.out.println("Connection established successfully");
			con.setAutoCommit(false);
			try {
			PreparedStatement ps1=con.prepareStatement(withdrawal);
			PreparedStatement ps2=con.prepareStatement(deposit);
			ps1.setDouble(1,500.0);
			ps1.setString(2, "account123");
			ps2.setDouble(1,500.0);
			ps2.setString(2, "account456");
			ps1.executeUpdate();
			ps2.executeUpdate();
			con.commit();
			System.out.println("Transaction successful");
		
			}catch(SQLException e) {
				con.rollback();
				System.out.println("Transaction failed");
			}
			
			
			

		}catch(SQLException e) {
			
			System.out.println(e.getMessage());
		}
	}

}
