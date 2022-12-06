package payment;

import java.util.ArrayList;
import java.util.List;

import com.diy.hardware.DoItYourselfStation;

public class PaymentController {
	private long balance;
	
	private CardPaymentManager cardManager;
	private CashPaymentManager cashManager;
	
	private List<PaymentListener> listeners;
	
	
	public PaymentController(DoItYourselfStation station) {
		cardManager = new CardPaymentManager(this, station);
		cashManager = new CashPaymentManager(this, station);
		listeners = new ArrayList<PaymentListener>();
		cashManager.register(new CashIssueListener() {

			@Override
			public void notifyNotEnoughCash() {
				for (PaymentListener listener : listeners) {
					listener.notifyNotEnoughCash();
				}
			}

			@Override
			public void notifyRequireAdditionalBanknotes(int neededBanknoteDenomination) {
				for (PaymentListener listener : listeners) {
					listener.notifyRequiresAdditionalBanknote(neededBanknoteDenomination);
				}
			}

			@Override
			public void notifyRequireAdditionalCoins(long neededCoinDenomination) {
				for (PaymentListener listener : listeners) {
					listener.notifyRequiresAdditionalCoins(neededCoinDenomination);
				}
			}
			
		});
	}
	
	public boolean hasRemainingBalance() {
		long funds = cashManager.availableFunds();
		funds += cardManager.availableFunds();
		return balance > funds;
	}
	
	public void completeTransaction() {
		if (hasRemainingBalance()) throw new IllegalStateException();
		long remaining = balance - cashManager.pay(balance);
		remaining -= cardManager.pay(remaining);
		if (remaining < 0) cashManager.returnFunds(-remaining);
		balance = 0;
	}
	
	public void addCost(long cost) {
		balance += cost;
	}
	
	public long getBalance() {
		return balance;
	}
	
	public long getRemainingBalance() {
		return balance - getAvailableFunds();
	}
	
	public void holdSuccessful() {
		for (PaymentListener listener : listeners) listener.cardPaymentSucceeded();
	}
	
	public long getAvailableFunds() {
		long res = 0;
		res += cashManager.availableFunds();
		res += cardManager.availableFunds();
		return res;
	}
	
	public void notifyCashPayment() {
		for (PaymentListener listener : listeners) listener.cashInserted();
	}
	
	public long availableCash() {
		return cashManager.availableFunds();
	}
	
	/**
	 * Register an PaymentListener to this
	 * @param listener PaymentListener to add
	 * @return boolean true if successful false otherwise
	 */
	public boolean register(PaymentListener listener) {
		if (listeners.contains(listener)) return false;
		listeners.add(listener);
		return true;
	}
	
	/**
	 * Unregister an PaymentListener to this
	 * @param listener PaymentListener to remove
	 * @return boolean true if successful false otherwise
	 */
	public boolean deregister(PaymentListener listener) {
		if (!listeners.contains(listener)) return false;
		listeners.remove(listener);
		return true;
	}
}
