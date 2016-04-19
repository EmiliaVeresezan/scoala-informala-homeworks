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
		bankAccount = new BankAccount(50000);
		client = new Client("Elena Pop", bankAccount);
		
	}
	
	/**
	*Tests that the method decreaseBankAccountSum(Double sum) works correctly.
	*/
	@Test
	public void decreaseBankAccountSumTest(){
		client.decreaseBankAccountSum(10000.0);
		assertEquals(40000, bankAccount.getAccountSum(),0.001);
	}
	
	/**
	 * Tests that the method increaseBankAccountSum(float amount) updates the bank account
	 * if a bank account exists for the caller.
	 * Throws an exception if the client does not have a bank account.
	 */
	@Test
	public void increaseBankAccountSumTest(){
		try{
		client.increaseBankAccountSum(1000.0f);
		} catch(BankAccountNotFoundException ex){
			assertEquals(51000, bankAccount.getAccountSum(),0.001);
		}
		
	}
		
	@After
	public void destroy(){
		client = null;
		bankAccount = null;
	}
}
