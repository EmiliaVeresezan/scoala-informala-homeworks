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
	}
	
	@Test
	public void testClientCarListNotEmpty(){
		//given
		ClientAccount clientAccount = new ClientAccount("Maria Petrescu");
		Car car1 = new SKlasse("jjjj", 2010);
		Car car2 = new Golf("ssss", 2008);
		//when 
		clientAccount.addCarToFleet(car1);
		clientAccount.addCarToFleet(car2);
		//then
		assertNotNull(clientAccount.getFleet());
	}
	
	@Test
	public void testClientCarListContainsCorrectNumberOfCars(){
		//given
		ClientAccount clientAccount = new ClientAccount("Maria Petrescu");
		Car car1 = new SKlasse("jjjj", 2010);
		Car car2 = new Golf("ssss", 2008);
		//when 
		clientAccount.addCarToFleet(car1);
		clientAccount.addCarToFleet(car2);
		//then
		assertEquals(2,clientAccount.getFleet().size());
	}
	
	@Test
	public void testExternalModificationToCarListDoesNotAffectInternalStructure(){
		//given
		ClientAccount clientAccount = new ClientAccount("Maria Petrescu");
		Car car1 = new SKlasse("jjjj", 2010);
		Car car2 = new Golf("ssss", 2008); 
		clientAccount.addCarToFleet(car1);
		clientAccount.addCarToFleet(car2);
		//when 
		clientAccount.getFleet().remove(car1);
		//then
		assertEquals(2,clientAccount.getFleet().size());
	}
	
	 /**
	 * Closes data types.
	 */
	@After
	public void destroy(){

	}
}
