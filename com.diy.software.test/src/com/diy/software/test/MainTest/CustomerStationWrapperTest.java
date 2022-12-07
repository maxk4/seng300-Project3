package com.diy.software.test.MainTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.AttendantStation;
import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.PLUCodedProduct;
import com.diy.simulation.Customer;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.Numeral;

import main.CustomerStationWrapper;
import simulation.Simulation;
import ui.AttendantUI;
import ui.AttendantUIListener;

public class CustomerStationWrapperTest{
	CustomerStationWrapper customerWrapper;
	DoItYourselfStation station;
	Customer customer;
	AttendantUI attendantUI;
	AttendantStation attendantStation;
	List<DoItYourselfStation> stations;
	
	@Before
	public void setUp() {
		station = new DoItYourselfStation();
		station.plugIn();
		station.turnOn();
		customer = new Customer();
		
		attendantStation = new AttendantStation();
		attendantStation.add(station);
		stations = new ArrayList<DoItYourselfStation>();
		stations.add(station);
		attendantUI = new AttendantUI(attendantStation, 1);
		//attendantUI.addStation(station);
		customer.useStation(station);
		customerWrapper = new CustomerStationWrapper(station, attendantUI);
		
	}
	@After
	public void tearDown() {
	
	}
	@Test
	public void testWrapper() {
		attendantUI.startupStation(station);
		Simulation.main(new String[] {"1"});
		assertFalse(customerWrapper == null);
		ArrayList listeners = (ArrayList) attendantUI.returnListeners();
		AttendantUIListener listener = (AttendantUIListener) listeners.get(0);
		listener.requestProductInfo(station);
		
		listener.approveOwnBag(station);
		listener.denyOwnBag(station);
		listener.disableStation(station);
		listener.enableStation(station);
		listener.shutdownStation(station);
		listener.approveWeight(station);
		listener.approveNoBag(station);
		listener.approveOwnBag(station);
		
		Barcode barcode = new Barcode(new Numeral[] {Numeral.one});
		BarcodedProduct product = new BarcodedProduct(barcode, "apple", 1, 2.3);
		listener.addItem(station, product, "apple");
		listener.removeItem(station, product, "apple", 1, 2.3);
		
	}
}
