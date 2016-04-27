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
	
	private String clientName;
	private List<Car> clientCarList = new ArrayList<>();

	public ClientAccount(String clientName) {
		super();
		this.clientName = clientName;
	}
	
	public void addCarToFleet(Car car) {
		clientCarList.add(car);
	}

	public List<Car> getFleet() {
		return new ArrayList<Car>(clientCarList);
	}
	
	//this method should only be used by the car dealership internally
	void removeCarFromFleet(Car car){
		clientCarList.remove(car);
	}
	
	public String getClientName() {
		return clientName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientName == null) ? 0 : clientName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientAccount other = (ClientAccount) obj;
		if (clientName == null) {
			if (other.clientName != null)
				return false;
		} else if (!clientName.equals(other.clientName))
			return false;
		return true;
	}

	/**
	 * This method takes an AgeComparator object as parameter and sorts the list it is applied to
	 * in ascending order, using the comparator criteria.
	 * @param SORT_BY_AGE  an AgeComparator object 
	 * @return   the sorted list
	 */
	public List<Car> myFleet(AgeComparator SORT_BY_AGE) {
		Collections.sort(this.clientCarList, SORT_BY_AGE);
		return this.clientCarList;
	}
	
	/**
	 * This method takes a FuelEfficiencyComparator object as parameter and sorts the list 
	 * it is applied to in ascending order, using the comparator criteria.
	 * @param SORT_BY_FUEL_EFF  a FuelEfficiencyComparator object 
	 * @return   the sorted list
	 */
	public List<Car> myFleet(FuelEfficiencyComparator SORT_BY_FUEL_EFF){
		Collections.sort(this.clientCarList, SORT_BY_FUEL_EFF);
		return this.clientCarList;
	}

	/**
	 * Implements the Comparator interface for type Car,
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
	 * Implements the Comparator interface for type Car,
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
