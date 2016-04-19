package week5;

public class SKlasse extends Mercedes {
	
	private TurboCharge turbo = new TurboCharge();
	
	public SKlasse(float currentFuelAmount, String chasseNr){
		super(currentFuelAmount, chasseNr);
	}
	
	public SKlasse(String chasseNr, int productionYear){
		super(chasseNr, productionYear);
	}
	
	public void useTurbo(){
		turbo.start();
	}
	
	public void stopTurbo(){
		turbo.stop();
		turbo = null;
	}
	
	@Override 
	protected void updateFuelConsumption(double nrKm){
		super.updateFuelConsumption(nrKm);
		if (turbo.isOn()){
			float nrOf100M = (float) (nrKm * 10) ;
			//When turbo charge in on fuel consumption increases by 25% 
			currentFuelAmount = currentFuelAmount - 0.25f * (nrOf100M * getFuelConsumptionFor100M(currentGear));
		}
	}
	
	
	protected float getFuelConsumptionFor100M(int gear){
		float result=0.0f;
		
		switch(gear){
			//0 is reverse gear
			case 0:
				result = 0.03f;
				break;
			case 1: 
				result = 0.05f;
				break;
			case 2: 
				result = 0.04f;
				break;
			case 3:
				result = 0.03f;
				break; 
			case 4: 
				result = 0.02f;
				break;
			case 5:
				result = 0.01f;
				break;
		}
		return result;
}

	private class TurboCharge {
		
		private boolean active = true;
		
		public void start(){
			active = true;
		}
		
		public void stop(){
			active = false;
		}
		
		public boolean isOn (){
			return active;
		}
	}

}
