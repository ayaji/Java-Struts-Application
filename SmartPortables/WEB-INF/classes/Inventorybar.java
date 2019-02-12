import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class Inventorybar extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession(false);
			
			Map<String,Product> pMap = MySQLDataStoreUtilities.getProductsinventorygraph();
			
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
                    out.println("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");
                    out.println("<script type=\"text/javascript\">");
                    out.println("google.charts.load('current', {'packages':['bar']});");
                    out.println("google.charts.setOnLoadCallback(drawStuff);");
                    out.println("function drawStuff() {");
                    out.println("var data = new google.visualization.arrayToDataTable([");
                    out.println("['Category', 'Items available'],");
                    
                    for (Map.Entry<String, Product> entry : pMap.entrySet()) 
                    {
                    	Product product = entry.getValue();
                    	out.println("['"+product.getDisplayUnder()+"', "+product.getProdcount()+"],");       
                     } 
                    out.println("]);"); 
                                                                                                        
                    out.println("var options = { ");
                    out.println("width: 600,");
                    out.println("chart: {");
                    out.println("title: 'Inventory Details',");
                    out.println("},");
                    out.println("bars: 'horizontal',");
                    out.println("series: {");
                    out.println("0: { axis: 'distance' }, ");
                    out.println("//1: { axis: 'brightness' } ");
                    out.println("},");
                    out.println("axes: {");
                    out.println("x: { ");
                    out.println("distance: {label: 'No. of Items'},");
                    out.println("} ");
                    out.println("} ");
                    out.println("};");
                    out.println("var chart = new google.charts.Bar(document.getElementById('dual_x_div'));");
                    out.println("chart.draw(data, options);");
                    out.println("};");
                    out.println("</script>");
                    out.println("<div id=\"dual_x_div\" style=\"width: 600px; height: 500px;\"></div>");
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