import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class HeadPhoneProducts extends HttpServlet {
		
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request,response);
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{	
			HttpSession session = request.getSession(false);
			Map<String,Product> pMap = new HashMap<String,Product>();
			pMap =  MySQLDataStoreUtilities.getProducts();
			//Map<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();
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
					//
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
	out.println("<li class=\"mainmenu\"><a href=\"dealmatch\">Deal Matches</a></li>");
				
					if(session!=null){
						String userTypeInfo = (String)session.getAttribute("userTypeInfo");
						if(userTypeInfo.equalsIgnoreCase("Salesman")){					
							out.println("<li class=\"mainmenu\"><a href=\"addnewuser.html\">Add New User</a></li>");
						}
					}
					out.println("</ul>");
					out.println("</nav>");
	out.println("<br/>");
					out.println("<section id=\"content\">");
					out.println("<article>");
					out.println("<table>");
					out.println("<tr>");
					out.println("<td>");
					out.println("<h1 style=\"color: #d37639;border-bottom: 2px solid #777;float: center\">Headphones</h1>");
					out.println("</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div class=\"displaylist\">");
					
			for (Map.Entry<String, Product> entry : pMap.entrySet()) {
				Product product = entry.getValue();
				if(product.getDisplayUnder().equalsIgnoreCase("headphoneproducts")){
						
					
					
					out.println("<ul>");
					out.println("<li>");
					out.println("<a href=\"showproductinfo?product_id="+product.getId()+"\"><img src=\""+product.getImageUrl()+"\" height=\"140px\" width=\"155px\">");
					out.println("<br/>");
					out.println("<br/>");
					out.println("<span style=\"font-weight: bold;font-size:16px;text-align: center\">");
					out.println(product.getName()+" "+product.getDescription()+"<br/>");
					out.println("<br/>");
					out.println("</span>");
					out.println("<span style=\"font-weight: bold;font-size:26px;text-align: center\">");
					out.println("$"+product.getPrice());
					out.println("</span>");
					out.println("</a>");
					out.println("</li>");
					out.println("</ul>");
					
					
						
				}				
			}	
out.println("</div>");		
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
out.println("<li><a href=\"accessoryinformation?product_id=TV991\">Fitness Watch Accessories</a></li>");
out.println("<li><a href=\"accessoryinformation?product_id=LAP91\">Headphones Accessories</a></li>");
out.println("<li><a href=\"accessoryinformation?product_id=TAB91\">Virtual Reality</a></li>");
out.println("<li><a href=\"accessoryinformation?product_id=TAB91\">Pet Tracker</a></li>");
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