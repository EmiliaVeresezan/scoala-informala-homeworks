
public class Golf extends Volkswagen {
	
	//All Golf objects will have cheat devices that have the same fuel decrease coefficient	
	protected CheatDevice cheatDevice = new CheatDevice(0.03f);

	public Golf(float currentFuelAmount, String chasseNr){
		super(currentFuelAmount, chasseNr);
	}
	
	@Override
	protected void updateFuelConsumption(double nrKm){
		super.updateFuelConsumption(nrKm);
	}
	
	protected float getFuelConsumptionFor100M(int gear){
		float result = 0.0f;
		float fuelDecreaseC = 0;  //default value for when the cheatDevice is turned off.
		if (cheatDevice.isOn()){
			//When the cheat device is turned on, fuel consumption/100m decreases by a coefficient
			fuelDecreaseC = cheatDevice.getFuelDecreaseCoef(); 
		}
			switch(gear){
			//0 is reverse gear
				case 0:
					result = 0.005f; 
					break;
				case 1: 
					result = 0.015f;
					break;
				case 2: 
					result = 0.015f;
					break;
				case 3:
					result = 0.016f;
					break; 
				case 4: 
					result = 0.01f;
					break;
				case 5:
					result = 0.005f;
					break;
			}
			result = result- fuelDecreaseC * result;
			return result;
	}

	
}
