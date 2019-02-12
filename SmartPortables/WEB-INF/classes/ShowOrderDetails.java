import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;
import java.text.SimpleDateFormat;

public class ShowOrderDetails extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession(false);	
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			if(session != null){	

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
					
				try{
					
					HashMap<String,ArrayList<OrderDetailsInfo>> orderDetailsInfoMap =  MySQLDataStoreUtilities.getOrderDetails();
					
					
					if(orderDetailsInfoMap.isEmpty()){
						out.println("<div id=\"content\">");
						out.println("<section id=\"shoppingcart-content\">");
						out.println("<article>");
						out.println("<div class=\"shopping-cart-empty\">");
						out.println("<table style=\"width: 100%\">");
					    out.println("<tr>");
					    out.println("<td>");
					    out.println("<h1>Current Orders</h1>");
					    out.println("<h2>There are no orders to display!</h2>");
					    out.println("</td>");
					    out.println("</tr>");
					    out.println("<tr>");
					    out.println("<td colspan=\"2\">");
					    out.println("<p>You haven't order anything yet. Go ahead and order some cool stuffs! </p>");
					    out.println("</td>");
					    out.println("</tr>");
					    out.println("</table>");
					    out.println("</div>");				
						out.println("</article>");						
						out.println("</section>");
					}
					else{
										
						
						if(orderDetailsInfoMap.containsKey((String)session.getAttribute("currentUser"))){
						        out.println("<section id=\"shoppingcart-content\">");
								//out.println("<article>");
								out.println("<div class=\"shopping-cart\">");
								out.println("<table class=\"display-shoppingcart\">");
								out.println("<tr>");
								out.println("<td>");
								out.println("<h1>My Orders</h1>");
								out.println("</td>");
								out.println("</tr>");
								out.println("</table>");
								out.println("<table class=\"shoppingcartitem-table\">");
								out.println("<tr>");
								out.println("<th>");
								out.println("</th>");
								out.println("<th>");
								out.println("Product Description");
								out.println("</th>");
								out.println("<th>");
								out.println("Price");
								out.println("</th>");
								out.println("<th>");
								out.println("Quantity");
								out.println("</th>");
								out.println("<th>");
								out.println("Ship To");
								out.println("</th>");
								out.println("<th>");
								out.println("Delivery");
								out.println("</th>");
								out.println("<th>");
								out.println("Status");
								out.println("</th>");
								out.println("<th>");
								out.println("Cancel");
								out.println("</th>");
								out.println("</tr>");
								
						ArrayList<OrderDetailsInfo> orderDetailsInfoArrList = orderDetailsInfoMap.get((String)session.getAttribute("currentUser"));
					
								
								for (OrderDetailsInfo orderDetailsInfo: orderDetailsInfoArrList) {
									
									Map<String,Product> pMap = new HashMap<String,Product>();
									pMap = 	MySQLDataStoreUtilities.getProducts();
									Map<String,Warranty> wMap = new HashMap<String,Warranty>();				
									wMap = MySQLDataStoreUtilities.getWarranty();
									
								//	Map<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();							
								//	Map<String,Warranty> wMap = (HashMap<String,Warranty>)WarrantyUtility.getWarrantyMap();
									
									if(pMap.get(orderDetailsInfo.getItem())!=null){
										Product product = pMap.get(orderDetailsInfo.getItem());
										out.println("<tr>");
										out.println("<td>");
										out.println("<img src=\""+product.getImageUrl()+"\" width=\"150px\" height=\"140px\"/>");
										out.println("</td>");
										out.println("<td width=\"20%\">");
										out.println(product.getName()+" "+product.getDescription());
										out.println("</td>");
										if(product.getRebateAmt()!=0.0 && product.getDiscounAmt()!=0.0){										
											Double reducedPrice = product.getPrice() - (product.getRebateAmt()+product.getDiscounAmt());
											out.println("<td>");
											out.println("$"+reducedPrice);
											out.println("</td>");
										
										}
										else if(product.getDiscounAmt()!=0.0){
											Double reducedPrice = product.getPrice() - product.getDiscounAmt();
											out.println("<td>");
											out.println("$"+reducedPrice);
											out.println("</td>");
										}
										else if(product.getRebateAmt() !=0.0){
											Double reducedPrice = product.getPrice() - product.getRebateAmt();
											out.println("<td>");
											out.println("$"+reducedPrice);
											out.println("</td>");
										}
										else{
											out.println("<td>");
											out.println("$"+product.getPrice());
											out.println("</td>");
										}
										out.println("<td>");							    
										out.println(orderDetailsInfo.getItemCount());						
										out.println("</td>");										
										out.println("<td width=\"20%\">");					    
										out.println(orderDetailsInfo.getFirst_name()+" "+orderDetailsInfo.getLast_name()+"<br/>"+orderDetailsInfo.getAddress1());
								   
										if(!orderDetailsInfo.getAddress1().isEmpty()){
											out.println("<br/>"+orderDetailsInfo.getAddress2());
										}
										out.println("<br/>"+orderDetailsInfo.getCity()+", "+orderDetailsInfo.getState()+" "+orderDetailsInfo.getZipcode());						
										out.println("</td>");
										out.println("<td>");							    
										out.println(dateFormat.format(orderDetailsInfo.getOrderDelivaryDate()));						
										out.println("</td>");
										out.println("<td>");							    
										out.println(orderDetailsInfo.getOrderStatus());						
										out.println("</td>");							    
										out.println("<td>");
										out.println("<a href=\"cancelcurrentorder?product_id="+orderDetailsInfo.getItem()+"&order_id="+orderDetailsInfo.getOrderNumber()+"\">Cancel</a>");
										out.println("</td>");
										out.println("</tr>");
										
										
									}
									else if(wMap.get(orderDetailsInfo.getItem())!=null){
										Warranty warranty = wMap.get(orderDetailsInfo.getItem());
										
										out.println("<tr>");
										out.println("<td>");
										out.println("<img src=\"images/noimage.png\" width=\"150px\" height=\"140px\"/>");
										out.println("</td>");
										out.println("<td width=\"20%\">");
										out.println(warranty.getName()+" "+warranty.getDescription());
										out.println("</td>");
										out.println("<td>");
										out.println("$"+warranty.getPrice());
										out.println("</td>");
										
										out.println("<td>");							    
										out.println(orderDetailsInfo.getItemCount());						
										out.println("</td>");										
										out.println("<td width=\"20%\">");					    
										out.println(orderDetailsInfo.getFirst_name()+" "+orderDetailsInfo.getLast_name()+"<br/>"+orderDetailsInfo.getAddress1());
								   
										if(!orderDetailsInfo.getAddress1().isEmpty()){
											out.println("<br/>"+orderDetailsInfo.getAddress2());
										}
										out.println("<br/>"+orderDetailsInfo.getCity()+", "+orderDetailsInfo.getState()+" "+orderDetailsInfo.getZipcode());						
										out.println("</td>");
										out.println("<td>");							    
										out.println(dateFormat.format(orderDetailsInfo.getOrderDelivaryDate()));						
										out.println("</td>");
										out.println("<td>");							    
										out.println(orderDetailsInfo.getOrderStatus());						
										out.println("</td>");							    
										out.println("<td>");
										out.println("<a href=\"cancelcurrentorder?product_id="+orderDetailsInfo.getItem()+"&order_id="+orderDetailsInfo.getOrderNumber()+"\">Cancel</a>");
										out.println("</td>");
										out.println("</tr>");
									}
									else{
										Map<String, Accessory> aMap = new HashMap<String,Accessory>();
										for (Map.Entry<String, Product> pEntry : pMap.entrySet()) {
											Product product = pEntry.getValue();									
											aMap = product.getAccessories();
											if(aMap.get(orderDetailsInfo.getItem())!=null){
												break;
											}
										}
								
										Accessory accessory = aMap.get(orderDetailsInfo.getItem());
										
										out.println("<tr>");
										out.println("<td>");
										out.println("<img src=\""+accessory.getImageUrl()+"\" width=\"150px\" height=\"140px\"/>");
										out.println("</td>");
										out.println("<td width=\"20%\">");
										out.println(accessory.getName()+" "+accessory.getDescription());
										out.println("</td>");
										out.println("<td>");
										out.println("$"+accessory.getPrice());
										out.println("</td>");										
										out.println("<td>");							    
										out.println(orderDetailsInfo.getItemCount());						
										out.println("</td>");										
										out.println("<td width=\"20%\">");					    
										out.println(orderDetailsInfo.getFirst_name()+" "+orderDetailsInfo.getLast_name()+"<br/>"+orderDetailsInfo.getAddress1());
								   
										if(!orderDetailsInfo.getAddress1().isEmpty()){
											out.println("<br/>"+orderDetailsInfo.getAddress2());
										}
										out.println("<br/>"+orderDetailsInfo.getCity()+", "+orderDetailsInfo.getState()+" "+orderDetailsInfo.getZipcode());						
										out.println("</td>");
										out.println("<td>");							    
										out.println(dateFormat.format(orderDetailsInfo.getOrderDelivaryDate()));						
										out.println("</td>");
										out.println("<td>");							    
										out.println(orderDetailsInfo.getOrderStatus());						
										out.println("</td>");							    
										out.println("<td>");
										out.println("<a href=\"cancelcurrentorder?product_id="+orderDetailsInfo.getItem()+"&order_id="+orderDetailsInfo.getOrderNumber()+"\">Cancel</a>");
										out.println("</td>");
										out.println("</tr>");
									}
									
								}
								
												
								
							
							
							
						
						
						out.println("</table>");
						//out.println("</div>");		
						//out.println("</article>");
						out.println("</section>");
						
					}
					else{
						out.println("<section id=\"shoppingcart-content\">");
						out.println("<article>");
						out.println("<div class=\"shopping-cart-empty\">");
						out.println("<table style=\"width: 100%\">");
					    out.println("<tr>");
					    out.println("<td>");
					    out.println("<h1>Current Orders</h1>");
					    out.println("<h2>There are no orders to display!</h2>");
					    out.println("</td>");
					    out.println("</tr>");
					    out.println("<tr>");
					    out.println("<td colspan=\"2\">");
					    out.println("<p>You haven't order anything yet. Go ahead and order some cool stuffs! </p>");
					    out.println("</td>");
					    out.println("</tr>");
					    out.println("</table>");
					    out.println("</div>");				
						out.println("</article>");						
						out.println("</section>");
					}
					}
					
				}
				catch(Exception e){	
						
				}
				
				
					out.println("<div class=\"clear\"></div>");				    
					out.println("<footer>");	
					
					out.println("<div class=\"footer-bottom\">");
					out.println("<p>Designed By: Aditya Yaji</p>");
					out.println("<p>Contact information: <a href=\"mailto:someone@example.com\"> ayaji@hawk.iit.edu</a>.</p>");
					out.println("</div>");
					out.println("</footer>");
					
					out.println("</body>");
					out.println("</div>");
					out.println("</div>");
					out.println("</html>");	
			}
			else{
				response.sendRedirect(request.getContextPath()+"/login.html");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
	}

}