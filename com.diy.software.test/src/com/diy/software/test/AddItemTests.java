package com.diy.software.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.external.ProductDatabases;
import com.diy.simulation.Customer;
import com.jimmyselectronics.AbstractDevice;
import com.jimmyselectronics.AbstractDeviceListener;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodeScanner;
import com.jimmyselectronics.necchi.BarcodeScannerListener;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
import com.jimmyselectronics.opeechee.Card;

import ca.powerutility.PowerGrid;
import ca.ucalgary.seng300.simulation.NullPointerSimulationException;
import cart.CartController;
import cart.ScanItemListener;
import simulation.Simulation;
import ui.CustomerUI;
import util.MembershipDatabase;

public class AddItemTests {
	
	Customer customer;
	CustomerUI ui;
	DoItYourselfStation station;
	CartController cartController;
	ScanItemListener sil;
	Barcode bc1, bc2, bc3, nullBarcode;
	BarcodedItem item1, item2, item3, nullItem;
	BarcodedProduct prod1, prod2;
	
	public List<Card> cards = new ArrayList<Card>();
	long price1 = 10L, price2 = 15L;
	double weight1 = 1.3, weight2 = 5.2, weight3 = 3.2;
	int found;
	
	@Before
	public void setup() {
		ProductDatabases.BARCODED_PRODUCT_DATABASE.clear();
		
		found = 0;
		   
	    bc1 = new Barcode(new Numeral[] {Numeral.one});
	    bc2 = new Barcode(new Numeral[] {Numeral.one, Numeral.two});
	    bc3 = new Barcode(new Numeral[] {Numeral.three});
	       
	    item1 = new BarcodedItem(bc1, weight1);
	    item2 = new BarcodedItem(bc2, weight2);
	    item3 = new BarcodedItem(bc3, weight3);
	       
	    prod1 = new BarcodedProduct(bc1, "Product 1", price1, weight1);
	    prod2 = new BarcodedProduct(bc2, "Product 2", price2, weight2);
	       
	    ProductDatabases.BARCODED_PRODUCT_DATABASE.put(bc1, prod1);
	    ProductDatabases.BARCODED_PRODUCT_DATABASE.put(bc2, prod2);
	    
	    Card membership_card1 = new Card("Membership","99999999", "John Member-Card", "000", "000", false, false);
	    cards.add(membership_card1);
	    MembershipDatabase.MEMBER_DATABASE.put(99999999,"John Member-Card");
	    
	    station = new DoItYourselfStation();
	    station.plugIn();
	    station.turnOn();
	    PowerGrid.instance().forcePowerRestore();
		PowerGrid.engageUninterruptiblePowerSource();
		
	    ui = new CustomerUI(station, "Station 1");
	    ui.beginSession();
	    
	    cartController = new CartController(station);
	    
	    sil = new ScanItemListener(station.mainScanner, cartController);
	    station.mainScanner.register(sil);
	    
	    customer = new Customer();
	    customer.useStation(station);
	}
	
	/*
	 * Test when the scanner is disabled and tries to scan a barcode.
	 */
	@Test
	public void testScanItemScannerDisabled() {
		station.mainScanner.disable();
		
		customer.shoppingCart.add(item1);
		customer.selectNextItem();
		customer.scanItem(false);
		customer.placeItemInBaggingArea();
		
		assertFalse(cartController.productList.containsProduct(prod1));
		assertEquals(cartController.productList.size(), 0);
	}
	
	/*
	 * Test when a valid barcode is scanned.
	 */
   	@Test
	public void testScanItemValidBarcode() { 
   		station.mainScanner.enable();
   		
   		customer.shoppingCart.add(item1);
   		customer.selectNextItem();
   		customer.scanItem(false);
   		customer.placeItemInBaggingArea();
   		
   		assertTrue(cartController.productList.containsProduct(prod1));
   		assertEquals(1, cartController.productList.size());
   	}
   	
   	/*
   	 * Test when scanning an item with no barcode.
   	 */
   	@Test
   	public void testScanItemNoBarcode() {
   		station.mainScanner.enable();
   		
        customer.shoppingCart.add(item3);
        customer.selectNextItem();
        customer.scanItem(false);
        customer.placeItemInBaggingArea();
        
        assertEquals(0, cartController.productList.size());
   	}
}
