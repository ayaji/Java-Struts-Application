import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class AddNewAccessory extends HttpServlet {
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{			
			String accessory_id = request.getParameter("accessory_id");
			String accessory_name = request.getParameter("accessory_name");
			String accessory_description = request.getParameter("accessory_description");
			Double price_amt = Double.parseDouble(request.getParameter("price_amt"));	
            String product_id = request.getParameter("product_id");
			
			
			Accessory accessory =  new Accessory();
			accessory.setId(accessory_id);
			accessory.setName(accessory_name);
			accessory.setDescription(accessory_description);			
			accessory.setImageUrl("images/noimage.png");
			accessory.setPrice(price_amt);
			
			
			Map<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();
			
			Product product = pMap.get(product_id);
			
			if(product!=null){
				product.getAccessories().put(accessory.getId(), accessory);
				pMap.put(product_id,product);
			}		
			
			response.sendRedirect(request.getContextPath()+"/adminhomepage.html");	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}

