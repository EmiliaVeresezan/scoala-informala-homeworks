package week9;
/**
 * Holds client information, such as id (which is unique), name, address and bank account.
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
