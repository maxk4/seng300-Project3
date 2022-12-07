package com.diy.software.test.MainTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
import com.diy.hardware.PriceLookUpCode;
import com.diy.hardware.external.ProductDatabases;
import com.diy.simulation.Customer;
import com.jimmyselectronics.OverloadException;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.Numeral;

import ca.powerutility.PowerGrid;
import main.CustomerStationWrapper;
import simulation.Simulation;
import ui.AttendantUI;
import ui.AttendantUIListener;
import ui.CustomerUI;
import util.MembershipDatabase;

public class CustomerStationWrapperTest{
	CustomerStationWrapper customerWrapper;
	DoItYourselfStation station;
	AttendantUI attendantUI;
	AttendantStation attendantStation;
	List<DoItYourselfStation> stations;
	List<AttendantUIListener> aListeners;
	AttendantUIListener aListener;
	
	@Before
	public void setUp() {
		PowerGrid.engageUninterruptiblePowerSource();
		station = new DoItYourselfStation();
		station.plugIn();
		station.turnOn();
		
		attendantStation = new AttendantStation();
		attendantStation.add(station);
		stations = new ArrayList<DoItYourselfStation>();
		stations.add(station);
		attendantUI = new AttendantUI(attendantStation, 1);
		customerWrapper = new CustomerStationWrapper(station, attendantUI);
		
		attendantUI.startupStation(station);
		Simulation.main(new String[] {"1"});
		
		//Set up attendant listener
		aListeners = attendantUI.returnListeners();
		aListener = (AttendantUIListener) aListeners.get(0);
	}

	@Test
	public void testWrapper() throws OverloadException {
		//Test set up customerWrapper
		assertFalse(customerWrapper == null);
		
		//Next section of code test attendantUI listener
		//Random products are set up for simulation, so product info is non null
		assertFalse(aListener.requestProductInfo(station) == null);
		
		//Test station screens when station is disabled
		aListener.disableStation(station);
		assertFalse(station.screen == null); //non null since there is a disabled
											//gui screen
		aListener.enableStation(station);
		assertFalse(station.screen.isDisabled());
		
		//Test station when they are turned on and shut off
		customerWrapper.customerUIListener.beginSession();
		assertTrue(station.baggingArea.isPoweredUp());
		assertTrue(station.cardReader.isPoweredUp());
		assertTrue(station.handheldScanner.isPoweredUp());
		
		aListener.shutdownStation(station);
		assertFalse(station.baggingArea.isPoweredUp());
		assertFalse(station.cardReader.isPoweredUp());
		assertFalse(station.handheldScanner.isPoweredUp());
		
		//TODO
		aListener.approveWeight(station);
		//assertFalse(station.scanningArea.)
		
		aListener.approveNoBag(station);
		//assert
		
		Barcode barcode = new Barcode(new Numeral[] {Numeral.one});
		BarcodedProduct product = new BarcodedProduct(barcode, "apple", 1, 2.3);
		station.turnOn();
		
		aListener.addItem(station, product, "apple");
		//when apple is added, weight is greater than 0
		assertTrue(station.baggingArea.getCurrentWeight() > 0);
		
		aListener.removeItem(station, product, "apple", 1, 2.3);
		//Random weight is added when getCurrentWeight() is called,
		//but its weight won't get higher than 0.2
		assertFalse(station.baggingArea.getCurrentWeight() > 0.2);
		
		//Test membership
		//Used the actual membership cards
		String result = customerWrapper.membershipListener.notifyMembershipCardRead(99999999);
		assertFalse(result == null);
		//Used the false membership cards
		String invalidResult = customerWrapper.membershipListener.notifyMembershipCardRead(1111);
		assertNull(invalidResult);
		
		//Test payment
		//test if gui is updated properly
		customerWrapper.paymentListener.cashInserted();
		//assertEquals();
		
		customerWrapper.paymentListener.cardPaymentSucceeded();
		//assertEquals;
		
		
		//test cart
		customerWrapper.cartListener.notifyItemAdded(product, 1, 2.3);
		//assert
		customerWrapper.cartListener.notifyItemRemoved(product, 1, 2.3);
		//assert
		
		//Test weight
		//customerWrapper.scaleListener.notifyWeightDiscrepancyDetected();
		//customerWrapper.scaleListener.notifyWeightDiscrepancyResolved();
		
		//Test customer
		PriceLookUpCode appleCode = new PriceLookUpCode("3283");
		PLUCodedProduct apple = new PLUCodedProduct(appleCode, "Apple", 4 * 100);
		
		//The 2 method below gets the description of the product
		//without adding it to the bagging area yet
		customerWrapper.customerUIListener.addPLUProduct(apple);
		assertFalse(station.baggingArea.getCurrentWeight() > 0.3);
		
		customerWrapper.customerUIListener.selectItem(apple, "apple");
		assertFalse(station.baggingArea.getCurrentWeight() > 0.4);
		
		//The method below puts apple into bagging area,
		//so bagging area weight will be updated 
		customerWrapper.customerUIListener.itemPlaced();
		//assertTrue(station.scanningArea.getCurrentWeight() >= 1);
		System.out.println("Scanning: " + station.scanningArea.getCurrentWeight());
		System.out.println("Baggigng: " + station.baggingArea.getCurrentWeight());
		
		
		//100 bags = 10 grams
		//customerWrapper.customerUIListener.purchaseBags(100);
		//assertTrue(station.baggingArea.getCurrentWeight() >= 2.3);
		
		//customerWrapper.customerUIListener.requestUsePersonalBag();
		//customerWrapper.customerUIListener.endSession();
	}
}
