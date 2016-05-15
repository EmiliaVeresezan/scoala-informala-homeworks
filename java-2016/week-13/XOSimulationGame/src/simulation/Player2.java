package simulation;

public class Player2 implements Runnable {

	private GameTable gameTable;
	private State state;

	public Player2(GameTable gameTable) {
		this.gameTable=gameTable;
		this.state = State.O;
	}

	@Override
	public void run() {
		while (gameTable.isGameOver()==false){
			gameTable.setO(state);
		}		
	}
}
