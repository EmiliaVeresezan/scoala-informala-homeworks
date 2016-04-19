package week5;

public class VehicleApp {
	
	public static void main(String... args){
		
		int currentFuelAmmount = 27;

		String chasseNumber = "oiqe0934hkkadsn";
		
		Car car = new SKlasse(currentFuelAmmount, chasseNumber); // SKlasse extends from Mercedes, Mercedes extends from Car
		
		car.start();

		car.shiftGear(1);

		car.drive(0.01);// drives 0.01 KMs

		car.shiftGear(2);

		car.drive(0.02);

		car.shiftGear(3);

		car.drive(0.5);

		car.shiftGear(4);

		car.drive(0.5);

		car.shiftGear(4);

		car.drive(0.5);

		car.shiftGear(5);

		car.drive(10);

		car.shiftGear(4);

		car.drive(0.5);

		car.shiftGear(3);

		car.drive(0.1);

		car.stop();

		float availableFuel = car.getAvailableFuel(); // this value must be smaller than the initial value passed in the constructor

		System.out.println("Available fuel after "+car.getDrivenKm()+" km for " +car.toString() + ": "+ availableFuel);
		
		float fuelConsumedPer100Km = car.getAverageFuelConsumption();
		
		System.out.println("Fuel consumed per 100 km for "+ car.toString()+ ": "+ fuelConsumedPer100Km);


		Vehicle vehicle = new Golf(30, "1987ddkshik289"); //available fuel and chasse number

		vehicle.start();

		vehicle.drive(1);

		vehicle.stop();

		Car car2 = (Car) vehicle;

		availableFuel = car2.getAvailableFuel();
		
		System.out.println("Available fuel after "+car2.getDrivenKm()+" km for " +car2.toString() + ": "+ availableFuel);

		fuelConsumedPer100Km = car2.getAverageFuelConsumption();
		
		System.out.println("Fuel consumed per 100 km for "+ car2.toString()+ ": " + fuelConsumedPer100Km);

	}

}
