import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class StoreShoppingCart extends HttpServlet {
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			 HttpSession session = request.getSession(false);
			 
			 if(session!=null){
				 
				 String requestFrom = request.getParameter("requestFrom");
				 String product_id= request.getParameter("product_id");
				 String actproduct_id= request.getParameter("actproduct_id");
				 
				 
				 if(session.getAttribute("currentShoppingCartItems")==null){
					 
					 Map items = new HashMap();
					 items.put(product_id,1);
					 session.setAttribute("currentShoppingCartItems", items);	
				 }
				 else{
					 Map currentShoppingCartItems = new HashMap();
					 currentShoppingCartItems = (Map) session.getAttribute("currentShoppingCartItems");
					 if(currentShoppingCartItems.get(product_id)==null){
						 currentShoppingCartItems.put(product_id,1);
						 session.setAttribute("currentShoppingCartItems", currentShoppingCartItems);	
					 }
					 else{
						 int numOfItems = (Integer) currentShoppingCartItems.get(product_id);
						 numOfItems++;
						 currentShoppingCartItems.put(product_id,numOfItems);
						 session.setAttribute("currentShoppingCartItems", currentShoppingCartItems);	
					 }
				 }							 
				 if(requestFrom.equals("showProduct")){
					response.sendRedirect(request.getContextPath()+"/showproductinfo?product_id="+product_id);
				 }
				 else if(requestFrom.equals("showAccessory")){
					 response.sendRedirect(request.getContextPath()+"/showaccessoryinfo?accessory_id="+product_id+"&product_id="+actproduct_id);
				 }
				 else if(requestFrom.equals("showWarranty")){
					 response.sendRedirect(request.getContextPath()+"/showwarrantyinfo?warranty_id="+product_id);
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
}