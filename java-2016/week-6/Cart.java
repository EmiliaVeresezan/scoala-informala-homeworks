package ro.sci.bookstore;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	List<CartEntry> cartList = new ArrayList<CartEntry>();
	double totalPrice=0;
	
	public void addCartEntryToCart(CartEntry cartEntry){
		cartList.add(cartEntry);
		cartEntry.adjustStock();
	}
	
	public void calculateTotalPrice(){
		for (CartEntry item:cartList){
			totalPrice = totalPrice + (item.getStock().getBook().getPrice()*item.getOrderQuantity());
		}
	}
	public List<CartEntry> getCart() {
		return cartList;
	}
	public void setCart(List<CartEntry> cart) {
		this.cartList = cart;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
