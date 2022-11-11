

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 * Servlet implementation class appealsrv
 */
@WebServlet("/appealsrv")
@MultipartConfig(maxFileSize = 16177215)
public class appealsrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private String dbURL = "jdbc:mysql://localhost:3306/appealing";
	    private String dbUser = "root";
	    private String dbPass = "";
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public appealsrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reg_number=request.getParameter("regnumber");
		String c_name=request.getParameter("cname");
		String c_code=request.getParameter("code");
		String lect=request.getParameter("lname");
		
		 InputStream inputStream = null; // input stream of the upload file
         
	        // obtains the upload file part in this multipart request
	        Part filePart = request.getPart("reason");
	        if (filePart != null) {
	            // prints out some information for debugging
	            System.out.println(filePart.getName());
	            System.out.println(filePart.getSize());
	            System.out.println(filePart.getContentType());
	             
	            // obtains input stream of the upload file
	            inputStream = filePart.getInputStream();
	        }
	         
	        InputStream inputStream2 = null; // input stream of the upload file
	         
	        // obtains the upload file part in this multipart request
	        Part filePart2 = request.getPart("letter");
	        if (filePart2 != null) {
	            // prints out some information for debugging
	            System.out.println(filePart2.getName());
	            System.out.println(filePart2.getSize());
	            System.out.println(filePart2.getContentType());
	             
	            // obtains input stream of the upload file
	            inputStream2 = filePart2.getInputStream();
	        }
	        Connection conn = null; // connection to the database
	        String message = null;  // message will be sent back to client

			  String to = "irayvesmuli01@gmail.com";
			  
		      // Sender's email ID needs to be mentioned
		      String from = "iraguhayves0788@gmail.com";
		 
		      // Assuming you are sending email from localhost
		      String host = "localhost";
		 
		      // Get system properties
		      Properties properties = System.getProperties();
		 
		      // Setup mail server
		      properties.setProperty("mail.smtp.host", host);
		 
		      // Get the default Session object.
		      Session session = Session.getDefaultInstance(properties);
		      
		      // Set response content type
		      response.setContentType("text/html");
		      PrintWriter out = response.getWriter();

		      try {
		         
		         // Create a default MimeMessage object.
		         MimeMessage messages = new MimeMessage(session);
		         
		         // Set From: header field of the header.
		         messages.setFrom(new InternetAddress(from));
		         
		         // Set To: header field of the header.
		         messages.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		         // Set Subject: header field
		         messages.setSubject("This is the Subject Line!");

		         // Send the actual HTML message, as big as you like
		         messages.setContent("<h1>This is actual message</h1>", "text/html" );
		         
		         // Send message
		         Transport.send(messages);
		         String title = "Send Email";
		         String res = "Sent message successfully....";
		         String docType =
		         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		         
		         out.println(docType +
		            "<html>\n" +
		               "<head><title>" + title + "</title></head>\n" +
		               "<body bgcolor = \"#f0f0f0\">\n" +
		                  "<h1 align = \"center\">" + title + "</h1>\n" +
		                  "<p align = \"center\">" + res + "</p>\n" +
		               "</body></html>"
		         );
		      } catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
	         
	        try {
	            // connects to the database
	        	 // connects to the database
	            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	            conn = DriverManager.getConnection(dbURL, dbUser, dbPass); 
	            // constructs SQL statement
	            Statement st=null;
	            String sql1="select * from courses where course_name='"+c_name+"' ";
	            st=conn.createStatement();
	            ResultSet rs;
	            rs=st.executeQuery(sql1);
	            PreparedStatement ps;
	            int id1 = 0;
	            while(rs.next()){
	            	id1 = rs.getInt("course_id");
	            	
	            }
	            
	            Statement st2=null;
	            String sql2="select * from lecturer_tbl where lect_name='"+lect+"' ";
	            st2=conn.createStatement();
	            ResultSet rs2;
	            rs2=st2.executeQuery(sql2);
	            int id2 = 0;
	            while(rs2.next()){
	            	id2 = rs2.getInt("lect_id");
	            	
	            }
	            String sql="INSERT INTO appeal(regnumber,course_id,lect_id,letter,reason,status)values(?,?,?,?,?,?)";
	            
	            ps=conn.prepareStatement(sql);
	            ps.setString(1, reg_number);
	            ps.setInt(2, id1);
	            ps.setInt(3, id2);
	            ps.setString(6, "pending");

	            if (inputStream != null) {
	                // fetches input stream of the upload file for the blob column
	                ps.setBlob(4, inputStream);
	            }
	            if (inputStream2 != null) {
	                // fetches input stream of the upload file for the blob column
	                ps.setBlob(5, inputStream2);
	            }
	 
	            // sends the statement to the database server
	            int row = ps.executeUpdate();
	            if (row > 0) {
	                message = "<script>alert('your appealing has been accepted...');window.location='Dashboard/appealing.jsp';</script>";
	            }
	        } catch (SQLException ex) {
	            message = "ERROR: " + ex.getMessage();
	            ex.printStackTrace();
	        } finally {
	            if (conn != null) {
	                // closes the database connection
	                try {
	                    conn.close();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	            // sets the message in request scope
	            request.setAttribute("Message", message);
	             
	            // forwards to the message page
	            getServletContext().getRequestDispatcher("/Dashboard/Message.jsp").forward(request, response);
	        }

	                   
	}

}
