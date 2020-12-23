import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Purchase {
	private String ItemId;
	private String ItemName;
	private double ItemPriceInDollars;
	private int ItemQuantity;
	private Date PurchaseDate;
	private double TotalPayment;

	
	
	
	public Purchase(String itemId, String itemName, double itemPrice, int itemQuantity, Date purchaseDate) {
		super();
		ItemId = itemId;
		ItemName = itemName;
		ItemPriceInDollars = itemPrice;
		ItemQuantity = itemQuantity;
		PurchaseDate = purchaseDate;
		TotalPayment=ItemQuantity*ItemPriceInDollars;
	}
	

	public String getItemId() {
		return ItemId;
	}
	public void setItemId(String itemId) {
		ItemId = itemId;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	
	public void setItemPrice(double itemPrice) {
		ItemPriceInDollars = itemPrice;
	}
	public double getItemPrice() {
		
		return ItemPriceInDollars;
		
	}
	public int getItemQuantity() {
		return ItemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		ItemQuantity = itemQuantity;
	}
	public Date getPurchaseDate() {
		return PurchaseDate;
	}
	
		
	public double getTotalPayment() {
		return TotalPayment;
	}


	public void setTotalPayment(double totalPayment) {
		TotalPayment = totalPayment;
	}


	public void setPurchaseDate(Date purchaseDate) {
		PurchaseDate = purchaseDate;
	}
	

}
