package simulation;

import java.util.ArrayList;
import java.util.Random;

public class GameTable {

	private ArrayList<State> grid = new ArrayList<State>();
	private int moveCount = 0;
	private boolean gameOver = false;
	private boolean XCanMakeMove = true;
	
	public GameTable() {
		//Initialize the gameTable object
		for (int i=0; i < 9; i++){
			grid.add(State.Blank);
		}
	}
	
	//setX will not have the position as parameter because while we check if the 
	//position set as parameter is available the other thraed is making modifications to the grid  
	public synchronized void setX(State state) {
		while (!XCanMakeMove){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//It's O's turn to make the move
		XCanMakeMove = false;
		int position = generateAvailablePosition(state);
		grid.add(position, state);
		moveCount = moveCount+1;
		System.out.println("Added "+state+" on position "+position);
		notifyAll();	
		
	}

	public synchronized void setO(State state){
		while (XCanMakeMove){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//It's X's turn to make the move
		XCanMakeMove = true;
		int position = generateAvailablePosition(state);
		grid.add(position, state);
		moveCount = moveCount+1;
		System.out.println("Added "+state+" on position "+position);
		notifyAll();	
		
	}
	
	
	public boolean getGameOver (){
		return gameOver;
	}
	
	public void printGrid() {
		for(int i=0; i<grid.size();i++)
			System.out.println(grid.get(i));
	}
	
	public synchronized ArrayList<State> getGrid(){
		return new ArrayList<State>(grid);
	}
	
	public boolean checkPositionAvailability(int tablePosition, ArrayList<State> grid) {
		if (getGrid().get(tablePosition)==State.Blank){
			return true;
		}
		else {
			return false;
		}
	}
	
	public synchronized int generateAvailablePosition(State state){
		Random random = new Random();
		int tablePosition;
		do {
			tablePosition = random.nextInt(8);
			System.out.println("Generated " +state +" "+ tablePosition);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (checkPositionAvailability(tablePosition, grid)==false);
			
		return tablePosition;
	}
	
	
}
	

