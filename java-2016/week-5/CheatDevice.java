
class CheatDevice {

	private boolean active = true; 
	private float fuelDecreaseCoef;
	
	public CheatDevice(float fuelDecreaseCoef){
		this.fuelDecreaseCoef = fuelDecreaseCoef;
	}
	
	public void start() {
		active = true;
	}
	
	public void stop() {
		active = false; 
	}
	
	public boolean isOn(){
		return active; 
	}
	
	//The cheat device reduces fuel consumption/100m by a coefficient
	public float getFuelDecreaseCoef(){
		return fuelDecreaseCoef;
	}
	
	//fuel used for the functioning of the cheat device is 0.01 liters for all cheat device objects
	public float getCheatDeviceConsumption(){
		return 0.01f;
	}

}
