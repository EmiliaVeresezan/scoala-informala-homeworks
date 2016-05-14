package simulation;

import java.util.Random;

public class Player1 implements Runnable{
	
	private GameTable gameTable;
	private State state;

	public Player1(GameTable gameTable) {
		this.gameTable=gameTable;
		this.state = State.X;
	}

	@Override
	public void run() {
		while (gameTable.getGameOver()==false){
//			System.out.println("Player "+state+" will make move on position "+position);
			gameTable.setX(state);
		}
		
	}
	
//	public int generateAvailablePosition(){
//		Random random = new Random();
//		int tablePosition;
//		do {
//			tablePosition = random.nextInt(8);
//			System.out.println("Generated X: " + tablePosition);
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
