package week9;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import week5.*;

/**
 * Test class for the class ClientAccount.
 * 
 * @author Emilia
 *
 */
public class ClientAccountTest {

	List<Car> myFleet;
	BankAccount bankAccount;
	Client client;
	ClientAccount clientAccount;

	Car car1, car2, car3;
	
	@Before
	public void init() {

		myFleet = new ArrayList<Car>();
		bankAccount = createNewBankAccount(10000);
		client = new Client("Ada Gherghe", bankAccount);
		clientAccount = new ClientAccount(client);
		car1 = new Golf("gthd0187tnuas8920hdj", 2010);
		clientAccount.addCarToFleet(car1);
		car2 = new Passat("kaja0007tnuas0wh20hdj", 2011);
		clientAccount.addCarToFleet(car2);
		
		car3 = new SKlasse("isju82001jdhadsahnwuqlao", 2003);
		
	}
	
	/**
	 * Tests that the method addCarToFleet(Car car) from class ClientAccount adds the car to the fleet inventory.
	 */
	@Test
	public void addCarToFleetTest (){
		assertEquals(2, clientAccount.getMyFleet().size());
	}
	/**
	 * Tests that the method removeCarFromFleet(Car car) from class ClientAccount 
	 * removes the car passed as parameter form the fleet inventory. 
	 */
	@Test
	public void removeCarFromFleetTest(){
		Car car = new Golf("gthd0187tnuas8920hdj", 2010);
		clientAccount.removeCarFromFleet(car);
		
		assertEquals(1, clientAccount.getMyFleet().size());
		assertEquals("kaja0007tnuas0wh20hdj", clientAccount.getMyFleet().get(0).getChasseNr());
	}
	
	/**
	 * Tests the method isCarInFleet(Car car) in class ClientAccount.
	 */
	@Test
	public void isCarInFleetTest(){
		assertTrue(clientAccount.isCarInFleet(car1));
		assertFalse(clientAccount.isCarInFleet(car3));
	}
	
	/**
	 * Closes data types.
	 */
	@After
	public void destroy(){

		myFleet = null;
		bankAccount = null;
		client = null;
		clientAccount = null;
		
	}
	
	/**
	 * Helper method for constructing a BankAccount object
	 * @param sum  bank account initial sum
	 * @return     a BankAccount object
	 */
	private BankAccount createNewBankAccount(double sum){
		BankAccount bankAccount = new BankAccount(sum);
		bankAccount.setAccountNr("RO67BTRL891023RON526001XX");
		return bankAccount;
	}
	

}
