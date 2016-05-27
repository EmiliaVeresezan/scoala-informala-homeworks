package simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
/**
 * Includes all the logic of the XO game.
 * It constructs the grid that keeps the information regarding the moves.
 * The game involves 2 players that wait for each others turn in making a move.
 * The move can only be made on an available square so the game table 
 * generates an available position foe each player by checking the grid. 
 * After each move checks are performed to establish of the game is over
 * either because there is a winner or because moves have been made in all 
 * the squares. The game announces a winner or announces a draw and prints the grid.
 * @author emiliaveresezan
 *
 */
public class GameTable {

	private static final int N=3;
	private ArrayList<State> list = new ArrayList<State>();
	private State[][] matrix = new State[N][N]; 
	private int moveCount = 0;
	private boolean XCanMakeMove = true;
	private int row;
	private int col;
	private boolean gameOver = false; 
	
	
	/**
	 * Constructs a list and a 2D grid to represent the grid and initializes them with State.Blank.
	 * The list stores the positions and the 2D grid is used to perform end game checks.
	 */
	public GameTable() {
		for (int i=0; i < N*N; i++){
			list.add(State.Blank);
		}
		for (int i=0; i<N;i++ ){
			for(int j=0; j<N; j++){
				matrix[i][j]=State.Blank;
			}
		}
	}
	
	/**
	 * Represents a move made by player1 with all the steps involved:
	 * Waits to be notified when it's player's 1 turn to make a move, 
	 * generates an available position based on the current state of the grid, 
	 * makes the new move on the grid and checks if it's a winning move or whether the grid is full.
	 * After making a move notifies the other player that they can make a move.
	 * @param state  the type of mark that will be set on the board and that is 
	 * 				  associated with a player: X for player 1 
	 */
	public synchronized void setX(State state) {
		while (!XCanMakeMove){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}			
		if (gameOver==false){
			//It's O's turn to make the move
			XCanMakeMove = false;
			int position = generateAvailablePosition(state);
			list.set(position, state);
			System.out.println("Added "+state+" on position "+position);
			addToMatrix(position, state);
			moveCount = moveCount+1;
			if (checkForWin(state)){
				gameOver=true;
				announceWinner(state);
			}
			else {
				if (moveCount==N*N){
					gameOver=true;
					announceDraw();
				}
			}
			notifyAll();			
		}
	}

	/**
	 * Represents a move made by player 2 with all the steps involved:
	 * Waits to be notified when it's player's 2 turn to make a move, 
	 * generates an available position based on the current state of the grid, 
	 * makes the new move on the grid and checks if it's a winning move or whether the grid is full.
	 * After making a move notifies the other player that they can make a move.
	 * @param state  the type of mark that will be set on the board and that is 
	 * 				  associated with a player: O for player 2 
	 */
	public synchronized void setO(State state){
		while (XCanMakeMove){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		if (gameOver==false){
			//It's X's turn to make the move
			XCanMakeMove = true;
			int position = generateAvailablePosition(state);
			list.set(position, state);
			System.out.println("Added "+state+" on position "+position);
			addToMatrix(position, state);
			moveCount = moveCount+1;
			if (checkForWin(state)){
				gameOver=true;
				announceWinner(state);
			}
			else {
				if (moveCount==N*N){
					gameOver=true;
					announceDraw();
				}
			}
			notifyAll();			
		}
	}
	
	/**
	 * Takes the position of the move (randomly generated from 0 to 8) and 
	 * converts it to a 2D grid position by calculating the row and column 
	 * of that position. The row and col variables are instance variables and 
	 * represent the position state of the current move. 
	 * For example position 8 in the list will be converted to position [2][2] of the grid
	 * For a 3x3 grid the positions are in the interval [0][0]-[2][2]
	 * Adds the X or O set in the list position to the 2D grid converted position. 
	 * @param position  the position of the move in the list, between 0 and 8
	 * @param state	    the player's mark, X or O
	 */
	void addToMatrix(int position, State state) {
		row = (int) position/N;
		col = position - row*N;
		matrix[row][col]=state;
	}
	
	/**
	 * Checks whether a player has 3 marks on a row, or a column, or a primary diag
	 * or a secondary diagonal.
	 * @param state  the mark of the player: X or O
	 * @return  true if either of the conditions mentioned above is true;
	 * 		    false otherwise
	 */
	boolean checkForWin(State state){
		if (checkRow(state)){
			return true;
		}
		if (checkColumn(state)){
			return true;
		}
		if ((row==col) && (checkPrimaryDiag(state))){
				return true;
		}
		if ((row+col==N-1)&&(checkSecondDiag(state))){
			return true;
		}
		return false;
	}

	/**
	 * Uses the row instance variable that is the row for the last added position 
	 * and checks if the player has 3 marks in a row on that row. 
	 * @param state  the mark of the player, X or O
	 * @return true if there are 3 marks in a row
	 * 			false otherwise
	 */
	boolean checkRow(State state) {
		int count = 0; 
		gameOver = true; 
		while (count<N-1){
			if (matrix[row][count]==matrix[row][count+1]){
				count++;
			}
			else {
				gameOver=false;
				break;
			}
		}
		return gameOver;
	}
	
	/**
	 * Uses the col instance variable that is the column for the last added position 
	 * and checks if the player has 3 marks in a row on that column. 
	 * @param state  the mark of the player, X or O
	 * @return true if there are 3 marks in a row on that column
	 * 			false otherwise
	 */
	boolean checkColumn(State state) {
		int count = 0; 
		boolean gameOver = true; 
		while (count<2){
			if (matrix[count][col]==matrix[count+1][col]){
				count++;
			}
			else {
				gameOver=false;
				break;
			}
		}
		return gameOver;
	}
	/**
	 * Takes the row and column of the last added position and checks whether 
	 * the same mark is set on the primary diagonal of the grid.
	 * @param state the mark of the player X or O
	 * @return  true if the same mark is set on the primary diagonal
	 * 			false otherwise.
	 */
	boolean checkPrimaryDiag(State state) {
		int count = 0; 
		boolean gameOver = true; 
		while (count<N-1){
			if (matrix[count][count]==matrix[count+1][count+1]){
				count++;
			}
			else {
				gameOver=false;
				break;
			}
		}
		return gameOver;
	}

	/**
	 * Takes the row and column of the last added position and checks whether 
	 * the same mark is set on the secondary diagonal of the grid.
	 * @param state - the mark of the player X or O
	 * @return  true if the same mark is set on the secondary diagonal
	 * 			false otherwise.
	 */
	boolean checkSecondDiag(State state) {
		int count = 0; 
		boolean gameOver = true; 
		while (count<N-1){
			if (matrix[count][N-1-count]==matrix[count+1][N-1-(count+1)]){
				count++;
			}
			else {
				gameOver=false;
				break;
			}
		}
		return gameOver;
	}
	
	/**
	 * Randomly generates a number between 0 and 8(inclusive), checks if that position 
	 * in the list is available (has State.Blank set). If the position is available it sets 
	 * the mark of the player to the position. It generates numbers until it finds an available 
	 * position and returns that position
	 * @param state  the mark of the player, X or O
	 * @return the available position in the grid, between 0 and 8(inclusive)
	 */
	private int generateAvailablePosition(State state){
		Random random = new Random();
		int tablePosition;
		do {
			tablePosition = random.nextInt(9);
			System.out.println("Generated for " +state +": "+ tablePosition);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (checkPositionAvailability(tablePosition, getList())==false);
			
		return tablePosition;
	}
	
	/**
	 * Takes a position, an int between 0 and 8 (inclusive), and checks whether 
	 * that position in the list is available, meaning that no player has set their 
	 * mark to that position 
	 * @param tablePosition  
	 * @param list  the list of position that contains their state, X,O or Blank.
	 * @return  true if the position is available
	 * 			false otherwise
	 */
	boolean checkPositionAvailability(int tablePosition, ArrayList<State> list) {
		System.out.println("Value for position "+tablePosition+" is "+list.get(tablePosition));
		if (list.get(tablePosition).equals(State.Blank)){
			return true;
		}
		else {
			return false;
		}
	}
	
	public ArrayList<State> getList(){
		return new ArrayList<State>(list);
	}
	
	//Synchronized because I don't want a thread to call isGameOver() 
	//while another thread is setting the value of gameOver in setX or setO
	synchronized boolean isGameOver() {
		return gameOver;
	}
	
	/**
	 * Makes a copy of the 2D grid.
	 * @return a copy of the 2D grid
	 */
	public State[][] getMatrix() {
		State[][] copyMatrix = new State[3][3];
		for (int i=0; i<3;i++ ){
			for(int j=0; j<3; j++){
				copyMatrix[i][j]=matrix[i][j];
			}
		}
		return copyMatrix;
	}
	
	private void announceWinner(State state){
		System.out.println("The winner is player "+state);
		printMatrix();
	}
	
	private void announceDraw(){
		System.out.println("The game is a draw ");
		printMatrix();
	}
	
	public void printMatrix(){
		State[][]m = matrix;
		System.out.println(Arrays.deepToString(m));
	}
}
	

