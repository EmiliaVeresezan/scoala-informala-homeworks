package ro.sci.bookstore2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CustomerTest {
	
	Customer customer;
	List<Stock> stocks;
	Cart cart;
	
	@Before
	public void init(){
		
		Book book1 = createNewBook("1984", "fiction", "Orwell", 234567, 20.00);
		
		Book book2 = createNewBook("About Photography", "fiction", "Susan Sontag", 546300, 30.00);
		
		Book book3 = createNewBook("Walden", "fiction", "Henry David Thoreau", 2223701, 50.00);
					
		stocks = new ArrayList<Stock>();
		
		Stock stock1 = createNewStock(book1, 12);
		stocks.add(stock1);
		
		Stock stock2 = createNewStock(book2, 7);
		stocks.add(stock2);
		
		Stock stock3 = createNewStock(book3, 4);
		stocks.add(stock3);
		
		cart = new Cart();
	}
	
	@Test
	public void findBookWithQuantityTest(){
		
		Book book = createNewBook("1984", "fiction", "Orwell", 234567, 20.00);
		int orderQuantity = 10;
		//book is found and is in sufficient stock - the method returns Stock object
		assertNotNull(findBookWithQuantity(book, orderQuantity));
		
		orderQuantity = 16;
		//book is found but orderQuantity>stockQuantity - the method returns null  
		assertNull(findBookWithQuantity(book, orderQuantity));
		
		book = createNewBook("Harry Potter", "fantasy", "J.K. Rowlling", 400167, 80.00);
		orderQuantity = 1;
		//book is not found - the method returns null
		assertNull(findBookWithQuantity(book, orderQuantity));
	}
	
	private Stock findBookWithQuantity(Book book, int orderQuantity) {
		Stock result = null; 
		for (Stock stock: stocks){
			long stockISBN = stock.getBook().getISBN();
			long bookISBN= book.getISBN();
			if ((stockISBN==bookISBN) && stock.isInStock(orderQuantity)){
				result = stock;
				break;
			}
		}
		return result;
	}
	
	@Test
	public void addBookToCartTest(){
		
		Book book = createNewBook("About Photography", "fiction", "Susan Sontag", 546300, 30.00);
		int quantity = 2;
		addBookToCart(book,quantity);
		//test that the CartEntry object for book was entered into the cart
		assertNotNull(cart.getCartEntryList().get(0));
		//test that the expected book title was entered in the cart on the first Arraylist position
		assertEquals("About Photography", cart.getCartEntryList().get(0).getBook().getTitle());
		
		//add a second book into the cart 
		book = createNewBook("Walden", "fiction", "Henry David Thoreau", 2223701, 50.00);
		quantity = 3;
		addBookToCart(book,quantity);
		assertNotNull(cart.getCartEntryList().get(1));
		assertEquals("Walden", cart.getCartEntryList().get(1).getBook().getTitle());
		
			//test that a book that cannot be found is not added to the cart
		book = createNewBook("The Cave", "fiction", "Jose Saramago", 510788, 45.5);
		quantity = 2;
		addBookToCart(book,quantity);
		assertFalse(cart.getCartEntryList().size()==3);
		
		//test whether the total price of the cart is calculated correctly
		cart.calculateCartPrice();
		assertEquals(210.00, cart.getTotalPrice(),0.001);
		
	}
	
	private void addBookToCart(Book book, int orderQuantity){
		Stock stock = findBookWithQuantity(book, orderQuantity);
		if (stock!=null){
			CartEntry cartEntry = createNewCartEntry(book, orderQuantity);
			cart.add(cartEntry);
			stock.adjustStock(orderQuantity);
		}
		else {
			System.out.println("Book"+ book.getTitle()+" cannot be added to the cart");
		}
	}
	
	@After
	public void destroy(){
		customer = null;
		stocks = null;
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
