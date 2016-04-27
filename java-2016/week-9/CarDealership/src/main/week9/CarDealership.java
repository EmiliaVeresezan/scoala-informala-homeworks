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
 * Represents a car dealership and holds the inventories necessary for the functioning of 
 * the dealership: a stock inventory that holds the cars the dealership owns,
 * a price catalog that holds the prices of the cars, each price linked to its respective car, 
 * a client account inventory that holds the clients of the dealership.
 * @author emiliaveresezan
 *
 */
public class CarDealership {
	
	private String dealershipName;
	private HashSet<Car> carsInStock = new HashSet<Car>();
	private HashMap<String, Float> priceCatalog = new HashMap<>();
	private HashMap<String, ClientAccount> clientAccounts = new HashMap<>();
	private BankAccount bankAccount; 
	
	public CarDealership(String dealershipName){
		this.dealershipName = dealershipName;
	}
	
	/**
	 * Adds the car passed as parameter to the dealership stock.
	 * Adds the price passed as parameter to the price catalog.
	 * @param car	the car to be added
	 * @param price the price with which the car will be stored in the price catalog.
	 */
	public void addCarToStock(Car car, float price){
		carsInStock.add(car);
		priceCatalog.put(car.getChassisNumber(),  price);
	}
	
	/**
	 * Makes a copy of the internal cars inventory and returns it.
	 * @return  a copy of the cars inventory the car dealership keeps.
	 */
	public List<Car> listAllCars() {
		return new ArrayList<Car>(carsInStock);
	}
	
	/**
	 * Sells the car passed as paramater to the client passed as parameter.
	 * If the car is in the dealership stock the price of the car is transfered from 
	 * client to dealership. The car is removed from the dealership stock and added to 
	 * the client's list of cars.  
	 * @param client   
	 * @param car
	 * @throws InsufficientFundsException - if the client does not have enough money to pay for the car. 
	 * In this case the selling does not take place.
	 * @throws BankAccountNotFoundException - if the client does not have a bank account. 
	 * In this situation the selling does not take place. 
	 */
	public void sellCarToClient(Client client,Car car) throws InsufficientFundsException, BankAccountNotFoundException  {
		if (carsInStock.contains(car)) {
				client.getBankAccount().transferFunds(priceCatalog.get(car.getChassisNumber()), bankAccount);
				carsInStock.remove(car);
				ClientAccount clientAccount = getWithAddClientAccount(client);
				clientAccount.addCarToFleet(car);
		}
	}
	
	/**
	 * The car dealership buys the car passed as parameter from the client passed as 
	 * parameter at the price passed as parameter. 
	 * If the dealership has enough funds to pay for the car the transaction takes palce: 
	 * the money is transfered to the client's account, the car is added to the dealership's 
	 * stock with an increase in price. If the ar is contained in the client's ClientAccount then 
	 * it is removed.  
	 * If the dealership does not have enough funds to pay for the car then the InsufficientFundsException 
	 * is caught, as a notification to the dealership. 
	 * @param car 	car to be bought. It can be a car bought previously from the dealership, 
	 * or a car that the client has aquired from somewhere else.
	 * @param client  the client form whom the car is bought. It can be a previous client of the dealership 
	 * (in which case the client will have a ClientAccount) or a new client.
	 * @param price  the price of the car
	 * @throws BankAccountNotFoundException - if the client does not have a bank account
	 * @throws TransactionCannotTakePlaceException - if the dealership has insufficient funds a new exception is thrown 
	 * 												for the client's information
	 */													
	public void buyCarFromClient(Car car, Client client, float price) throws BankAccountNotFoundException, TransactionCannotTakePlaceException  {
			try {
				bankAccount.transferFunds(price, client.getBankAccount());
				float newPrice = (float) (price + 0.2*price);
				addCarToStock(car, newPrice);
				ifClientHasClientAccountRemoveCarFromFleet(client, car);
			} catch (InsufficientFundsException e) {
				e.printStackTrace();
				System.out.println("The dealership does not have sufficient funds to buy the car.");
				throw new TransactionCannotTakePlaceException("Transaction cannot take place.");
			}
	}
	/**
	 * If the client has ClientAccount and the car can be found in the ClientAccount fleet, 
	 * then it will be removed from the fleet. 
	 * If the client does not have the car in the ClientAccount or does not have a ClientAccount,
	 * no action will be taken.
	 */
	private void ifClientHasClientAccountRemoveCarFromFleet(Client client, Car car){
		if (getClientAccount(client)!=null){
			ClientAccount clientAccount = getClientAccount(client);
			if (clientAccount.getFleet().contains(car)){
				clientAccount.removeCarFromFleet(car);
			}
		}
	}
	/**
	 * Creates ClientAccount if the client doesn't already have one and returns it.	
	 * @param client
	 * @return ClientAccount for client
	 */
	ClientAccount getWithAddClientAccount(Client client) {
		if (!clientAccounts.containsKey(client.getId())) {
			clientAccounts.put(client.getId(), new ClientAccount(client.getName()));
		}
		ClientAccount clientAccount = clientAccounts.get(client.getId());
		return clientAccount;
	}
	
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public ClientAccount getClientAccount(Client client) {
		return clientAccounts.get(client.getId());
	}
	
	/**
	 * Makes a copy of the internal price catalog inventory and returns it.
	 * @return  a copy of the price catalog inventory the car dealership keeps.
	 */
	public HashMap<String, Float> getPriceCatalog() {
		return new HashMap<String, Float>(priceCatalog);
	}
	/**
	 * Makes a copy of the internal client accounts inventory and returns it.
	 * @return  a copy of the client accounts inventory the car dealership keeps.
	 */
	public HashMap<String, ClientAccount> getClientAccounts() {
		return new HashMap<String, ClientAccount>(clientAccounts);
	}

	/**
	 * Populates the car dealership stock with cars and their prices.
	 */
	public void addInitialStock() {
		Car car1 = new CKlasse("phdja0934hkkadsn", 2011);
		addCarToStock(car1, 20000.0f);
		Car car2 = new Passat("tjsij7828hajnsj", 2001);
		addCarToStock(car2, 19000.0f);
		Car car3 = new Golf("jshdyb9727jdhadhgg", 2006);
		addCarToStock(car3, 18500.0f);
	}
}
