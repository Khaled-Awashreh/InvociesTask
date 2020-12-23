import java.util.ArrayList;

import com.google.gson.Gson;

public class Invocie {
	int ID;
	private String firstName;
	private String LastName;
	private String Currency;
	private ArrayList<Purchase> purchases=new ArrayList<Purchase>();
	private double sum=0;

	public Invocie(int iD, String firstName, String lastName, String currency) {
		super();
		ID = iD;
		this.firstName = firstName;
		LastName = lastName;
		Currency = currency;
	}

	
	public void addAPurchase(Purchase purchase) {
		purchases.add(purchase);
				
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public ArrayList<Purchase> getPurchases() {
		return purchases;
	}
	public void setPurchases(ArrayList<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	public void increaseSum(double value) {
		
		sum=sum+value;
	}
	
	
	
	
	
	
}
