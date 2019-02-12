import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class ModifyProduct extends HttpServlet {
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{			
			String product_id = request.getParameter("product_id");
			String product_name = request.getParameter("product_name");
			String product_description = request.getParameter("product_description");
			
			
			
			
			
			
			
			String imageUrl = "images/" + request.getParameter("imageUrl");
			
			Double price_amt = 0.0;
			boolean priceChange = false;
			if(!request.getParameter("price_amt").isEmpty()){
				price_amt = Double.parseDouble(request.getParameter("price_amt"));	
				priceChange = true;
			}				
			String displayUnder = request.getParameter("displayUnder");	
			Double discount_amt = Double.parseDouble(request.getParameter("discount_amt"));
			Double rebate_amt = Double.parseDouble(request.getParameter("rebate_amt"));
			Double prodcount = Double.parseDouble(request.getParameter("prodcount"));
			//boolean discountChange = false;
			/* if(!request.getParameter("discount_amt").isEmpty()){
				discount_amt = Double.parseDouble(request.getParameter("discount_amt"));
				discountChange = true;
			}
			
			Double rebate_amt = 0.0;
			boolean rebateChange = false;
			if(!request.getParameter("rebate_amt").isEmpty()){			
				rebate_amt = Double.parseDouble(request.getParameter("rebate_amt"));
				rebateChange = true;
			} */
			
						
			Map<String,Product> pMap = new HashMap<String,Product>();
			pMap =  MySQLDataStoreUtilities.getProducts();
			
			Product product = pMap.get(product_id);
			
			if(product!=null){
				if(!product_name.isEmpty()){
					product.setName(product_name);
				}				
				if(!product_description.isEmpty()){
					product.setDescription(product_description);
				}
				if(!displayUnder.isEmpty()){
					product.setDisplayUnder(displayUnder);
				}
				if(price_amt!=null){
					product.setPrice(price_amt);
				}
				if(imageUrl!=null){
					product.setImageUrl(imageUrl);
				}
				if(discount_amt!=null){
					product.setDiscounAmt(discount_amt);
				}
				 if(rebate_amt!=null){
					product.setRebateAmt(rebate_amt);
				} 
				if(prodcount!=null){
					product.setProdcount(prodcount);
				}
				
				pMap.put(product_id,product);
				MySQLDataStoreUtilities.updateProduct(product);
			}
			
			
			response.sendRedirect(request.getContextPath()+"/adminhomepage.html");	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}

