import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class Trending extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession(false);
			Map<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();
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
					//--------------------------------------------------------------------------------------------------------
					out.println("<section id=\"content\">");
					out.println("<article>");
					out.println("<table>");
					out.println("<tr>");
					out.println("<td>");
					out.println("<h1 style=\"color: #d37639;border-bottom: 2px solid #777;float: center\">Trending</h1>");
					out.println("</td>");
					out.println("</tr>");
					out.println("</table>");

					out.println("<br/>");
					out.println("<br/>");
					out.println("<h4 align=\"center\" style=\"font-weight: bold\">Top 5 Most Liked Products:</h4>");
					out.println("<br>");
					out.println("<div >");
					out.println("<table style=\"font-size:10px; width:80%;color:#ff3333\">");
					out.println("<tr>");
					out.println("<th align=\"center\" width=\"50%\">");
					out.println("<span style=\"font-size:18px;font-weight: bold\"><u>Product Name</u></span>");
					out.println("</th>");
					out.println("<th align=\"center\" width=\"50%\">");
					out.println("<span style=\"font-size:18px;font-weight: bold\"><u>Avgerage Review Rating</u></span>");
					out.println("</th>");
					out.println("</tr>");
					out.println("</table>");

					Map<String, String> mostLikedProductsMap = MongoDBDataStoreUtilities.getTop5MostLikedProducts();
					
					if(mostLikedProductsMap != null){
						if(mostLikedProductsMap.isEmpty()){
							out.println("<br/>");
							out.println("<br/>");
							out.println("<h3 style=\"font-weight: bold\">Not enough information available to display this!</h3>");
						}
						else{
							for (Map.Entry<String,String> entry : mostLikedProductsMap.entrySet()) {
								out.println("<table style=\"font-size:14px; width:80%;color:#ff3333\">");
								out.println("<tr>");
								out.println("<td  width=\"50%\">");
								out.println("<span style=\"font-size:15px;font-weight: bold\">"+entry.getKey()+"</span>");
								out.println("</td>");
								out.println("<td align=\"center\" width=\"50%\">");
								out.println("<span style=\"font-size:15px;font-weight: bold\">"+entry.getValue()+"</span>");
								out.println("</td>");
								out.println("</tr>");
								out.println("</table>");
							}
						}
					}
					else{
						out.println("<br/>");
						out.println("<br/>");
						out.println("<h1>MongoDB Server is not running!</h1>");
					}
					out.println("</div >");
					//-------------------------------------------------------------------------------------------------
					out.println("<br/>");
					out.println("<br/>");
					out.println("<h4 align=\"center\" style=\"font-weight: bold\">Top 5 Zipcodes where maximum number of products Reviewed</h4>");
					out.println("<br>");
					out.println("<div >");
					out.println("<table style=\"font-size:14px; width:80%;color:#ff3333\">");
					out.println("<tr>");
					out.println("<th align=\"center\" width=\"50%\">");
					out.println("<span style=\"font-size:18px;font-weight: bold\"><u>Zipcode</u></span>");
					out.println("</th>");
					out.println("<th align=\"center\" width=\"50%\">");
					out.println("<span style=\"font-size:18px;font-weight: bold\"><u>Number of Items Reviewed</u></span>");
					out.println("</th>");
					out.println("</tr>");
					out.println("</table>");
					
					Map<String, String> mostProductsSoldZipcodeMap = MongoDBDataStoreUtilities.getTop5MostProductsSoldZipcode();
					
					if(mostProductsSoldZipcodeMap != null){
						if(mostProductsSoldZipcodeMap.isEmpty()){
							out.println("<br/>");
							out.println("<br/>");
							out.println("<h3 style=\"font-weight: bold\">Not enough information available to display this!</h3>");
						}
						else{
							for (Map.Entry<String,String> entry : mostProductsSoldZipcodeMap.entrySet()) {
								out.println("<table style=\"font-size:14px; width:80%;color:#ff3333\">");
								out.println("<tr>");
								out.println("<td  width=\"50%\">");
								out.println("<span style=\"font-size:15px;font-weight: bold\">"+entry.getKey()+"</span>");
								out.println("</td>");
								out.println("<td align=\"center\" width=\"50%\">");
								out.println("<span style=\"font-size:15px;font-weight: bold\">"+entry.getValue()+"</span>");
								out.println("</td>");
								out.println("</tr>");
								out.println("</table>");
							}
						}
					}
					else{
						out.println("<br/>");
						out.println("<br/>");
						out.println("<h1>MongoDB Server is not running!</h1>");
					}


					out.println("</div >");
					//---------------------------------------------------------------------------------------------------------------
					out.println("<br/>");
					out.println("<br/>");
					out.println("<h4 align=\"center\" style=\"font-weight: bold\">Top 5 most reviewed products regardless of rating :</h4>");
					out.println("<br>");
					out.println("<h3 style=\"font-weight: bold\"></h3>");
					out.println("<div >");
					out.println("<table style=\"font-size:14px; width:80%;color:#ff3333\">");
					out.println("<tr>");
					out.println("<th align=\"center\" width=\"50%\">");
					out.println("<span style=\"font-size:18px;font-weight: bold\"><u>Product</u></span>");
					out.println("</th>");
					out.println("<th align=\"center\" width=\"50%\">");
					out.println("<span style=\"font-size:18px;font-weight: bold\"><u>Number of Reviews</u></span>");
					out.println("</th>");
					out.println("</tr>");
					out.println("</table>");

					Map<String, String> mostSoldProductMap = MongoDBDataStoreUtilities.getTop5MostSoldProducts();

					if(mostSoldProductMap != null){
						if(mostSoldProductMap.isEmpty()){
							out.println("<br/>");
							out.println("<br/>");
							out.println("<h3 style=\"font-weight: bold\">Not enough information available to display this!</h3>");
						}
						else{
							for (Map.Entry<String,String> entry : mostSoldProductMap.entrySet()) {
								out.println("<table style=\"font-size:14px; width:80%;color:#ff3333\">");
								out.println("<tr>");
								out.println("<td  width=\"50%\">");
								out.println("<span style=\"font-size:15px;font-weight: bold\">"+entry.getKey()+"</span>");
								out.println("</td>");
								out.println("<td align=\"center\" width=\"50%\">");
								out.println("<span style=\"font-size:15px;font-weight: bold\">"+entry.getValue()+"</span>");
								out.println("</td>");
								out.println("</tr>");
								out.println("</table>");
							}
						}
					}
					else{
						out.println("<br/>");
						out.println("<br/>");
						out.println("<h1>MongoDB Server is not running!</h1>");
					}


					out.println("</div >");





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
					out.println("<h4>Options</h4>");
					out.println("<ul>");
					out.println("<li><a href=\"trending\">Trending</a></li>");
					out.println("<li><a href=\"#\">Encash Points</a></li>");
					out.println(" <li><a href=\"#\">Exchange Offers</a></li>");
					out.println(" <li><a href=\"#\">Price Match Guarantee</a></li>");
					out.println("</ul>");
					out.println("</li>");
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
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
