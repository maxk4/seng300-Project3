package util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.Product;
import com.jimmyselectronics.EmptyException;
import com.jimmyselectronics.OverloadException;
import com.unitedbankingservices.DisabledException;

import views.EnterMemberNumberGUI;
import views.OrderFinishedGUI;
import views.PayWithCashGUI;
import views.PayWithCreditGUI;
import views.PayWithDebitGUI;
import views.PurchaseBagsGUI;
import views.ScanScreenGUI;
import views.StartScreenGUI;
import views.WeightDiscrepancyGUI;


public class CustomerUI {
	
	private long balance = 0;
	private boolean inProgress = false, placingBag = false;
	private long total = 0;
	
	private ExpectedWeightListener weightListener;
	private ProductList productList;
	private MembershipNumber membershipNum;
	
	private List<CustomerStationListener> stationListeners = new ArrayList<CustomerStationListener>();
	private List<NoBaggingRequestListener> nbrListeners = new ArrayList<NoBaggingRequestListener>();
	private DoItYourselfStation station;
	
	private CashPayment cashPayment;
	
	private PayWithCashGUI payWithCashGUI;
	private PayWithCreditGUI payWithCreditGUI;
	private PayWithDebitGUI payWithDebitGUI;

	private ScanScreenGUI scanScreenGUI;
	private StartScreenGUI startScreenGUI;
	private PurchaseBagsGUI purchaseBagsGUI;
	private EnterMemberNumberGUI memberNumGUI;
	
	private WeightDiscrepancyGUI weightDiscrepancyGUI;
	private OrderFinishedGUI orderFinishedGUI;
	
	public CustomerUI(DoItYourselfStation station) {
		this.station = station;
		beginSession();
	}
	
	public CustomerUI(DoItYourselfStation station, String title) {
		this.station = station;
		
		beginSession();
		
		scanScreenGUI.setTitle(title);
		startScreenGUI.setTitle(title);
		orderFinishedGUI.setTitle(title);
	}
	
	/**
	 * Gets the cashPayment field
	 * @return CashPayment cashPayment field of this customer ui
	 */
	public CashPayment getCashPaymentController() {
		return cashPayment;
	}
	
	/**
	 * Sets the cashPayment field
	 * @param CashPayment cashPayment field of this customer ui
	 */
	public void setCashPaymentController(CashPayment cashPayment) {
		this.cashPayment = cashPayment;
	}
	
	/**
	 * Get the main frame of the customerGUI
	 * @return the frame
	 */
	public JFrame getFrame() {
		return scanScreenGUI;
	}

	/**
	 * Setter method for weightListener
	 * 
	 * @param weightListener ExpectedWeightListener to set
	 */
	public void setWeightListener(ExpectedWeightListener weightListener) {
		this.weightListener = weightListener;
	}

	/**
	 * Notify the weight listener that the expected weight should be updated
	 * 
	 * @param weight double weight to change the expected weight by
	 */
	public void notifyExpectedWeight(double weight) {
		weightListener.updateExpectedWeight(weight);
	}

	/**
	 * Notify the weight listener that the weight is correct
	 * 
	 */
	public void notifyWeightApproved() {
		weightListener.approveWeightDiscrepancy();
	}

	/**
	 * Add a product to the UI
	 * 
	 * @param product Product to add to the UI
	 */
	public void addBarcodedProductToList(BarcodedProduct product) {
		// Add product to UI
		productList.add(product, product.getDescription(), product.getPrice());
		balance += product.getPrice();
		scanScreenGUI.update(balance, productList);
	}
	

	/**
	 * Check if product exists in list
	 * 
	 * @param Ensure product added exists in list
	 */
	public boolean checkProductList(Product product) {
		boolean statement;
		statement = productList.containsProduct(product);
		return statement;
	}
	
	/**
	 * Get the number of products in the product list
	 * @return int the number of products in the product list
	 */
	public int productCount() {
		return productList.size();
	}

	/**
	 * Register a DiscrepancyListener
	 * 
	 * @param listener DiscrepancyListener to register
	 * @throws IllegalStateException    when the listener is already registered
	 * @throws IllegalArgumentException when the listener is null
	 */
	public void registerDiscrepancyListener(CustomerStationListener listener)
			throws IllegalStateException, IllegalArgumentException {
		if (listener == null)
			throw new IllegalArgumentException("listener cannot be null");
		if (stationListeners.contains(listener))
			throw new IllegalStateException("listener is already registerd");
		stationListeners.add(listener);
	}

	/**
	 * Register a NoBaggingRequestListener
	 * 
	 * @param listener NoBaggingRequestListener to register
	 * @throws IllegalStateException when the listener is already registered
	 * @throws IllegalArgumentException when the listener is null
	 */
	public void registerNoBaggingRequestListener(NoBaggingRequestListener listener)
			throws IllegalStateException, IllegalArgumentException {
		if (listener == null)
			throw new IllegalArgumentException("listener cannot be null");
		if (nbrListeners.contains(listener))
			throw new IllegalStateException("listener is already registerd");
		nbrListeners.add(listener);
	}

	/**
	 * Check if No Bagging Request Listener is added to array
	 * 
	 * @param Check array contents to ensure discrepancy listener is present
	 */
	public boolean checkNoBaggingRequestListener(NoBaggingRequestListener listener) {
		boolean contains;
		contains = nbrListeners.contains(listener);
		return contains;
	}

	/**
	 * Check if Discrepancy Listener is added to array
	 * 
	 * @param Check array contents to ensure discrepancy listener is present
	 */
	public boolean checkDiscrepancyListener(CustomerStationListener listener) {
		boolean array_contains;
		array_contains = stationListeners.contains(listener);
		return array_contains;
	}

	/**
	 * Alert the UI that there is a weight discrepancy
	 */
	public void alertWeightDiscrepancy() {
		if (inProgress) {
			// Show weight discrepancy gui
			station.mainScanner.disable();
			for (CustomerStationListener listener : stationListeners) {
				if (placingBag) listener.nofityAddBag(this);
				else listener.notifyWeightDiscrepancyDetected(this);
			}
			
			weightDiscrepancyGUI.setVisible(true);
		}
	}
	
	/**
	 * Resolve a weight discrepancy *only to be called by the AttendantStation or
	 * the attached expected weight listener
	 */
	public void resolveWeightDiscrepancy() {
		if (inProgress) {
			station.mainScanner.enable();
			for (CustomerStationListener listener : stationListeners)
				if (!placingBag) listener.notifyWeightDiscrepancyResolved(this);
			weightDiscrepancyGUI.setVisible(false);
		} else {
			startScreenGUI.dispose();
			scanScreenGUI.dispose();
			payWithCashGUI.dispose();
			payWithCreditGUI.dispose();
			payWithDebitGUI.dispose();
			weightDiscrepancyGUI.dispose();
			orderFinishedGUI.dispose();
			
			beginSession();
		}
	}

	/**
	 * Notify the customer that their no bagging request was approved Note: only to
	 * be called by attendant & related classes IMPORTANT!
	 */
	public void notifyNoBaggingRequestApproved() {
		approveWeightDiscrepancy();
	}

	/**
	 * Notify the customer that their no bagging request was denied Note: only to
	 * be called by attendant & related classes IMPORTANT!
	 */
	public void notifyNoBaggingRequestDenied() {
		alertWeightDiscrepancy();
	}

	/**
	 * Force resolve a weight discrepancy Note: only to be called by attendant &
	 * related classes IMPORTANT!
	 */
	public void approveWeightDiscrepancy() {
		weightListener.approveWeightDiscrepancy();
	}
	
	/**
	 * Force set the balance for notify payment in case of system error
	 * @param Set the balance used for notify payment method
	 */
	public void setBalance(long tbalance) {
		this.balance = tbalance;
	}
	
	/**
	 * Notify the UI of a successful payment
	 * @param amount long the amount paid successfully
	 */
	public void notifyPayment(long amount) {
		balance -= amount;
		scanScreenGUI.update(balance, productList);
		if (balance <= 0) {
			completePayment();
		}
	}
	
	/**
	 * Enable/Disable the station associated with this customerUI's scanner
	 * @param enabled boolean true if the scanner should be enabled false otherwise
	 */
	public void startScanning() {
		station.mainScanner.enable();

		startScreenGUI.setVisible(false);
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
		purchaseBagsGUI.setVisible(false);
		memberNumGUI.setVisible(false);
		inProgress = true;
	}
	
	/**
	 * Notify customerui that payment has completed
	 */
	public void completePayment() {
		startScreenGUI.setVisible(false);
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
		station.cardReader.disable();
		station.coinSlot.disable();
		station.banknoteInput.disable();
		station.banknoteOutput.disable();
		
		scanScreenGUI.update(balance, productList);
	}
	
	/**
	 * Notify the customerui that the customer has requested to pay with cash
	 * 
	 */
	public void payWithCash() {
		if (balance > 0) {
			scanScreenGUI.setVisible(true);
			payWithCashGUI.setVisible(true);
			payWithCreditGUI.setVisible(false);
			payWithDebitGUI.setVisible(false);
			
			station.coinSlot.enable();
			station.banknoteInput.enable();
			station.banknoteOutput.enable();
			station.banknoteValidator.activate();
		}
	}

	/**
	 * Notify the customerui that the customer has requested to pay with credit
	 * 
	 */
	public void payWithCredit() {
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(true);
		payWithDebitGUI.setVisible(false);
		
		station.cardReader.enable();
	}

	/**
	 * Notify the customerui that the customer has requested to pay with debit
	 * 
	 */
	public void payWithDebit() {
		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(true);
		
		station.cardReader.enable();
	}
	
	/**
	 * Get the current balance
	 * @return Get the current balance
	 */
	public long getBalance() {
		return balance;
	}
	
	/**
	 * End the current session
	 * @throws IllegalStateException when balance > 0
	 */
	public void endSession() throws IllegalStateException {
		if (balance > 0) throw new IllegalStateException("The Customer's balance is greater than 0");

		scanScreenGUI.setVisible(true);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
		// Show end screen
		orderFinishedGUI.setVisible(true);
		
		printReceipt();
		
		try {
			cashPayment.returnChange();
		} catch (DisabledException e) {
			e.printStackTrace();
		} catch (Exception e) {
			for (CustomerStationListener listener : stationListeners) {
				listener.notifyOutOfChange(this);
			}
		}
		
		inProgress = false;
		
		weightListener.setExpectedWeight(0);
		
	}
	
	/**
	 * Print Receipt
	 */
	public void printReceipt() {
		total = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("Transaction Complete\n");
		sb.append("--------------------\n");
		productList.forEach((prod, desc, price) -> {
			sb.append(String.format("%s\t\t$%.2f\n", desc, price / 100d));
			total += price;
		});
		sb.append("--------------------\n");
		sb.append(String.format("\t\tTotal:\t$%.2f", total / 100d));
		String receipt = sb.toString();
		for (char c : receipt.toCharArray()) {
			try {
				station.printer.print(c);
			} catch (EmptyException e) {
				// Notify attendant
			} catch (OverloadException e) {
				e.printStackTrace();
			}
		}
		station.printer.cutPaper();
	}
	
	/**
	 * Begin a session
	 */
	public void beginSession() {

		startScreenGUI = new StartScreenGUI(this);
		scanScreenGUI = new ScanScreenGUI(this);
		payWithCashGUI = new PayWithCashGUI(this);
		payWithCreditGUI = new PayWithCreditGUI(this);
		payWithDebitGUI = new PayWithDebitGUI(this);
		purchaseBagsGUI = new PurchaseBagsGUI(this);
		memberNumGUI = new EnterMemberNumberGUI(this);
		weightDiscrepancyGUI = new WeightDiscrepancyGUI();
		orderFinishedGUI = new OrderFinishedGUI();
		
		
		startScreenGUI.setVisible(true);
		scanScreenGUI.setVisible(false);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
		weightDiscrepancyGUI.setVisible(false);
		orderFinishedGUI.setVisible(false);
		purchaseBagsGUI.setVisible(false);
		memberNumGUI.setVisible(false);
		
		balance = 0;
		productList = new ProductList();
		membershipNum = new MembershipNumber();
		scanScreenGUI.update(0, productList);
		
		station.coinSlot.disable();
		station.banknoteInput.disable();
		station.banknoteOutput.disable();
		station.banknoteValidator.disactivate();
		station.cardReader.disable();
	}

	/**
	 * Open purchase bag gui
	 */
	public void purchageBags() {
		startScreenGUI.setVisible(false);
		scanScreenGUI.setVisible(false);
		payWithCashGUI.setVisible(false);
		payWithCreditGUI.setVisible(false);
		payWithDebitGUI.setVisible(false);
		weightDiscrepancyGUI.setVisible(false);
		orderFinishedGUI.setVisible(false);
		purchaseBagsGUI.setVisible(true);
	}
	
	/*
	 *  Open enter membership number gui
	 */
	public void enterMemNum() {
		if (membershipNum.getCurrentMember() == null) {
			startScreenGUI.setVisible(false);
			scanScreenGUI.setVisible(false);
			payWithCashGUI.setVisible(false);
			payWithCreditGUI.setVisible(false);
			payWithDebitGUI.setVisible(false);
			weightDiscrepancyGUI.setVisible(false);
			orderFinishedGUI.setVisible(false);
			memberNumGUI.setVisible(true);
		}
	}
	public Integer checkMemberNumber(Integer number) {
		return membershipNum.checkMemNum(number);
	}
	
	public void useMemberNumber(Integer number) {
		scanScreenGUI.updateMember(number);
	}
	
	public void addMemberNumber(Integer number) {
		membershipNum.addMem(number);
	}
	
	public Integer getCurrMem() {
		return membershipNum.getCurrentMember();
	}
	public void addBag() {
		placingBag = true;
	}
	
	public void approveBag() {
		weightListener.approveWeightDiscrepancy();
		placingBag = false;
	}
	
	public void denyBag() {
		placingBag = false;
	}
}
