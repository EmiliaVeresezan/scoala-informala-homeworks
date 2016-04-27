package week9;

import java.util.List;

import week5.Car;


public class CarDealershipApp {
	
	public static void main(String[] args){
		
		Client client = new Client("28650925", "Laura C.", "Plopilor 28");
		BankAccount bankAccount = new BankAccount("8787", 100000.0f);
		client.setBankAccount(bankAccount);

		CarDealership compexit = new CarDealership("Compexit");
		BankAccount dealershipBankAccount = new BankAccount("3333", 10000.0f);
		compexit.setBankAccount(dealershipBankAccount);

		compexit.addInitialStock();
		System.out.println("List of cars owned by the dealership");
		List<Car> stock = compexit.listAllCars();
		for (Car c: stock){
			System.out.println(c.toString());
		}

		//sell 2 cars to the client
		try{
			Car car = stock.get(1);
			compexit.sellCarToClient(client, car);
		} catch(IndexOutOfBoundsException e){
			System.out.println("The car you wish to buy is not in stock.");
		} catch (InsufficientFundsException ex){
			ex.printStackTrace();
		} catch (BankAccountNotFoundException e){
			e.printStackTrace();
		}
		
		try{
			Car car = stock.get(2);
			compexit.sellCarToClient(client, car);
		}catch(IndexOutOfBoundsException e){
			System.out.println("The car you wish to buy is not in stock.");
		} catch (InsufficientFundsException ex){
			ex.printStackTrace();
		} catch (BankAccountNotFoundException e){
			e.printStackTrace();
		}
		
		ClientAccount clientAccount = compexit.getClientAccount(client);
		List<Car> myCars = clientAccount.getFleet();
		ClientAccount.AgeComparator SORT_BY_AGE = new ClientAccount.AgeComparator();
		myCars = clientAccount.myFleet(SORT_BY_AGE);
		
		//list the cars in the client's fleet
		System.out.println("List of cars that are in the client's fleet, sorted by age of car");
		for (Car c: myCars){
			System.out.println(c.toString());
		}
		
		// Sell oldest car
		Car oldestCar = myCars.get(0);
		System.out.println("Oldest car: " + oldestCar.toString());
		float carPrice = 4999f;
		try{
		compexit.buyCarFromClient(oldestCar, client, carPrice);
		// bank account should be updated
		 // fleet should not contain the sold car. 
		} catch (BankAccountNotFoundException e){
			 System.out.println(e.getMessage());
		}
		
		System.out.println("List of cars in the clien't fleet, after oldest car was sold");
		for (Car c: myCars){
			System.out.println(c.toString());
		}	
	}
}
