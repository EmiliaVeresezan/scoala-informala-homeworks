package ro.sci.bookstore;

public class Stock {
	
	private Book book;
	private int quantity;
		
	public Stock() {
	}	
	
	public Stock(Book book, int quantity) {
		this.book = book;
		this.quantity = quantity;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int stock) {
		this.quantity = stock;
	}
	
	

}
