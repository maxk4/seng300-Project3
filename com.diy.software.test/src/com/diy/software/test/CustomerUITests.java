package com.diy.software.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.PLUCodedProduct;
import com.diy.hardware.PriceLookUpCode;
import com.diy.hardware.Product;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.Numeral;

import ui.CustomerUI;
import ui.CustomerUIListener;
import util.MembershipDatabase;

/**
 * Test suite for the CustomerUI class.
 * @author Matthias Kee
 */
public class CustomerUITests {
	
	CustomerUI customerUI;
	DoItYourselfStation customerStation;
	CUL listener;
	
	/*
	 * Setup for the test suite.
	 */
	@Before 
	public void setup() {
		listener = new CUL();
		customerStation = new DoItYourselfStation();
		customerStation.plugIn();
		customerStation.turnOn();
		
		customerUI = new CustomerUI(customerStation, "Station 1");
	}
	
	/*
	 * Tear down for the test suite.
	 */
	@After
	public void tearDown() {
		MembershipDatabase.MEMBER_DATABASE.clear();
	}
	
	/*
	 * Test for registering a listener.
	 */
	@Test
	public void testRegisterListener() {
		assertTrue(customerUI.register(listener));
		assertFalse(customerUI.register(listener));
	}
	
	/*
	 * Test for deregistering a listener.
	 */
	@Test
	public void testDeregisterListener() {
		customerUI.register(listener);
		assertFalse(customerUI.deregister(listener));
		CUL listener2 = new CUL();
		assertFalse(customerUI.deregister(listener2));
	}
	
	/*
	 * Test for customer requesting to use a personal bag with a listener.
	 */
	@Test
	public void testRequestPersonalBag() {
		customerUI.register(listener);
		assertEquals(0, requestPersonalBag);
		customerUI.requestPersonalBag();
		assertEquals(1,requestPersonalBag);
	}
	
	/*
	 * Test for customer purchasing bags with a listener.
	 */
	@Test
	public void testPurchaseBags() {
		customerUI.register(listener);
		assertEquals(0, purchasedBags);
		customerUI.purchaseBags(1);
		assertEquals(1, purchasedBags);
	}
	
	/*
	 * Test for customer adding a product though a PLU code.
	 */
	@Test
	public void testAddPLUProduct() {
		PriceLookUpCode PLUCode = new PriceLookUpCode("0000");
		PLUCodedProduct PLUProduct = new PLUCodedProduct(PLUCode, "PLU Product", 10L);
		
		customerUI.register(listener);
		assertEquals(0, PLUProductAdded);
		customerUI.addPLUProduct(PLUProduct);
		assertEquals(1, PLUProductAdded);
	}
	
	/*
	 * Test for a inactive session.
	 */
	@Test 
	public void testSessionRunningFalse() {
		customerUI.register(listener);
		customerUI.endSession();
		assertFalse(sessionRunning);
	}
	
	/*
	 * Test for an active session.
	 */
	@Test 
	public void testSessionRunningTrue() {
		customerUI.register(listener);
		customerUI.beginSession();
		assertTrue(sessionRunning);
	}
	
	/*
	 * Test for getting the current station.
	 */
	@Test
	public void testGetStation() {
		DoItYourselfStation actual = customerUI.getStation();
		assertEquals(customerStation, actual);
	}
	
	/*
	 * Test for adding a member number.
	 */
	@Test
	public void testAddMemberNumber() {
		assertFalse(MembershipDatabase.MEMBER_DATABASE.containsKey(1234));
		customerUI.addMemberNumber(1234, "John Doe");
		assertTrue(MembershipDatabase.MEMBER_DATABASE.containsKey(1234));
	}
	
	/*
	 * Test for using a valid member name.
	 */
	@Test
	public void testUseMemberNameValid() {
		customerUI.useMemberName(1234);
		assertEquals(null, customerUI.getCurrMem());
		customerUI.addMemberNumber(1234, "John Doe");
		customerUI.useMemberName(1234);
		assertEquals("John Doe", customerUI.getCurrMem());
	}
	
	/*
	 * Test for using a invalid member name.
	 */
	@Test
	public void testUseMemberNameInvalid() {
		customerUI.addMemberNumber(1234, "Invalid Membership Number");
		customerUI.useMemberName(1234);
		assertEquals(null, customerUI.getCurrMem());
	}
	
	/*
	 * Test for selecting an item with a listener.
	 */
	@Test
	public void testSelectItem() {
		customerUI.register(listener);
		BarcodedProduct product = new BarcodedProduct(new Barcode(new Numeral[] {Numeral.one}), "Product", 10L, 5);
		assertEquals(0, itemSelected);
		customerUI.selectItem(product, product.getDescription());
		assertEquals(1, itemSelected);
	}
	
	/*
	 * Test for a item placed with a listener.
	 */
	@Test
	public void testItemPlaced() {
		customerUI.register(listener);
		assertFalse(itemPlaced);
		customerUI.itemPlaced();
		assertTrue(itemPlaced);
	}
	
	
	int purchasedBags = 0;
	boolean sessionRunning = false;
	int itemSelected = 0;
	boolean itemPlaced = false;
	int requestPersonalBag = 0;
	int PLUProductAdded = 0;
	
	public class CUL implements CustomerUIListener {

		@Override
		public void purchaseBags(int amount) {
			purchasedBags++;
		}

		@Override
		public void endSession() {
			sessionRunning = false;
		}

		@Override
		public void beginSession() {
			sessionRunning = true;
		}
		@Override
		public void selectItem(Product product, String description) {
			itemSelected++;
		}

		@Override
		public void itemPlaced() {
			itemPlaced = true;
		}

		@Override
		public void requestUsePersonalBag() {
			requestPersonalBag++;
		}

		@Override
		public void addPLUProduct(PLUCodedProduct product) {
			PLUProductAdded++;
		}
		
	}
}
