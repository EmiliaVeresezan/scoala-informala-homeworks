package ro.sci.bookstore;

import static org.junit.Assert.*;
import static org.junit.Test.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;


	public class CartTest {
	
		//declare list of books
		List<Book> books;
		
		//declare list of stocks
		List<Stock> stocks;
		
		//declare Cart object that contains List<CartEntry> cartList
		Cart cart;
		
		@Before
		public void init(){
			
			books = new ArrayList<Book>();
			
			Book book1 = createNewBook("1984", "fiction", "Orwell", 234567, 20.00);
			books.add(book1);
			
			Book book2 = createNewBook("About Photography", "fiction", "Susan Sontag", 546300, 30.00);
			books.add(book2);
			
			Book book3 = createNewBook("Walden", "fiction", "Henry David Thoreau", 2223701, 50.00);
			books.add(book3);
						
			stocks = new ArrayList<Stock>();
			
			Stock stock1 = createNewStock(book1, 12);
			stocks.add(stock1);
			
			Stock stock2 = createNewStock(book2, 7);
			stocks.add(stock2);
			
			Stock stock3 = createNewStock(book3, 4);
			stocks.add(stock3);
			
			cart = new Cart();
			
			CartEntry cartEntry1 = createNewCartEntry(stock1, 2);
			if (cartEntry1.isInStock()){
				cartEntry1.adjustStock();
				cart.cartList.add(cartEntry1); // add to cart 2 books with the price 20.00 
			}
			
			CartEntry cartEntry2 = createNewCartEntry(stock2, 1);
			if (cartEntry2.isInStock()){
				cartEntry2.adjustStock();
				cart.cartList.add(cartEntry2);// add to cart 1 book with the price 30.00
			}
		
			CartEntry cartEntry3 = createNewCartEntry(stock3, 3);
			if (cartEntry3.isInStock()){
				cartEntry3.adjustStock();
				cart.cartList.add(cartEntry3); //add to cart 3 books with the price 50.00
			}
		}
		
		@org.junit.Test
		public void testBookListSize(){
			assertNotNull(books);
			assertTrue(books.size()>0);
		}
		
		@org.junit.Test
		public void testStockListSize(){
			assertNotNull(stocks);
			assertTrue(stocks.size()>0);
		}
		
		
		@org.junit.Test
		public void testBookListContent(){
			Book book = books.get(0);
			assertEquals("1984", book.getTitle());
			assertEquals(20.00, book.getPrice(),0);
		}
		
		@org.junit.Test
		public void testStockListContent(){
			Stock stock = stocks.get(1);
			assertNotNull(stock.getBook());
			assertEquals("About Photography", stock.getBook().getTitle());
		}
		
		@org.junit.Test
		public void testCartSize(){
			assertNotNull(cart);
			assertTrue(cart.cartList.size()>0);
			assertEquals(3,cart.cartList.size());
		}

		@org.junit.Test
		public void testCartListContent(){
			
			CartEntry cartEntry = cart.cartList.get(0);
			assertEquals("1984", cartEntry.getStock().getBook().getTitle());
			
			cartEntry = cart.cartList.get(1);
			assertEquals("About Photography", cartEntry.getStock().getBook().getTitle());
			
			cartEntry = cart.cartList.get(2);
			assertEquals("Walden", cartEntry.getStock().getBook().getTitle());
			
		}
			
		
		@org.junit.Test
		public void calculateTotalPriceTest() {
			cart.calculateTotalPrice();
			assertEquals(220,cart.getTotalPrice(),0.001); 
		}
		
		@After
		public void destroyBooksList() {
			//destroy book list
			books = null;
		}
		
		@After
		public void destroyStocksList() {
			//destroy stock list
			stocks = null;
		}
		
		@After
		public void destroyCart() {
			//destroy cart
			cart = null;
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
