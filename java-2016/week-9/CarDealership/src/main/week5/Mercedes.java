package week5;

public abstract class Mercedes extends Car {
	
	private SatNav satNav = new SatNav();
	
	public Mercedes(float currentFuelAmount, String chasseNr){
		super(currentFuelAmount, chasseNr);
	}
	
	public Mercedes(String chasseNr, int productionYear) {
		super(chasseNr, productionYear);
	}

	public Mercedes(float currentFuelAmount, String chasseNr, SatNav satNav) {
		super(currentFuelAmount, chasseNr);
		this.satNav = satNav;
	}



	public void useSatNav(){
		satNav.start();
	}
	
	public void closeSatNav(){
		satNav.stop();
		satNav = null;
	}
	
	@Override
	protected void updateFuelConsumption(double nrKm){
		super.updateFuelConsumption(nrKm);
		float nrOf100M = (float) (nrKm * 10) ; 
		if (satNav.isOn()){
				//When Sat Nav is on fuel consumption increases by 1% per 100m
				currentFuelAmount = currentFuelAmount - 0.01f *(nrOf100M * getFuelConsumptionFor100M(currentGear));
		}
	}
	
	private class SatNav {
		
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
	

