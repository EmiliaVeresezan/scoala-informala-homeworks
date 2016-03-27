package ro.sci.bookstore;

public class CartEntry {
	private Stock stock;
	private int orderQuantity;

	public CartEntry(){
		
	}
	
	public CartEntry(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public CartEntry(Stock stock, int orderQuantity) {
		this.stock = stock;
		this.orderQuantity = orderQuantity;
	}
	
	public boolean isInStock(){
		//an entry is in stock when stock quantity >= orderQuantity 
		boolean result = false;
		if (orderQuantity<=0){
			result = false;
		} 
		else {
			if (stock.getQuantity()-orderQuantity>=0){
				result = true;
			}
		}
		return result;	
	}
	
	public void adjustStock(){
		if (isInStock()){
			int stockQuantity = stock.getQuantity();
			stock.setQuantity(stockQuantity-orderQuantity);
		}
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
}
