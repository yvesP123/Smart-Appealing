

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
 * Servlet implementation class Logincheck
 */
@WebServlet("/Logincheck")
public class Logincheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logincheck() {
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
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		 HttpSession session = request.getSession();
		
		 String username=request.getParameter("username");
		 
		 String password=request.getParameter("password");
		
		 if(Connection.checklogin(username,password)) 
			{
				 session.setAttribute("username", username);
			  
//				out.println("<script>window.location='welcome.jsp';</script>");
				 response.sendRedirect("Dashboard/index2.jsp?username="+username);
				
			       
				
		}
		 else if(Connection.user(username, password)) 
		 {
			 session.setAttribute("username", username);
			  
//				out.println("<script>window.location='welcome.jsp';</script>");
				 response.sendRedirect("Dashboard/index.jsp?username="+username);
			 
		 } 
		
			else
			{
				 RequestDispatcher rs = request.getRequestDispatcher("login.jsp");		 
			   out.println("<font color=red>Username or Password incorrect</font>");
			   rs.include(request, response);
			
			
			
			
		 
	}

	}

}
