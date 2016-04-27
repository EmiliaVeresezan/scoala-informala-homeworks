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
	public void testCarDealershipCarListNotEmpty() {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Car car1 = new Passat("aaaaa", 2015);
		Car car2 = new Golf("bbbbb", 2014);
		carDealership.addCarToStock(car1, 4500);
		carDealership.addCarToStock(car2, 8500);
		// when
		List<Car> cars = carDealership.listAllCars();
		// then
		assertTrue(!cars.isEmpty());
	}

	@Test
	public void testCarDealershipCarListContainsCorrectNumberOfCars() {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Car car1 = new Passat("aaaaa", 2015);
		Car car2 = new Golf("bbbbb", 2014);
		carDealership.addCarToStock(car1, 4500);
		carDealership.addCarToStock(car2, 8500);
			// when
		List<Car> cars = carDealership.listAllCars();
		// then
		assertTrue(cars.size() == 2);
	}

	@Test
	public void testCarDealershipCarListDoesNotContainDuplicates() {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Car car1 = new Passat("aaaaa", 2015);
		Car car2 = new Golf("bbbbb", 2014);
		Car car3 = new Golf("bbbbb", 2014);
		carDealership.addCarToStock(car1, 4500);
		carDealership.addCarToStock(car2, 8500);
		carDealership.addCarToStock(car3, 8500);
		// when
		List<Car> cars = carDealership.listAllCars();
		// then
		assertTrue(cars.size() == 2);
	}

	@Test
	public void testExternalModificationOfCarDealershipDoNotAffectInternalStructure() {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Car car1 = new Passat("aaaaa", 2015);
		Car car2 = new Golf("bbbbb", 2014);
		carDealership.addCarToStock(car1, 4500);
		carDealership.addCarToStock(car2, 8500);
			// when
		List<Car> cars = carDealership.listAllCars();
		cars.remove(car1);
		// then

		assertEquals(2, carDealership.listAllCars().size());
	}

	
	@Test
	public void testCarDealershipPriceCatalogIsNotEmpty() {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Car car1 = new Passat("aaaaa", 2015);
		Car car2 = new Golf("bbbbb", 2014);
		carDealership.addCarToStock(car1, 4500);
		carDealership.addCarToStock(car2, 8500);
		// when
		HashMap<String, Float> prices = carDealership.getPriceCatalog();
		// then
		assertTrue(!prices.isEmpty());
	}

	@Test
	public void testCarDealershipPriceCatalogContainsCorrectNumberOfCarPrices() {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Car car1 = new Passat("aaaaa", 2015);
		Car car2 = new Golf("bbbbb", 2014);
		carDealership.addCarToStock(car1, 4500);
		carDealership.addCarToStock(car2, 8500);
		// when
		HashMap<String, Float> prices = carDealership.getPriceCatalog();
		// then
		assertTrue(prices.size() == 2);
	}

	@Test
	public void testCarDealershipPriceCatalogDoesNotContainDuplicates() {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Car car1 = new Passat("aaaaa", 2015);
		Car car2 = new Golf("bbbbb", 2014);
		Car car3 = new Golf("bbbbb", 2014);
		carDealership.addCarToStock(car1, 4500);
		carDealership.addCarToStock(car2, 8500);
		carDealership.addCarToStock(car3, 8500);
		// when
		HashMap<String, Float> prices = carDealership.getPriceCatalog();
		// then
		assertTrue(prices.size() == 2);
	}

	@Test
	public void testExternalModificationOfPriceCatalogDoNotAffectInternalStructure() {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Car car1 = new Passat("aaaaa", 2015);
		Car car2 = new Golf("bbbbb", 2014);
		carDealership.addCarToStock(car1, 4500);
		carDealership.addCarToStock(car2, 8500);
			// when
		HashMap<String, Float> prices = carDealership.getPriceCatalog();
		prices.remove(car1.getChassisNumber());
		// then

		assertEquals(2, carDealership.getPriceCatalog().size());
	}

	@Test
	public void testWhenAddingCarToStockPriceIsCorrect(){
		//given
		CarDealership carDealership = new CarDealership("compexit");
		Car car1 = new Passat("aaaaa", 2015);
		//when
		carDealership.addCarToStock(car1, 4500);
		//then
		HashMap<String, Float> prices = carDealership.getPriceCatalog();
		float price = prices.get(car1.getChassisNumber());
		assertEquals(4500, price,0);
	}
	
	@Test
	public void testCarDealershipClientAccountsIsNotEmpty() {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Client client = new Client("1902345","name","str. Primaverii nr.2");
		ClientAccount clientAccount = carDealership.getWithAddClientAccount(client);
		// when
		HashMap<String, ClientAccount> clientsInventory = carDealership.getClientAccounts();
		// then
		assertTrue(!clientsInventory.isEmpty());
	}

	@Test
	public void testCarDealershipClientAccountsContainsCorrectNumberOfClientAccounts() {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Client client1 = new Client("1902345","name","str. Primaverii nr.2");
		ClientAccount clientAccount1 = carDealership.getWithAddClientAccount(client1);
		Client client2 = new Client("1907777777","Misty Copeland","str. Memorandumului nr.2");
		ClientAccount clientAccount2 = carDealership.getWithAddClientAccount(client2);
		// when
		HashMap<String, ClientAccount> clientsInventory = carDealership.getClientAccounts();
		// then
		assertEquals(2, clientsInventory.size());
	}

	@Test
	public void testCarDealershipClientAccountsDoesNotContainDuplicates() {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Client client1 = new Client("1902345","name","str. Primaverii nr.2");
		ClientAccount clientAccount1 = carDealership.getWithAddClientAccount(client1);
		Client client2 = new Client("1902345","NAME","str. Buzesti nr.2");
		ClientAccount clientAccount2 = carDealership.getWithAddClientAccount(client2);
		// when
		HashMap<String, ClientAccount> clientsInventory = carDealership.getClientAccounts();
		// then
		assertEquals(1, clientsInventory.size());
		String key = client1.getId();
		assertEquals("name",(clientsInventory.get(key).getClientName()));
		assertNotEquals("NAME",(clientsInventory.get(key).getClientName()));
	}

	@Test
	public void testExternalModificationOfClientAccountsDoesNotAffectInternalStructure() {
		// given
		CarDealership carDealership = new CarDealership("compexit");
		Client client1 = new Client("1902345","name","str. Primaverii nr.2");
		ClientAccount clientAccount1 = carDealership.getWithAddClientAccount(client1);
		Client client2 = new Client("1907777777","Misty Copeland","str. Memorandumului nr.2");
		ClientAccount clientAccount2 = carDealership.getWithAddClientAccount(client2);
			// when
		HashMap<String, ClientAccount> clientsInventory = carDealership.getClientAccounts();
		clientsInventory.remove(client1.getId());
		// then
		assertEquals(2, carDealership.getClientAccounts().size());
	}

	

}	
