package ui;

import java.util.ArrayList;
import java.util.List;

import com.diy.hardware.AttendantStation;
import com.diy.hardware.DoItYourselfStation;

import ui.views.AttendantGUI;

public class AttendantUI {
	
	private AttendantGUI gui;
	private List<DoItYourselfStation> stations;
	private List<AttendantUIListener> listeners;
	
	/**
	 * Make a new AttendantUI attached to the touch screen of the provided AttendantStation
	 * @param station AttendantStation who's touch screen the new AttendantUI will be attached to
	 */
	public AttendantUI(AttendantStation station, int maxStations) {
		listeners = new ArrayList<AttendantUIListener>();
		stations = new ArrayList<DoItYourselfStation>(maxStations);
		
		gui = new AttendantGUI(this);
		station.screen.getFrame().setContentPane(gui);
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
 }