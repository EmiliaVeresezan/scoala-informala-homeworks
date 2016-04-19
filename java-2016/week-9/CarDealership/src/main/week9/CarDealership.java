package week9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import week5.CKlasse;
import week5.Car;
import week5.Golf;
import week5.Passat;
/**
 * Holds the information necessary for the functioning of a car dealership:
 * the cars the dealership owns, the prices of those cars, the clients of the dealership.
 * @author emiliaveresezan
 *
 */
public class CarDealership {
	
	private String name;
	private Set<Car> cars = new HashSet<Car>();
	private PriceCatalog priceCatalog = new PriceCatalog();
	private ClientAccountManagement clientAccounts = new ClientAccountManagement(); 
	
	public CarDealership(String name){
		this.name = name;
	}
		
	public void addCar(Car car){
		boolean added=cars.add(car);
		if (!added){
			System.out.println("This car already exists in stock.");
		}
	}
	
	/**
	 * If the client has enough money in the bank to pay for the car the bank account sum is decreased.
	 * If the client already has a client account in the dealership that account is used. If not, an account
	 * is created. The car is added to the car fleet of the client. The car is removed from the dealership 
	 * stock and price catalog.
	 * If the client does not have enough money in their bank account, an IllegalStateException is thrown to
	 * the caller of the method .
	 * @param car	  the car the client wants to buy
	 * @param client  the client that contains the client's bank account	
	 * @throws IllegalStateException if the client does not have at least the price of the car 
	 * in their bank account. 
	 */
	public void sellCarToClient(Car car, Client client) throws IllegalStateException  {
		//decrease the client's bank account sum with the car price 
		client.decreaseBankAccountSum(priceCatalog.getCarPrice(car));
		
		//if the client cannot be found in the client accounts, create client account
		if(!clientAccounts.contains(client)){
			clientAccounts.createClientAccount(client);
		}
		//add car to the client's car fleet in clientAccount
		clientAccounts.getClientAccount(client).addCarToFleet(car);
		
		//remove the car from the dealership stock and price catalog
		cars.remove(car);
		priceCatalog.remove(car);
	} 
	
	/**
	 * When the dealership buys a car from a client, the bank account sum of the client is increased,
	 * the car is removed from the client's fleet inventory, if the client has a client account.
	 * The car is added to the dealership's car inventory and price catalog.
	 * @param car		car to be bought
	 * @param client	the client, who can have a client account or not
	 * @param carPrice	the amount of money the client receives for the car
	 * @throws BankAccountNotFoundException if the client the car is cold to does not have a bank account.
	 */
	public void buyCarFromClient(Car car, Client client, float carPrice) throws BankAccountNotFoundException{
		//increase the client's bank account amount with the car price
		client.increaseBankAccountSum(carPrice);
		
		if (clientAccounts.contains(client)){
		//remove car form the client's car fleet
		clientAccounts.getClientAccount(client).removeCarFromFleet(car);
		}
		//add the car to dealership's stock
		cars.add(car);
		
		//add the car to the dealership's price catalog with an increase in price
		Double price = carPrice + 0.2*carPrice;
		priceCatalog.addCarPrice(car, price);
		
	}
	
	
	/**
	 * Creates a list of the cars the dealerships owns and prints them with their prices.
	 * @return  the list of cars 
	 */
	public List<Car> listAllCars() {
		List<Car> carList = new ArrayList<Car>();
		//copy cars from HashSet to List
		carList.addAll(cars);
		//Print cars from List 
		for (Car c : carList){
			System.out.println(c.toString()+" price: "+ priceCatalog.getCarPrice(c));
		}
		return carList;
	}
	
	/**
	 * Populates the car dealership stock with cars and their prices.
	 */
	public void addInitialStock() {
		Car car1 = new CKlasse("phdja0934hkkadsn", 2011);
		cars.add(car1);
		priceCatalog.addCarPrice(car1, 20000.0);
		Car car2 = new Passat("tjsij7828hajnsj", 2001);
		cars.add(car2);
		priceCatalog.addCarPrice(car2, 19000.0);
		Car car3 = new Golf("jshdyb9727jdhadhgg", 2006);
		cars.add(car3);
		priceCatalog.addCarPrice(car3, 18500.0);

	}
	/**
	 * Returns a ClientAccount for the Client reference passed as parameter.
	 * @param client   a reference to a Client
	 * @return  a ClientAccount reference
	 * @throws 	ClientAccountNotFoundException if the Client passed as paramater does not have
	 * a ClientAccount in the client accounts inventory of the dealership.
	 */
	public ClientAccount getAccount(Client client) throws ClientAccountNotFoundException{
			
		if (!clientAccounts.contains(client)){
			throw new ClientAccountNotFoundException("You do not have a client account.");
		}
		else {
			return clientAccounts.getClientAccount(client);
		}
		
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(HashSet<Car> cars) {
		this.cars = cars;
	}

	public PriceCatalog getPriceCatalog() {
		return priceCatalog;
	}

	public void setPriceCatalog(PriceCatalog priceCatalog) {
		this.priceCatalog = priceCatalog;
	}


	public ClientAccountManagement getClientAccounts() {
		return clientAccounts;
	}


	public void setClientAccounts(ClientAccountManagement clientAccounts) {
		this.clientAccounts = clientAccounts;
	}
		
}
