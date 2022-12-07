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
import com.diy.hardware.PriceLookUpCode;
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
		ArrayList aListeners = (ArrayList) attendantUI.returnListeners();
		AttendantUIListener aListener = (AttendantUIListener) aListeners.get(0);
		aListener.requestProductInfo(station);
		
		aListener.approveOwnBag(station);
		aListener.denyOwnBag(station);
		aListener.disableStation(station);
		aListener.enableStation(station);
		aListener.shutdownStation(station);
		aListener.approveWeight(station);
		aListener.approveNoBag(station);
		aListener.approveOwnBag(station);
		
		Barcode barcode = new Barcode(new Numeral[] {Numeral.one});
		BarcodedProduct product = new BarcodedProduct(barcode, "apple", 1, 2.3);
		aListener.addItem(station, product, "apple");
		aListener.removeItem(station, product, "apple", 1, 2.3);
		
		//Test membership
		customerWrapper.membershipListener.notifyMembershipCardRead(1111);
		
		//Test payment
		customerWrapper.paymentListener.cashInserted();
		customerWrapper.paymentListener.cardPaymentSucceeded();
		
		
		//test cart
		customerWrapper.cartListener.notifyItemAdded(product, 1, 2.3);
		customerWrapper.cartListener.notifyItemRemoved(product, 1, 2.3);
		
		//test weight
		//customerWrapper.scaleListener.notifyWeightDiscrepancyDetected();
		//customerWrapper.scaleListener.notifyWeightDiscrepancyResolved();
		
		//test customer
		PriceLookUpCode appleCode = new PriceLookUpCode("3283");
		PLUCodedProduct apple = new PLUCodedProduct(appleCode, "Apple", 4 * 100);
		customerWrapper.customerUIListener.beginSession();
		customerWrapper.customerUIListener.addPLUProduct(apple);
		
		customerWrapper.customerUIListener.selectItem(apple, "apple");
		customerWrapper.customerUIListener.itemPlaced();
		customerWrapper.customerUIListener.purchaseBags(3);
		//customerWrapper.customerUIListener.requestUsePersonalBag();
		
		//customerWrapper.customerUIListener.endSession();
	}
}
