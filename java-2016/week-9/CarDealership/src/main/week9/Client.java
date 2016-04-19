package week9;
/**
 * Holds client information, such as id (which is unique), name, address and bank account.
 * @author Emilia
 *
 */
public class Client {
	
	private long id;
	private String name;
	private String address;
	private BankAccount bankAccount;
	
	public Client(long id){
		this.id = id;
	}
	
	public Client(String name){
		super();
		this.name = name;
	}
	
	/**
	 * Creates a client object that must have a name and a bank account.
	 * @param name 		   the client's name
	 * @param bankAccount  a bank account object
	 * @see 			   BankAccount
	 */
	public Client(String name, BankAccount bankAccount) {
		super();
		this.name = name;
		this.bankAccount = bankAccount;
	}
	
	/**
	 * Decreases the sum the client has in his/her bank account with the sum specified byu the argument.
	 * @param sum   the money amount to be subtracted out of the client's bank account  
	 */
	public void decreaseBankAccountSum(Double sum){
		this.getBankAccount().decreaseBankAccountSum(sum);
	}
	
	/**
	 *  Adds the amount passed as parameter to the bank account sum.
	 * @param amount
	 */
	public void increaseBankAccountSum(float amount) throws BankAccountNotFoundException{
		if (this.getBankAccount()==null){
			throw new BankAccountNotFoundException("The client does not have a bank account.");
		}
		this.getBankAccount().increaseBankAccountSum(amount);
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", bankAccount=" + bankAccount + "]";
	}

	
	
	
	
	
}
