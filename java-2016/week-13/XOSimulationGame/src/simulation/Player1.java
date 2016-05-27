package simulation;

public class Player1 implements Runnable{
	
	private GameTable gameTable;
	private State state;

	public Player1(GameTable gameTable, State state) {
		this.gameTable=gameTable;
		this.state = state;
	}

	@Override
	public void run() {
		while (gameTable.isGameOver()==false){
			gameTable.setX(state);
		}
	}
}
