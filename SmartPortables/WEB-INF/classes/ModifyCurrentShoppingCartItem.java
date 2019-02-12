import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class ModifyCurrentShoppingCartItem extends HttpServlet {		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			 HttpSession session = request.getSession(false);			 
			 if(session!=null){				
				 String product_id = request.getParameter("product_id");
				 Integer itemCount = Integer.parseInt(request.getParameter("itemCount"));
				 
				 if(itemCount < 1){
					 response.sendRedirect(request.getContextPath()+"/showshoppingcartinfo");
				 }
				 else {
					 if(session.getAttribute("currentShoppingCartItems")!=null){
						 Map currentShoppingCartItems = new HashMap();
						 currentShoppingCartItems = (Map) session.getAttribute("currentShoppingCartItems");
						 currentShoppingCartItems.put(product_id,itemCount);
						 session.setAttribute("currentShoppingCartItems", currentShoppingCartItems);	
					 }			
					 response.sendRedirect(request.getContextPath()+"/showshoppingcartinfo");
				 }
				 
			 }
			 else{
				 response.sendRedirect(request.getContextPath()+"/login.html");
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	
	}
}