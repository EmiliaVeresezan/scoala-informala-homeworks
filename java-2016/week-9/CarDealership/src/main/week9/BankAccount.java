package week9;
/**
 * Holds bank account number and bank account sum. 
 * Can make check and decrease bank account sum.
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
