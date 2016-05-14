package simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameTable {

	private static final int N=3;
	private ArrayList<State> list = new ArrayList<State>();
	private State[][] matrix = new State[N][N]; 
	private int moveCount = 0;
	private boolean XCanMakeMove = true;
	private int row;
	private int col;
	private boolean gameOver = false; 
	
	

	public GameTable() {
		//Initialize the list with State.Blank
		for (int i=0; i < N*N; i++){
			list.add(State.Blank);
		}
		//Initialize 2D array with State.Blank
		for (int i=0; i<N;i++ ){
			for(int j=0; j<N; j++){
				matrix[i][j]=State.Blank;
			}
		}
	}
	
	//setX will not have the position as parameter because while we check if the 
	//position set as parameter is available the other thread is making modifications to the list  
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
		list.set(position, state);
		System.out.println("Added "+state+" on position "+position);
		addToMatrix(position, state);
		if (checkIfGameOver(state)){
			gameOver=true;
			announceWinner(state);
			return;
		}
		moveCount = moveCount+1;
		if (moveCount==N*N){
			announceDraw();
			return;
		}
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
		list.set(position, state);
		System.out.println("Added "+state+" on position "+position);
		addToMatrix(position, state);
		if (checkIfGameOver(state)){
			gameOver=true;
			announceWinner(state);
			return;
		}
		moveCount = moveCount+1;
		if (moveCount==N*N){
			announceDraw();
			return;
		}		
		notifyAll();			
	}
	
	void addToMatrix(int position, State state) {
		row = (int) position/N;
		col = position - row*N;
		matrix[row][col]=state;
	}
	
	public boolean checkIfGameOver(State state){
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
	 * Uses the row of the element that was just added.
	 * @param state
	 * @return
	 */
	public boolean checkRow(State state) {
		int count = 0; 
		boolean gameOver = true; 
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
	
	public boolean checkColumn(State state) {
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
	
	public boolean checkPrimaryDiag(State state) {
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

	public boolean checkSecondDiag(State state) {
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

	public void printList() {
		for(int i=0; i<list.size();i++)
			System.out.println(list.get(i));
	}

	private synchronized int generateAvailablePosition(State state){
		Random random = new Random();
		int tablePosition;
		do {
			tablePosition = random.nextInt(9);
			System.out.println("Generated " +state +" "+ tablePosition);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (checkPositionAvailability(tablePosition, getList())==false);
			
		return tablePosition;
	}
	
	public boolean checkPositionAvailability(int tablePosition, ArrayList<State> list) {
		System.out.println("Value for table position "+tablePosition+" is "+list.get(tablePosition));
		if (list.get(tablePosition).equals(State.Blank)){
			return true;
		}
		else {
			return false;
		}
	}
	
	public synchronized ArrayList<State> getList(){
		return new ArrayList<State>(list);
	}
	
	public boolean isGameOver() {
		return gameOver;
	}

	public State[][] getMatrix() {
		State[][] copyMatrix = new State[3][3];
		for (int i=0; i<3;i++ ){
			for(int j=0; j<3; j++){
				copyMatrix[i][j]=matrix[i][j];
			}
		}
		return copyMatrix;
	}
	
	public void announceWinner(State state){
		System.out.println("The winner is player "+state);
		printMatrix();
	}
	
	public void announceDraw(){
		System.out.println("The game is a draw ");
		printMatrix();

	}
	
	public void printMatrix(){
		State[][]m = matrix;
		System.out.println(Arrays.deepToString(m));
	}
	
	
}
	

