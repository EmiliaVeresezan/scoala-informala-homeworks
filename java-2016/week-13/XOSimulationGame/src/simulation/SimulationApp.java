package simulation;

public class SimulationApp {
	
	public static void main (String[] args){
		GameTable gameTable = new GameTable();
		Thread player1 = new Thread(new Player1(gameTable));
		Thread player2 = new Thread(new Player2(gameTable));
		player1.start();
		player2.start();
	}
}
