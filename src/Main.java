import java.sql.*;
public class Main {
//retrive data from the mysql database
	public static void main(String[] args) throws ClassNotFoundException{
		String url="jdbc:mysql://localhost:3306/mydatabasejdbc";
		String username="root";
		String password="root";
		String query="select * from employee";
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
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()) {
            	int id=rs.getInt("id");//here id is mysql field name
            	String name=rs.getString("name");
                String job_title=rs.getString("job_title");
                double salary=rs.getDouble("salary");
                System.out.println("==========================");
                System.out.println("ID: "+id);
                System.out.println("Name: "+name);
                System.out.println("JOB: "+job_title);
                System.out.println("Salary: "+salary);
            }
            rs.close();
            stmt.close();
            con.close();
            System.out.println("connection closed sucessfully");
            
           }catch(SQLException e) {
            
			System.out.println(e.getMessage());
		}
		

	}

}
