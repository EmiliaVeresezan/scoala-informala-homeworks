package week9;
/**
 * Represents a bank account and holds the iban of the account and the account balance. 
 * @author emiliaveresezan
 *
 */
public class BankAccount {
	
	private String iban;
	protected float balance;
	
	public BankAccount(String iban, float balance) {
		super();
		this.iban = iban;
		this.balance = balance;
	}
	/**
	 * Transfers the amount passed as parameter from the caller bank account to the bank account 
	 * passed as parameter.
	 * @param amount	the amount of money to be transfered
	 * @param destinationAccount	the bank account to receive the transfer     
	 * @throws InsufficientFundsException  -  when the bank account balance is smaller then the 
	 * amount to be transfered.
	 */
	public void transferFunds(Float amount, BankAccount destinationAccount) throws InsufficientFundsException {
		if (balance < amount) {
			throw new InsufficientFundsException();
		}
		balance -= amount;
		destinationAccount.balance += amount;
	}
	
	@Override
	public String toString() {
		return "BankAccount [iban=" + iban + ", balance=" + balance + "]";
	}
}
