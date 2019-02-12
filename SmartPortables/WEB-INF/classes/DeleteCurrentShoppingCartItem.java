import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class DeleteCurrentShoppingCartItem extends HttpServlet {		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try{
			 HttpSession session = request.getSession(false);			 
			 if(session!=null){				
				 String product_id = request.getParameter("product_id");
				 if(session.getAttribute("currentShoppingCartItems")!=null){
					Map currentShoppingCartItems = new HashMap();
					currentShoppingCartItems = (Map) session.getAttribute("currentShoppingCartItems");
					currentShoppingCartItems.remove(product_id);
					if(currentShoppingCartItems.size() > 0){
						session.setAttribute("currentShoppingCartItems", currentShoppingCartItems);	
					}
					else{
						session.removeAttribute("currentShoppingCartItems");
					}
				}			
				response.sendRedirect(request.getContextPath()+"/showshoppingcartinfo");				 
			 }
			 else{
				 response.sendRedirect(request.getContextPath()+"/login.html");
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}