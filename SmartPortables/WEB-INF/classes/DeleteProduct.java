import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class DeleteProduct extends HttpServlet {
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{			
			
            String product_id = request.getParameter("product_id");
//Changes*****************************************					
			Map<String,Product> productsInfoMap = new HashMap<String,Product>();
			productsInfoMap =  MySQLDataStoreUtilities.getProducts();
			productsInfoMap.remove(product_id);		
			
			MySQLDataStoreUtilities.deleteProduct(product_id);
//*********************************************
			
			response.sendRedirect(request.getContextPath()+"/adminhomepage.html");	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}

