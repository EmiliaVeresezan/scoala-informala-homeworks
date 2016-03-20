
public class Passat extends Volkswagen {
	
	FourWheelDrive fourWheelDrive = new FourWheelDrive();
	
	//All Passat objects will have cheat device objects that have the same fuel decrease coefficient	
	protected CheatDevice cheatDevice = new CheatDevice(0.02f);

	public Passat(float currentFuelAmount, String chasseNr){
		super(currentFuelAmount, chasseNr);
	}
	
	@Override
	protected void updateFuelConsumption(double nrKm){
		super.updateFuelConsumption(nrKm);
		if (fourWheelDrive.isOn()){
			float nrOf100M = (float)(nrKm * 10) ; 
			//When four wheel drive is on, the fuel consumption increases by 35%
			currentFuelAmount = currentFuelAmount - 0.35f*(nrOf100M * getFuelConsumptionFor100M(currentGear));
		}
	}
	
	protected float getFuelConsumptionFor100M(int gear){
		float result = 0.0f;
		float fuelDecreaseC = 0;  //default coefficient value for when the cheatDevice is turned off.
		if (cheatDevice.isOn()){
			//When the cheat device is turned on, fuel consumption/100m decreases by a coefficient 
			fuelDecreaseC = cheatDevice.getFuelDecreaseCoef(); 
		}
		switch(gear){
			//0 is reverse gear
			case 0:
				result = 0.02f; 
				break;
			case 1: 
				result = 0.04f;
				break;
			case 2: 
				result = 0.045f;
				break;
			case 3:
				result = 0.04f;
				break; 
			case 4: 
				result = 0.03f;
				break;
			case 5:
				result = 0.015f;
				break;
		}
		result = result - fuelDecreaseC * result;
		return result;
	}
	
	private class FourWheelDrive {
		private boolean active = true;
		
		public void start(){
			active = true;
		}
		
		public void stop(){
			active = false;
		}
		
		public boolean isOn(){
			return active;
		}
	}
}


