package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.diy.hardware.AttendantStation;
import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.Product;

import ui.views.attendant.AttendantGUI;
import ui.views.attendant.AttendantSearchCatalogueGUI;
import ui.views.attendant.AttendentLoginWithKeyboardGUI;
import ui.views.util.AttendantView;
import util.ProductInfo;

public class AttendantUI {
	
	public static final int LOGIN = 0, MAIN = 1;
	
	private AttendantGUI gui;
	private List<DoItYourselfStation> stations;
	private List<AttendantUIListener> listeners;
	private AttendantStation station;
	private JFrame mainFrame;

	private final AttendantView[] views;
	
	/**
	 * Make a new AttendantUI attached to the touch screen of the provided AttendantStation
	 * @param station AttendantStation who's touch screen the new AttendantUI will be attached to
	 */
	public AttendantUI(AttendantStation station, int maxStations) {
		this.station = station;

		listeners = new ArrayList<AttendantUIListener>();
		stations = new ArrayList<DoItYourselfStation>(maxStations);
	
		mainFrame = station.screen.getFrame();
		gui = new AttendantGUI(this, mainFrame);
		
		views = new AttendantView[]{new AttendentLoginWithKeyboardGUI(this), gui};
		
		setView(LOGIN);
		
		mainFrame.setVisible(true);
	}
	
	public void setView(int view) {
		mainFrame.setContentPane(views[view]);
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.pack();
	}
	
	public void setView(AttendantView view) {
		mainFrame.setContentPane(view);
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.pack();
	}
	
	public void addStation(DoItYourselfStation customerStation) {
		station.add(customerStation);
		gui.addStation(customerStation);
		stations.add(customerStation);
	}
	
	public void approveWeight(DoItYourselfStation station) {
		for (AttendantUIListener listener : listeners) listener.approveWeight(station);
	}
	
	public void approveOwnBag(DoItYourselfStation station) {
		for (AttendantUIListener listener : listeners) listener.approveOwnBag(station);
	}
	
	public void denyOwnBag(DoItYourselfStation station) {
		for (AttendantUIListener listener : listeners) listener.denyOwnBag(station);
	}
	
	public boolean register(AttendantUIListener listener) {
		if (listeners.contains(listener)) return false;
		listeners.add(listener);
		return true;
	}
	
	public boolean deregister(AttendantUIListener listener) {
		if (!listeners.contains(listener)) return false;
		listeners.remove(listener);
		return false;
	}
	
	/**
	 * Notify the attendant that there is a weight discrepancy
	 * @param station CustomerUI who has a weight discrepancy
	 */
	public void notifyWeightDiscrepancyDetected(DoItYourselfStation station) {
		int index = idOf(station);
		System.out.println(String.format("Station %d: Weight Discrepancy Detected", index + 1));
		gui.notifyWeightDiscrepancyDetected(station);
	}
	
	/**
	 * Notify the attendant that the discrepancy was resolved;
	 * @param station CustomerUI that had the discrepancy
	 */
	public void notifyWeightDiscrepancyResolved(DoItYourselfStation station) {
		int index = idOf(station);
		System.out.println(String.format("Station %d: Weight Discrepancy Resolved", index + 1));
		gui.notifyWeightDiscrepancyResolved(station);
	}

	public void notifyLowInkDetected(DoItYourselfStation station) {
		int index = idOf(station);
		System.out.println(String.format("Station %d: Low Ink", index + 1));
		gui.notifyLowInkDetected(station);
	}
	
	public void notifyLowInkResolved(DoItYourselfStation station) {
		int index = idOf(station);
		System.out.println(String.format("Station %d: Ink Refilled", index + 1));
		gui.notifyLowInkResolved(station);
	}
	
	public void notifyLowPaperDetected(DoItYourselfStation station) {
		int index = idOf(station);
		System.out.println(String.format("Station %d: Low Paper", index + 1));
		gui.notifyLowPaperDetected(station);
	}
	
	public void notifyLowPaperResolved(DoItYourselfStation station) {
		int index = idOf(station);
		System.out.println(String.format("Station %d: Paper Refilled", index + 1));
		gui.notifyLowPaperResolved(station);
	}
	
	/**
	 * Add a station to the AttendantUI
	 * @param station CustomerUI to add
	 */
	public void addCustomerUI(DoItYourselfStation station) {
		stations.add(station);
		gui.addStation(station);
	}
	
	private int idOf(DoItYourselfStation station) {
		return stations.indexOf(station);
	}

	public void notifyOutOfChange(DoItYourselfStation station) {
		gui.notifyOutOfChangeDetected(station);
	}
	
	public void notifyAddBag(DoItYourselfStation station) {
		gui.notifyAddOwnBag(station);
	}
	
	public void forceAddItem(DoItYourselfStation station, Product product, String description) {
		for (AttendantUIListener listener : listeners) {
			listener.addItem(station, product, description);
		}
	}
	
	public void forceRemove(DoItYourselfStation station, Product product, String description, long price, double weight) {
		for (AttendantUIListener listener : listeners) {
			listener.removeItem(station, product, description, price, weight);
		}
	}
	
	public ProductInfo[] requestProductList(DoItYourselfStation station) {
		for (AttendantUIListener listener : listeners) {
			ProductInfo[] response = listener.requestProductInfo(station);
			if (response != null) return response;
		}
		return null;
	}
	
	/**
	 * Prompt Attendant to approve or deny a own bag request
	 * @param station DoItYourselfStation that made the request
	 * @return boolean true if approved false otherwise
	 */
	public boolean approveOwnBagRequest(DoItYourselfStation station) {
		int index  = idOf(station);
		
		int choice = JOptionPane.showConfirmDialog(gui, String.format("Allow station %d to use their own bag?", index), "Aprove/Deny Own Bag Request", JOptionPane.YES_NO_OPTION);
	
		return choice == 0;
	}

	public void disableStation(DoItYourselfStation station){
		for (AttendantUIListener listener : listeners) listener.disableStation(station);
	}

	public void enableStation(DoItYourselfStation station){
		for (AttendantUIListener listener : listeners) listener.enableStation(station);
	}
	
	public void startupStation(DoItYourselfStation station) {
		for (AttendantUIListener listener : listeners) listener.disableStation(station);
	}

	public void shutdownStation(DoItYourselfStation station) {
		for (AttendantUIListener listener : listeners) listener.enableStation(station);
	}
 }