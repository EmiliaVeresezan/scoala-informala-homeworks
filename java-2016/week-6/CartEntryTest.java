package ro.sci.bookstore;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CartEntryTest {
			
	Book book1;
			
	Stock stock1;
			
	CartEntry cartEntry1, cartEntry2, cartEntry3, cartEntry4, cartEntry5;
	
	@Before
	public void init(){
		
		Book book1 = createNewBook("1984", "fiction", "Orwell", 234567, 23.55);
		
		Stock stock1 = createNewStock(book1, 12); //stock quantity for book1 is 12 pieces
		Stock stock2 = createNewStock(book1, 12);
		Stock stock3 = createNewStock(book1, 12);
		Stock stock4 = createNewStock(book1, 12);
		Stock stock5 = createNewStock(book1, 12);
		
		cartEntry1 = createNewCartEntry(stock1, 10); //cart entry for book1 with quantity 10 pieces 
		cartEntry2 = createNewCartEntry(stock2, 12); //cart entry for book1 with quantity 12 pieces
		cartEntry3 = createNewCartEntry(stock3, 16); //cart entry for book1 with quantity 16 pieces
		cartEntry4 = createNewCartEntry(stock4, 0);  //cart entry for book1 with quantity 0 pieces
		cartEntry5 = createNewCartEntry(stock5, -5); //cart entry for book1 with quantity -5 pieces
	}
	
	@Test
	public void isInStockTest() {
		assertTrue(cartEntry1.isInStock());
		assertTrue(cartEntry2.isInStock());
		assertFalse(cartEntry3.isInStock());
		assertFalse(cartEntry4.isInStock());
		assertFalse(cartEntry5.isInStock());
	}
	
	@Test
	public void adjustStockTest(){
		cartEntry1.adjustStock();
		assertEquals(2,cartEntry1.getStock().getQuantity()); // test if the remaining stock is calculated correctly when stockQuantity>orderQuantiy
		
		cartEntry2.adjustStock();
		assertEquals(0,cartEntry2.getStock().getQuantity()); // test if stock is calculated correctly when stockQuantity= orderQuantity
		
		cartEntry3.adjustStock();
		assertEquals(12,cartEntry3.getStock().getQuantity()); // test if stock remains the same when stockQuantity< orderQuantity
		
		cartEntry4.adjustStock();
		assertEquals(12,cartEntry4.getStock().getQuantity()); // test if stock remains the same when orderQuantity=0
		
		cartEntry5.adjustStock();
		assertEquals(12,cartEntry5.getStock().getQuantity()); // test if stock remains the same when orderQuantity<0
	}
	
	@After 
	public void destroy(){
		book1=null;
		stock1=null;
		cartEntry1=null;
		cartEntry2=null; 
		cartEntry3=null;
		cartEntry4=null; 
		cartEntry5=null;
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
	
	private Stock createNewStock(Book book, int quantity){
		Stock stock = new Stock();
		stock.setBook(book);
		stock.setQuantity(quantity);
		
		return stock;
	}
	
	private CartEntry createNewCartEntry(Stock stock, int orderQuantity){
		CartEntry cartEntry = new CartEntry();
		cartEntry.setStock(stock);
		cartEntry.setOrderQuantity(orderQuantity);
		
		return cartEntry;
	}
}


