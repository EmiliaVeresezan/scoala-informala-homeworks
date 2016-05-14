package simulation;

import java.util.Random;

public class Player2 implements Runnable {

	private GameTable gameTable;
	private State state;

	public Player2(GameTable gameTable) {
		this.gameTable=gameTable;
		this.state = State.O;
	}

	@Override
	public void run() {
		Random random = new Random();
		while (gameTable.getGameOver()==false){
//			System.out.println("Player "+state+" will make move on position "+position);
			gameTable.setO(state);
		}
		
	}
	
//	public int generateAvailablePosition(){
//		Random random = new Random();
//		int tablePosition;
//		do {
//			tablePosition = random.nextInt(8);
//			System.out.println("Generated O: " + tablePosition);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		} while (gameTable.checkPositionAvailable(tablePosition, gameTable.getGrid())==false);
//			
//		return tablePosition;
//	}

}
