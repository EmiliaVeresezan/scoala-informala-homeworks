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
	
	@Before
	public void init(){
		
	}
	
	@Test
	public void testTransferFundsSuccesfulFlow() throws InsufficientFundsException{
		//given
		TestBankAccount bankAccount = new TestBankAccount("shdasad", 2000.0f);
		TestBankAccount destinationBankAccount = new TestBankAccount("asdadwqen", 12000);
		//when
		bankAccount.transferFunds(1000.0f, destinationBankAccount);
		//then
		assertEquals(1000.0f, bankAccount.getBalance(),0);
		assertEquals(13000.0f, destinationBankAccount.getBalance(),0);
	}
	
	@Test (expected = InsufficientFundsException.class)
	public void whenNotEnoughMoneyInBankAccountTransferFails() throws InsufficientFundsException{
		//given
		TestBankAccount bankAccount = new TestBankAccount("shdasad", 2000.0f);
		TestBankAccount destinationBankAccount = new TestBankAccount("asdadwqen", 12000);
		//when
		bankAccount.transferFunds(3000.0f, destinationBankAccount);
		//then
		fail("Exception should have been thrown");		
	}
	
	private static class TestBankAccount extends BankAccount {

		public TestBankAccount(String iban, float balance) {
			super(iban, balance);
		}

		private float getBalance() {
			return balance;
		}

	}
	
	
}
