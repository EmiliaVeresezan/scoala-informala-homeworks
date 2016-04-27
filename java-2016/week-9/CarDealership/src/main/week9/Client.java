package week9;
/**
 * Holds information about a client, whether the client has already had transactions with the dealership or not. 
 * Keeps the client is, name, address and bank account.
 * @author Emilia
 *
 */
public class Client {
	
	private String name;
	private String address;
	private BankAccount bankAccount;
	private String id;

	public Client(String id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
	
	/**
	 * Returns the bank account of the Client object which calls the method, if that client has a bank account.
	 * @return a reference to the BankAccount object if the bank account exists.
	 * @throws BankAccountNotFoundException - if the client does not have a bank account.
	 */
	public BankAccount getBankAccount() throws BankAccountNotFoundException{
		if (this.bankAccount==null){
			throw new BankAccountNotFoundException();
		}
		return this.bankAccount;
	}
	
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", bankAccount=" + bankAccount + "]";
	}
}
