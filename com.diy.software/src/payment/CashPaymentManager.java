package payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

import com.diy.hardware.DoItYourselfStation;
import com.unitedbankingservices.DisabledException;
import com.unitedbankingservices.OutOfCashException;
import com.unitedbankingservices.TooMuchCashException;
import com.unitedbankingservices.banknote.BanknoteDispenserObserver;
import com.unitedbankingservices.banknote.BanknoteValidator;
import com.unitedbankingservices.banknote.BanknoteValidatorObserver;
import com.unitedbankingservices.coin.CoinValidator;
import com.unitedbankingservices.coin.CoinValidatorObserver;

public class CashPaymentManager extends PaymentManager implements BanknoteValidatorObserver, CoinValidatorObserver, BanknoteDispenserObserver {
	
	private DoItYourselfStation station;
	private boolean needMaintenance;
	private int validBanknoteCount, validCoinCount;
	private int minimumBanknoteCount = 5;
	private int minimumCoinCount = 10;
	
	private List<CashIssueListener> listeners = new ArrayList<CashIssueListener>();
	
	public CashPaymentManager (PaymentController controller, DoItYourselfStation station) {
		super(controller);
		this.station = station;
		this.station.banknoteValidator.attach(this);
		this.station.coinValidator.attach(this);
		needMaintenance = false;
		validBanknoteCount = 0;
		validCoinCount = 0;
	}
	
	@Override
	public void validBanknoteDetected(BanknoteValidator validator, Currency currency, long value) {
		validBanknoteCount++;
		funds += value;
		controller.notifyCashPayment();
	}
	
	@Override
	public void validCoinDetected(CoinValidator validator, BigDecimal value) {
		validCoinCount++;
		funds += value.longValue();
		controller.notifyCashPayment();
	}
	
	public int getValidBanknoteCount() {
		return validBanknoteCount;
	}
	
	public int getValidCoinCount() {
		return validCoinCount;
	}
	
	/*
	 * issue banknote as the change
	 * return changeIssued
	 */
	private long emitBanknotes(long banknoteToDispense) throws OutOfCashException, DisabledException {
		
		ArrayList<Integer> requiredBanknoteDenominations = new ArrayList<Integer>();

		if (banknoteToDispense == 0)
			return 0;

		// get banknote denomination available 
		int[] banknoteDenominations = station.banknoteDenominations;
		banknoteDenominations = sort(banknoteDenominations);
		long notesIssued = 0;
		
		int index = 0; // index of denomination
		while (banknoteToDispense != 0) {
			try {
			// not enough banknote to issue
			if (index == banknoteDenominations.length) {
				needMaintenance = true;
				break;
			}
			
			if (banknoteDenominations[index] <= banknoteToDispense) {
				if (station.banknoteDispensers.get(banknoteDenominations[index]).size() > 0) {
					station.banknoteDispensers.get(banknoteDenominations[index]).emit();
					banknoteToDispense -= banknoteDenominations[index];
					notesIssued += banknoteDenominations[index];
					if (station.banknoteDispensers.get(banknoteDenominations[index]).size() < minimumBanknoteCount) {
						if (requiredBanknoteDenominations.isEmpty() || requiredBanknoteDenominations.get(requiredBanknoteDenominations.size() - 1) != index) {
							requiredBanknoteDenominations.add(index);
						}
					}
				} else {
					System.out.println("Out of " + banknoteDenominations[index]);
					index++; // not enough banknotes for this denomination.
				}
			} else {
				index++; // the current denomination is too large
			}
			} catch(TooMuchCashException e) {}
		}
		if (!requiredBanknoteDenominations.isEmpty()) {
			for (int i : requiredBanknoteDenominations) {
				for (CashIssueListener listener : listeners) listener.notifyRequireAdditionalBanknotes(banknoteDenominations[i]);
			}
		}
		return notesIssued;		
	}
	
	/*
	 * issue banknote as the change
	 * return changeIssued 
	 */
	private long emitCoins(long coinToDispense) throws OutOfCashException, DisabledException {
		
		ArrayList<Integer> requiredCoinDenominations = new ArrayList<Integer>();

		if (coinToDispense == 0)
			return 0;
		
		// get coin denomination available 
		List<BigDecimal> coinDenominations = station.coinDenominations;
		Collections.reverse(coinDenominations);
		
		// turn coin value into cents
		long changeInCents = coinToDispense;
		long centsIssued = 0;
		
		int index = 0; // index of denomination
		while (changeInCents != 0) {
			try {
				// note enough coins to issue
				if (index == coinDenominations.size()) {
					needMaintenance = true;
					break;
				}
				
				long coinValue = coinDenominations.get(index).longValue();
				if (coinValue <= changeInCents) {
					System.out.println("Remaining: " + changeInCents + " Trying: " + coinValue);
					if (station.coinDispensers.get(coinDenominations.get(index)).size() > 0) {
						station.coinDispensers.get(coinDenominations.get(index)).emit();
						System.out.println("Emited: " + coinValue);
						changeInCents -= coinValue;
						centsIssued += coinValue;
						if (station.coinDispensers.get(coinDenominations.get(index)).size() < minimumCoinCount) {
							if (requiredCoinDenominations.isEmpty() || requiredCoinDenominations.get(requiredCoinDenominations.size() - 1) != index) {
								requiredCoinDenominations.add(index);
							}
						}
					} else {
						System.out.println("Out of " + coinValue);
						index++; // not enough coins for this denomination.
					}
				} else {
					index++; // the current denomination is too large
				}
			} catch (TooMuchCashException e) {}
		}
		if (!requiredCoinDenominations.isEmpty()) {
			for (int i : requiredCoinDenominations) {
				for (CashIssueListener listener : listeners) listener.notifyRequireAdditionalCoins(coinDenominations.get(i).longValue());
			}
		}
		return centsIssued;
	}
	
	/*
	 * change to primitive type array to non-primitive type array 
	 * sort
	 * change array back to primitive type
	 */
	private int[] sort(int[] denominations) {
		List<Integer> temp = new ArrayList<Integer>();
		for (int d : denominations) 
			temp.add(d);
		Collections.reverse(temp);
		for (int i = 0; i < denominations.length; i++) 
			denominations[i] = temp.get(i);		
		return denominations;
	}

	@Override
	public long returnFunds(long amount) {
		
		// Round balance
		station.coinDenominations.sort((a, b) -> a.compareTo(b));
		long changeToDispense;
		long smallest = station.coinDenominations.get(0).longValue();
		long rDown = amount - (amount % smallest);
		long rUp = amount - (amount % smallest) + smallest;
		long diffUp = amount - rUp;
		long diffDown = amount - rDown;
		if (rDown == 0) changeToDispense = rUp;
		else if (Math.abs(diffUp) < Math.abs(diffDown)) changeToDispense = rUp;
		else changeToDispense = rDown;
		
		long changeIssued = 0;
		try {
			long banknoteToDispense = changeToDispense;
			changeIssued = emitBanknotes(banknoteToDispense);
			long coinToDispense = changeToDispense - changeIssued;
			changeIssued += emitCoins(coinToDispense);
		} catch(DisabledException e) {
			e.printStackTrace();
		} catch (OutOfCashException e) {
			for (CashIssueListener listener : listeners) listener.notifyNotEnoughCash();
			System.out.println("Out of Cash");
		}
		funds -= changeIssued;
		System.out.println("After Return: " + funds);
		if (funds < station.coinDenominations.get(station.coinDenominations.size() - 1).longValue()) funds = 0;
		return changeIssued;
	}

	@Override
	public long pay(long amount) {
		long max = Math.min(amount, funds);
		funds -= max;
		System.out.println("Cash Paid:" + funds);
		return max;
	}
	
	public void register(CashIssueListener listener) {
		if (!listeners.contains(listener))
			listeners.add(listener);
	}
}
