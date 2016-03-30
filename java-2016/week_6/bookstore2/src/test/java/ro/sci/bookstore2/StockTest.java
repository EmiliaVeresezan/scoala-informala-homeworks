package ro.sci.bookstore2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class StockTest {

	Book book1;
	
	Stock stock1,stock2, stock3, stock4, stock5;
			
	CartEntry cartEntry1, cartEntry2, cartEntry3, cartEntry4, cartEntry5;
	
	
	@Before
	public void init(){
	
	
		Book book1 = createNewBook("1984", "fiction", "Orwell", 234567, 23.55);

		//stock quantity is the same for all stock for testing purposes
		stock1 = createNewStock(book1, 12); 	
		stock2 = createNewStock(book1, 12);
		stock3 = createNewStock(book1, 12);
		stock4 = createNewStock(book1, 12);
		stock5 = createNewStock(book1, 12);
		
		//order quantity is different 
		cartEntry1 = createNewCartEntry(book1, 10); //cart entry for book1 with orderQuantity 10 pieces 
		cartEntry2 = createNewCartEntry(book1, 12); //cart entry for book1 with orderQuantity 12 pieces
		cartEntry3 = createNewCartEntry(book1, 16); //cart entry for book1 with orderQuantity 16 pieces
		cartEntry4 = createNewCartEntry(book1, 0);  //cart entry for book1 with orderQuantity 0 pieces
		cartEntry5 = createNewCartEntry(book1, -5); //cart entry for book1 with orderQuantity -5 pieces
	}
	
	@Test
	public void isInStockTest(){
		assertTrue(stock1.isInStock(10));//tests case where stockQuantity>orderQuantity
		assertTrue(stock2.isInStock(12));//tests case wherestockQuantity=orderQunatity
		assertFalse(stock3.isInStock(16));//tests case where stockQuantity<orderQuantity
		assertFalse(stock4.isInStock(0)); //tests case whereorderQuantity=0
		assertFalse(stock5.isInStock(-5)); //test case where orderQuantity<0 
		
	}
	
	@Test
	public void adjustStockTest(){
		stock1.adjustStock(10);
		assertEquals(2,stock1.getStockQuantity()); // test if the remaining stock is calculated correctly when stockQuantity>orderQuantiy
		
		stock2.adjustStock(12);
		assertEquals(0,stock2.getStockQuantity()); // test if stock is calculated correctly when stockQuantity= orderQuantity
		
		stock3.adjustStock(16);
		assertEquals(12,stock3.getStockQuantity()); // test if stock remains the same when stockQuantity< orderQuantity
		
		stock4.adjustStock(0);
		assertEquals(12,stock4.getStockQuantity()); // test if stock remains the same when orderQuantity=0
		
		stock5.adjustStock(-5);
		assertEquals(12,stock5.getStockQuantity()); // test if stock remains the same when orderQuantity<0
	}
	
	@After 
	public void destroy(){
		cartEntry1 = null;
		book1 = null;
		stock1 = null;
		stock2 = null;
		stock3=null;
		stock4=null;
		stock5=null;
		cartEntry1=null;
		cartEntry2 = null;
		cartEntry3=null;
		cartEntry4 = null;
		cartEntry5 = null;
		
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
