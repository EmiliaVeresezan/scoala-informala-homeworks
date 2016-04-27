/**
 * 
 */
package week9;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import week5.CKlasse;
import week5.Car;
import week5.Golf;
import week5.Passat;
import week5.SKlasse;

/**
 * @author emiliaveresezan
 *
 */
public class TestCarDealershipFlows {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void init() {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void erase() {
	}

	@Test
	public void testSuccesfullSellingOfCarToClientFlow() throws InsufficientFundsException, BankAccountNotFoundException {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Client client = new Client("666", "Jack", "Florilor 33");
		BankAccount bankAccount = new TestBankAccount("000", 50000);
		client.setBankAccount(bankAccount);
		Car car1 = new Passat("aaaaa", 2015);
		Car car2 = new Golf("bbbbb", 2014);
		carDealership.addCarToStock(car1, 4500);
		carDealership.addCarToStock(car2, 8500);
		TestBankAccount carDealershipBankAccount = new TestBankAccount("001", 60000);
		carDealership.setBankAccount(carDealershipBankAccount);
		// when
		carDealership.sellCarToClient(client, car1);
		// then
		assertEquals(1, carDealership.listAllCars().size());
		assertFalse(carDealership.listAllCars().contains(car1));
		assertEquals(45500, ((TestBankAccount) client.getBankAccount()).getBalance(), 0);
		assertEquals(64500, carDealershipBankAccount.getBalance(), 0);
		ClientAccount clientAccount = carDealership.getClientAccount(client);
		assertNotNull(clientAccount);
		assertTrue(clientAccount.getFleet().contains(car1));

	}
	
	
	@Test(expected = InsufficientFundsException.class)
	public void whenNotEnoughtMoneyCarSellingFails() throws InsufficientFundsException, BankAccountNotFoundException {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Client client = new Client("666", "Jack", "Florilor 33");
		BankAccount bankAccount = new TestBankAccount("000", 100);
		client.setBankAccount(bankAccount);
		Car car1 = new Passat("aaaaa", 2015);
		Car car2 = new Golf("bbbbb", 2014);
		carDealership.addCarToStock(car1, 4500);
		carDealership.addCarToStock(car2, 8500);
		// when
		TestBankAccount carDealershipBankAccount = new TestBankAccount("001", 60000);
		carDealership.setBankAccount(carDealershipBankAccount);
		carDealership.sellCarToClient(client, car1);
		// then
		fail("Exception should have been thrown.");
	}
	
	@Test
	public void testBuyCarFromClientWithCarInFleetSuccessfulFlow() throws InsufficientFundsException, BankAccountNotFoundException {
		//given 
		CarDealership carDealership = new CarDealership("compexit");
		Client client = new Client("666", "Jack", "Florilor 33");
		BankAccount bankAccount = new TestBankAccount("000", 5000);
		client.setBankAccount(bankAccount);
		ClientAccount clientAccount = carDealership.getWithAddClientAccount(client);
		Car cklasse = new CKlasse("shajs", 2005);
		clientAccount.addCarToFleet(cklasse);
		Car car1 = new Passat("aaaaa", 2015);
		carDealership.addCarToStock(car1, 4500);
		TestBankAccount carDealershipBankAccount = new TestBankAccount("001", 60000);
		carDealership.setBankAccount(carDealershipBankAccount);
		
		//when 
		carDealership.buyCarFromClient(cklasse, client, 10000f);
		
		//then 
		assertEquals(2, carDealership.listAllCars().size());
		assertTrue(carDealership.listAllCars().contains(cklasse));
		assertEquals(15000, ((TestBankAccount) client.getBankAccount()).getBalance(), 0);
		assertEquals(50000, carDealershipBankAccount.getBalance(), 0);
		assertEquals(0, clientAccount.getFleet().size());
	}
	
	@Test
	public void testBuyCarFromClientWithClientAccountButCarNotInFleetSuccessfulFlow() throws InsufficientFundsException, BankAccountNotFoundException {
		//given 
		CarDealership carDealership = new CarDealership("compexit");
		Client client = new Client("666", "Jack", "Florilor 33");
		BankAccount bankAccount = new TestBankAccount("000", 5000);
		client.setBankAccount(bankAccount);
		ClientAccount clientAccount = carDealership.getWithAddClientAccount(client);
		Car car1 = new Passat("aaaaa", 2015);
		carDealership.addCarToStock(car1, 4500);
		TestBankAccount carDealershipBankAccount = new TestBankAccount("001", 60000);
		carDealership.setBankAccount(carDealershipBankAccount);
		Car cklasse = new CKlasse("shajs", 2005);
		
		//when 
		carDealership.buyCarFromClient(cklasse, client, 10000f);
		
		//then 
		assertEquals(2, carDealership.listAllCars().size());
		assertTrue(carDealership.listAllCars().contains(cklasse));
		assertEquals(15000, ((TestBankAccount) client.getBankAccount()).getBalance(), 0);
		assertEquals(50000, carDealershipBankAccount.getBalance(), 0);
		assertEquals(0, clientAccount.getFleet().size());
	}

	@Test
	public void testBuyCarFromClientWithoutClientAccountSuccessfulFlow() throws InsufficientFundsException, BankAccountNotFoundException {
		//given 
		CarDealership carDealership = new CarDealership("compexit");
		Client client = new Client("666", "Jack", "Florilor 33");
		BankAccount bankAccount = new TestBankAccount("000", 5000);
		client.setBankAccount(bankAccount);
		Car car1 = new Passat("aaaaa", 2015);
		carDealership.addCarToStock(car1, 4500);
		TestBankAccount carDealershipBankAccount = new TestBankAccount("001", 60000);
		carDealership.setBankAccount(carDealershipBankAccount);
		Car cklasse = new CKlasse("shajs", 2005);
		
		//when 
		carDealership.buyCarFromClient(cklasse, client, 10000f);
		
		//then 
		assertEquals(2, carDealership.listAllCars().size());
		assertTrue(carDealership.listAllCars().contains(cklasse));
		assertEquals(15000, ((TestBankAccount) client.getBankAccount()).getBalance(), 0);
		assertEquals(50000, carDealershipBankAccount.getBalance(), 0);
	}
	
	//The InsufficientFundsException is caught in the buyCarFromClient method 
	//because when the dealership does not have enough money the delaership is supposed 
	//to be notified, not the client
	@Test
	public void whenNotEnoughtMoneyCarBuyingFails() throws BankAccountNotFoundException  {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Client client = new Client("666", "Jack", "Florilor 33");
		BankAccount bankAccount = new TestBankAccount("000", 1000);
		client.setBankAccount(bankAccount);
		// when
		TestBankAccount carDealershipBankAccount = new TestBankAccount("001", 1000);
		carDealership.setBankAccount(carDealershipBankAccount);
		Car car = new Golf("jsasa", 2007);
		carDealership.buyCarFromClient(car, client, 10000);
		// then
		assertEquals(0, carDealership.listAllCars().size());
		assertFalse(carDealership.listAllCars().contains(car));
		assertEquals(1000, ((TestBankAccount) client.getBankAccount()).getBalance(), 0);
		assertEquals(1000, carDealershipBankAccount.getBalance(), 0);

	}
	
	@Test (expected = BankAccountNotFoundException.class)
	public void whenClientDoesNotHaveABankAccountCarBuyingFails() throws InsufficientFundsException, BankAccountNotFoundException {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		TestBankAccount carDealershipBankAccount = new TestBankAccount("001", 1000);
		carDealership.setBankAccount(carDealershipBankAccount);
		Car car = new Golf("jsasa", 2007);
		Client client = new Client("666", "Jack", "Florilor 33");
		// when
		carDealership.buyCarFromClient(car, client, 10000);
		// then
		fail("Exception should have been thrown.");

	}
	
	private static class TestBankAccount extends BankAccount {

		public TestBankAccount(String iban, float balance) {
			super(iban, balance);
		}

		private float getBalance() {
			return balance;
		}

	}

}