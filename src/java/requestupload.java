

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
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;  
import java.util.UUID;

//import okhttp3.OkHttpClient;

/**
 * Servlet implementation class requestupload
 */
@WebServlet("/requestupload")
@MultipartConfig(maxFileSize = 16177215)
public class requestupload extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String dbURL = "jdbc:mysql://localhost:3306/appealing";
    private String dbUser = "root";
    private String dbPass = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public requestupload() {
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
		String c_name=request.getParameter("c_name");
		String c_code=request.getParameter("c_code");
		String lect=request.getParameter("lect");
		String phone=request.getParameter("phone");
		String reason=request.getParameter("reason");
         
        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("file1");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
//        Unirest.setTimeouts(0, 0);
//        HttpResponse<String> response = Unirest.post("https://opay-api.oltranz.com/opay/paymentrequest")
//          .header("Content-Type", "application/json")
//          .body("\n{\n  \"telephoneNumber\" : \"250788517392\",\n  \"amount\" : 100.0,\n  \"organizationId\" : \"22278efe-21c1-4381-b07b-1485eb34c9fb\",\n  \"description\" : \"Payment for Printing services\",\n  \"callbackUrl\" : \"http://myonlineprints.com/payments/callback\",\n  \"transactionId\" : \"53ce1863-71ef-4c05-bce5-4d2b9e449bf9\"\n}")
//          .asString();



      
      

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
       

        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
         
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
            rs2=st.executeQuery(sql2);
            int id2 = 0;
            while(rs2.next()){
            	id2 = rs2.getInt("lect_id");
            	
            }
            String sql="INSERT INTO remarks(regnumber,phone,status,course_id,lect_id,reason)values(?,?,?,?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, reg_number);
            ps1.setString(2, phone);
            ps1.setString(3, "pending");
            ps1.setInt(4, id1);
            ps1.setInt(5, id2);
             
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                ps1.setBlob(6, inputStream);
            }
 
            // sends the statement to the database server
            int row = ps1.executeUpdate();
             UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
             OkHttpClient client = new OkHttpClient().newBuilder()
  .build();
MediaType mediaType = MediaType.parse("application/json");
RequestBody body = RequestBody.create(mediaType, "\n{\n  \"telephoneNumber\":\""  + phone + "\",\n  \"amount\" : 100.0,\n  \"organizationId\" : \"22278efe-21c1-4381-b07b-1485eb34c9fb\",\n  \"description\" : \"Payment for Printing services\",\n  \"callbackUrl\" : \"http://myonlineprints.com/payments/callback\",\n  \"transactionId\" : \""  + uuidAsString + "\"\n}");
Request requests;
        requests = new Request.Builder()
                .url("https://opay-api.oltranz.com/opay/paymentrequest")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
Response responses;
        responses = client.newCall(requests).execute();
            if (row > 0) {
                
       
                message = "<script>alert('your request to remark has been acceppted...');window.location='Dashboard/request.jsp';</script>";
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


