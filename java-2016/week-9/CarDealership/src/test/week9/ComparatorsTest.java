package week9;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import week5.CKlasse;
import week5.Car;
import week5.Passat;

/**
 * Test class for the comparators used to sort cars that belong to clients. 
 * @author emiliaveresezan
 *
 */
public class ComparatorsTest {

	ClientAccount clientAccount;
	Car car1, car2, car3;
	
	@Before
	public void init(){
		
		clientAccount = new ClientAccount();
		car1 = new CKlasse("phdja0934hkkadsn", 2011);
		car1.setStartFuelAmount(35);
		car1.setCurrentFuelAmount(5);
		car1.setDrivenKm(180);
		
		car2 = new Passat("tjsij7828hajnsj", 2001);
		car2.setStartFuelAmount(40);
		car2.setCurrentFuelAmount(10.3f);
		car2.setDrivenKm(120);

		car3 = new Passat("abba9386ndhydnj09snn", 2007);
		car3.setStartFuelAmount(25);
		car3.setCurrentFuelAmount(10.3f);
		car3.setDrivenKm(50);
		
		clientAccount.addCarToFleet(car1);
		clientAccount.addCarToFleet(car2);
		clientAccount.addCarToFleet(car3);	
		
	}
	
	/**
	 * This method tests that after performing a sort using an AgeComparator the list returned
	 * is sorted correctly, meaning that the oldest car is set to the first position in the list.
	 * It also prints the sorted list. 
	 */
	@Test
	public void sortByAgeComparatorTest() {
		ClientAccount.AgeComparator SORT_BY_AGE = new ClientAccount.AgeComparator();
		
		clientAccount.myFleet(SORT_BY_AGE);
		//test that the oldest car is on the first index of myFleet list
		assertEquals("tjsij7828hajnsj", clientAccount.getMyFleet().get(0).getChasseNr());
		System.out.println("List of cars in order of age");
		clientAccount.listCars();
	}
	
	/**
	 * This method tests that after performing a sort using a FuelEfficiencyComparator the list
	 * returned is sorted correctly. This means that the car with the highest fuel efficiency 
	 * (lowest average fuel consumption) is placed in the first index of the list.
	 * It also prints the sorted list.
	 */
	@Test
	public void sortByFuelEfficiencyTest(){
		ClientAccount.FuelEfficiencyComparator SORT_BY_FUEL_EFF = new ClientAccount.FuelEfficiencyComparator();
		
		//list the cars' fuel consumption 
		System.out.println(car1.toString()+"- fuel consumption: "+car1.getAverageFuelConsumption());
		System.out.println(car2.toString()+"- fuel consumption: "+car2.getAverageFuelConsumption());
		System.out.println(car3.toString()+"- fuel consumption: "+car3.getAverageFuelConsumption());

		
		clientAccount.myFleet(SORT_BY_FUEL_EFF);
		
		//test that car with the lowest fuel consumption is on the first index of myFleet list
		assertEquals("phdja0934hkkadsn", clientAccount.getMyFleet().get(0).getChasseNr());
		
		System.out.println("List of cars in order of fuel efficieny");
		clientAccount.listCars();
		
	}
	
	@After
	public void destroy(){
		clientAccount = null;
		car1= null; car2 = null; car3= null;
		
	}
	
	

}
