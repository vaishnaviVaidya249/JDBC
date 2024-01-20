import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/mydatabasejdbc";
		String username="root";
		String password="root";
		String query="INSERT INTO employee(id,name,job_title,salary) VALUES(4,'Harsha','React Developer',77000.0);";
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
            	System.out.println("Insert Successful "+rowsEffected);
            }else {
            	System.out.println("Insertion Failed");
            }
            stmt.close();
            con.close();
            System.out.println("connection closed sucessfully");
            
           }catch(SQLException e) {
            
			System.out.println(e.getMessage());
		}
		

		

	}

}
