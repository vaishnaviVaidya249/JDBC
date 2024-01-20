import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BatchProcessing {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/mydatabasejdbc";
		String username="root";
		String password="root";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Drivers loaded successfully");
			
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
			
		}
		try {
			Connection con=DriverManager.getConnection(url,username,password);
			con.setAutoCommit(false);
			String query="insert into employee(id,name,job_title,salary) values(?,?,?,?);";
			PreparedStatement ps=con.prepareStatement(query);
			Scanner sc=new Scanner(System.in);
			while(true) {
				System.out.println("Id: ");
				int id=sc.nextInt();
				
				System.out.println("Name: ");
				String name=sc.next();
				sc.nextLine();
				
				System.out.println("Job: ");
				String job_title=sc.nextLine();
				
				System.out.println("Salary: ");
				String salary=sc.nextLine();
				
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, job_title);
				ps.setString(4, salary);
				ps.addBatch();
				System.out.println("add more? Y/N");
				String s=sc.nextLine();
				if(s.toUpperCase().equals("N"))
					break;
				
			}
			int[] batchRes=ps.executeBatch();
	        con.commit();
		    System.out.println("Batch processed!");
			
			
			//Statement stmt=con.createStatement();
			
//			stmt.addBatch("insert into employee(id,name,job_title,salary) values(12,'ravi','frontend developer',55000)");
//			stmt.addBatch("insert into employee(id,name,job_title,salary) values(11,'raju','devops Engineer',58000)");
//			stmt.addBatch("insert into employee(id,name,job_title,salary) values(10,'rahul','Software developer',75590)");
//		    int[] batchRes=stmt.executeBatch();
//		    con.commit();
//		    System.out.println("Batch processed!");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			
		}

	}

}
