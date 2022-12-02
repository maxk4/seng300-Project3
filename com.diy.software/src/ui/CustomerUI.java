package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JRootPane;

import com.diy.hardware.DoItYourselfStation;

import ui.views.EnterMemberNumberGUI;
import ui.views.OrderFinishedGUI;
import ui.views.PayWithCashGUI;
import ui.views.PayWithCreditGUI;
import ui.views.PayWithDebitGUI;
import ui.views.PayWithGiftCardGUI;
import ui.views.PurchaseBagsGUI;
import ui.views.ScanScreenGUI;
import ui.views.StartScreenGUI;
import ui.views.CustomerView;
import ui.views.WeightDiscrepancyGUI;
import util.MembershipDatabase;


public class CustomerUI {
	
	public static final int 
		START = 0,
		SCAN = 1,
		PAY_WITH_CASH = 2,
		PAY_WITH_CREDIT = 3,
		PAY_WITH_DEBIT = 4,
		PURCHASE_BAGS = 5,
		ENTER_MEMBERSHIP = 6,
		WEIGHT_DISCREPANCY = 7,
		END = 8,
		PAY_WITH_GIFT = 9;
	
	private CustomerView[] views;
	
	private JFrame mainFrame;
	private List<CustomerUIListener> listeners;
	//Added in Iteration 3 @Simrat (Starts)
	private String currentMember = null;
	private DoItYourselfStation currentStationInUse = null;
	//Added in Iteration 3 @Simrat (Ends)
	
	public CustomerUI(DoItYourselfStation station, String title) {
		mainFrame = station.screen.getFrame();
		mainFrame.setTitle(title);
		currentStationInUse = station;
		listeners = new ArrayList<CustomerUIListener>();
		views = new CustomerView[] {
				new StartScreenGUI(this, station),
				new ScanScreenGUI(this, station),
				new PayWithCashGUI(this, station),
				new PayWithCreditGUI(this, station),
				new PayWithDebitGUI(this, station),
				new PurchaseBagsGUI(this),
				new EnterMemberNumberGUI(this),
				new WeightDiscrepancyGUI(this),
				new OrderFinishedGUI(this),
				new PayWithGiftCardGUI(this, station)
		};
		mainFrame.setAlwaysOnTop(true);
		setView(START);
		station.screen.setVisible(true);
	}
	
	/**
	 * Get the main frame of the customerGUI
	 * @return the frame
	 */
	public JFrame getFrame() {
		return mainFrame;
	}
	
	public void setView(int view) {
		mainFrame.setVisible(false);
		mainFrame.setContentPane(views[view]);
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	public boolean register(CustomerUIListener listener) {
		if (listeners.contains(listener)) return false;
		listeners.add(listener);
		return true;
	}
	
	public boolean deregister(CustomerUIListener listener) {
		if (!listeners.contains(listener)) return false;
		listeners.remove(listener);
		return false;
	}
	
	public void purchaseBags(int amount) {
		for (CustomerUIListener listener : listeners) listener.purchaseBags(amount);
	}

	//Removed in Iteration 3 @Simrat (Starts)
//	public void setMember(int id) {
//		((ScanScreenGUI) views[SCAN]).updateMember(id);
//	}
	//Removed in Iteration 3 @Simrat (Ends)
	
	public void endSession() {
		for (CustomerUIListener listener : listeners) listener.endSession();
	}
	
	public void beginSession() {
		for (CustomerUIListener listener : listeners) listener.beginSession();
	}
	
	public void updateCashGUI(long paid, long balance) {
		((PayWithCashGUI) views[PAY_WITH_CASH]).update(paid, balance);
	}

	public void updateProductList(long total, long paid, String productString, String priceString) {
		((ScanScreenGUI) views[SCAN]).update(total, paid, productString, priceString);
	}

	/**
	 * @author Simrat
	 * @return the current used station by this customer
	 */
	public DoItYourselfStation getStation()
	{
		return currentStationInUse;
	}
	//Updated in Iteration @Simrat (Starts)
	public void addMemberNumber(Integer number, String CxName) {
		//membershipNum.addMem(number);
		MembershipDatabase.MEMBER_DATABASE.put(number,CxName);
	}
	public String getCurrMem() {
		return currentMember;
	}
	/**
	 * Updates the Member/Customer Name on the GUI
	 * @param customerName Name from the membership database, (if the given member exists)
	 *                      otherwise it says "Invalid Membership Number"
	 */
	public void useMemberName(String customerName) {
		//update the current member name
		System.out.println("(CustomerUI) Membership number entered: " + customerName);
		if(Objects.equals(customerName, "Invalid Membership Number"))
		{
			currentMember = null;
		}
		else
		{
			currentMember = customerName;
		}
		((ScanScreenGUI) views[SCAN]).updateMember(customerName);
	}
	//Updated in Iteration @Simrat (Ends)
}
