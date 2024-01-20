import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/mydatabasejdbc";
		String username="root";
		String password="root";
		String query="UPDATE employee SET job_title='Software Engineer',salary=75000.0 WHERE id=2;";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Drivers Loaded successfully");
		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		try {
			Connection con=DriverManager.getConnection(url,username,password);
			System.out.println("Connection successful");
			Statement stmt=con.createStatement();//creating the statement this stmt is used to run queries.
			int rowsEffected=stmt.executeUpdate(query);
            if(rowsEffected>0) {
            	System.out.println("Updation Successful "+rowsEffected);
            }else {
            	System.out.println("updation Failed");
            }
            stmt.close();
            con.close();
            System.out.println("connection closed sucessfully");
            
           }catch(SQLException e) {
            
			System.out.println(e.getMessage());
		}
		
		

	}

}
