import java.util.*;

public class WarrantyUtility{
	
	private static Map<String,Warranty> warrantyMap = null;

	public static Map<String,Warranty> getWarrantyMap(){
		
		if(warrantyMap == null){
			warrantyMap = new HashMap<String,Warranty>();
			return warrantyMap;
		}
		else{
			return warrantyMap;
		}		
	}

}