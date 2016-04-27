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
	
	private String dealershipName;
	private HashSet<Car> carsInStock = new HashSet<Car>();
	private HashMap<String, Float> priceCatalog = new HashMap<>();
	private HashMap<String, ClientAccount> clientAccounts = new HashMap<>();
	private BankAccount bankAccount; 
	
	//private PriceCatalog priceCatalog = new PriceCatalog();
	//private ClientAccountManagement clientAccounts = new ClientAccountManagement(); 
	
	
	public CarDealership(String dealershipName){
		this.dealershipName = dealershipName;
	}
		
	public void addCarToStock(Car car, float price){
		carsInStock.add(car);
		priceCatalog.put(car.getChassisNumber(),  price);
	}
	
	public List<Car> listAllCars() {
		return new ArrayList<Car>(carsInStock);
	}
	
	public void sellCarToClient(Client client,Car car) throws InsufficientFundsException, BankAccountNotFoundException  {
		if (carsInStock.contains(car)) {
				client.getBankAccount().transferFunds(priceCatalog.get(car.getChassisNumber()), bankAccount);
				carsInStock.remove(car);
				ClientAccount clientAccount = getWithAddClientAccount(client);
				clientAccount.addCarToFleet(car);
		}
	}
			
	public void buyCarFromClient(Car car, Client client, float price) throws BankAccountNotFoundException  {
			try {
				bankAccount.transferFunds(price, client.getBankAccount());
				float newPrice = (float) (price + 0.2*price);
				addCarToStock(car, newPrice);
				ifClientHasClientAccountRemoveCarFromFleet(client, car);
			} catch (InsufficientFundsException e) {
				e.printStackTrace();
				System.out.println("The dealership does not have sufficient funds to buy the car.");
			}
	}
	
	private void ifClientHasClientAccountRemoveCarFromFleet(Client client, Car car){
		if (getClientAccount(client)!=null){
			ClientAccount clientAccount = getClientAccount(client);
			if (clientAccount.getFleet().contains(car)){
				clientAccount.removeCarFromFleet(car);
			}
			//else do nothing
		}
		//else do nothing
	}
		
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
	
	public HashMap<String, Float> getPriceCatalog() {
		return new HashMap<String, Float>(priceCatalog);
	}
	
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
