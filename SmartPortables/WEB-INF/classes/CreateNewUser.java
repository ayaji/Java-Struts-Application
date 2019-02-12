import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;


import java.util.*;

public class CreateNewUser extends HttpServlet {
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{		
			String firstName = request.getParameter("first_name");
			String lastName = request.getParameter("last_name");
			String userId = request.getParameter("user_id");
			String password = request.getParameter("password");			
			String userType = request.getParameter("user_type");
						
			HttpSession session=request.getSession(false);  
			
			HashMap<String, User> users = MySQLDataStoreUtilities.getUsers();
			
			if(users != null){
				if(!users.containsKey(userId)){
					User user = new User();
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setUserId(userId);
					user.setPassword(password);
					user.setUserType(userType);
					MySQLDataStoreUtilities.insertUser(user);
					
					if(session==null){
						session=request.getSession();  	        	
						if(userType.equalsIgnoreCase("Customer")){
							session.setAttribute("currentUser",userId);  
							session.setAttribute("userTypeInfo",userType);
							response.sendRedirect(request.getContextPath()+"/validlogin.html");
						}
						else if(userType.equalsIgnoreCase("Salesman")){
							session.setAttribute("currentUser",userId);  
							session.setAttribute("userTypeInfo",userType);
							response.sendRedirect(request.getContextPath()+"/salesmanvalidlogin.html");
						}
					}
					else{
						response.sendRedirect(request.getContextPath()+"/salesmanvalidlogin.html");
					}
				}
				else{
					if(session==null){					
						response.sendRedirect(request.getContextPath()+"/invalidsignup.html");				
					}
					else{
						response.sendRedirect(request.getContextPath()+"/invalidadduser.html");	
					}
				}				
			}
			else{
				if(session==null){					
					response.sendRedirect(request.getContextPath()+"/signupserverdown.html");				
				}
				else{
					response.sendRedirect(request.getContextPath()+"/adduserserverdown.html");	
				}
			}			
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}

