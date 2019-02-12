import java.sql.*;  
import java.util.*;

public class AjaxUtility {
	
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
	
	
	public static StringBuffer findMatachingProducts(String searchText){
		
		HashMap<String,Product> pMap;
		StringBuffer sb = new StringBuffer();
		
		pMap=getProducts();
		
		for (Map.Entry<String, Product> entry : pMap.entrySet()) {
			System.out.println("***** entered1");
			Product product = entry.getValue();	
			System.out.println("***** product"+product.getName().toLowerCase());			
			if (product.getName().toLowerCase().startsWith(searchText.toLowerCase()))
			{
				System.out.println("***** entered2 ");
				sb.append("<product>");
				sb.append("<id>" + product.getId() + "</id>");
				sb.append("<productName>" + product.getName() + "</productName>");
				sb.append("</product>");
			}
		}
		return sb;
	}
	
	
}