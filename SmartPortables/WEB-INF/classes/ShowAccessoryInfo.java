import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class ShowAccessoryInfo extends HttpServlet {
		
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request,response);
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{				
			String product_id = request.getParameter("product_id");
			String accessory_id = request.getParameter("accessory_id");
			HttpSession session = request.getSession(false);
			Map<String,Product> pMap = new HashMap<String,Product>();
			pMap =  MySQLDataStoreUtilities.getProducts();
			
			Product product = pMap.get(product_id);
			Map<String,Accessory> accessories = product.getAccessories();
			Accessory accessory = accessories.get(accessory_id);
			
			PrintWriter out = response.getWriter();					
				
		    out.println("<!doctype html>");
		    out.println("<html>");
			out.println("<head>");
			out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
					out.println("<title>Smart Portables</title>");    
					out.println("<link rel=\"stylesheet\" href=\"index_style.css\" type=\"text/css\" />");				
			out.println("<script type=\"text/javascript\" src=\"javascript.js\"></script>");
			out.println("</head>");
			out.println("<body onload=\"init()\">");
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
			out.println("<input class=\"search_input\" id=\"search\" type=\"text\" placeholder=\"Search items..\" onkeyup=\"doCompletion(this.value)\" >");
				out.println("<div id=\"auto-row\">");
				out.println("<table id=\"complete-table\" class=\"gridtable\" ></table>");
				out.println("</div>");																									 
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
				out.println("<section id=\"content\">");
				out.println("<article>");
				out.println("<table class=\"wrapper\">");
				out.println("<tr>");
				out.println("<td colspan=\"2\">");
				out.println("<h1>");
				out.println("&nbsp;"+accessory.getName()+" "+accessory.getDescription());
				out.println("</h>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>");				
				out.println("<img src=\""+accessory.getImageUrl()+"\" height=\"260px\" width=\"280px\"");
				out.println("</td>");
				out.println("<td>");				
				out.println("<h2>");
				out.println("Buy New");
				out.println("</h2>");
				out.println("<br/>");
				out.println("<h3>");
				out.println("<em>$</em>");
				out.println("<span>"+accessory.getPrice()+"<span>");
				out.println("</h3>");
				out.println("<br/>");
				out.println("<br/>");
				out.println("<button onclick=\"location.href='storetoshoppingcart?product_id="+accessory.getId()+"&actproduct_id="+product_id+"&requestFrom=showAccessory'\" class=\"sign-in-up-button\">&nbsp;&nbsp;Buy&nbsp;</button>");
				out.println("<br/>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");				
				out.println("</article>");
				out.println("<article>");
				out.println("<br/>");
				out.println("<br/>");			
				out.println("</article>");
				out.println("</section>");
				
				out.println("<aside class=\"sidebar\">");
					out.println("<ul>	");
					out.println("<li>");
					out.println("<h4>Products</h4>");
					out.println("<ul>");
					out.println("<li class=\"mainmenu\"><a href=\"smartwatchproducts\">Smart Watches</a></li>");
					out.println("<li class=\"mainmenu\"><a href=\"fitnessproducts\">Fitness Watches</a></li>");
					out.println(" <li class=\"mainmenu\"><a href=\"headphoneproducts\">Headphones</a></li>");
					out.println("<li class=\"mainmenu\"><a href=\"virtualrealityproducts\">Virtual Reality</a></li>");
					out.println("<li class=\"mainmenu\"><a href=\"pettracker\">Pet Tracker</a></li>");
					out.println("<li class=\"mainmenu\"><a href=\"phoneproducts\">Phones</a></li>");
					out.println("<li class=\"mainmenu\"><a href=\"laptopproducts\">Laptops</a></li>");
					out.println(" <li class=\"mainmenu\"><a href=\"speakerproducts\">Speakers</a></li>");
					
					
					
					
					out.println("<li>");
out.println("<h4>Accessories</h4>");
out.println("<ul>");
out.println("<li><a href=\"accessoryinformation?product_id=TV991\">Smart Watch Accessories</a></li>");
out.println("<li><a href=\"accessoryinformation?product_id=FP991\">Fitness Watch Accessories</a></li>");
out.println("<li><a href=\"accessoryinformation?product_id=H0001\">Headphones Accessories</a></li>");
out.println("<li><a href=\"accessoryinformation?product_id=VRP91\">Virtual Reality</a></li>");
out.println("<li><a href=\"accessoryinformation?product_id=PT091\">Pet Tracker</a></li>");
out.println("<li><a href=\"accessoryinformation?product_id=SMP91\">Phone Accessories</a></li>");
out.println("<li><a href=\"accessoryinformation?product_id=TOP91\">Laptop Accessories</a></li>");
out.println("<li><a href=\"accessoryinformation?product_id=TAB91\">SmartSpeakers Accessories</a></li>");
out.println("</ul>");
out.println("</li>");	
					out.println("</ul>");
					out.println("</aside>");
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
			
			
			