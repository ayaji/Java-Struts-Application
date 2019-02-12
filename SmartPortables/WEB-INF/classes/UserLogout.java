import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class UserLogout extends HttpServlet {		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try{
			 HttpSession session = request.getSession(false);
			 session.invalidate();
			 response.sendRedirect(request.getContextPath()+"/Index.html");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}