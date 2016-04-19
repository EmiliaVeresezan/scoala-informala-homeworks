package week9;

import java.util.List;

import week5.Car;


public class CarDealershipApp {
	
	public static void main(String[] args){
		
		Client client = new Client(2781023872301L);

		BankAccount bankAccount = new BankAccount(100000);

		client.setBankAccount(bankAccount);

		CarDealership compexit = new CarDealership("Compexit");
		
		compexit.addInitialStock();

		System.out.println("List of cars owned by the dealership");
		
		List<Car> stock = compexit.listAllCars();
		
		//sell 2 cars to the client
				
		try{
		
			Car car = stock.get(1);
		
			compexit.sellCarToClient(car, client);
		
		}catch(IndexOutOfBoundsException e){
			
			System.out.println("The car you wish to buy is not in stock.");
		}
		catch (IllegalStateException ex){
			
			System.out.println(ex.getMessage());
		}
		
		try{
			
			Car car = stock.get(2);
			
			compexit.sellCarToClient(car, client);
			
		}catch(IndexOutOfBoundsException e){
				
			System.out.println("The car you wish to buy is not in stock.");
		
		}catch (IllegalStateException ex){
				
			System.out.println(ex.getMessage());
		}
				
		try {
		
			ClientAccount clientAccount = compexit.getAccount(client);
			
			List<Car> myCars = clientAccount.myFleet();
			
			ClientAccount.AgeComparator SORT_BY_AGE = new ClientAccount.AgeComparator();
		
			myCars = clientAccount.myFleet(SORT_BY_AGE);
			
			//list the cars in the client's fleet
			
			System.out.println("List of cars that are in the client's fleet, sorted by age of car");
			
			clientAccount.listCars();
			
			// Sell oldest car

			Car oldestCar = myCars.get(0);

			System.out.println("Oldest car: " + oldestCar.toString());
			 
			float carPrice = 4999f;
			 
			compexit.buyCarFromClient(oldestCar, client, carPrice);
			 
			// bank account should be updated

			 // fleet should not contain the sold car. 
			 
			 System.out.println("List of cars in the clien't fleet, after oldest car was sold");
			 
			 clientAccount.listCars();

		
		} catch (ClientAccountNotFoundException e1) {
			
			System.out.println(e1.getMessage());
		}
		catch (BankAccountNotFoundException e){
			 
			 System.out.println(e.getMessage());
		}
		 		 
	}
	
	

}
