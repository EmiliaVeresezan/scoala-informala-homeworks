package week9;
/**
 * Holds bank account number and bank account sum. 
 * Can make check and decrease bank account sum.
 * @author emiliaveresezan
 *
 */
public class BankAccount {
	
	private String accountNr;
	private double accountSum;
	
	public BankAccount(double accountSum) {
		this.accountSum=accountSum;
	}
	
	/**
	 * Checks whether the sum in the bank account is less than the amount passed as parameter. 
	 * @param amount  the amount to be checked against the bank account sum  
	 * @return  true if the bank account sum is equal or larger than the amount passed as parameter
	 * 			false otherwise 
	 */
	public boolean checkSum(Double amount){
		if (this.accountSum < amount ) {
			return false;	
		}
		else {
			return true;
		}
	}
	
	/**
	 * Reduces the bank account sum with the amount passed as parameter.
	 * @param amount  sum to be subtracted from the bank account  
	 * @throws IllegalStateException if the amount in the bank account is smaller than 
	 * the amount passed as parameter 
	 */
	public void decreaseBankAccountSum (Double amount) throws IllegalStateException {
		if (checkSum(amount)){
			accountSum = accountSum - amount;
		}
		else {
			throw new IllegalStateException("Not enough money in the bank account.");
		}
	}
	
	/**
	 * Adds the amount passed as parameter to the bank account sum.
	 * @param amount  the value with which the bank account sum is increased.
	 */
	public void increaseBankAccountSum(float amount) {
		accountSum = accountSum + amount;
	}

	public String getAccountNr() {
		return accountNr;
	}

	public void setAccountNr(String accountNr) {
		this.accountNr = accountNr;
	}

	public double getAccountSum() {
		return accountSum;
	}

	public void setAccountSum(double accountSum) {
		this.accountSum = accountSum;
	}

	@Override
	public String toString() {
		return "BankAccount [accountNr=" + accountNr + ", accountSum=" + accountSum + "]";
	}

	
	
	
	

}
