import java.util.Currency;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.diy.hardware.DoItYourselfStation;
import com.unitedbankingservices.DisabledException;
import com.unitedbankingservices.OutOfCashException;
import com.unitedbankingservices.Sink;
import com.unitedbankingservices.TooMuchCashException;
import com.unitedbankingservices.banknote.Banknote;
import com.unitedbankingservices.coin.Coin;

import ca.powerutility.PowerGrid;
import util.AttendantStation;
import util.AttendantUI;
import util.CashPayment;
import util.CustomerUI;

public class CashPaymentTest {
	
	private CustomerUI customer;
	private AttendantUI attendant;
	private DoItYourselfStation checkoutStation;
	private AttendantStation attendantStation;
	private CashPayment cashPayment;
	
	@Before
	public void setup() throws TooMuchCashException {
		
		// configure denominations
		int[] banknoteDenominations = {5000,2000,1000,500};
		long[] coinDenominations = {200, 100, 25, 10, 5};
		DoItYourselfStation.configureBanknoteDenominations(banknoteDenominations);
		DoItYourselfStation.configureCoinDenominations(coinDenominations);
		
		// setup checkout station
		checkoutStation = new DoItYourselfStation();
		PowerGrid.engageUninterruptiblePowerSource();
		PowerGrid.instance().forcePowerRestore();
		checkoutStation.plugIn();
		checkoutStation.turnOn();
		
		// setup banknote and coin dispensers
		for (int denomination: banknoteDenominations) {
			checkoutStation.banknoteDispensers.get(denomination).load(new Banknote(Currency.getInstance(Locale.CANADA), denomination));
			checkoutStation.banknoteDispensers.get(denomination).sink = new Sink<Banknote>() {
				@Override
				public void receive(Banknote cash) throws TooMuchCashException, DisabledException {
				}
				@Override
				public boolean hasSpace() {
					return true;
				}
			};
		}
		
		for (long denomination: coinDenominations) {
			checkoutStation.coinDispensers.get(denomination).load(new Coin(Currency.getInstance(Locale.CANADA), denomination));
		}
		
		// initialize software
		customer = new CustomerUI(checkoutStation);
		attendant = new AttendantUI(attendantStation, 1);
		cashPayment = new CashPayment(customer, attendant, checkoutStation);
		customer.setCashPaymentController(cashPayment);
		customer.startScanning();
		customer.setBalance(100);
		customer.payWithCash();
	}
	
	@After
	public void teardown() {
		PowerGrid.reconnectToMains();
	}
	
	
	@Test
	public void testBanknotePayment() throws DisabledException, TooMuchCashException {
		//banknoteSlotToValidator();
		
		Banknote fiveDollar = new Banknote(Currency.getInstance(Locale.CANADA), 500);
		Banknote tenDollar = new Banknote(Currency.getInstance(Locale.CANADA), 1000);
		
		customer.setBalance(100);
		
		while(cashPayment.getValidBanknoteCount() != 1) {
			checkoutStation.banknoteInput.receive(fiveDollar);
			if (cashPayment.getValidBanknoteCount() == 1)
				break;
				checkoutStation.banknoteInput.removeDanglingBanknote();
		}
		assertEquals(-400, customer.getBalance());
		customer.setBalance(100);
		customer.payWithCash();
		
		while(cashPayment.getValidBanknoteCount() != 2) {
			checkoutStation.banknoteInput.receive(tenDollar);
			if (cashPayment.getValidBanknoteCount() != 2)
				checkoutStation.banknoteInput.removeDanglingBanknote();
		}
		assertEquals(-900, customer.getBalance());
		
	}
	
	@Test
	public void testCoinPayment() throws DisabledException, TooMuchCashException {
		
		//coinSlotToValidator();
		
		Coin quarter = new Coin(Currency.getInstance(Locale.CANADA), 25);
		
		customer.setBalance(100);
		
		while(true) {
			checkoutStation.coinSlot.receive(quarter);
			if (cashPayment.getValidCoinCount() == 1)
				break;
		}
		assertEquals(75, customer.getBalance());
		
		while(true) {
			checkoutStation.coinSlot.receive(quarter);
			if (cashPayment.getValidCoinCount() == 2)
				break;
		}
		assertEquals(50, customer.getBalance());
		
	}
	
	@Test
	public void testBanknoteAndCoinPayment() throws DisabledException, TooMuchCashException {
		
		//banknoteSlotToValidator();
		//coinSlotToValidator();
		
		Banknote fiveDollar = new Banknote(Currency.getInstance(Locale.CANADA), 500);		
		Coin quarter = new Coin(Currency.getInstance(Locale.CANADA), 25);
		
		customer.setBalance(100);
		
		while(true) {
			checkoutStation.banknoteInput.receive(fiveDollar);
			if (cashPayment.getValidBanknoteCount() == 1) 
				break;
			checkoutStation.banknoteInput.removeDanglingBanknote();
		}
		assertEquals(-400, customer.getBalance());
		customer.setBalance(100);
		customer.payWithCash();
		
		while(true) {
			checkoutStation.coinSlot.receive(quarter);
			if (cashPayment.getValidCoinCount() == 1)
				break;
		}
		assertEquals(75, customer.getBalance());
	}
	
	@Test
	public void testReturnBanknoteChange() throws TooMuchCashException, OutOfCashException, DisabledException {
		customer.setBalance(-5);
		cashPayment.returnChange();
		assertEquals(0, customer.getBalance());
	}
	
	@Test
	public void testReturnCoinChange() throws TooMuchCashException, OutOfCashException, DisabledException {
		customer.setBalance(-25);
		cashPayment.returnChange();
		assertEquals(0, customer.getBalance());
	}

	@Test
	public void testBanknoteAndCoinChange() throws TooMuchCashException, OutOfCashException, DisabledException {
		customer.setBalance(-600);
		cashPayment.returnChange();
		assertEquals(0, customer.getBalance());
	}

	@Test (expected = OutOfCashException.class)
	public void testInsufficientBanknoteChange() throws TooMuchCashException, OutOfCashException, DisabledException {
		customer.setBalance(-4000);
		cashPayment.returnChange();
		assertEquals(0, customer.getBalance());
	}

	@Test (expected = OutOfCashException.class)
	public void testInsufficientCoinChange() throws TooMuchCashException, OutOfCashException, DisabledException {
		customer.setBalance(-2);
		cashPayment.returnChange();
		assertEquals(-2, customer.getBalance());
	}
	
	@Test (expected = OutOfCashException.class)
	public void testInsufficientBanknoteAndCoinChange() throws TooMuchCashException, OutOfCashException, DisabledException {
		customer.setBalance(-4002);
		cashPayment.returnChange();
		assertEquals(-4002 + 3840, customer.getBalance());
		
	}
}
