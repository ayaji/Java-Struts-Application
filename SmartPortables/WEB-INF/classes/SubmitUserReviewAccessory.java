import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class SubmitUserreviewAccessory extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{		
		
				Reviewa reviewa = new Reviewa();
				reviewa.setProductMN(request.getParameter("productMN"));
				reviewa.setProductC(request.getParameter("productC"));
				reviewa.setProductId((String)request.getSession(false).getAttribute("itemId"));
				reviewa.setProductP(Double.valueOf(request.getParameter("productP")));
				reviewa.setProductRN(request.getParameter("productRN"));
				reviewa.setProductRZ(request.getParameter("productRZ"));
				reviewa.setProductRC(request.getParameter("productRC"));
				reviewa.setProductRS(request.getParameter("productRS"));
				reviewa.setProductOS(request.getParameter("productOS"));
				reviewa.setProductMrN(request.getParameter("productMrN"));
				reviewa.setProductMR(request.getParameter("productMR"));
				reviewa.setProductRvU(request.getParameter("productRvU"));
				reviewa.setProductRvA(request.getParameter("productRvA"));
				reviewa.setProductRvG(request.getParameter("productRvG"));
				reviewa.setProductRvO(request.getParameter("productRvO"));
				reviewa.setProductRvD(request.getParameter("productRvD"));
				reviewa.setProductRvS(request.getParameter("productRvS"));
				reviewa.setProductRvB(request.getParameter("productRvB"));
				reviewa.setProductRvR(Integer.valueOf(request.getParameter("productRvR")));	
				
				MongoDBDataStoreUtilities.insertUserreview(review);
				
				
				
				if(request.getParameter("pageId").equals("product")){
					request.getSession(false).removeAttribute("itemId");
					response.sendRedirect(request.getContextPath()+"/showaccessoryinfo?accessory_id="+reviewa.getId());
				}
				else if(request.getParameter("pageId").equals("accessory")){					
					String product_id = (String)request.getSession(false).getAttribute("accmainitemId");
					request.getSession(false).removeAttribute("itemId");
					request.getSession(false).removeAttribute("accmainitemId");
					response.sendRedirect(request.getContextPath()+"/showaccessoryinfo?accessory_id="+reviewa.getId());
				}
				
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}