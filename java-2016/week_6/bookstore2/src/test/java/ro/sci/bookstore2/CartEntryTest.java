package ro.sci.bookstore2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CartEntryTest {

	CartEntry cartEntry1, cartEntry2;
	Book book1, book2;
	Stock stock1, stock2;
	
	
	@Before
	public void init(){
	
		book1 = createNewBook("The Cave", "fiction", "Jose Saramago", 510788, 45.5);
		
		stock1 = createNewStock(book1, 26);
				
		cartEntry1 = createNewCartEntry(book1, 5);
		
		book2 = createNewBook("Dune", "SF", "Frank Herbert", 117285, 0.001);
		
		stock2 = createNewStock(book2, 15);
				
		cartEntry2 = createNewCartEntry(book2, 2);
		
	}
	
	@Test
	public void calculateCartEntryPriceTest() {
		assertEquals(227.5, cartEntry1.calculateCartEntryPrice(),0.001);
		assertEquals(0.002, cartEntry2.calculateCartEntryPrice(), 0.001);
	}
	
	@After 
	public void destroy(){
		cartEntry1 = null;
		book1 = null;
		stock1 = null;
		cartEntry2 = null;
		book2 = null;
		stock2 = null;
	}
	
	private Book createNewBook(String title, String genre, String author, long isbn, double price) {
		Book book = new Book(isbn);
		book.setAuthor(author);
		book.setGenre(genre);
		book.setTitle(title);
		
		book.setPrice(price);
		book.setWeight(200);

		return book;
	}
	
	private Stock createNewStock(Book book, int stockQuantity){
		Stock stock = new Stock();
		stock.setBook(book);
		stock.setStockQuantity(stockQuantity);
		
		return stock;
	}
	
	private CartEntry createNewCartEntry(Book book, int orderQuantity){
		CartEntry cartEntry = new CartEntry();
		cartEntry.setBook(book);
		cartEntry.setOrderQuantity(orderQuantity);
		
		return cartEntry;
	}

}
