import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;
import java.text.SimpleDateFormat;

public class StoreOrderDetails extends HttpServlet {
		
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request,response);
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		     HttpSession session = request.getSession(false);
			 String first_name = request.getParameter("first_name");
			 String last_name = request.getParameter("last_name");
			 String address1 = request.getParameter("address1");
			// String address2 = request.getParameter("address2");
			 String city = request.getParameter("city");
			 String state = request.getParameter("state");
			 String zipcode = request.getParameter("zipcode");
			 String phone = request.getParameter("phone");			 
			 String card_name = request.getParameter("card_name");
			 String card_number = request.getParameter("card_number");
			 String month = request.getParameter("month");
			 String year = request.getParameter("year");
			 String securityCode = request.getParameter("securityCode");
			 
			 Map<String,Integer> currentShoppingCartItems = new HashMap<String,Integer>();
			 currentShoppingCartItems = (Map<String,Integer>) session.getAttribute("currentShoppingCartItems");
			 
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(new Date());
			 calendar.add(Calendar.DATE, 14); 

			 String orderId = "SmartPort"+System.currentTimeMillis();
			 
			 int result = 1;
			
			 for (Map.Entry<String, Integer> entry : currentShoppingCartItems.entrySet()) {			 
				OrderDetailsInfo orderDetailsInfo = new OrderDetailsInfo();
				orderDetailsInfo.setFirst_name(first_name);
				orderDetailsInfo.setLast_name(last_name);
				orderDetailsInfo.setAddress1(address1);
				//orderDetailsInfo.setAddress2(address2);
				orderDetailsInfo.setCity(city);
				orderDetailsInfo.setState(state);
				orderDetailsInfo.setZipcode(zipcode);
				orderDetailsInfo.setPhone(phone);
				orderDetailsInfo.setCard_name(card_name);
				orderDetailsInfo.setCard_number(card_number);
				orderDetailsInfo.setMonth(month);
				orderDetailsInfo.setYear(year);
				orderDetailsInfo.setSecurityCode(securityCode);			 
				orderDetailsInfo.setOrderNumber(orderId);	
				orderDetailsInfo.setOrderDelivaryDate(calendar.getTime());
				orderDetailsInfo.setOrderStatus("Pending");
				orderDetailsInfo.setItem(entry.getKey());
				orderDetailsInfo.setItemCount(entry.getValue());
				orderDetailsInfo.setUserId((String)session.getAttribute("currentUser"));
				
				result = MySQLDataStoreUtilities.insertOrderDetails(orderDetailsInfo);
			 }
			 
			 if(result == 1){
				 session.removeAttribute("currentShoppingCartItems");
			 }
			 
			 
			 
			 PrintWriter out = response.getWriter();
			out.println("<!doctype html>");
			out.println("<html>");
			out.println("<head>");
					out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
					out.println("<title>Smart Portables</title>");    
					out.println("<link rel=\"stylesheet\" href=\"index_style.css\" type=\"text/css\" />");				
					out.println("</head>");
					out.println("<body>");
					out.println("<div id=\"container\">");
					out.println("<header>");
					out.println("<h1 align=\"left\">");
                    out.println("<div id=\"header\">");
					out.println("<h1><b><i><a href=\"Index.html\" style=\"color:#D54A1E\">Smart Portables</a></h1>");
					out.println("<h2 align=\"left\"><font  color=\"#D5871E\" ><b><i>World's best gadget store...</i></b></font><br></h2>");
                    
					out.println("</div>");
              
                    out.println("</h1>");
						
                    
										
					
					out.println("<div style=\"width: 960px; height:120px\">");
					if(session==null){
						out.println("<a href=\"Index.html\"></a>");
					}
					else{
						String userTypeInfo = (String)session.getAttribute("userTypeInfo");
						if(userTypeInfo.equalsIgnoreCase("Customer")){
							out.println("<a href=\"validlogin.html\"></a>");
						}
						else{
							out.println("<a href=\"salesmanvalidlogin.html\"></a>");
						}
					}
					out.println("<form id=\"searchbox\" action=\"#\">");
					out.println("<input class=\"search_input\" id=\"search\" type=\"text\" placeholder=\"Search items..\" >");
					out.println("<input id=\"submit\" type=\"submit\" value=\"Search\" class=\"searchbutton\">");
					out.println("<ul class=\"navigation\">");
					if(session==null){
						out.println("<li >");
						out.println("<a href=\"#\" style=\"width: 90px; text-align: center;\">Log In&nbsp;<img  src=\"images/arrowdown.png\" width=\"18px\" height=\"13px\"/></a>");
						out.println("<ul>");
						out.println("<li><a href=\"login.html\">Log In</a></li>");
						out.println("<li><a href=\"login.html\">Current Orders</a></li>");				    
						out.println("</ul>");
						out.println("</li>");
					}
					else{
						out.println("<li >");
						out.println("<a href=\"#\" style=\"width: 90px; text-align: center;\">Log Out&nbsp;<img  src=\"images/arrowdown.png\" width=\"18px\" height=\"13px\"/></a>");
						out.println("<ul>");
						out.println("<li><a href=\"logout\">Log Out</a></li>");
						out.println("<li><a href=\"displaycurrentorders\">Current Orders</a></li>");					       
						out.println("</ul>");
						out.println("</li>");
					}
					out.println("<li >");
					out.println("<a href=\"showshoppingcartinfo\" style=\"width: 60px; text-align: center;\">Cart</a>");
					out.println("</li>");
					out.println("</ul>");
					out.println("</form>");
					out.println("</div>");
					out.println("</header>");				
					out.println("<nav>");
					out.println("<ul>");
					out.println("<li class=\"mainmenu\"><a href=\"smartwatchproducts\">Smart Watches</a></li>");
					out.println("<li class=\"mainmenu\"><a href=\"fitnessproducts\">Fitness Watches</a></li>");
					out.println("<li class=\"mainmenu\"><a href=\"headphoneproducts\">Headphones</a></li>");
					out.println("<li class=\"mainmenu\"><a href=\"virtualrealityproducts\">Virtual Reality</a></li>");
					out.println(" <li class=\"mainmenu\"><a href=\"pettracker\">Pet Tracker</a></li>");
					out.println("<li class=\"mainmenu\"><a href=\"phoneproducts\">Phones</a></li>");
					out.println("<li class=\"mainmenu\"><a href=\"laptopproducts\">Laptops</a></li>");
					
					out.println(" <li class=\"mainmenu\"><a href=\"speakerproducts\">Smart Speakers</a></li>");
				
					if(session!=null){
						String userTypeInfo = (String)session.getAttribute("userTypeInfo");
						if(userTypeInfo.equalsIgnoreCase("Salesman")){					
							out.println("<li class=\"mainmenu\"><a href=\"addnewuser.html\">Add New User</a></li>");
						}
					}
					out.println("</ul>");
					out.println("</nav>");
			
			out.println("<section id=\"shoppingcart-content\">");
			out.println("<article>");
			out.println("<div class=\"order-confirmation\">");
			if(result == 1){
				out.println("<table style=\"width: 100%\">");
				out.println("<tr>");
				out.println("<td>");
				out.println("<h1>Order Confirmation</h1>");
				out.println("<h2>Thank you for shopping with us!</h2>");
				out.println("<h3>Your Order number is: "+orderId+"</h3>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td colspan=\"2\">");
				out.println("<p>Your estimated order delivery date is <span style=\"font-weight:bold; color:#333334\">"+dateFormat.format(calendar.getTime())+"</span>. If you choose to cancel your order, it should be 5 days before the delivery date.</p>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				MySQLDataStoreUtilities.updateProductInventory();
			}
			else if(result == 0){
				out.println("<table style=\"width: 100%\">");
				out.println("<tr>");
				out.println("<td>");
				out.println("<h1>Order error</h1>");
				out.println("<h2 style=\"color:red\">MySQL Server is Down!</h2>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
			}
		    out.println("</div>");				
			out.println("</article>");			
			out.println("</section>");
			
			
					out.println("<div class=\"clear\"></div>");				    
					out.println("<footer>");	
					
					out.println("<div class=\"footer-bottom\">");
					out.println("<p>Designed By: Aditya Yaji</p>");
					out.println("<p>Contact information: <a href=\"mailto:someone@example.com\"> ayaji@hawk.iit.edu</a>.</p>");
					out.println("</div>");
					out.println("</footer>");
					out.println("</div>");
					out.println("</body>");
					out.println("</html>");	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}