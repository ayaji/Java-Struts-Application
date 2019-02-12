import java.sql.*;
import java.util.*;
import com.google.gson.*;

public class MySQLDataStoreUtilities {

	public static Connection conn = null;
	public static int getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportables?autoReconnect=true&useSSL=false","root","root");
			return 1;
		}
		catch(Exception e){
			return 0;
		}
	}
	
	public static void insertProduct(Product product){
		try{
			if(getConnection() == 1){
				//String insertProducts = "INSERT INTO products(id,name,description,displayUnder,imageUrl,price,discounAmt,rebateAmt) "+ "VALUES (?,?,?,?,?,?,?,?)";
				String insertProduct = "INSERT INTO products(id,name,description,displayUnder,imageUrl,price,discounAmt,rebateAmt,prodcount) "+ "VALUES (?,?,?,?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(insertProduct);
				pst.setString(1,product.getId());
				pst.setString(2,product.getName());
				pst.setString(3,product.getDescription());
				pst.setString(4,product.getDisplayUnder());
				pst.setString(5,product.getImageUrl());
				pst.setDouble(6,product.getPrice());	
				pst.setDouble(7,product.getDiscounAmt());
				pst.setDouble(8,product.getRebateAmt());
				pst.setDouble(9,product.getProdcount());
				pst.executeUpdate();	

				pst.close();
				conn.close();				
			}		
		}
		catch(Exception e){
			System.out.println("Not connected");
			e.printStackTrace();
		}		
	}
	
	public static HashMap<String, Product> getProducts(){
		try{
			if(getConnection() == 1){
				HashMap<String, Product> products=new HashMap<String, Product>();
				String selectProductQuery ="select * from products";
				PreparedStatement pst = conn.prepareStatement(selectProductQuery);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()){
					Product product = new Product();					
					product.setId(rs.getString("id"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setDisplayUnder(rs.getString("displayUnder"));
					product.setImageUrl(rs.getString("imageUrl"));
					product.setPrice(rs.getDouble("price"));
					product.setDiscounAmt(rs.getDouble("discounAmt"));
					product.setRebateAmt(rs.getDouble("rebateAmt"));
					product.setProdcount(rs.getDouble("prodcount"));
					HashMap<String, Accessory> aMap = getAccessory(product.getId());
					product.setAccessories(aMap);
					
					products.put(product.getId(),product);
				}
				
				pst.close();
				conn.close();
				return products;
				
			}
			else{
				return null;
			}			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	// for inventory update
	 public static void updateProductInventory(){
		try{
			if(getConnection() == 1){
				String updateProducts = "UPDATE products p, (SELECT o.item,COUNT(o.item) as b FROM orderdetails o,products p WHERE p.id = o.item GROUP BY p.id) AS l	SET p.prodcount = p.prodcount-b WHERE p.id = l.item";
				PreparedStatement pst = conn.prepareStatement(updateProducts);
				pst.executeUpdate();	

				pst.close();
			}
			
		}
			
			catch(Exception e)
			{
			e.printStackTrace();
			
		}		
	} 
	
	//inventory of products on sale
	public static HashMap<String, Product> productssales(){
		try{
			if(getConnection() == 1){
				HashMap<String, Product> products=new HashMap<String, Product>();
				String selectProductQuery ="select * from products where prodcount>0";
				PreparedStatement pst = conn.prepareStatement(selectProductQuery);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()){
					Product product = new Product();					
					product.setId(rs.getString("id"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setDisplayUnder(rs.getString("displayUnder"));
					product.setImageUrl(rs.getString("imageUrl"));
					product.setPrice(rs.getDouble("price"));
					product.setDiscounAmt(rs.getDouble("discounAmt"));
					product.setRebateAmt(rs.getDouble("rebateAmt"));
					//product.set(rs.getDouble("rebateAmt"));
					product.setProdcount(rs.getDouble("prodcount"));
					HashMap<String, Accessory> aMap = getAccessory(product.getId());
					product.setAccessories(aMap);
					
					products.put(product.getId(),product);
				}
				
				pst.close();
				conn.close();
				return products;
				
			}
			else{
				return null;
			}			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
		
	

	//inventory of products on rebate
	public static HashMap<String, Product> prodrebate(){
		try{
			if(getConnection() == 1){
				HashMap<String, Product> products=new HashMap<String, Product>();
				String selectProductQuery ="select * from products where rebateAmt>0";
				PreparedStatement pst = conn.prepareStatement(selectProductQuery);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()){
					Product product = new Product();					
					product.setId(rs.getString("id"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setDisplayUnder(rs.getString("displayUnder"));
					product.setImageUrl(rs.getString("imageUrl"));
					product.setPrice(rs.getDouble("price"));
					product.setDiscounAmt(rs.getDouble("discounAmt"));
					product.setRebateAmt(rs.getDouble("rebateAmt"));
					product.setProdcount(rs.getDouble("prodcount"));
					HashMap<String, Accessory> aMap = getAccessory(product.getId());
					product.setAccessories(aMap);
					
					products.put(product.getId(),product);
				}
				
				pst.close();
				conn.close();
				return products;
				
			}
			else{
				return null;
			}			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
		
	//inventory of products on rebates
		// for inventory update
		
	public static HashMap<String, Product> getProductsinventory(){
		try{
			if(getConnection() == 1){
				
							
				HashMap<String, Product> products=new HashMap<String, Product>(); 	
				String selectProductQuery ="select * from products;";
				PreparedStatement pst1 = conn.prepareStatement(selectProductQuery);
				ResultSet rs = pst1.executeQuery();
				while(rs.next()){
					Product product = new Product();					
					product.setId(rs.getString("id"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setDisplayUnder(rs.getString("displayUnder"));
					product.setImageUrl(rs.getString("imageUrl"));
					product.setPrice(rs.getDouble("price"));
					product.setDiscounAmt(rs.getDouble("discounAmt"));
					product.setRebateAmt(rs.getDouble("rebateAmt"));
					product.setProdcount(rs.getDouble("prodcount"));
					HashMap<String, Accessory> aMap = getAccessory(product.getId());
					product.setAccessories(aMap);
					
					products.put(product.getId(),product);
				}
				
				pst1.close();
				conn.close();
				return products;
				
			}
			else{
				return null;
			}			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
	
	//inventory graph
	public static HashMap<String, Product> getProductsinventorygraph(){
		try{
			if(getConnection() == 1){
				
							
				HashMap<String, Product> products=new HashMap<String, Product>();
				String selectProductQuery ="SELECT p.displayUnder,p.id,p.name,p.description,p.imageUrl,p.price,p.discounAmt,p.rebateAmt,sum(prodcount) FROM products p group by displayUnder;";
				PreparedStatement pst1 = conn.prepareStatement(selectProductQuery);
				ResultSet rs = pst1.executeQuery();
				
				while(rs.next()){
					Product product = new Product();					
					product.setId(rs.getString("p.id"));
					product.setName(rs.getString("p.name"));
					product.setDescription(rs.getString("p.description"));
					product.setDisplayUnder(rs.getString("p.displayUnder"));
					product.setImageUrl(rs.getString("p.imageUrl"));
					product.setPrice(rs.getDouble("p.price"));
					product.setDiscounAmt(rs.getDouble("p.discounAmt"));
					product.setRebateAmt(rs.getDouble("p.rebateAmt"));
					product.setProdcount(rs.getDouble("sum(prodcount)"));
					HashMap<String, Accessory> aMap = getAccessory(product.getId());
					product.setAccessories(aMap);
					
					products.put(product.getId(),product);
				}
				
				pst1.close();
				conn.close();
				return products;
				
			}
			else{
				return null;
			}			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
	
	
	
	
	
	//sales graph
	public static HashMap<String, Product> getProductssalesgraph(){
		try{
			if(getConnection() == 1){
				
							
				HashMap<String, Product> products=new HashMap<String, Product>();
				String selectProductQuery ="SELECT p.displayUnder,p.id,p.name,p.description,p.imageUrl,p.price,p.discounAmt,p.rebateAmt,sum(o.itemCount) FROM products p,orderdetails o where p.id=o.item group by displayUnder;";
				PreparedStatement pst1 = conn.prepareStatement(selectProductQuery);
				ResultSet rs = pst1.executeQuery();
				
				while(rs.next()){
					Product product = new Product();					
					product.setId(rs.getString("p.id"));
					product.setName(rs.getString("p.name"));
					product.setDescription(rs.getString("p.description"));
					product.setDisplayUnder(rs.getString("p.displayUnder"));
					product.setImageUrl(rs.getString("p.imageUrl"));
					product.setPrice(rs.getDouble("p.price"));
					product.setDiscounAmt(rs.getDouble("p.discounAmt"));
					product.setRebateAmt(rs.getDouble("p.rebateAmt"));
					product.setProdcount(rs.getDouble("sum(o.itemCount)"));
					HashMap<String, Accessory> aMap = getAccessory(product.getId());
					product.setAccessories(aMap);
					
					products.put(product.getId(),product);
				}
				
				pst1.close();
				conn.close();
				return products;
				
			}
			else{
				return null;
			}			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
	
	
	
	
	//sales graph
	
	
	
	// sales
	public static HashMap<String, Product> getsales(){
		try{
			if(getConnection() == 1){
				
							
				HashMap<String, Product> products=new HashMap<String, Product>();
				String selectProductQuery ="select p.id,p.description,p.displayUnder,p.imageUrl,p.name ,p.price,p.discounAmt,p.rebateAmt,count(o.itemCount) from products p, orderdetails o where p.id=o.item group by p.name";
				PreparedStatement pst1 = conn.prepareStatement(selectProductQuery);
				ResultSet rs = pst1.executeQuery();
				
				while(rs.next()){
					Product product = new Product();					
					product.setId(rs.getString("p.id"));
					product.setName(rs.getString("p.name"));
					product.setDescription(rs.getString("p.description"));
					product.setDisplayUnder(rs.getString("p.displayUnder"));
					product.setImageUrl(rs.getString("p.imageUrl"));
					product.setPrice(rs.getDouble("p.price"));
					product.setDiscounAmt(rs.getDouble("p.discounAmt"));
					product.setRebateAmt(rs.getDouble("p.rebateAmt"));
					product.setProdcount(rs.getDouble("count(o.itemCount)"));
					
					HashMap<String, Accessory> aMap = getAccessory(product.getId());
					product.setAccessories(aMap);
					
					products.put(product.getId(),product);
				}
				
				pst1.close();
				conn.close();
				return products;
				
			}
			else{
				return null;
			}			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
	//sales
	
	// daily sales
	
	public static HashMap<String, Product> getsalesdaily(){
		try{
			if(getConnection() == 1){
				
							
				HashMap<String, Product> products=new HashMap<String, Product>();
				String selectProductQuery ="select o.orderDelivaryDate,p.id,p.name,p.description,p.displayUnder,p.price,p.discounAmt,p.rebateAmt,count(o.itemCount) from products p, orderdetails o where p.id=o.item group by o.orderDelivaryDate,p.id,p.name,p.displayUnder,p.price,p.discounAmt,p.rebateAmt";
				PreparedStatement pst1 = conn.prepareStatement(selectProductQuery);
				ResultSet rs = pst1.executeQuery();
				
				while(rs.next()){
					Product product = new Product();		
			product.setImageUrl(rs.getString("o.orderDelivaryDate"));
					product.setId(rs.getString("p.id"));
					product.setName(rs.getString("p.name"));
					product.setDescription(rs.getString("p.description"));
					product.setDisplayUnder(rs.getString("p.displayUnder"));
					
					product.setPrice(rs.getDouble("p.price"));
					product.setDiscounAmt(rs.getDouble("p.discounAmt"));
					product.setRebateAmt(rs.getDouble("p.rebateAmt"));
					product.setProdcount(rs.getDouble("count(o.itemCount)"));
					HashMap<String, Accessory> aMap = getAccessory(product.getId());
					product.setAccessories(aMap);
					
					products.put(product.getId(),product);
				}
				
				pst1.close();
				conn.close();
				return products;
				
			}
			else{
				return null;
			}			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
	
	// daily sales
	
	//inventory update
	 /* public static HashMap<String, Product> getProductsinventorygraph(){
		try{
			if(getConnection() == 1){				
				HashMap<String, Product> products=new HashMap<String, Product>();
				String selectProductQuery ="SELECT displayUnder as Products, sum(prodcount) as Item_Count FROM products group by displayUnder";
				PreparedStatement pst1 = conn.prepareStatement(selectProductQuery);
				ResultSet rs = pst1.executeQuery();
				
				while(rs.next()){
					Product product = new Product();					
					product.setId(rs.getString("id"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setDisplayUnder(rs.getString("displayUnder"));
					product.setImageUrl(rs.getString("imageUrl"));
					product.setPrice(rs.getDouble("price"));
					product.setDiscounAmt(rs.getDouble("discounAmt"));
					product.setRebateAmt(rs.getDouble("rebateAmt"));
					product.setProdcount(rs.getDouble("prodcount"));
					HashMap<String, Accessory> aMap = getAccessory(product.getId());
					product.setAccessories(aMap);
					
					products.put(product.getId(),product);
				}
				
				pst1.close();
				conn.close();
				return products;
				
			}
			else{
				return null;
			}			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}  */
	
		//inventory update ends
	
	
	
	
	
	public static void updateProduct(Product product){
		try{
			if(getConnection() == 1){
				
				String updateProducts = "UPDATE products SET name = ?,description = ?,displayUnder = ?,imageUrl = ?,price = ?,discounAmt = ?,rebateAmt = ?,prodcount=?"
				                  + " WHERE id = ?";
				
				PreparedStatement pst = conn.prepareStatement(updateProducts);
				pst.setString(1,product.getName());
				pst.setString(2,product.getDescription());
				pst.setString(3,product.getDisplayUnder());
				pst.setString(4,product.getImageUrl());
				pst.setDouble(5,product.getPrice());
				pst.setDouble(6,product.getDiscounAmt());
				pst.setDouble(7,product.getRebateAmt());
				pst.setDouble(8,product.getProdcount());
				pst.setString(9,product.getId());
				pst.executeUpdate();	

				pst.close();
				conn.close();				
			}		
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	
	
	public static void deleteProduct(String product_id){
		try{
			if(getConnection() == 1){
				String deleteProductQuery = "delete from products Where id='"+product_id+"'";
				PreparedStatement pst = conn.prepareStatement(deleteProductQuery);
				pst.executeUpdate();	

				String deleteAccessoryQuery = "delete from accessory Where productId='"+product_id+"'";
				PreparedStatement pst1 = conn.prepareStatement(deleteAccessoryQuery);
				pst1.executeUpdate();				
			}		
		}
		catch(Exception e){			
			e.printStackTrace();
		}
	}
	
	public static void dropAllProducts(){
		try{
			if(getConnection() == 1){
				String deleteProductQuery = "delete from products";
				PreparedStatement pst = conn.prepareStatement(deleteProductQuery);
				pst.executeUpdate();	

				String deleteAccessoryQuery = "delete from accessory";
				PreparedStatement pst1 = conn.prepareStatement(deleteAccessoryQuery);
				pst1.executeUpdate();				
			}		
		}
		catch(Exception e){			
			e.printStackTrace();
		}
	}
	
	public static void insertAccessory(Accessory accessory){
		try{
			if(getConnection() == 1){
				String insertAccessory = "INSERT INTO accessory(id,productId,name,description,imageUrl,price) "+ "VALUES (?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(insertAccessory);
				pst.setString(1,accessory.getId());
				pst.setString(2,accessory.getProductId());
				pst.setString(3,accessory.getName());
				pst.setString(4,accessory.getDescription());				
				pst.setString(5,accessory.getImageUrl());
				pst.setDouble(6,accessory.getPrice());
				 
				pst.executeUpdate();	

				pst.close();
				conn.close();				
			}		
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public static HashMap<String, Accessory> getAccessory(String productKey){
		try{
			if(getConnection() == 1){
				HashMap<String, Accessory> accessories=new HashMap<String, Accessory>();
				String selectAccessoryQuery ="select * from accessory Where productId='"+productKey+"'";
				PreparedStatement pst = conn.prepareStatement(selectAccessoryQuery);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()){
					Accessory accessory = new Accessory();					
					accessory.setId(rs.getString("id"));
					accessory.setProductId(rs.getString("productId"));
					accessory.setName(rs.getString("name"));
					accessory.setDescription(rs.getString("description"));
					accessory.setImageUrl(rs.getString("imageUrl"));
					accessory.setPrice(rs.getDouble("price"));
					
					accessories.put(accessory.getId(),accessory);
				}
				
				pst.close();
				conn.close();
				return accessories;
				
			}
			else{
				return null;
			}			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
	public static void deleteAccessory(String product_id, String accessory_id){
		try{
			if(getConnection() == 1){
				String deleteAccessoryQuery = "delete from accessory Where productId='"+product_id+"' and id='"+accessory_id+"'";
				PreparedStatement pst = conn.prepareStatement(deleteAccessoryQuery);
				pst.executeUpdate();				
			}		
		}
		catch(Exception e){			
			e.printStackTrace();
		}
	}
	
	public static void insertWarranty(Warranty warranty){
		try{
			if(getConnection() == 1){
				String insertWarranty = "INSERT INTO warranty(id,name,description,price) "+ "VALUES (?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(insertWarranty);
				pst.setString(1,warranty.getId());
				pst.setString(2,warranty.getName());
				pst.setString(3,warranty.getDescription());				
				pst.setDouble(4,warranty.getPrice());
				 
				pst.executeUpdate();	

				pst.close();
				conn.close();				
			}		
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public static HashMap<String, Warranty> getWarranty(){
		try{
			if(getConnection() == 1){
				HashMap<String, Warranty> warranties=new HashMap<String, Warranty>();
				String selectWarrantyQuery ="select * from warranty";
				PreparedStatement pst = conn.prepareStatement(selectWarrantyQuery);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()){
					Warranty warranty = new Warranty();					
					warranty.setId(rs.getString("id"));					
					warranty.setName(rs.getString("name"));
					warranty.setDescription(rs.getString("description"));
					warranty.setPrice(rs.getDouble("price"));
					
					warranties.put(warranty.getId(),warranty);
				}
				
				pst.close();
				conn.close();
				return warranties;
				
			}
			else{
				return null;
			}			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
	
	public static void deleteWarranty(String warranty_id){
		try{
			if(getConnection() == 1){
				String deleteWarrantyQuery = "delete from warranty Where id='"+warranty_id+"'";
				PreparedStatement pst = conn.prepareStatement(deleteWarrantyQuery);
				pst.executeUpdate();							
			}		
		}
		catch(Exception e){			
			e.printStackTrace();
		}
	}												 
	
	public static void insertUser(User user){
		try{
			if(getConnection() == 1){
				String insertUser = "INSERT INTO users(firstName,lastName,userId,password,userType) " + "VALUES (?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(insertUser);
				pst.setString(1,user.getFirstName());
				pst.setString(2,user.getLastName());
				pst.setString(3,user.getUserId());
				pst.setString(4,user.getPassword());
				pst.setString(5,user.getUserType());
				pst.executeUpdate();

				pst.close();
				conn.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

		public static HashMap<String, User> getUsers(){
		try{
			if(getConnection() == 1){
				HashMap<String, User> users=new HashMap<String, User>();
				String selectUserQuery ="select * from users";
				PreparedStatement pst = conn.prepareStatement(selectUserQuery);
				ResultSet rs = pst.executeQuery();

				while(rs.next()){
					User user = new User();
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					user.setUserId(rs.getString("userId"));
					user.setPassword(rs.getString("password"));
					user.setUserType(rs.getString("userType"));

					users.put(user.getUserId(),user);
				}

				pst.close();
				conn.close();
				return users;

			}
			else{
				return null;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


	public static int insertOrderDetails(OrderDetailsInfo orderDetailsInfo){
		try{
			if(getConnection() == 1){
				String insertOrderDetailsQuery = "INSERT INTO orderdetails(orderNumber, userId, orderDelivaryDate, orderStatus,  item, itemCount, first_name, last_name, address1, address2, city, state, zipcode, phone, card_name, card_number, month, year,securityCode) "+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(insertOrderDetailsQuery);
				pst.setString(1,orderDetailsInfo.getOrderNumber());
				pst.setString(2,orderDetailsInfo.getUserId());
				pst.setDate(3, new java.sql.Date(orderDetailsInfo.getOrderDelivaryDate().getTime()));
				pst.setString(4,orderDetailsInfo.getOrderStatus());
				pst.setString(5,orderDetailsInfo.getItem());
				pst.setInt(6,orderDetailsInfo.getItemCount());
				pst.setString(7,orderDetailsInfo.getFirst_name());
				pst.setString(8,orderDetailsInfo.getLast_name());
				pst.setString(9,orderDetailsInfo.getAddress1());
				pst.setString(10,orderDetailsInfo.getAddress2());
				pst.setString(11,orderDetailsInfo.getCity());
				pst.setString(12,orderDetailsInfo.getState());
				pst.setString(13,orderDetailsInfo.getZipcode());
				pst.setString(14,orderDetailsInfo.getPhone());
				pst.setString(15,orderDetailsInfo.getCard_name());
				pst.setString(16,orderDetailsInfo.getCard_number());
				pst.setString(17,orderDetailsInfo.getMonth());
				pst.setString(18,orderDetailsInfo.getYear());
				pst.setString(19,orderDetailsInfo.getSecurityCode());

				pst.executeUpdate();

				pst.close();
				conn.close();
				return 	1;
			}
			return 0;
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public static HashMap<String,ArrayList<OrderDetailsInfo>> getOrderDetails(){
		try{
		if(getConnection() == 1){
			HashMap<String,ArrayList<OrderDetailsInfo>> orderDetailsMap = new HashMap<String,ArrayList<OrderDetailsInfo>>();
			String selectOrderDetailsQuery ="select * from orderdetails";
			PreparedStatement pst = conn.prepareStatement(selectOrderDetailsQuery);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				if(!orderDetailsMap.containsKey(rs.getString("userId"))){
					ArrayList<OrderDetailsInfo> orderDetailsInfoArr = new ArrayList<OrderDetailsInfo>();
					orderDetailsMap.put(rs.getString("userId"), orderDetailsInfoArr);
				}
				ArrayList<OrderDetailsInfo> orderDetailsInfoArrList = orderDetailsMap.get(rs.getString("userId"));

				OrderDetailsInfo orderDetailsInfo = new OrderDetailsInfo();
				orderDetailsInfo.setFirst_name(rs.getString("first_name"));
				orderDetailsInfo.setLast_name(rs.getString("last_name"));
				orderDetailsInfo.setAddress1(rs.getString("address1"));
				orderDetailsInfo.setAddress2(rs.getString("address2"));
				orderDetailsInfo.setCity(rs.getString("city"));
				orderDetailsInfo.setState(rs.getString("state"));
				orderDetailsInfo.setZipcode(rs.getString("zipcode"));
				orderDetailsInfo.setPhone(rs.getString("phone"));
				orderDetailsInfo.setCard_name(rs.getString("card_name"));
				orderDetailsInfo.setCard_number(rs.getString("card_number"));
				orderDetailsInfo.setMonth(rs.getString("month"));
				orderDetailsInfo.setYear(rs.getString("year"));
				orderDetailsInfo.setSecurityCode(rs.getString("securityCode"));
				orderDetailsInfo.setOrderNumber(rs.getString("orderNumber"));
				orderDetailsInfo.setOrderDelivaryDate(rs.getDate("orderDelivaryDate"));
				orderDetailsInfo.setOrderStatus(rs.getString("orderStatus"));
				orderDetailsInfo.setItem(rs.getString("item"));
				orderDetailsInfo.setItemCount(rs.getInt("itemCount"));
				orderDetailsInfo.setUserId(rs.getString("userId"));

				orderDetailsInfoArrList.add(orderDetailsInfo);

			}

			return orderDetailsMap;

		}
		else{
			return null;
		}

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static void deleteOrder(String product_id,String order_id){
		try{
			if(getConnection() == 1){
				String selectOrderDetailsQuery ="select * from orderdetails Where orderNumber='"+order_id+"' and item='"+product_id+"'";
				PreparedStatement pst = conn.prepareStatement(selectOrderDetailsQuery);
				ResultSet rs = pst.executeQuery();
				OrderDetailsInfo orderDetailsInfo = new OrderDetailsInfo();
				while(rs.next()){
					orderDetailsInfo.setOrderDelivaryDate(rs.getDate("orderDelivaryDate"));
				}
				int numberOfDaysPending = (int)( (orderDetailsInfo.getOrderDelivaryDate().getTime()-new java.util.Date().getTime())/ (1000 * 60 * 60 * 24) );

				if(numberOfDaysPending > 5){
					String deleteOrderQuery = "delete from orderdetails Where orderNumber='"+order_id+"' and item='"+product_id+"'";
					PreparedStatement pst1 = conn.prepareStatement(deleteOrderQuery);
					pst1.executeUpdate();
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


}
