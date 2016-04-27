package week9;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import week5.Car;
/**
 * Test class for class Client. 
 * @author Emilia
 *
 */
public class ClientTest {
	
	Client client;
	BankAccount bankAccount;
	
	@Before
	public void init() {
			
	}
	
	@Test
	public void testGetBankAccount() throws BankAccountNotFoundException{
		//given
		TestBankAccount bankAccount = new TestBankAccount("RO836934174", 2000.0f); 
		Client client = new Client("222222", "Jane", "Fericirii 32" );
		client.setBankAccount(bankAccount);
		//when
		BankAccount retrievedAccount = client.getBankAccount();
		//then
		assertNotNull(retrievedAccount);
	}
	
	@Test (expected = BankAccountNotFoundException.class)
	public void testGetBankAccountWhenNoBankAccountExists() throws BankAccountNotFoundException{
		//given
		Client client = new Client("222222", "Jane", "Fericirii 32" );
		//when
		BankAccount retrievedAccount = client.getBankAccount();
		//then
		fail("Exception should have been thrown.");
	}
	
	@After
	public void destroy(){
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
