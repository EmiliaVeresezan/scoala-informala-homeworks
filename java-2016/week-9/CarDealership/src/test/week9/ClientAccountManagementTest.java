package week9;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientAccountManagementTest {
	
	private ClientAccountManagement clientManage;
	private Map<Long, ClientAccount> clientsAcc = new HashMap<Long, ClientAccount>(); 

	Client client;
	ClientAccount clientAccount;
	
	
	@Before
	public void init(){
		clientManage = new ClientAccountManagement();
		
		Client client1 = createNewClient(28509251255829L);
		ClientAccount clientAccount1 = new ClientAccount(client1);
		clientsAcc.put(client1.getId(), clientAccount1);		
		
		clientManage.setClientAccounts(clientsAcc);
		
		client = createNewClient(28207152265899L);
		clientAccount = new ClientAccount(client);

	}
	
	/**
	 * Test method for contains(Client client) method in class ClientAccountManagement.
	 * Checks whether the method returns true if the id of the client, passed as parameter, 
	 * is already registered as having a Client Account.
	 * Checks whether the method returns false if the client id cannot be found.
	 */
	@Test
	public void containsTest() {
		//client has the same id as client1 that already has a client account
		Client client = createNewClient(28509251255829L);
		assertTrue(clientManage.contains(client));
		
		client = createNewClient(1850925789245L);
		assertFalse(clientManage.contains(client));
	}
	
	/**
	 * Test method for method createClientAccount(). 
	 * Checks whether the method creates a new client account and adds it to the 
	 * client accounts inventory. 
	 */
	@Test
	public void createClientAccountTest(){
		
		//create a new client account
		clientManage.createClientAccount(client);
		
		//check that the client account has been added to the client accounts inventory
		assertEquals(2, clientManage.getClientAccounts().size());
		assertTrue(clientManage.contains(client));
	}
	
	/**
	 * Test method for method addClientAccount(ClientAccount clientAccount).
	 * Checks whether the client account passed as parameter is added to the client accounts inventory.
	 */
	@Test
	public void addClientAccountTest(){
		
		//add existing client account
		clientManage.addClientAccount(clientAccount);		
				
		assertEquals(2, clientManage.getClientAccounts().size());
		assertTrue(clientManage.getClientAccounts().containsKey(client.getId()));
	}
	
	@After
	public void destroy(){
		clientManage = null;
		clientsAcc = null;
		client = null;
		clientAccount = null;
	}

	
	/**
	 * Helper method that creates a client using the id field which is the only necessary information for this test class.
	 * @param id	client identification number. It is unique
	 * @return      a Client object that has only the id field set
	 */
	private Client createNewClient(long id){
		Client client = new Client(id);
		
		return client;
	}

}
