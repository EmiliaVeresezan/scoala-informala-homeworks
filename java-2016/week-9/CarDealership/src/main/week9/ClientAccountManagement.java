package week9;

import java.util.HashMap;
import java.util.Map;
/**
 * Holds client accounts information. Identifies client accounts with the unique id of each client.
 * @author emiliaveresezan
 *
 */
public class ClientAccountManagement {
	
	private Map<Long, ClientAccount> clientAccounts = new HashMap<Long, ClientAccount>(); 

	/**
	 * Checks whether the client passed as parameter already has a Client Account.
	 * @param client  a reference to a Client object
	 * @return		  true if the client already has a ClientAccount
	 * 				  false, otherwise
	 */
	public boolean contains(Client client){
		if (clientAccounts.containsKey(client.getId())){
			return true;
		}	
		else {
			return false;
		}
	}
		
	/**
	 * Creates a ClientAccount and adds it to the client accounts inventory.
	 * @param client  a reference to the client object to be added
	 */
	public void createClientAccount(Client client){
		ClientAccount clientAccount = new ClientAccount(client);
		clientAccounts.put(client.getId(), clientAccount);
	}
	
	/**
	 * Adds an existing client account, that is passed as parameter, to the client accounts inventory.
	 * @param clientAccount  a reference to the ClientAccount object to be added.
	 */
	public void addClientAccount(ClientAccount clientAccount){
		clientAccounts.put(clientAccount.getClient().getId(), clientAccount);
	}
	
	/**
	 * Retrieves a client account based on the client reference passed as paramater.
	 * @param client  the client for which we want to retrieve the client account
	 * @return a reference to the ClientAccount object, found in the client accounts
	 *  inventory 
	 */
	public ClientAccount getClientAccount(Client client){
		if(contains(client)){
		ClientAccount clientAccount = clientAccounts.get(client.getId());
		return clientAccount;
		}
		return null;
	}

	public Map<Long, ClientAccount> getClientAccounts() {
		return clientAccounts;
	}

	public void setClientAccounts(Map<Long, ClientAccount> clientAccounts) {
		this.clientAccounts = clientAccounts;
	}
	
	
		

	
}
