import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Connections
{
	public static boolean checklogin(String username,String password) 
	{
		boolean s=false;
		  try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/appealing","root","");
			PreparedStatement ps;
			ps=conn.prepareStatement("select  * from users where username=? AND password=? AND category='admin' ");
			ps.setString(1, username);
			
			ps.setString(2,password);
			
			
			ResultSet rs=ps.executeQuery();
			s=rs.next();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
		
	}
	public static boolean user(String username,String password) 
	{
		boolean r=false;
		  try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/appealing","root","");
				PreparedStatement ps1;
				ps1=conn.prepareStatement("select  * from users where username=?  AND password=? AND category='user' ");
				ps1.setString(1, username);
				
				ps1.setString(2,password);
				
				
				ResultSet rs1=ps1.executeQuery();
				r=rs1.next();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return r;
	}
	public static boolean Teacher(String username,String password) 
	{
		boolean c=false;
		  try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/appealing","root","");
				PreparedStatement ps2;
				ps2=conn.prepareStatement("select  * from lecturer_tbl where email=?  AND Password=? ");
				ps2.setString(1, username);
				
				ps2.setString(2,password);
				
				
				ResultSet rs2=ps2.executeQuery();
				c=rs2.next();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return c;
	}

}