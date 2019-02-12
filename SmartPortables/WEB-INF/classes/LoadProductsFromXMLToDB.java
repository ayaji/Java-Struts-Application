import java.io.*;
import javax.servlet.http.*;
import java.util.*;

public class LoadProductsFromXMLToDB extends HttpServlet {
	
	public void init(){
		
		try{
			
			MySQLDataStoreUtilities.dropAllProducts();
			Map<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();
			System.out.println("Inserted data to Product table");
			
			for (Map.Entry<String, Product> pEntry : pMap.entrySet()) {				
				Product product = pEntry.getValue();
				MySQLDataStoreUtilities.insertProduct(product);	
				
				Map<String,Accessory> accessories = new HashMap<String,Accessory>();
				accessories = (HashMap<String,Accessory>) product.getAccessories();
				
				for (Map.Entry<String, Accessory> aEntry : accessories.entrySet()) {
					Accessory accessory = aEntry.getValue();
					accessory.setProductId(product.getId());
					MySQLDataStoreUtilities.insertAccessory(accessory);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}