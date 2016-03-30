package ro.sci.bookstore2;

public class Stock {
	
	private Book book;
	private int stockQuantity;
	
	public boolean isInStock(int orderQuantity) {
		boolean result = false;
		if (orderQuantity<=0){
			result = false;
		} 
		else {
			if (stockQuantity-orderQuantity>=0){
				result = true;
			}
		}
		return result;	
	}
	
	public void adjustStock(int orderQuantity){
		if (isInStock(orderQuantity)){
		stockQuantity = stockQuantity - orderQuantity;
		}
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	

}
