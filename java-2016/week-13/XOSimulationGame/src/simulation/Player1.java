package simulation;

public class Player1 implements Runnable{
	
	private GameTable gameTable;
	private State state;

	public Player1(GameTable gameTable) {
		this.gameTable=gameTable;
		this.state = State.X;
	}

	@Override
	public void run() {
		while (gameTable.isGameOver()==false){
			gameTable.setX(state);
		}
	}
}
