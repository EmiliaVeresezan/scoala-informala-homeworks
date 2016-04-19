/**
 * 
 */
package week9;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import week5.*;

/**
 * @author Emilia
 *
 */
public class CarDealershipTest {
	
	private CarDealership compexitTest;
	private ClientAccountManagement clientAccounts;
	private PriceCatalog prices ;
	Car car1, car2, car3, car4, car5;
	Client client1, client2, client3;
	BankAccount bankAccount1, bankAccount2;
	ClientAccount clientAccount2;
	
	

	@Before
	public void init(){
		
		compexitTest = new CarDealership("Compexit");
		
		clientAccounts = new ClientAccountManagement();
		
		car1 = new CKlasse("phdja0934hkkadsn", 2004);
		compexitTest.addCar(car1);
		car2 = new Passat("tjsij7828hajnsj", 2008);
		compexitTest.addCar(car2);
		car3 = new Golf("jshdyb9727jdhadhgg", 2012);
		compexitTest.addCar(car3);
		
		
		prices = new PriceCatalog();
		prices.addCarPrice(car1, 5000.0);
		prices.addCarPrice(car2, 10000.0);
		prices.addCarPrice(car3, 7000.0);		
		
		compexitTest.setPriceCatalog(prices);
		
		bankAccount1 = createNewBankAccount(7000);
		client1 = createNewClient(2850892345102L, bankAccount1);
		clientAccounts.createClientAccount(client1);
		
		car4 = new Golf("hsim0012hsbemaaaysh", 2014);
		car5 = new Passat("oaki01978shdashdda", 2008);
		
		bankAccount2 = createNewBankAccount(1000);
		client2 = createNewClient(1902310345102L, bankAccount2);
		clientAccounts.createClientAccount(client2);
		
		clientAccounts.getClientAccount(client2).addCarToFleet(car4);
		clientAccounts.getClientAccount(client2).addCarToFleet(car5);
		compexitTest.setClientAccounts(clientAccounts);
		
		client3 = new Client("Gigel Muresan");
		
	}
	
	/**
	 * Tests that after a car is added, the car can be found in the car dealership car inventory.
	 */
	@Test
	public void addCarTest(){
		assertTrue(compexitTest.getCars().contains(car1));
	}
	
	/**
	 * Tests that after adding cars to a car dealership, the car dealership car inventory is not empty
	 * and contains the expected number of cars.
	 */
	@Test
	public void compexitTestSizeTest(){
		assertNotNull(compexitTest.getCars().size());
		assertEquals(3, compexitTest.getCars().size());
	}
	
	
	/**
	 * Tests the method sellCarToClient(Car car, Client client) in class CarDealership
	 * in the test case in which the client has enough money in their bank account to pay for the
	 * car they want to buy.  
	 */
	@Test
	public void sellCarToClientPositiveFlowTest(){
		
		//car1 is sold to client1
		
		//check that the client has enough money - she does
		assertTrue(client1.getBankAccount().checkSum(compexitTest.getPriceCatalog().getCarPrice(car1)));
		
		compexitTest.sellCarToClient(car1, client1);

		//check that the client's bank account is updated correctly 
		
		assertEquals(2000, client1.getBankAccount().getAccountSum(),0.001);
				
		//check that the car is removed from the dealership stock - 2 cars should be left
		assertEquals(2, compexitTest.getCars().size());
			
		// check that the car is removed from the price catalog
		assertFalse(compexitTest.getPriceCatalog().isInPriceCatalog(car1));
		
		//check that the car is added to the client's car fleet
		assertTrue(compexitTest.getClientAccounts().getClientAccount(client1).isCarInFleet(car1));
		
	}
		
	/**
	 * Tests the method sellCarToClient(Car car, Client client) in class CarDealership
	 * in the test case in which the client does not have enough money in their bank account to pay for the
	 * car they want to buy. 
	 * In this situation the method sellCarToClient(car, client) throws an IllegalStateException and the test
	 * method catches it.  
	 */
	@Test
	public void sellCarToClientNegativeFlowTest(){
		
		//car2 is not sold to client1. 
		try{	
			//check if the client has enough money - she doesn't
			assertFalse(client1.getBankAccount().checkSum(compexitTest.getPriceCatalog().getCarPrice(car2)));
			
			compexitTest.sellCarToClient(car2, client1);
			
		} catch(IllegalStateException ex) {
			
			//check that the client's bank account has not been updated 
			assertEquals(7000, client1.getBankAccount().getAccountSum(),0.001);
					
			//check that the car is not removed from the dealership stock - 3 cars should be left
			assertEquals(3, compexitTest.getCars().size());
				
			// check that the car is not removed from the price catalog
			assertTrue(compexitTest.getPriceCatalog().isInPriceCatalog(car2));
			
			//check that the car is not added to the client's car fleet
			assertFalse(compexitTest.getClientAccounts().getClientAccount(client1).isCarInFleet(car2));
		
		}
	}
	
	/**
	 * Test method for method buyCarFromClient(Car car, Client client, float CarPrice) 
	 * in the test case when the car is bought from the client.
	 */
	@Test
	public void buyCarFromClientPositiveFlowTest(){
			
			try{
				
			compexitTest.buyCarFromClient(car4, client2, 5000.0f);
			
			}catch (BankAccountNotFoundException e){
			
			//check that the client's bank account amount was increased
			assertEquals(6000.0, compexitTest.getClientAccounts().getClientAccount(client2).getClient().getBankAccount().getAccountSum(),0.001);

			//checK that the car has been removed form the client's car fleet
			assertEquals(1, compexitTest.getClientAccounts().getClientAccount(client2).getMyFleet().size());
			
			//check that the car has been added to the dealership's stock
			assertTrue(compexitTest.getCars().contains(car4));
			
			//check that the car has been added to the dealership's price catalog
			assertEquals(4, compexitTest.getPriceCatalog().getCarPrices().size());
			
			//check that the price has been calculated correctly, with a 20% increase
			Double newPrice = compexitTest.getPriceCatalog().getCarPrice(car4);
			assertEquals(6000.0, newPrice,0.001);
			}
			
	}

	/**
	 * Test method for method buyCarFromClient(Car car, Client client, float CarPrice) 
	 * in the test case when the car cannot be bought from the client because the client 
	 * does not have a bank account. 
	 * In this situation a BankAccountNotFoundException is thrown and the test method catches it.
	 * If a client does not have a bank account then the client does not have a client account,
	 * because a client account is created when the client buys a car from the dealership, 
	 * and can only pay through a bank account.
	 */
	@Test
	public void buyCarFromClientNegativeFlowTest(){
		
		try {
			
			compexitTest.buyCarFromClient(car4, client3, 5000.0f);
						
		} catch (BankAccountNotFoundException e) {
			
			//System.out.println(e.getMessage());
			
			//check that the client does not have a bank account
			assertNull(client3.getBankAccount());
			
			//check that the car has not been added to the dealership's stock
			assertFalse(compexitTest.getCars().contains(car4));
			
			//check that the car has not been added to the dealership's price catalog
			assertEquals(3, compexitTest.getPriceCatalog().getCarPrices().size());
		}
	}
	
	/**
	 * Tests the method listAllCars() and checks that no information is lost when the way the data is stored is changed.
	 */
	@Test
	public void listAllCarsTest() {
		List<Car> carList = compexitTest.listAllCars();
		assertEquals(3, carList.size());
	}
	
	/**
	 * Tests the behavior of the method getAccount(Client client) when the client has
	 * a ClientAccount in the CarDealership inventory.
	 * 
	 */
	@Test
	public void getAccountPositiveFlowTest(){
		
		try{
			ClientAccount clientAccount = compexitTest.getAccount(client1);
			assertTrue(compexitTest.getClientAccounts().contains(client1));
		} catch(ClientAccountNotFoundException e){
			
		}
	}
	
	/**
	 * Tests the behavior of the method getAccount(Client client) when the client
	 * does not have a ClientAccount.
	 * In this situation getAccount(Client client) throws an exception and the test method catches it.
	 */
	@Test
	public void getAccountNegativeFlowTest(){
		
		try{
			ClientAccount clientAccount = compexitTest.getAccount(client3);
		} catch (ClientAccountNotFoundException e){
			assertFalse(compexitTest.getClientAccounts().contains(client3));		
		}
	}
	
	@After
	public void destroy(){
		compexitTest = null;
		clientAccounts = null;
		car1=null; car2=null; car3=null; car4=null; car5 = null;
		client1=null; client2 = null; client3 = null;
		prices = null;
		bankAccount1 = null; bankAccount2=null;
	}
	
	private BankAccount createNewBankAccount(double accountSum){
		BankAccount bankAccount = new BankAccount(accountSum);
		return bankAccount;
	}
	
	private Client createNewClient(long id, BankAccount bankAccount){
		Client client = new Client(id);
		client.setBankAccount(bankAccount);
		return client;
	}
	
	

}
