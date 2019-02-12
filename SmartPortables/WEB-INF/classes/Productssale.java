import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class Productssale extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession(false);
			//Map<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();
			Map<String,Product> pMap = MySQLDataStoreUtilities.productssales();
			//pMap =  MySQLDataStoreUtilities.getProductsinventory();
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
							out.println("<h3><a href=\"storemanager.html\"></span></a></h3><br>");
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
					out.println("<li class=\"mainmenu\"><a href=\"addnewproduct.html\">Add Product</a></li>");
				out.println(" <li class=\"mainmenu\"><a href=\"addnewwarranty.html\">Add Warranty</a></li>");
				out.println(" <li class=\"mainmenu\"><a href=\"addnewaccessory.html\">Add New Accessory</a></li>");
							
					 
					out.println("</ul>");
					out.println("</nav>");	
					
					out.println("<section id=\"content\">");
					out.println("<article>");
					out.println("<table style=\"font-size:14px; width:80%;color:#d37639\">");
					out.println("<tr>");
					
					out.println("<h1 style=\"color: #d37639;border-bottom: 2px solid #777;\">Product Sale Details</h1>");
					out.println("</td>");
					
					out.println("</table>");
					
					out.println("<br/>");
					out.println("<br/>");
					out.println("<div >");
					out.println("<table style=\"font-size:14px; width:80%;color:#333334\">");					
					out.println("<tr>");
					out.println("<th align=\"center\" width=\"50%\">");
					out.println("<span style=\"font-size:24px;font-weight: bold\">Product Name</span>");
					out.println("</th>");
					
					out.println("<th align=\"center\" width=\"50%\">");
					out.println("<span style=\"font-size:24px;font-weight: bold\">Product category</span>");
					out.println("</th>");
					
				
					out.println("</tr>");
					out.println("</table>");
							
			
				
			
						
					if(pMap != null){
						if(pMap.isEmpty()){
							out.println("<br/>");
							out.println("<br/>");
							out.println("<h3 style=\"font-weight: bold\">Not enough information available to display this!</h3>");
						}
						else{
							for (Map.Entry<String,Product> entry : pMap.entrySet()) {
								Product product = entry.getValue();
								out.println("<table style=\"font-size:14px; width:80%;color:#333334\">");			    
								out.println("<tr>");
								out.println("<td  width=\"50%\">");
								out.println("<span style=\"font-size:20px;font-weight: bold\">"+product.getName()+"</span>");				   
								out.println("</td>");
								out.println("<td  width=\"50%\">");
								out.println("<span style=\"font-size:20px;font-weight: bold\">"+product.getDisplayUnder()+"</span>");			
								out.println("</td>");
																
								out.println("</tr>");               
								out.println("</table>");
							}
						}
					}
					else{
						out.println("<br/>");
						out.println("<br/>");
						out.println("<h1>SQL Server is not running!</h1>");
					}									
					out.println("</div >");				
					out.println("</article>");				
					out.println("</section>");
					out.println("<aside class=\"sidebar\">");
					out.println("<ul>	");
					out.println("<li>");
					out.println("<h4>Manage Products</h4>");
					out.println("<ul>");
					out.println("<li><a href=\"modifyproduct.html\">Modify Products</a></li>");
					out.println("<li><a href=\"deleteproduct.html\">Delete Products</a></li>");
					out.println("<li><a href=\"deletewarranty.html\">Delete Warranty</a></li>");
					out.println("</ul>");
					out.println("</li>");
					out.println("<li>");
					out.println("<h4>Inventory Information</h4>");
					out.println("<ul>");
					out.println("<li><a href=\"inventory\">Inventory Report</a></li>");
					out.println("<li><a href=\"inventorybar\">Inventory Graph Report</a></li>");
					out.println("<li><a href=\"productssale\">Products on Sale</a></li>");
					out.println("<li><a href=\"prodrebate\">Products with Rebate Amount</a></li>");
					out.println("</ul>");
					out.println("</li>");
					out.println("<li>");
					out.println("<h4>Sales Information</h4>");
					out.println("<ul>");
					out.println("<li><a href=\"sales\">Total Sales Report</a></li>");
					out.println("<li><a href=\"salesgraph\">Sales Graph Report</a></li>");
					out.println("<li><a href=\"salesdaily\">Daily Sales Report</a></li>");
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