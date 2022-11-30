package payment;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	private List<CashIssueListener> listeners;
	
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
	}
	
	@Override
	public void validCoinDetected(CoinValidator validator, long value) {
		validCoinCount++;
		funds += value;
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
	private long emitBanknotes(long banknoteToDispense, long changeIssued) throws OutOfCashException, DisabledException {

		if (banknoteToDispense == 0)
			return changeIssued;

		// get banknote denomination available 
		int[] banknoteDenominations = station.banknoteDenominations;
		banknoteDenominations = sort(banknoteDenominations);
		
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
					changeIssued += banknoteDenominations[index];
				} else {
					index++; // not enough banknotes for this denomination.
				}
			} else {
				index++; // the current denomination is too large
			}
			} catch(TooMuchCashException e) {}
		}
		return changeIssued;		
	}
	
	/*
	 * issue banknote as the change
	 * return changeIssued 
	 */
	private long emitCoins(long coinToDispense, long changeIssued) throws OutOfCashException, DisabledException {

		if (coinToDispense == 0)
			return changeIssued;
		
		// get coin denomination available 
		List<Long> coinDenominations = station.coinDenominations;
		Collections.reverse(coinDenominations);
		
		// turn coin value into cents
		long changeInCents = coinToDispense;
		long centsIssued = changeIssued;
		
		int index = 0; // index of denomination
		while (changeInCents != 0) {
			try {
				// note enough coins to issue
				if (index == coinDenominations.size()) {
					needMaintenance = true;
					break;
				}
				
				long coinValue = coinDenominations.get(index);
				if (coinValue <= changeInCents) {
					if (station.coinDispensers.get(coinDenominations.get(index)).size() > 0) {
						station.coinDispensers.get(coinDenominations.get(index)).emit();
						
						changeInCents -= coinValue;
						centsIssued += coinValue;
					} else {
						index++; // not enough coins for this denomination.
					}
				} else {
					index++; // the current denomination is too large
				}
			} catch (TooMuchCashException e) {}
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
		station.coinDenominations.sort((a, b) -> (int) (a - b));
		long changeToDispense;
		long smallest = station.coinDenominations.get(0);
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
			changeIssued = emitBanknotes(banknoteToDispense, changeIssued);
			long coinToDispense = changeToDispense - changeIssued;
			changeIssued = emitCoins(coinToDispense, changeIssued);
		} catch(DisabledException e) {
			e.printStackTrace();
		} catch (OutOfCashException e) {
			for (CashIssueListener listener : listeners) listener.notifyNotEnoughCash();
		}
		return changeIssued;
	}

	@Override
	public long pay(long amount) {
		long max = Math.min(amount, funds);
		funds -= max;
		return max;
	}
}
