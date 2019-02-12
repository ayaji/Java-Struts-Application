import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class StoreAddressAndCreditCardInfo extends HttpServlet {
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try{
			 HttpSession session = request.getSession(false);
			 String first_name = request.getParameter("first_name");
			 String last_name = request.getParameter("last_name");
			 String address1 = request.getParameter("address1");
			 String address2 = request.getParameter("address2");
			 String city = request.getParameter("city");
			 String state = request.getParameter("state");
			 String zipcode = request.getParameter("zipcode");
			 String phone = request.getParameter("phone");			 
			 String card_name = request.getParameter("card_name");
			 String card_number = request.getParameter("card_number");
			 String month = request.getParameter("month");
			 String year = request.getParameter("year");
			 String securityCode = request.getParameter("securityCode");
			 			 
			 Map<String,CreditCardAndAddress> map = null;
			try{				
				FileInputStream fis = new FileInputStream("CreditCardAndAddressMap.ser");
		        ObjectInputStream ois = new ObjectInputStream(fis);
		        map = (HashMap<String,CreditCardAndAddress>) ois.readObject();
		        ois.close();
				fis.close();
			}
			catch(FileNotFoundException e){			   
				Map<String,CreditCardAndAddress> CreditCardAndAddressMap = new HashMap<String,CreditCardAndAddress>();				
				FileOutputStream fos = new FileOutputStream("CreditCardAndAddressMap.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(CreditCardAndAddressMap);
				oos.flush();
				oos.close();
				fos.close();
				
				FileInputStream fis = new FileInputStream("CreditCardAndAddressMap.ser");
		        ObjectInputStream ois = new ObjectInputStream(fis);
		        map = (Map<String,CreditCardAndAddress>) ois.readObject();
		        ois.close();
				fis.close();
			}
			 
			 
			 CreditCardAndAddress creditAndAddress = new CreditCardAndAddress();
			 creditAndAddress.setFirst_name(first_name);
			 creditAndAddress.setLast_name(last_name);
			 creditAndAddress.setAddress1(address1);
			 creditAndAddress.setAddress2(address2);
			 creditAndAddress.setCity(city);
			 creditAndAddress.setState(state);
			 creditAndAddress.setZipcode(zipcode);
			 creditAndAddress.setPhone(phone);
			 creditAndAddress.setCard_name(card_name);
			 creditAndAddress.setCard_number(card_number);
			 creditAndAddress.setMonth(month);
			 creditAndAddress.setYear(year);
			 creditAndAddress.setSecurityCode(securityCode);
			 String orderId = "PORT"+System.currentTimeMillis();
			 creditAndAddress.setOrderNumber(orderId);			

			map.put(orderId,creditAndAddress);
			
			FileOutputStream fos = new FileOutputStream("CreditCardAndAddressMap.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(map);
			oos.flush();
			oos.close();
			fos.close();
			 
			 RequestDispatcher view = request.getRequestDispatcher("/addorderdetails?orderId="+orderId );
			 view.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}