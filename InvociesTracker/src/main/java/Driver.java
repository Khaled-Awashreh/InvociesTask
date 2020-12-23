import java.awt.List;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;


import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
	
public class Driver {
	
		
		private static HashMap<Integer, Invocie> invocies = new HashMap<Integer, Invocie>();
		private static Map<String, Double> currencies;
		public static void main(String[] args) throws IOException, ParseException {
			convertCurrency();
			String row;
		
			try {
				BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/Invoices - Sheet1.csv"));
	
				while ((row = csvReader.readLine()) != null) {
	
					processData(row);
	
				}
				csvReader.close();
	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			print();
		}	
		public static void processData(String row) throws ParseException {
			 String[] data = row.split(",");
			 Integer ID=Integer.parseInt(data[0]);
			 if(invocies.containsKey(ID)){
				 addPurchase(invocies.get(ID),data);
			}
			 else {
				 String currency=data[1];
				 String firstName=data[2];
				 String lastName=data[3];
				 Invocie newInvocie=new Invocie(ID,firstName,lastName,currency);	 
				 invocies.put(ID,newInvocie);
				 addPurchase(invocies.get(ID),data);
				 
			 }
				
		}
		
		public static void addPurchase(Invocie invocie, String[] data) throws ParseException {
		    	DateFormat formatter= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		     String PurchaseId=data[4];
			 String ItemName=data[5];
			 double ItemPrice=Double.parseDouble(data[6]);
			 int ItemQuantity=Integer.parseInt(data[8]);
			 
			 double itemPriceInDollar=ItemPrice/currencies.get(invocie.getCurrency());
			 Date PurchaseDate=(Date)formatter.parse(data[7]);
		 	 Purchase newPurchase =new  Purchase( PurchaseId, ItemName,  itemPriceInDollar,  ItemQuantity,  PurchaseDate);
		 	 invocie.addAPurchase(newPurchase);
		 	 invocie.increaseSum(itemPriceInDollar*ItemQuantity);

		 	
		 }
		
		public static void print() throws IOException {		
			Gson gson=new Gson();		
			Collection<Invocie> values = invocies.values(); 		
			String json = gson.toJson(values); 
			FileWriter file = new FileWriter("src/main/resources/output.json");
			file.write(json);
			file.close();		
			
		}
		
		public static void convertCurrency() throws IOException {
			String url_str = "https://v6.exchangerate-api.com/v6/900a07f6fcffc75e0e1e5258/latest/USD";
			URL url = new URL(url_str);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.connect();
			InputStream in = new BufferedInputStream(request.getInputStream());
	        String response = IOUtils.toString(in, "UTF-8");
	        String responseData=response.substring(response.indexOf("{\"USD\""));
	        responseData=responseData.substring(0,responseData.indexOf("}")+1);
	        ObjectMapper mapper = new ObjectMapper();
	        currencies = mapper.readValue(responseData, Map.class);
	
		}
		
	}
	
	
