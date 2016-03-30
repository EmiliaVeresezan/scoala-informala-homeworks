package ro.sci.bookstore2;

public class CartEntry {
	Book book;
	int orderQuantity;
	
	public CartEntry() {
		super();
	}

	public CartEntry(Book book, int orderQuantity) {
		super();
		this.book = book;
		this.orderQuantity = orderQuantity;
	}
	
	public double calculateCartEntryPrice(){
		double cartEntryPrice =  book.getPrice()*orderQuantity;
		
		return cartEntryPrice;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	
	
	

}
