package payment;

public abstract class PaymentManager {
	protected long funds;
	protected PaymentController controller;
	
	
	protected PaymentManager(PaymentController controller) {
		this.controller = controller;
	}
	
	public long currentBalance() {
		return controller.getBalance();
	}
	
	public long availableFunds() {
		return funds;
	}
	
	public void withdrawFunds(long amount) {
		funds -= amount;
	}
	
	/**
	 * Return funds
	 * @param amount long amount to return
	 * @return long amount actually returned
	 */
	public abstract long returnFunds(long amount);
	
	/**
	 * Make Payment
	 * @param amount long amount to pay
	 * @return long amount paid
	 */
	public abstract long pay(long amount);
}
