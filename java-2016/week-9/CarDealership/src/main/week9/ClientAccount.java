package week9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import week5.Car;

/**
 * Represents the client account that a dealership creates for each client. 
 * Holds a reference to the client and a list of cars the client has bought from the dealership.
 * @see Client, CarDealership
 * @author Emilia
 *
 */
public class ClientAccount {
	
	private Client client;
	private List<Car> myFleet = new ArrayList<Car>();
	
	public ClientAccount() {
		
	}

	/**
	 * Creates a ClientAccount object by taking a reference to a client. 
	 * @param client reference to a Client object
	 */
	public ClientAccount(Client client) {
		this.client = client;
	}
	
	/**
	 * Adds a Car object to the list of cars the client has bought from the dealership.
	 * @param car  the car to be added
	 * @see Car
	 */
	public void addCarToFleet(Car car){
		myFleet.add(car);
	}
	
	/**
	 * Removes the car passed as parameter from the client account's fleet inventory.
	 * @param car
	 */
	public void removeCarFromFleet(Car car) {
		myFleet.remove(car);
	}
	
	/**
	 * Prints the cars found in myFleet inventory
	 */
	public void listCars() {
		for (Car c : myFleet){
			System.out.println(c.toString());
		}
	}
	
	/**
	 * Checks whether a car, passed as parameter, is already in the fleet inventory.
	 * @param car  a reference to the the car object
	 * @return true if the car is already in the fleet, returns false otherwise.
	 */
	public boolean isCarInFleet(Car car){
		if (myFleet.contains(car)){
			return true;
		}
		return false;
	}
	
	public List<Car> getMyFleet() {
		return myFleet;
	}
	
	public List<Car> myFleet(){
		return this.myFleet;
	}

	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "ClientAccount [client=" + client + ", myFleet=" + myFleet + "]";
	}

	
	/**
	 * This method takes an AgeComparator object as parameter and sorts the list it is applied to
	 * in ascending order, using the comparator criteria.
	 * @param SORT_BY_AGE  an AgeComparator object 
	 * @return   the sorted list
	 */
	public List<Car> myFleet(AgeComparator SORT_BY_AGE) {
		Collections.sort(this.myFleet, SORT_BY_AGE);
		return this.myFleet;
	}
	
	/**
	 * This method takes a FuelEfficiencyComparator object as parameter and sorts the list 
	 * it is applied to in ascending order, using the comparator criteria.
	 * @param SORT_BY_FUEL_EFF  a FuelEfficiencyComparator object 
	 * @return   the sorted list
	 */
	public List<Car> myFleet(FuelEfficiencyComparator SORT_BY_FUEL_EFF){
		Collections.sort(this.myFleet, SORT_BY_FUEL_EFF);
		return this.myFleet;
	}
	
	
	/**
	 * This class implements the Comparator interface for type Car,
	 * and establishes order of objects based on the car's production year.
	 * which also implements the Comparable interface. 
	 * @author emiliaveresezan
	 * @see Car
	 *
	 */
	static class AgeComparator implements Comparator<Car> {
		
		/**
		 * This method compares 2 Car objects using acompareTo method, overridden
	     * in class Car to take for comparison the car's production year.
		 */
		@Override
		public int compare(Car car1, Car car2) {
			return car1.compareTo(car2);
		}
	}
	
	
	/**
	 * This class implements the Comparator interface for type Car,
	 * and establishes order of objects based on a car's fuel efficiency. 
	 * @author emiliaveresezan
	 *
	 */
	static class FuelEfficiencyComparator implements Comparator<Car>{
		
		/**
		 * This method is overridden to compare 2 car object based on their fuel efficiency. 
		 * Low average fuel consumption means high fuel efficiency.
		 * The method compares its two arguments, car1 and car2, for order. 
		 * Returns a negative integer, zero, or a positive integer as the first argument is less than,
		 * equal to, or greater than the second.
		 * 		
		 */
		@Override
		public int compare(Car car1, Car car2){
			//the most fuel efficient car is the one with the lowest average fuel consumption 
			Float car1FuelEff = car1.getAverageFuelConsumption();
			Float car2FuelEff = car2.getAverageFuelConsumption();
			return car1FuelEff.compareTo(car2FuelEff);
		}
	}

}
