package ro.sci.bookstore;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
	
	//declare Customer list
	List<Customer> customerList;
	
	List<Stock> stocks;
	
	Cart cart;
	
	CartEntry cartEntry1, cartEntry2;
	
	@Before
	public void init(){
		
		customerList = new ArrayList<Customer>();
		
		stocks = new ArrayList<Stock>();
		
		Address address1 = createNewAddress("Romania", "Cluj-Napoca", "str. Tasnad nr.1", 400511);
		cart = new Cart();
		Customer customer1 = createNewCustomer("Emilia Veresezan", address1, cart, "emi@test.ro");
		customerList.add(customer1);
		
		Book book1 = createNewBook("1984", "fiction", "Orwell", 234567, 23.55);
		Book book2 = createNewBook("About Photography", "fiction", "Susan Sontag", 546300, 30.00);
		
		Stock stock1 = createNewStock(book1, 12);
		stocks.add(stock1);
		Stock stock2 = createNewStock(book2, 4);
		stocks.add(stock2);
		
		cartEntry1 = createNewCartEntry(stock1, 2);
		if(cartEntry1.isInStock()){
		cartEntry1.adjustStock();
		customerList.get(0).getCart().cartList.add(cartEntry1); 
		}
		
		cartEntry2 = createNewCartEntry(stock2, 1);
		if (cartEntry2.isInStock()){
		cartEntry2.adjustStock();
		customerList.get(0).getCart().cartList.add(cartEntry2); 
		}
	}
	
	@Test
	public void customerListSize() {
		assertNotNull(customerList);
		assertTrue(customerList.size()>0);
		}

	@Test
	public void customerListContentTest() {
		assertNotNull(customerList.get(0));
		assertEquals("Emilia Veresezan", customerList.get(0).getName());
		assertEquals("Romania", customerList.get(0).getAddress().getCountry());
	}
	
	@Test
	public void cartSizeTest(){
		assertNotNull(customerList.get(0).getCart());
		assertTrue(customerList.get(0).getCart().cartList.size()==2);
	}
	
	@Test 
	public void cartContentTest(){
		//test whether the cart entry titles and order quantities are correct
		assertEquals("1984",customerList.get(0).getCart().cartList.get(0).getStock().getBook().getTitle());
		assertEquals(2 ,customerList.get(0).getCart().cartList.get(0).getOrderQuantity());
		assertEquals("About Photography",customerList.get(0).getCart().cartList.get(1).getStock().getBook().getTitle());
		assertEquals(1 ,customerList.get(0).getCart().cartList.get(1).getOrderQuantity());
	}
	
	@Test
	public void findBookTest(){
		Book book =  createNewBook("About Photography", "fiction", "Susan Sontag", 546300, 30.00);
		int quantity = 2;
		assertTrue(findBook(book,quantity));
	}
	
	private boolean findBook(Book book, int quantity){
		boolean found = false; 
		
		for (Stock stock: stocks){
			//search through books that have stock
			if((stock.getBook().getTitle().equals(book.getTitle()))){
				CartEntry cartEntry = new CartEntry(stock, quantity);
				if (cartEntry.isInStock()){
					found=true;
				}
			}
		}
		return found;
	}

	
	private Address createNewAddress(String country, String city, String street, int postalCode){
		Address address = new Address();
		address.setCountry(country);
		address.setCity(city);
		address.setStreet(street);
		address.setPostalCode(postalCode);
		
		return address;
	}
	
	private Customer createNewCustomer(String name, Address address, Cart cart, String email){
		Customer customer = new Customer(name, address);
		customer.setCart(cart);
		customer.setEmail(email);
		
		return customer;
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
