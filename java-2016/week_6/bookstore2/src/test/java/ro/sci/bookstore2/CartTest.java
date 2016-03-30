package ro.sci.bookstore2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CartTest {

	Cart cart;
	Book book1, book2;
	Stock stock1, stock2;
	CartEntry cartEntry1, cartEntry2;
	
	@Before
	public void init(){
		
		cart = new Cart();
		
		book1 = createNewBook("The Cave", "fiction", "Jose Saramago", 510788, 20.0);
		
		stock1 = createNewStock(book1, 26);
				
		cartEntry1 = createNewCartEntry(book1, 5);
		cart.add(cartEntry1);
		
		book2 = createNewBook("Dune", "SF", "Frank Herbert", 117285, 30.00);
		
		stock2 = createNewStock(book2, 15);
				
		cartEntry2 = createNewCartEntry(book2, 2);
		cart.add(cartEntry2);
	}
	
	
	@Test
	public void cartEntryListSizeTestAfterAdd(){
		assertNotNull(cart.getCartEntryList());
		assertTrue(cart.getCartEntryList().size()>0);
		assertTrue(cart.getCartEntryList().size()==2);
	}
	
	@Test
	public void cartEntryListContentTestAfterAdd(){
		assertEquals("The Cave", cart.getCartEntryList().get(0).getBook().getTitle());
		assertEquals(5,cart.getCartEntryList().get(0).getOrderQuantity());
		assertEquals("Dune", cart.getCartEntryList().get(1).getBook().getTitle());
		assertEquals(2, cart.getCartEntryList().get(1).getOrderQuantity());
	}
	
	@Test
	public void calculateCartPriceTest(){
		cart.calculateCartPrice();
		assertEquals(160.00, cart.getTotalPrice(),0.001);
	}
	
	@After
	public void destroy(){
		cart=null;
		book1=null;
		book2=null;
		stock1=null;
		stock2=null;
		cartEntry1=null;
		cartEntry2=null;
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
