package payment;

public interface PaymentListener {
	public abstract void cardPaymentSucceeded();

	public abstract void cashInserted();
}
