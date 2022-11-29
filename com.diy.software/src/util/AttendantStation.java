package util;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.jimmyselectronics.disenchantment.TouchScreen;

public class AttendantStation {
	
	private TouchScreen touchScreen;
	private List<CustomerUI> stations;
	private List<AttendantStationListener> listeners;
	
	public AttendantStation() {
		touchScreen = new TouchScreen();
		stations = new ArrayList<CustomerUI>();
		listeners = new ArrayList<AttendantStationListener>();
	}
	
	/**
	 * Start tracking the station provided 
	 * @param station CustomerUI to start tracking
	 * @throws NullPointerException when the station provided is null
	 */
	public void registerStation(CustomerUI station) throws NullPointerException {
		if (station == null) throw new NullPointerException("CustomerUI cannot be null");
		if (stations.contains(station)) throw new IllegalArgumentException("CustomerUI is already registerd");
		stations.add(station);
		for (AttendantStationListener listener : listeners) listener.noftifyRegistered(station);
	}
	
	/**
	 * Register the provided listener
	 * @param listener AttendantStationListener to add
	 * @throws IllegalArgumentException when the listener is already registered
	 * @throws NullPointerException when the listener provided is null
	 */
	public void registerListener(AttendantStationListener listener) throws IllegalArgumentException, NullPointerException {
		if (listener == null) throw new NullPointerException("listener cannot be null");
		if (listeners.contains(listener)) throw new IllegalArgumentException("The listener provided is already registered");
		listeners.add(listener);
	}
}
