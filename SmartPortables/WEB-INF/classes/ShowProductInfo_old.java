import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class ShowProductInfo extends HttpServlet {
		
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request,response);
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{				
			String product_id = request.getParameter("product_id");
			HttpSession session = request.getSession(false);
			Map<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();
			
			Product product = pMap.get(product_id);
			Map<String,Accessory> accessories = product.getAccessories();
			
			
			PrintWriter out = response.getWriter();					
				
		    out.println("<!doctype html>");
		    out.println("<html>");
			out.println("<head>");
			out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			out.println("<title>Smart Portables</title>");    
			out.println("<link rel=\"stylesheet\" href=\"index_style.css\" type=\"text/css\"/>");
			out.println("<meta charset=\"utf-8\">");
			out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
			out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
			out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script> ");
			out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>"); 
			out.println("</head>");
			out.println("<body>");
			out.println("<div id=\"container\">");
			out.println("<header>");
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
					out.println("<a href=\"#\" style=\"width: 90px; text-align: center;\">Log In&nbsp;<img  src=\"images/content/arrowdown.png\" width=\"18px\" height=\"13px\"/></a>");
					out.println("<ul>");
					out.println("<li><a href=\"login.html\">Log In</a></li>");
					out.println("<li><a href=\"login.html\">Current Orders</a></li>");				    
					out.println("</ul>");
					out.println("</li>");
				}
				else{
					//out.println("<a href=\"\" style=\"width: 90px; text-align: center;\">Cart&nbsp;<img  src=\"images/cart.jpg\" width=\"18px\" height=\"13px\"></a>");
					//out.println("<a href=\"login.html\" style=\"width: 90px; text-align: center;\">Sign Out&nbsp;<img  src=\"images/arrowdown.png\" width=\"18px\" height=\"13px\"/></a>");
					out.println("<li >");
					out.println("<a href=\"login.html\" style=\"width: 90px; text-align: center;\">Log Out<img  src=\"images/arrowdown.png\" width=\"18px\" height=\"13px\"/></a>");
					out.println("<ul>");
					
					out.println("<li><a href=\"logout\">Log Out</a></li>");
					out.println("<li><a href=\"displaycurrentorders\">Current Orders</a></li>");					       
					out.println("</ul>");
					out.println("</li>");
				}
				out.println("<li>");
				//out.println("<a href=\"login.html\" style=\"width: 90px; text-align: center;\">Sign Out&nbsp;<img  src=\"images/arrowdown.png\" width=\"18px\" height=\"13px\"/></a>");
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

         if(product!=null){	
				out.println("<section id=\"content\">");
				out.println("<article>");
				out.println("<table class=\"wrapper\">");
				out.println("<tr>");
				out.println("<td colspan=\"4\">");
				out.println("<h1>");
				out.println("&nbsp;"+product.getName()+" "+product.getDescription());
				out.println("</h>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\">");				
				out.println("<img src=\""+product.getImageUrl()+"\" height=\"200px\" width=\"200px\"");
				out.println("</td>");
				out.println("<td>");
				out.println("<span>");
				out.println("<h2 align=\"right\">");
				out.println("<b>Buy Now</b>");
				out.println("</h2>");
				out.println("</span>");
				out.println("<br/>");
				out.println("<h3>");
				out.println("<em>$</em>");
				out.println("<span>"+product.getPrice()+"<span>");
				out.println("</h3>");
				
				out.println("<br/>");
				out.println("<br/>");
				out.println("<button onclick=\"location.href='storetoshoppingcart?product_id="+product_id+"&requestFrom=showProduct'\" class=\"sign-in-up-button\">&nbsp;&nbsp;Buy&nbsp;</button>");
				out.println("<br/>");
				out.println("<br/>");
				//out.println("<button onclick=\"location.href='accessoryproducts?product_id="+product_id+"'\" class=\"sign-in-up-button\">View Accessories</button>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");				
				out.println("</article>");
				
				out.println("<article>");
				if(session == null){
					out.println("<button onclick=\"location.href='login.html'\" class=\"sign-in-up-button\">Write A Review</button>");
				}
				else{
					session.setAttribute("itemId", product_id);	
					out.println("<button onclick=\"location.href='userreview.html'\" class=\"sign-in-up-button\">Write A Review</button>");
				}				
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");				
				out.println("</article>");
				out.println("<article>");
				out.println("<br/>");
				out.println("<br/>");
				
				HashMap<String, ArrayList<Review>> reviewsMap = MongoDBDataStoreUtilities.getUserReviews();
				
				if(reviewsMap != null){
					out.println("<h2>&nbsp;User Reviews:</h2>");
					out.println("<div >");	
					ArrayList<Review> reviewsArrList = 	new ArrayList<Review>();
					if(reviewsMap.get(product_id)!=null){
						reviewsArrList =  reviewsMap.get(product_id);	
					}
					for (Review review : reviewsArrList) {               	
										
						out.println("<table style=\"font-size:14px; width:80%;color:#333334\">");
						out.println("<tr>");
						//out.println("<h2>Product Review</h2>");
						out.println("<th colspan=\"2\" style=\"font-size:18px\"><h2>Product Review</h2></th>");
						out.println("</tr>");
						out.println("<tr>");
						//out.println("<td valign=\"top\" width=\"70%\">");
						out.println("<table>");
						//out.println("<tr>");
						//out.println("<td>");
						//out.println("<h3>rated</h3>");
						out.println(review.getProductRvU()+" rated<img src=\"images/"+review.getProductRvR()+".png\"/>&nbsp;&nbsp;&nbsp;<span style=\"font-size:36px;font-weight: bold\">"+review.getProductRvR()+"</span>");
						out.println("<br>");
						//out.println("<td>");
						out.println("Reviewed on:");
						out.println(review.getProductRvD());
						//out.println("</td>");
						//out.println("</tr>");
						//out.println("<tr>");
						//out.println("<td colspan=\"2\">");
						out.println(review.getProductRvU()+"<br/>");
						//out.println("<br>");
						out.println(review.getProductRvA()+"yrs "+review.getProductRvG()+", "+ review.getProductRvO());
						//out.println("</td>");
						//out.println("</tr>");
						//out.println("<tr>");
						//out.println("<td colspan=\"2\">");
						out.println("<p style=\"font-size:14px; text-align: justify;line-height:20px;color:#333334\">"+review.getProductRvB()+"</p>");
						//out.println("</td>");
						//out.println("</tr>");
						//out.println("</table>");
						//out.println("</td>");
						//out.println("<td style=\"font-size: 13px;\" width=\"28%\" valign=\"top\">");
						out.println("<span style=\"text-decoration: underline;font-weight: bold;\">Model Name</span><br/><br/>");
						out.println("Model Name: <span style=\"font-weight: bold;\">"+review.getProductMN()+"</span><br/>");
						out.println("Item Category: "+review.getProductC()+"<br/>");
						out.println("Item Price: "+review.getProductP()+"<br/>");
						out.println("ItemOnSale: "+review.getProductOS()+"<br/>");
						out.println("Manufacturer Name: "+review.getProductMrN()+"<br/>");
						out.println("Manufacture Rebate: "+review.getProductMR());
						out.println("<br/>");
						out.println("<br/>");
						out.println("<span style=\"text-decoration: underline;font-weight: bold;\">Retailer:</span><br/><br/>");
						out.println(review.getProductRN()+"<br/>");
						out.println(review.getProductRC()+", "+review.getProductRS()+" "+ review.getProductRZ());
						//out.println("</td>");
						out.println("</tr>");
						out.println("</table>");	               
					}
					out.println("</div");
				}
				else{
					out.println("<h2>&nbsp;User Reviews:</h2>");
					out.println("<div >");
					out.println("<h4 style=\"color:red\">&nbsp;&nbsp; MongoDB Server is not running right now!</h4>");
					out.println("</div");
				}
				out.println("</article>");
				
				//new code end modified
				
				
				
				
				
				
				
				
				
				
				
				
				out.println("<article>");
				out.println("<br/>");
				out.println("<h2 style=\"color:blue; font-family:Times New Roman; font-style: oblique; font-size: 30px;\">Accessories....</h2>");
				out.println("<br/>");			
				out.println("</article>");
				
				
				
				
				
				
								


				
//for carousel

out.println("<div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\">");
out.println("<ol class=\"carousel-indicators\">");
out.println("<li data-target=\"#myCarousel\" data-slide-to=\"0\" class=\"active\"></li>");
out.println("<li data-target=\"#myCarousel\" data-slide-to=\"1\"></li>");
out.println("<li data-target=\"#myCarousel\" data-slide-to=\"2\"></li>");
out.println("<li data-target=\"#myCarousel\" data-slide-to=\"3\"></li>");
out.println("<li data-target=\"#myCarousel\" data-slide-to=\"4\"></li>");
out.println("<li data-target=\"#myCarousel\" data-slide-to=\"5\"></li>");
out.println("</ol>");
out.println("<div class=\"carousel-inner\">");
int a=0;
for (Map.Entry<String, Accessory> entry : accessories.entrySet()) {
	Accessory accessory = entry.getValue();
	a++;
	if(a==1){
				out.println("<div class=\"item active\">");
				out.println("<a href=\"showaccessoryinfo?accessory_id="+accessory.getId()+"&product_id="+product_id+"\"><img class=\"img-responsive center-block\" src=\""+accessory.getImageUrl()+"\">");
				out.println("<button type=\"button\" onclick=\"location.href='storetoshoppingcart?product_id="+accessory.getId()+"&actproduct_id="+product_id+"&requestFrom=showAccessory'\" class=\"sign-in-up-button\">&nbsp;&nbsp;BuyTheAccessoryNow&nbsp;</button>");
				
				//review code
				
				out.println("<a href=\"accessoryreview.html\">&nbsp;&nbsp;;</a>");
				out.println("<button type=\"button\"onclick=\"location.href='accessoryreview.html'\"class=\"sign-in-up-button\">\"&nbsp;&nbsp;WriteReview&nbsp;</button>");
				
				//end review code
				
				out.println("</div>");
				
			}
			out.println("<div class=\"item\">");
			out.println("<a href=\"showaccessoryinfo?accessory_id="+accessory.getId()+"&product_id="+product_id+"\"><img class=\"img-responsive center-block\" src=\""+accessory.getImageUrl()+"\">");
			out.println("<button type=\"button\"align=\"middle\"onclick=\"location.href='storetoshoppingcart?product_id="+accessory.getId()+"&actproduct_id="+product_id+"&requestFrom=showAccessory'\" class=\"sign-in-up-button\">&nbsp;&nbsp;BuyTheAccessoryNow&nbsp;</button>");
			
			//review code
				
				out.println("<a href=\"accessoryreview.html\">&nbsp;&nbsp;;</a>");
				out.println("<button type=\"button\"onclick=\"location.href='accessoryreview.html'\"class=\"sign-in-up-button\">\"&nbsp;&nbsp;WriteReview&nbsp;</button>");
				
				//end review code
				
			
			
			
			out.println("</div>");
}
out.println("</div>");
out.println("<a class=\"left carousel-control\" href=\"#myCarousel\" data-slide=\"prev\">");
out.println("<span class=\"glyphicon glyphicon-chevron-left\"></span>");
out.println("<span class=\"sr-only\">Previous</span>");
out.println("</a>");
out.println("<a class=\"right carousel-control\" href=\"#myCarousel\" data-slide=\"next\">");
out.println("<span class=\"glyphicon glyphicon-chevron-right\"></span>");
out.println("<span class=\"sr-only\">Next</span>");
out.println("</a>");
out.println("</div>");
out.println("</section>");
//end carousel				
				
				
		 }
				
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
					
					out.println("</ul>");
					out.println("</li>");
					out.println("<li>");
					out.println("<h4>Options</h4>");
					out.println("<ul>");
					out.println("<li><a href=\"trending\">Trending</a></li>");
					out.println("<li><a href=\"#\">Pay With Points</a></li>");
					out.println(" <li><a href=\"#\">Exchange Promise</a></li>");
					out.println(" <li><a href=\"#\">Price Match Guarantee</a></li>");
					out.println("</ul>");
					out.println("</li>");
					
					
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
			
			
			