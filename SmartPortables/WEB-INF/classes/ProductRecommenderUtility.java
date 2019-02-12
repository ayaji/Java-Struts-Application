import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.util.*;

public class ProductRecommenderUtility{
	
	static Connection conn = null;
    static String message;
	
	public static int getConnection()
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportables","root","root");							
			message="Successfull";
			return 1;
		}
		catch(SQLException e)
		{
			 message="unsuccessful";
		     return 0;
		}
		catch(Exception e)
		{
			 message="unsuccessful";
		     return 2;
		}
	}
	/* public static Connection conn = null; 
	public static int getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportables?autoReconnect=true&useSSL=false","root","root");
			return 1;
		}
		catch(Exception e){
			return 0;
		}
	}  */

	public HashMap<String,String> readOutputFile(){

		String TOMCAT_HOME = System.getProperty("catalina.home");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
		HashMap<String,String> prodRecmMap = new HashMap<String,String>();
		try {

            br = new BufferedReader(new FileReader(new File(TOMCAT_HOME+"\\webapps\\SmartPortables\\output.csv")));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] prod_recm = line.split(cvsSplitBy,2);
				prodRecmMap.put(prod_recm[0],prod_recm[1]);
            }
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
		
		return prodRecmMap;
	}
	
	/* public static Product getProduct(String product){
		Product prodObj = new Product();
		try 
		{
			String msg = getConnection();
			String selectProd="select * from products where id=?";
			PreparedStatement pst = conn.prepareStatement(selectProd);
			pst.setString(1,product);
			ResultSet rs = pst.executeQuery();
		
			while(rs.next())
			{	
				prodObj = new Product(rs.getString("id"),rs.getString("name"),rs.getString("description"),rs.getString("displayUnder"),rs.getString("imageUrl"),rs.getDouble("price"),rs.getDouble("discounAmt"),rs.getDouble("rebateAmt"),rs.getDouble("prodcount"),HashMap<String, Accessory> aMap = getAccessory(product.getId()));
				/*prodObj = new Product(rs.getString("id"),rs.getString("name"),rs.getDouble("price"),rs.getString("imageUrl"),rs.getString("description"),rs.getString("productCondition"),rs.getString("ProductType"),rs.getDouble("discounAmt"));
			}
			rs.close();
			pst.close();
			conn.close();
		}
		catch(Exception e)
		{
		}
		return prodObj;	
	} */
	
	
	public static Product getProduct(String pro){
		Product prodObj = new Product();
		try 
		{
			if(getConnection() == 1){
				String selectProductQuery ="select * from products where id=?";
				PreparedStatement pst = conn.prepareStatement(selectProductQuery);
				pst.setString(1,pro);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()){
					prodObj.setId(rs.getString("id"));
					prodObj.setName(rs.getString("name"));
					prodObj.setDescription(rs.getString("description"));
					prodObj.setDisplayUnder(rs.getString("displayUnder"));
					prodObj.setImageUrl(rs.getString("imageUrl"));
					prodObj.setPrice(rs.getDouble("price"));
					prodObj.setDiscounAmt(rs.getDouble("discounAmt"));
					prodObj.setRebateAmt(rs.getDouble("rebateAmt"));
					prodObj.setProdcount(rs.getDouble("prodcount"));
					//HashMap<String, Accessory> aMap = getAccessory(product.getId());
					//product.setAccessories(aMap);
					
					//products.put(product.getId(),product);
				}
				
				pst.close();
				conn.close();
				return prodObj;
				
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
}