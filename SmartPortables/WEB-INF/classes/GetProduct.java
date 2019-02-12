import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.util.*;


public class GetProduct{
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
}}