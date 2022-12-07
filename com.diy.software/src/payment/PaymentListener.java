package payment;

public interface PaymentListener {
	public abstract void cardPaymentSucceeded();
	public abstract void cashInserted();
	public abstract void notifyNotEnoughCash();
	public abstract void notifyRequiresAdditionalBanknote(int req);
	public abstract void notifyRequiresAdditionalCoins(long neededCoinDenomination);
}
