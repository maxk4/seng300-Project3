package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JFrame;

import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.PLUCodedProduct;
import com.diy.hardware.Product;

import ui.views.*;
import ui.views.customer.AddProductWIthPLUCodeGUI;
import ui.views.customer.CustomerSearchCatalogueGUI;
import ui.views.customer.EnterMemberNumberGUI;
import ui.views.customer.OrderFinishedGUI;
import ui.views.customer.PayWithCashGUI;
import ui.views.customer.PayWithCreditGUI;
import ui.views.customer.PayWithDebitGUI;
import ui.views.customer.PayWithGiftCardGUI;
import ui.views.customer.PlaceBagGUI;
import ui.views.customer.PlaceItemGUI;
import ui.views.customer.PurchaseBagsGUI;
import ui.views.customer.ScanScreenGUI;
import ui.views.customer.StartScreenGUI;
import ui.views.customer.StationDisabledGUI;
import ui.views.customer.WeightDiscrepancyGUI;
import ui.views.util.CustomerView;
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
		PAY_WITH_GIFT = 9,
		CATALOGUE = 10,
		PLU = 11,
		PLACE_ITEM = 12,
		PLACE_BAG = 13,
		DISABLED = 14;
	private CustomerView[] views;
	
	private JFrame mainFrame;
	private List<CustomerUIListener> listeners;
	//Added in Iteration 3 @Simrat (Starts)
	private String currentMember = null;
	private DoItYourselfStation currentStationInUse = null;
	//Added in Iteration 3 @Simrat (Ends)
	private int lastView = 0;
	private int currentView = 0;
	
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
				new PayWithGiftCardGUI(this, station),
				new CustomerSearchCatalogueGUI(this),
				new AddProductWIthPLUCodeGUI(this),
				new PlaceItemGUI(this),
				new PlaceBagGUI(this),
				new StationDisabledGUI(this)
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
	
	public void disable() {
		setView(DISABLED);
	}
	
	public void enable() {
		setView(lastView);
	}
	
	public void setView(int view) {
		mainFrame.setVisible(false);
		mainFrame.setContentPane(views[view]);
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.pack();
		mainFrame.setVisible(true);
		lastView = currentView;
		currentView = view;
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
	
	public void requestPersonalBag() {
		for (CustomerUIListener listener : listeners) listener.requestUsePersonalBag();
	}
	
	public void purchaseBags(int amount) {
		for (CustomerUIListener listener : listeners) listener.purchaseBags(amount);
	}
	
	public void addPLUProduct(PLUCodedProduct product) {
		for (CustomerUIListener listener : listeners) listener.addPLUProduct(product);
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
		((PayWithDebitGUI) views[PAY_WITH_DEBIT]).update(paid, balance);
		((PayWithCreditGUI) views[PAY_WITH_CREDIT]).update(paid, balance);
		((PayWithGiftCardGUI) views[PAY_WITH_GIFT]).update(paid, balance);
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
		setView(SCAN);
	}
	
	public void useMemberName(int memberId) {
		useMemberName(MembershipDatabase.MEMBER_DATABASE.get(memberId));
	}
	//Updated in Iteration @Simrat (Ends)
	
	public void selectItem(Product product, String description) {
		for (CustomerUIListener listener : listeners) listener.selectItem(product, description);
	}

	public void itemPlaced() {
		for (CustomerUIListener listener : listeners) listener.itemPlaced();
	}
}
