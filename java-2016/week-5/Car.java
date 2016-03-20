
public abstract class Car implements Vehicle {
	
	private final String chasseNr; 
	private final float maxFuelTank = 60; 
	protected final int nrOfGears = 6;
	private boolean isMoving = false;
	protected int currentGear;
	protected float currentFuelAmount;
	private float startFuelAmount;
	private double drivenKm;
	
	/*private enum fuelType {
	PETROL, DIESEL, ELECTRIC
}*/
	
	public Car (float currentFuelAmount, String chasseNr){
		this.currentFuelAmount = currentFuelAmount;
		this.chasseNr = chasseNr;
	}
	
	public void refuel(float literes){
		if ((currentFuelAmount +literes) <= maxFuelTank)
			currentFuelAmount = currentFuelAmount +literes;
		else {
			System.out.println("Too much fuel");
			System.out.println("Current level of fuel is "+ getAvailableFuel()+" literes");
		}			
    }
	
	@Override 
	public void start(){
		isMoving=true;
		startFuelAmount = currentFuelAmount;
		drivenKm = 0;
	}
	
	@Override 
	public void stop(){
		isMoving = false;
	}
	
	@Override
	public void drive (double nrKm){
		if (isMoving){
			drivenKm = drivenKm + nrKm;
			updateFuelConsumption(nrKm);
		}
	}
	
	public void shiftGear(int gear){
		//nrOfGears is 6 - from 0 to 5
		if ((gear>=0)||(gear<nrOfGears)){
			currentGear = gear;
		}
		else {
			System.out.println("Gear does not exist");
		}
	}
	
	protected void updateFuelConsumption(double nrKm){
		if (currentFuelAmount > 0){
			//compute km in hundreds of meters
			float nrOf100M = (float)(nrKm * 10) ; 
			currentFuelAmount = currentFuelAmount - (nrOf100M * getFuelConsumptionFor100M(currentGear));
		}
		else {
			System.out.println("Tank is empty. Please refuel");
		}
	}
	
	public float getAvailableFuel(){
		return currentFuelAmount;
	}
	
	public float getDrivenKm(){
		return (float)drivenKm;
	}
	
	protected abstract float getFuelConsumptionFor100M(int gear);
	
	
	public float getAverageFuelConsumption(){
		float fuelUsed = startFuelAmount - currentFuelAmount;
		float averageFuelConsumption = (fuelUsed*100)/(float)drivenKm;
		return averageFuelConsumption;
	}
	
	@Override
	public String toString(){
		return getClass().getName();
	}
}
