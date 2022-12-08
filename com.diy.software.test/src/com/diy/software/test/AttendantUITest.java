package com.diy.software.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.AttendantStation;
import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.Product;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.Numeral;

import junit.framework.Assert;
import ui.AttendantUI;
import ui.AttendantUIListener;
import ui.CustomerUI;
import util.ProductInfo;

/**
 * JUnit test for testing System.out.println is referenced from
 * https://www.baeldung.com/java-testing-system-out-println
 *
 */
public class AttendantUITest { 
	
	public class AttendantUIListenerImpl implements AttendantUIListener{
		
		int weightApproved;
		int addOwnBagApproved;
		int noBagApproved;
		int itemAdded;
		int itemRemoved;
		Product productAdded;
		String productAddedDescription;
		Product productRemoved; 
		String productRemovedDescription;
		long productRemovedPrice;
		double productRemovedWeight;
		int stationDisabledCount;
		int stationEnabledCount;
		int stationStartupCount;
		int stationShutownCount;
		
		public AttendantUIListenerImpl() {
			weightApproved = 0;
			addOwnBagApproved = 0;
			noBagApproved = 0;
			itemAdded = 0;
		} 
		
		@Override
		public boolean approveWeight(DoItYourselfStation station) {
			weightApproved++;
			return true;
		}


		@Override
		public void approveNoBag(DoItYourselfStation station) {
			noBagApproved++;
		}

		@Override
		public ProductInfo[] requestProductInfo(DoItYourselfStation station) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void disableStation(DoItYourselfStation station) {
			stationDisabledCount++;
		}

		@Override
		public void enableStation(DoItYourselfStation station) {
			stationEnabledCount++;
		}

		@Override
		public void addItem(DoItYourselfStation station, Product product, String description) {
			itemAdded++;
			this.productAdded = product;
			this.productAddedDescription = description;
		}

		@Override
		public void removeItem(DoItYourselfStation station, Product product, String description, long price,
				double weightInGrams) {
			itemRemoved++;
			this.productRemoved = product;
			this.productRemovedDescription = description;
			this.productRemovedPrice = price;
			this.productRemovedWeight = weightInGrams;
		}

		@Override
		public void startupStation(DoItYourselfStation station) {
			stationStartupCount++;
		}

		@Override
		public void shutdownStation(DoItYourselfStation station) {
			stationShutownCount++;
		}
		
	}
	
	AttendantUI attendant;
	AttendantStation station;
	int maxCustomerStation;
	AttendantUIListenerImpl listener;
	DoItYourselfStation customerStation;
	CustomerUI customer;
	
	// https://www.baeldung.com/java-testing-system-out-println
	PrintStream standardOut = System.out;
	ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	
	@Before
	public void setup() {
		maxCustomerStation = 1;
		station = new AttendantStation();
		attendant = new AttendantUI(station, maxCustomerStation);
		listener = new AttendantUIListenerImpl();
		customerStation = new DoItYourselfStation();
		customerStation.plugIn();
		customerStation.turnOn();
		// https://www.baeldung.com/java-testing-system-out-println
		System.setOut(new PrintStream(outputStreamCaptor));
		attendant.addStation(customerStation);
		attendant.addCustomerUI(customerStation);
	}
	
	@After
	public void teardown() {
		// https://www.baeldung.com/java-testing-system-out-println
		System.setOut(standardOut);		
	}
	
	@Test
	public void testRegisterListener() {
		assertTrue(attendant.register(listener));
		assertFalse(attendant.register(listener));
	}
	
	@Test
	public void testDeregisterListener() {
		assertTrue(attendant.register(listener));
		AttendantUIListenerImpl listener2 = new AttendantUIListenerImpl();
		assertFalse(attendant.deregister(listener2));
		assertTrue(attendant.deregister(listener));
	}
	 
	@Test
	public void testApproveWeightNotif() {
		attendant.register(listener);
		assertEquals(0, listener.weightApproved);		
		attendant.approveWeight(customerStation);
		assertEquals(1, listener.weightApproved);
	}
	
	@Test
	public void testApproveOwnBagNotif() {
		attendant.register(listener);
		attendant.approveOwnBag(customerStation);
		assertEquals(0, listener.addOwnBagApproved);	
	}	
	
	
	@Test
	public void testDenyOwnBagNotif() {
		attendant.register(listener);
		assertEquals(0, listener.addOwnBagApproved);		
		attendant.denyOwnBag(customerStation);
		assertEquals(0, listener.addOwnBagApproved);
	}
	
	@Test
	public void testNotifyWeightDiscrepancyDetected() {
		attendant.notifyWeightDiscrepancyDetected(customerStation);
		
		String msg = "Station 1: Weight Discrepancy Detected";
		// https://www.baeldung.com/java-testing-system-out-println
	    assertTrue(msg.equals(outputStreamCaptor.toString().trim()));
	}
	
	@Test
	public void testNotifyWeightDiscrepancyResolved() {
		attendant.notifyWeightDiscrepancyResolved(customerStation);
		
		String msg = "Station 1: Weight Discrepancy Resolved";
		// https://www.baeldung.com/java-testing-system-out-println
	    assertTrue(msg.equals(outputStreamCaptor.toString().trim()));
	}

	@Test
	public void testNotifyLowInkDetected() {
		attendant.notifyLowInkDetected(customerStation);
		
		String msg = "Station 1: Low Ink";
		// https://www.baeldung.com/java-testing-system-out-println
	    assertTrue(msg.equals(outputStreamCaptor.toString().trim()));
	}

	@Test
	public void testNotifyLowInkResolved() {
		attendant.notifyLowInkResolved(customerStation);
		
		String msg = "Station 1: Ink Refilled";
		// https://www.baeldung.com/java-testing-system-out-println
	    assertTrue(msg.equals(outputStreamCaptor.toString().trim()));
	}
	
	@Test
	public void testNotifyLowPaperDetected() {
		attendant.notifyLowPaperDetected(customerStation);
		
		String msg = "Station 1: Low Paper";
		// https://www.baeldung.com/java-testing-system-out-println
	    assertTrue(msg.equals(outputStreamCaptor.toString().trim()));
	}

	@Test
	public void testNotifyLowPaperResolved() {
		attendant.notifyLowPaperResolved(customerStation);
		
		String msg = "Station 1: Paper Refilled";
		// https://www.baeldung.com/java-testing-system-out-println
	    assertTrue(msg.equals(outputStreamCaptor.toString().trim()));
	}
	
	@Test
	public void testForceAddItem() {
		attendant.register(listener);
		String description = "product";
		Product p = new BarcodedProduct(new Barcode(new Numeral[] {Numeral.one, Numeral.two, 
				Numeral.three, Numeral.four}), description, 10, 10.0);
		attendant.forceAddItem(customerStation, p, description);
		assertEquals(1,listener.itemAdded);
		assertEquals(p, listener.productAdded);
		assertEquals(description, listener.productAddedDescription);
	}
	
	@Test
	public void testForceRemoveItem() {
		attendant.register(listener);
		String description = "product";
		long price = 10;
		double weight = 10.0;
		Product p = new BarcodedProduct(new Barcode(new Numeral[] {Numeral.one, Numeral.two, 
				Numeral.three, Numeral.four}), description, price, weight);
		attendant.forceRemove(customerStation, p, description, price, weight);
		assertEquals(1, listener.itemRemoved);
		assertEquals(p, listener.productRemoved);
		assertEquals(description, listener.productRemovedDescription);
		assertEquals(price, listener.productRemovedPrice);
		assertEquals(weight, listener.productRemovedWeight, 0.01);
	}

	@Test
	public void testRequestNullProductList() {
		attendant.register(listener);
		Object nullObject = null;
		assertEquals(nullObject, attendant.requestProductList(customerStation));
	}
	
	@Test
	public void testRequestProductList() {
		String description = "product";
		long price = 10;
		double weight = 10.0;
		Product p = new BarcodedProduct(new Barcode(new Numeral[] {Numeral.one, Numeral.two, 
				Numeral.three, Numeral.four}), description, price, weight);
		ProductInfo[] list = {new ProductInfo(p, description, price, weight)};
		attendant.register(new AttendantUIListener() {
			@Override
			public boolean approveWeight(DoItYourselfStation station) {return true;}
			@Override
			public void approveNoBag(DoItYourselfStation station) {}
			@Override
			public ProductInfo[] requestProductInfo(DoItYourselfStation station) {
				return list; 
			}
			@Override
			public void disableStation(DoItYourselfStation station) {}
			@Override
			public void enableStation(DoItYourselfStation station) {}
			@Override
			public void addItem(DoItYourselfStation station, Product product, String description) {}
			@Override
			public void removeItem(DoItYourselfStation station, Product product, String description, long price,
					double weightInGrams) {}
			@Override
			public void startupStation(DoItYourselfStation station) {}
			@Override
			public void shutdownStation(DoItYourselfStation station) {}
		});
		ProductInfo[] res = attendant.requestProductList(customerStation);
		if (res == null) {
			throw new AssertionError("null is returned");
		} else if (list.length != res.length){
			throw new AssertionError("wrong product info is passed");
		} else {
			for (int i = 0; i < list.length; i++) {
				assertEquals(res[i], list[i]);
			} 
		}
	}
	
	@Test
	public void testDisableStation() {
		attendant.register(listener);
		attendant.disableStation(customerStation);
		assertEquals(1, listener.stationDisabledCount);
	}
	
	@Test
	public void testEnableStation() {
		attendant.register(listener);
		attendant.enableStation(customerStation);
		assertEquals(1, listener.stationEnabledCount);
	}
	
	@Test
	public void testStartupStation() {
		attendant.register(listener);
		attendant.startupStation(customerStation);
		assertEquals(1, listener.stationStartupCount);
	}
	
	@Test
	public void testShutdownStation() {
		attendant.register(listener);
		attendant.shutdownStation(customerStation);
		assertEquals(1, listener.stationShutownCount);
	}
}
