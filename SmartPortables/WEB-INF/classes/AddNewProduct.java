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
			String imageUrl = "images/" + request.getParameter("imageUrl");
			Double price_amt = Double.parseDouble(request.getParameter("price_amt"));			
			String displayUnder = request.getParameter("displayUnder");			
			Double discount_amt = 0.0;
			if(!request.getParameter("discount_amt").isEmpty())
				discount_amt = Double.parseDouble(request.getParameter("discount_amt"));
			Double rebate_amt = 0.0;
			if(!request.getParameter("rebate_amt").isEmpty())				
				rebate_amt = Double.parseDouble(request.getParameter("rebate_amt"));
			
			//Double discount_amt = Double.parseDouble(request.getParameter("discount_amt"));
			//Double rebate_amt = Double.parseDouble(request.getParameter("rebate_amt"));
			Double prodcount = Double.parseDouble(request.getParameter("prodcount"));
			
			Product product =  new Product();
			product.setId(product_id);
			product.setName(product_name);
			product.setDescription(product_description);
			product.setDisplayUnder(displayUnder);
			product.setImageUrl(imageUrl);
			
			product.setPrice(price_amt);
			product.setDiscounAmt(discount_amt);
			product.setRebateAmt(rebate_amt);
			product.setProdcount(prodcount);
			
			Map<String,Product> productsInfoMap = new HashMap<String,Product>();
		    productsInfoMap =  MySQLDataStoreUtilities.getProducts();
			productsInfoMap.put(product_id,product);
			MySQLDataStoreUtilities.insertProduct(product);
		//	Map<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();
		//	pMap.put(product_id,product);
			/* PrintWriter out = response.getWriter();
					out.println("<h1>" + product.getId() + "</h1>");
					out.println("<h1>" + product.getName() + "</h1>");
					out.println("<h1>" + product_description + "</h1>"); */
			//pMap.put(product_id,product);
			response.sendRedirect(request.getContextPath()+"/adminhomepage.html");	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	
	
}

