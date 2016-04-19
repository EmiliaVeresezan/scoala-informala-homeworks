package week5;

public class CKlasse extends Mercedes {
	
	private SeatHeating seatHeating = new SeatHeating();
	
	public CKlasse (float currentFuelAmount, String chasseNr){
		super(currentFuelAmount, chasseNr);
	}
	
	public CKlasse (String chasseNr, int productionYear){
		super(chasseNr, productionYear);
	}
	
	public void startSeatHeating(){
		seatHeating.start();
	}
	
	public void stopSeatHeating(){
		seatHeating.stop();
	}
	
	@Override 
	protected void updateFuelConsumption(double nrKm){
		super.updateFuelConsumption(nrKm);
		if (seatHeating.isOn()){
			float nrOf100M = (float) (nrKm * 10) ;
			//When seat heating is on, fuel consumption increases by 0.1%
			currentFuelAmount = currentFuelAmount - 0.001f * (nrOf100M * getFuelConsumptionFor100M(currentGear));
		}
	}
	
	
	protected float getFuelConsumptionFor100M(int gear){
		float result=0.0f;
		
		switch(gear){
			//0 is reverse gear
			case 0:
				result = 0.015f;
				break;
			case 1: 
				result = 0.04f;
				break;
			case 2: 
				result = 0.03f;
				break;
			case 3:
				result = 0.02f;
				break; 
			case 4: 
				result = 0.02f;
				break;
			case 5:
				result = 0.015f;
				break;
		}
		return result;
}

	
	private class SeatHeating {
		
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
