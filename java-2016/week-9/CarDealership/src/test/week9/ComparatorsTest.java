package week9;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
		
	}
	
	/**
	 * This method tests that after performing a sort using an AgeComparator the list returned
	 * is sorted correctly, meaning that the oldest car is set to the first position in the list.
	 * It also prints the sorted list. 
	 */
	@Test
	public void sortByAgeComparatorTest() {
		//given 
		clientAccount = new ClientAccount("Jill");
		car1 = new CKlasse("nnnnn", 2011);
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
		
		ClientAccount.AgeComparator SORT_BY_AGE = new ClientAccount.AgeComparator();
		
		//when
		clientAccount.myFleet(SORT_BY_AGE);
		
		//then
		ArrayList<Car> cars = (ArrayList<Car>) clientAccount.getFleet();
		for (int i = 0; i<cars.size()-1; i++){
			assertTrue(cars.get(i).getProductionYear()<=cars.get(i+1).getProductionYear());
		}
		System.out.println("The cars, sorted by production year");
		for (Car c: cars){
			System.out.println(c.toString());
		}
	}
	
	/**
	 * This method tests that after performing a sort using a FuelEfficiencyComparator the list
	 * returned is sorted correctly. This means that the car with the highest fuel efficiency 
	 * (lowest average fuel consumption) is placed in the first index of the list.
	 * It also prints the sorted list.
	 */
	@Test
	public void sortByFuelEfficiencyTest(){
		//given
		clientAccount = new ClientAccount("Jill");
		car1 = new CKlasse("nnnnn", 2011);
		car1.setStartFuelAmount(35);
		car1.setCurrentFuelAmount(5);
		car1.setDrivenKm(180);
		
		car2 = new Passat("tjsij7828hajnsj", 2001);
		car2.setStartFuelAmount(40);
		car2.setCurrentFuelAmount(10.3f);
		car2.setDrivenKm(80);

		car3 = new Passat("abba9386ndhydnj09snn", 2007);
		car3.setStartFuelAmount(25);
		car3.setCurrentFuelAmount(10.3f);
		car3.setDrivenKm(50);
		
		clientAccount.addCarToFleet(car1);
		clientAccount.addCarToFleet(car2);
		clientAccount.addCarToFleet(car3);	

		ClientAccount.FuelEfficiencyComparator SORT_BY_FUEL_EFF = new ClientAccount.FuelEfficiencyComparator();
		
		//when
		clientAccount.myFleet(SORT_BY_FUEL_EFF);
		
		//then
		ArrayList<Car> cars = (ArrayList<Car>) clientAccount.getFleet();
		for (int i = 0; i<cars.size()-1; i++){
			assertTrue(cars.get(i).getAverageFuelConsumption()<=cars.get(i+1).getAverageFuelConsumption());
		}
		System.out.println("The cars, sorted by fuel efficiency");
		for (Car c: cars){
			System.out.println(c.toString()+"- "+c.getAverageFuelConsumption());
		}
	}
	
	@After
	public void destroy(){
	}
	
	

}
