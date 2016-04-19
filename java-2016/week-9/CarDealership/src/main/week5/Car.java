package week5;


public abstract class Car implements Vehicle, Comparable<Car> {
	
	private final String chasseNr; 
	private final float maxFuelTank = 60; 
	protected final int nrOfGears = 6;
	private boolean isMoving = false;
	protected int currentGear;
	protected float currentFuelAmount;
	private float startFuelAmount;
	private double drivenKm;
	protected Integer productionYear;
	
	/*private enum fuelType {
	PETROL, DIESEL, ELECTRIC
}*/
	public Car (float currentFuelAmount, String chasseNr){
		this.currentFuelAmount = currentFuelAmount;
		this.chasseNr = chasseNr;
	}
	
	public Car (String chasseNr, int productionYear){
		this.chasseNr=chasseNr;
		this.productionYear=productionYear;
		
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
	
	public void setDrivenKm(double drivenKm) {
		this.drivenKm = drivenKm;
	}

	protected abstract float getFuelConsumptionFor100M(int gear);
	
	
	public float getAverageFuelConsumption(){
		float fuelUsed = startFuelAmount - currentFuelAmount;
		float averageFuelConsumption = (fuelUsed*100)/(float)drivenKm;
		return averageFuelConsumption;
	}

	public String getChasseNr() {
		return chasseNr;
	}
	
	public int getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}
	
	public float getCurrentFuelAmount() {
		return currentFuelAmount;
	}

	public void setCurrentFuelAmount(float currentFuelAmount) {
		this.currentFuelAmount = currentFuelAmount;
	}

	public float getStartFuelAmount() {
		return startFuelAmount;
	}

	public void setStartFuelAmount(float startFuelAmount) {
		this.startFuelAmount = startFuelAmount;
	}
	
	/**
	 * hashCode() method is overridden so that the hash number of the object is established based on car characteristics 
	 * that are permanent for the car:
	 * chasse number, maximum fuel tank capacity, number of gears and production year.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chasseNr == null) ? 0 : chasseNr.hashCode());
		result = prime * result + Float.floatToIntBits(maxFuelTank);
		result = prime * result + nrOfGears;
		result = prime * result + ((productionYear == null) ? 0 : productionYear.hashCode());
		return result;
	}
		
	
	/**
	 * Equals method is overridden so that it establishes equality based on car characteristics that are permanent for the car:
	 * chasse number, maximum fuel tank capacity, number of gears and production year. 
	 * It does not use other characteristics because a car is the same car no matter what its start fuel amount, current fuel amount
	 * and driven km are. 
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (chasseNr == null) {
			if (other.chasseNr != null)
				return false;
		} else if (!chasseNr.equals(other.chasseNr))
			return false;
		if (Float.floatToIntBits(maxFuelTank) != Float.floatToIntBits(other.maxFuelTank))
			return false;
		if (nrOfGears != other.nrOfGears)
			return false;
		if (productionYear == null) {
			if (other.productionYear != null)
				return false;
		} else if (!productionYear.equals(other.productionYear))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Car car){
		return this.productionYear.compareTo(car.productionYear);
	}

	@Override
	public String toString() {
		return "Car [chasseNr=" + chasseNr + ", maxFuelTank=" + maxFuelTank + ", nrOfGears=" + nrOfGears
				+ ", productionYear=" + productionYear + "]";
	}
	
	
}
