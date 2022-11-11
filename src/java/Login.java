

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		 HttpSession session = request.getSession();
		
		 String username=request.getParameter("username");
		 
		 String password=request.getParameter("password");
		
		 if(Connections.checklogin(username,password)) 
			{
				 session.setAttribute("username", username);
			  
//				out.println("<script>window.location='welcome.jsp';</script>");
				 response.sendRedirect("Dashboard/index2.jsp?username="+username);
				
			       
				
		}
		 else if(Connections.user(username, password)) 
		 {
			 session.setAttribute("username", username);
			  
//				out.println("<script>window.location='welcome.jsp';</script>");
				 response.sendRedirect("Dashboard/index.jsp?username="+username);
			 
		 } 
		 else if(Connections.Teacher(username, password)) 
		 {
			 session.setAttribute("username", username);
			  
//				out.println("<script>window.location='welcome.jsp';</script>");
				 response.sendRedirect("Dashboard/index3.jsp?username="+username);
			 
		 } 
		
			else
			{
				 RequestDispatcher rs = request.getRequestDispatcher("login.jsp");		 
			   out.println("<font color=red>Username or Password incorrect</font>");
			   rs.include(request, response);
			
			
			
			
		 
	}

	}

}
