package simulation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GameTableTest {
	
	@Test
	public void testGridNotEmpty(){
		GameTable gameTable = new GameTable();
		ArrayList<State> testGrid = gameTable.getList();
		assertNotNull(testGrid);
	}
	
	@Test
	public void testGridSizeBeforeMoves(){
		GameTable gameTable = new GameTable();
		ArrayList<State> testGrid = gameTable.getList();
		assertEquals(9,testGrid.size());
	}
	
	@Test
	public void testGridSizeAfterMoves(){
		//given
		GameTable gameTable = new GameTable();
		//when
		ArrayList<State> testGrid = gameTable.getList();
		testGrid.set(1, State.X);
		testGrid.set(5,State.O);
		//then
		assertEquals(9,testGrid.size());
	}

	@Test
	public void testIfGridIsPopulatedCorrectly() {
		//given
		GameTable gameTable = new GameTable();
		//when
		ArrayList<State> testGrid = gameTable.getList();
		//then
		for (int i=0; i<testGrid.size();i++){
			assertTrue(testGrid.get(i)==State.Blank);
		}
	}
	
	@Test
	public void checkPositionAvailabilityTest(){
		//given
		GameTable gameTable = new GameTable();
		ArrayList<State> testGrid = gameTable.getList();
		//when
		testGrid.set(0, State.X);
		testGrid.set(1, State.X);
		testGrid.set(3, State.O);
		//then
		assertFalse(gameTable.checkPositionAvailability(0, testGrid));
		assertFalse(gameTable.checkPositionAvailability(1, testGrid));
		assertTrue(gameTable.checkPositionAvailability(2, testGrid));
		assertFalse(gameTable.checkPositionAvailability(3, testGrid));
		assertTrue(gameTable.checkPositionAvailability(4, testGrid));
	}
	
	@Test
	public void addToMatrixTest(){
		//given 
		GameTable gameTable = new GameTable();
		//when
		gameTable.addToMatrix(0,State.X);
		gameTable.addToMatrix(3,State.O);
		gameTable.addToMatrix(4,State.O);
		gameTable.addToMatrix(5,State.O);
		gameTable.addToMatrix(8,State.X);
		State[][] testMatrix=gameTable.getMatrix();
		//then
		assertEquals(State.X, testMatrix[0][0]);
		assertEquals(State.O, testMatrix[1][0]);
		assertEquals(State.O, testMatrix[1][1]);
		assertEquals(State.O, testMatrix[1][2]);
		assertEquals(State.X, testMatrix[2][2]);
		assertEquals(State.Blank, testMatrix[2][0]);
	}
	
	@Test
	public void checkRowPositiveTest(){
		//given 
		GameTable gameTable = new GameTable();
		//when
		gameTable.addToMatrix(3,State.O);
		gameTable.addToMatrix(4,State.O);
		gameTable.addToMatrix(5,State.O);
		//then
		assertTrue(gameTable.checkRow(State.O));
		//checkRow method uses the row of the last added element
	}
	
	@Test
	public void checkRowNegativeTest(){
		//given 
		GameTable gameTable = new GameTable();
		//when
		gameTable.addToMatrix(3,State.O);
		gameTable.addToMatrix(4,State.O);
		gameTable.addToMatrix(5,State.X);
		//then
		assertFalse(gameTable.checkRow(State.O));
		//checkRow method uses the row of the last added element
	}
	
	@Test
	public void checkColumnPositiveTest(){
		//given 
		GameTable gameTable = new GameTable();
		//when
		gameTable.addToMatrix(2,State.O);
		gameTable.addToMatrix(5,State.O);
		gameTable.addToMatrix(8,State.O);
		//then
		assertTrue(gameTable.checkColumn(State.O));
		//checkColumn method uses the column of the last added element
	}
	
	@Test
	public void checkColumnNegativeTest(){
		//given 
		GameTable gameTable = new GameTable();
		//when
		gameTable.addToMatrix(2,State.O);
		gameTable.addToMatrix(5,State.Blank);
		gameTable.addToMatrix(8,State.O);
		//then
		assertFalse(gameTable.checkColumn(State.O));
		//checkColumn method uses the column of the last added element
	}
	
	@Test
	public void checkPrimaryDiagPositiveTest(){
		//given 
		GameTable gameTable = new GameTable();
		//when
		gameTable.addToMatrix(0,State.X);
		gameTable.addToMatrix(4,State.X);
		gameTable.addToMatrix(8,State.X);
		//then
		assertTrue(gameTable.checkPrimaryDiag(State.X));
		//checkPrimaryDiag method uses the row and column of the last added element
	}
	
	@Test
	public void checkPrimaryDiagNegativeTest(){
		//given 
		GameTable gameTable = new GameTable();
		//when
		gameTable.addToMatrix(0,State.X);
		gameTable.addToMatrix(4,State.X);
		gameTable.addToMatrix(8,State.Blank);
		//then
		assertFalse(gameTable.checkPrimaryDiag(State.X));
		//checkPrimaryDiag method uses the row and column of the last added element
	}
	
	@Test
	public void checkSecondDiagPositiveTest(){
		//given 
		GameTable gameTable = new GameTable();
		//when
		gameTable.addToMatrix(2,State.X);
		gameTable.addToMatrix(4,State.X);
		gameTable.addToMatrix(6,State.X);
		//then
		assertTrue(gameTable.checkSecondDiag(State.X));
		//checkSecondDiag method uses the row and column of the last added element
	}
	
	@Test
	public void checkSecondDiagNegativeTest(){
		//given 
		GameTable gameTable = new GameTable();
		//when
		gameTable.addToMatrix(2,State.Blank);
		gameTable.addToMatrix(4,State.X);
		gameTable.addToMatrix(6,State.O);
		//then
		assertFalse(gameTable.checkSecondDiag(State.O));
		//checkSecondDiag method uses the row and column of the last added element
	}
	
	@Test
	public void checkIfGameOverTest(){
		//given 
		GameTable gameTable = new GameTable();
		//when
		gameTable.addToMatrix(0,State.O);
		gameTable.addToMatrix(1,State.O);
		gameTable.addToMatrix(3,State.O);
		gameTable.addToMatrix(4,State.X);
		gameTable.addToMatrix(5,State.X);
		gameTable.addToMatrix(6,State.X);
		gameTable.addToMatrix(7,State.Blank);
		gameTable.addToMatrix(8,State.X);
		gameTable.addToMatrix(2,State.O);

		//then
		assertTrue(gameTable.checkForWin(State.O));
	}
	
}
