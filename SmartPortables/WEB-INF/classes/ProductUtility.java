import java.util.*;

public class ProductUtility{
	
	private static Map<String,Product> productMap = null;

	public static Map<String,Product> getProductMap(){
		
		if(productMap == null){
			SAXParserForProducts prdSaxParser = new SAXParserForProducts("ProductCatalog.xml");
			productMap = (HashMap<String,Product>) prdSaxParser.getProducts();
			return productMap;
		}
		else{

			return productMap;
		}		
	}

}