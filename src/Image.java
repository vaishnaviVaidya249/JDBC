import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Image {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/mydatabasejdbc";
		String username="root";
		String password="root";
		//String image_path="D:\\images\\dog.jpg";
		String folder_path="D:\\images\\";
		String query="select image_data from image_table where image_id=(?);";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Drivers loaded successfully");
			
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
			
		}
		
		try {
			Connection con=DriverManager.getConnection(url,username,password);
			System.out.println("connection susccessful");
//			==>1.to insert image from folder to sql database through jdbc
			
			//FileInputStream f=new FileInputStream(image_path);
//			/* this fileinputstream class will convert any file to binary format.
//			 * so here this f will convert the imagepath image into byte format.
//			  */
//			byte[] imageData=new byte[f.available()];
//			
//			f.read(imageData);
//			//will store the byte values of image into the array imagedata.
//			
//			PreparedStatement ps=con.prepareStatement(query);
//			ps.setBytes(1, imageData);
//			int affectedRows=ps.executeUpdate();
//			if(affectedRows>0) {
//				System.out.println("image inserted successfully");
//			}else {
//				System.out.println("image not inserted");
//			}
			//==> retrieve the image from the sql dtabase into our folder
			
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, 1);;
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				byte[] imageData=rs.getBytes("image_data");
				String image_path=folder_path+"extractedImage.jpg";//setting the image name and its path
				
				OutputStream op=new FileOutputStream(image_path);
				op.write(imageData);//writes the bytes into a image ie into above imagepath
				
				
			}else {
				System.out.println("image not found");
			}
			
			
			
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

}
