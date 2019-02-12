import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletException;
import java.util.*;
import java.text.SimpleDateFormat;

public class CancelCurrentOrder extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession(false);			
			String product_id = request.getParameter("product_id");
			String order_id = request.getParameter("order_id");
			
			MySQLDataStoreUtilities.deleteOrder(product_id,order_id);
			
			 response.sendRedirect(request.getContextPath()+"/displaycurrentorders");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}