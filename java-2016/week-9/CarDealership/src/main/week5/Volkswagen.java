package week5;

public abstract class Volkswagen extends Car{
	
	protected CheatDevice cheatDevice = new CheatDevice(0.2f);
	
	public Volkswagen(float currentFuelAmount, String chasseNr){
		super(currentFuelAmount, chasseNr);
	}
	
	public Volkswagen(String chasseNr, int productionYear) {
		super(chasseNr, productionYear);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void updateFuelConsumption(double nrKm){
		super.updateFuelConsumption(nrKm);
		if (cheatDevice.isOn()){
				//When cheat device is on, fuel consumption increases by a constant value used to power the cheat device 
				currentFuelAmount = currentFuelAmount - cheatDevice.getCheatDeviceConsumption();
		}
	}
	
	
	
	 
}
