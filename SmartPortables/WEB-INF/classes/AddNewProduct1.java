import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class AddNewProduct extends HttpServlet {
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{			
			String product_id = request.getParameter("product_id");
			String product_name = request.getParameter("product_name");
			String product_description = request.getParameter("product_description");
			Double price_amt = Double.parseDouble(request.getParameter("price_amt"));			
			String displayUnder = request.getParameter("displayUnder");			
			Product product =  new Product();
			product.setId(product_id);
			product.setName(product_name);
			product.setDescription(product_description);
			product.setDisplayUnder(displayUnder);
			product.setImageUrl("images/noimage.png");
			product.setPrice(price_amt);
			//product.setDiscounAmt(discount_amt);
			//product.setRebateAmt(rebate_amt);
			
			Map<String,Product> productsInfoMap = new HashMap<String,Product>();
		    productsInfoMap =  MySQLDataStoreUtilities.getProducts();
			productsInfoMap.put(product_id,product);
			MySQLDataStoreUtilities.insertProduct(product);
			
			
			response.sendRedirect(request.getContextPath()+"/adminhomepage.html");	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}

