package week9;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import week5.Car;

/**
 * Holds a price catalog that uses car chasse numbers to identify car prices. 
 * @author Emilia
 *
 */
public class PriceCatalog {
	
	//the prices HashMap will have chasseNr as key and price as value
	HashMap<String, Double> carPrices = new HashMap<String, Double>();
	HashSet<Car> cars = new HashSet<Car>();
	
	public PriceCatalog(){
	}
	
	public PriceCatalog(HashSet<Car> cars) {
		super();
		this.cars = cars;
	}

	/**
	 * Adds the price of a car to the price catalog. The car is identified by its chasse number,
	 * which is unique.
	 * @param car	the car for which the price is added
	 * @param price the car price
	 */
	public void addCarPrice(Car car, Double price){
		if (!carPrices.containsKey(car.getChasseNr())){
		carPrices.put(car.getChasseNr(), price);
		}
		else {
			System.out.println("Car already has a price");
		}
	}
	
	/**
	 * Checks if the car reference passed as parameter can be found in the dealership's price catalog.
	 * @param car  reference to a car object 
	 * @return	   true if the car id - price pair is already in the price catalog
	 * 			   false if the car cannot be found in the price catalog 
	 */
	public boolean isInPriceCatalog(Car car){
		if (carPrices.containsKey(car.getChasseNr())){
			return true;
		}
		return false;
	}
	
	/**
	 * Takes as parameter a Car and gets its price form the price catalog.
	 * @param car  the car for which the price is needed
	 * @return		price of car 
	 */
	public Double getCarPrice(Car car){
		String carId = car.getChasseNr();
		return carPrices.get(carId);
	}
	
	/**
	 * Remove a car and its price from the price catalog.
	 * @param car  the reference to the car object to be removed.
	 */
	public void remove(Car car){
		carPrices.remove(car.getChasseNr());
	}
	
	/*
	public void changeCarPrice(Car car, Double price){
		if (carPrices.containsKey(car.getChasseNr())){
			carPrices.put(car.getChasseNr(), price);
		}
		else {
			System.out.println("Car cannot be found in the price inventory");
		}
	}
	*/
	
	public HashMap<String, Double> getCarPrices() {
		return carPrices;
	}

	public void setCarPrices(HashMap<String, Double> carPrices) {
		this.carPrices = carPrices;
	}
	
	
}
