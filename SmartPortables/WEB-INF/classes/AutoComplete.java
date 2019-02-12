import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class AutoComplete extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{			
			StringBuffer sb = new StringBuffer();
			String searchText = request.getParameter("searchText");
			String action = request.getParameter("action");
			
			System.out.println("***** search Text "+searchText);
			System.out.println("***** search Text "+action);
			
			if (action.equals("findMatch")){
				
				System.out.println("***** search Text1 "+searchText);
			System.out.println("***** search Text1 "+action);
			
				if (!searchText.trim().equals("")){
					AjaxUtility aUtility = new AjaxUtility();
					sb = aUtility.findMatachingProducts(searchText.trim());
					if(sb != null && !sb.equals("")){
						System.out.println("***** sb "+sb.toString());
						
						response.setContentType("text/xml");
						response.setHeader("Cache-Control", "no-cache");
						response.getWriter().write("<products>" + sb.toString() + "</products>");
					}
				}
			}
			
			if (action.equals("lookup")){
				String productId = request.getParameter("productId");
				response.sendRedirect(request.getContextPath()+"/showproductinfo?product_id="+productId);					
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
}

