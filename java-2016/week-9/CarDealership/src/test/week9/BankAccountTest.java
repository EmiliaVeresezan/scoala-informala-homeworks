/**
 * 
 */
package week9;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for class BankAccount.
 * @author emilia
 *
 */
public class BankAccountTest {

	BankAccount bankAccount;
	Client client;
	ClientAccount clientAccount;
	
	@Before
	public void init(){
		bankAccount = createNewBankAccount(12000.0);
		client = new Client("Ada Gherghe", bankAccount);
		//clientAccount = new ClientAccount(client);
		
	}
	
	/**
	 * Tests the method checkSum(Double amount) from class BankAccount.
	 */
	@Test
	public void checkSumTest() {
		assertTrue(bankAccount.checkSum(5000.0));
		assertTrue(bankAccount.checkSum(12000.0));
		assertFalse(bankAccount.checkSum(13000.0));
	}
	
	/**
	 * Tests method decreaseBankAccountSum(Double amount) from class BankAccount
	 * in 3 test cases: when the amount passed as parameter is smaller than the amount in the
	 * bank account; 
	 * when the two are equal; 
	 * when the amount passed as paramater is larger then the amount in the bank accout
	 * In this case the method decreaseBankAccountSum(amount) throws an exception and the test method catches it.
	 */
	@Test
	public void decreaseBankAccountSumTest(){
		bankAccount = createNewBankAccount(12000.0);
		bankAccount.decreaseBankAccountSum(5000.0);
		//Check that the bank account sum was updated
		assertEquals(7000.0,bankAccount.getAccountSum() , 0.001);
		
		bankAccount.setAccountSum(12000.0);
		bankAccount.decreaseBankAccountSum(12000.0);
		//check that the bank account was updated
		assertEquals(0.0,bankAccount.getAccountSum() , 0.001);
		
		bankAccount.setAccountSum(12000.0);
		try{
			bankAccount.decreaseBankAccountSum(15000.0);
		}catch (IllegalStateException ex) {
			//check that an exception was thrown and the bank account was not updated
			assertEquals(12000.0, bankAccount.getAccountSum(), 0.001 );
		}
		
	}
	
	/**
	 * Helper method for constructing a BankAccount object
	 * @param sum  bank account initial sum
	 * @return     a BankAccount object
	 */
	private BankAccount createNewBankAccount(double sum){
		BankAccount bankAccount = new BankAccount(sum);
		return bankAccount;
	}

}
