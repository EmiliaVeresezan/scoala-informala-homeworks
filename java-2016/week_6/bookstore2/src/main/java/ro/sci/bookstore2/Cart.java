package ro.sci.bookstore2;

import java.util.ArrayList;

public class Cart {
	
	private ArrayList<CartEntry> cartEntryList = new ArrayList<CartEntry>();
	private double totalPrice;
	
	public Cart() {
		super();
	}

	public void add(CartEntry cartEntry){
		cartEntryList.add(cartEntry);
	}

	public void calculateCartPrice(){
		for (CartEntry entry: cartEntryList){
			totalPrice = totalPrice + entry.calculateCartEntryPrice();
		}
	}

	public ArrayList<CartEntry> getCartEntryList() {
		return cartEntryList;
	}

	public void setCartEntryList(ArrayList<CartEntry> cartEntryList) {
		this.cartEntryList = cartEntryList;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	

}
