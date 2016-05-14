package simulation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GameTableTest {
	
	@Test
	public void testGridNotEmpty(){
		GameTable gameTable = new GameTable();
		assertNotNull(gameTable);
	}

	@Test
	public void testIfGridIsPopulatedCorrectly() {
		//given
		GameTable gameTable = new GameTable();
		//when
		ArrayList<State> testGrid = gameTable.getGrid();
		//then
		for (int i=0; i<testGrid.size();i++){
			assertTrue(testGrid.get(i)==State.Blank);
		}
	}
	
	@Test
	public void checkPositionAvailablePositiveFlowTest(){
		//given
		GameTable gameTable = new GameTable();
		ArrayList<State> testGrid = new ArrayList<State>();
		//when
		testGrid.add(0, State.X);
		testGrid.add(1, State.X);
		testGrid.add(2, State.Blank);
		testGrid.add(3, State.O);
		//then
		assertTrue(gameTable.checkPositionAvailable(2, testGrid));
		
	}
	
	@Test
	public void checkPositionAvailableNegativeFlowTest(){
		//given
		GameTable gameTable = new GameTable();
		ArrayList<State> testGrid = new ArrayList<State>();
		//when
		testGrid.add(0, State.X);
		testGrid.add(1, State.X);
		testGrid.add(2, State.Blank);
		//then
		assertFalse(gameTable.checkPositionAvailable(1, testGrid));
		
	}

}
