package com.diy.software.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.Product;
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
import cart.CartController;
import cart.CartListener;
import cart.ScanItemListener;
import ui.CustomerUI;
import util.MembershipDatabase;
import util.ProductInfo;

/**
 * Test suite for the cart package. 
 * @author Matthias Kee
 */
public class AddItemTests {
	
	Customer customer;
	CustomerUI ui;
	DoItYourselfStation station;
	CartController cartController;
	ScanItemListener sil;
	Barcode bc1, bc2, bc3;
	BarcodedItem item1, item2, item3;
	BarcodedProduct prod1, prod2;
	CartListener listener;
	BarcodeScannerListener bcListener;
	
	public List<Card> cards = new ArrayList<Card>();
	long price1 = 10L, price2 = 15L;
	double weight1 = 1.3, weight2 = 5.2, weight3 = 3.2;
	
	private final ByteArrayOutputStream content = new ByteArrayOutputStream();
	private final PrintStream original = System.out;
	
	/*
	 * Setup for the test suite.
	 */
	@Before
	public void setup() {
		ProductDatabases.BARCODED_PRODUCT_DATABASE.clear();
		   
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
	    
	    listener = new CL();
	    bcListener = new BSL();
	    
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
	 * Tear down of the test suite.
	 */
	@After
	public void teardown() {
		System.setOut(original);
	}
	
	/*
	 * Test when the scanner is disabled and tries to scan a barcode.
	 */
	@Test
	public void testScanItemScannerDisabled() {
		station.mainScanner.register(bcListener);
		station.mainScanner.disable();
		
		sil.barcodeScanned(station.mainScanner, bc1);
		
		assertEquals(0, sil.getSuccessfulScan());
		assertFalse(cartController.productList.containsProduct(prod1));
		assertEquals(cartController.productList.size(), 0);
		assertFalse(enabled);
	}
	
	/*
	 * Test when a valid barcode is scanned.
	 * 
	 * Current Bug: Product list size is expected to contain 1 item, but it contains 3 of the same items.
	 */
   	@Test
	public void testScanItemValidBarcode() { 
   		station.mainScanner.register(bcListener);
   		station.mainScanner.enable();
   		
   		customer.shoppingCart.add(item1);
   		customer.shoppingCart.add(item2);
   		customer.selectNextItem();
   		
   		customer.scanItem(false);
   		customer.placeItemInBaggingArea();
   		
   		assertTrue(cartController.productList.containsProduct(prod2));
   		assertFalse(cartController.productList.containsProduct(prod1));
   		assertEquals(1, scans);
   	}
   	
   	/*
   	 * Test for the number of successful scans.
   	 * 
   	 */
   	@Test
   	public void testNumberOfSuccessfulScans() {
   		station.mainScanner.register(bcListener);
   		station.mainScanner.enable();
   		sil.barcodeScanned(station.mainScanner, bc1);
  
   		int actual = sil.getSuccessfulScan();
   		assertEquals(1, actual);
   		assertEquals(1, scans);
   	}
   	/*
   	 * Test for the size of the product list after adding an item.
   	 * 
   	 */
   	@Test
   	public void testSizeOfProductList() {
   		station.mainScanner.enable();
   		
   		cartController.productList.add(prod1, prod1.getDescription(), price1, weight1);
   		
   		assertEquals(1, cartController.productList.size());
   	}
   	
   	/*
   	 * Test for checking if the scanner has power.
   	 */
   	@Test
   	public void testHasPower() {
   		station.mainScanner.register(bcListener);
   		station.mainScanner.turnOff();
   		assertFalse(isOn);
   		station.mainScanner.turnOn();
   		assertTrue(isOn);
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
   	
   	/*
   	 * Test when getting a scanned barcode as a string.
   	 */
   	@Test
   	public void testBarcodeScannedGetString() {
   		station.mainScanner.enable();
   		
   		sil.barcodeScanned(station.mainScanner, bc1);
   		
   		String expected = bc1.toString();
   		
   		assertEquals(expected, sil.getBarcodeScanned_String());
   	}
   	
   	/*
   	 * Test when a null barcode is scanned.
   	 */
   	@Test (expected = NullPointerException.class)
   	public void testNullBarcodeScanned() {
   		station.mainScanner.enable();
   		
   		sil.barcodeScanned(station.mainScanner, null);
   	}
   	@Test
   	public void testRemove() {
   		cartController.addItem(prod1, prod1.getDescription(), price1, weight1);
   		assertEquals(1, cartController.productList.size());
   		
   		cartController.removeItem(prod1, prod1.getDescription(), price1, weight1);
   		cartController.addItem(prod1, prod1.getDescription(), price1, weight1);
   		cartController.removeItem(prod2, prod2.getDescription(), price2, weight2);
   		assertEquals(1, cartController.productList.size());
   		
   		cartController.removeItem(prod1, prod1.getDescription(), price1, weight1);
   		cartController.removeItem(prod1, prod1.getDescription(), price1, weight1);
   		assertEquals(0, cartController.productList.size());
   		
   		cartController.addItem(prod1, "Not matched", price1, weight1);
   		cartController.removeItem(prod1, prod1.getDescription(), price1, weight1);
   		assertEquals(1,cartController.productList.size());
   		
   		cartController.removeItem(prod1, "Not matched", price1, weight1);
   		cartController.addItem(prod1, prod1.getDescription(), 1, weight1);
   		cartController.removeItem(prod1, prod1.getDescription(), price1, weight1);
   		assertEquals(1, cartController.productList.size());
   		
   		cartController.removeItem(prod1, prod1.getDescription(), 1, weight1);
   		cartController.addItem(prod1, prod1.getDescription(), price1, 2);
   		cartController.removeItem(prod1, prod1.getDescription(), price1, weight1);
   		assertEquals(1, cartController.productList.size());
   	}
 
   	/*
   	 * Test for clearing the items in the productList.
   	 */
	@Test
   	public void testClearProductList() {
   		station.mainScanner.enable();
   		
   		customer.shoppingCart.add(item1);
   		customer.selectNextItem();
   		customer.scanItem(false);
   		customer.placeItemInBaggingArea();
   
   		cartController.clear();
   		
   		assertFalse(cartController.productList.containsProduct(prod1));
   		
   	}
	
	/*
	 * Test for getting a receipt.
	 */
	@Test
	public void testGetReceipt() {
		cartController.addItem(prod1, prod1.getDescription(), price1, weight1);
		
		long total = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("Transaction Complete\n");
		sb.append("--------------------\n");
		sb.append(String.format("%s\t\t$%.2f\n", prod1.getDescription(), prod1.getPrice() / 100d));
		total += prod1.getPrice();
		sb.append("--------------------\n");
		sb.append(String.format("\t\tTotal:\t$%.2f", total / 100d));
		String receipt = sb.toString();
		
		assertEquals(receipt, cartController.getReceipt());
	}
	
	/*
	 * Test for getting the price as a string.
	 */
	@Test
	public void testGetPriceString() {
		cartController.addItem(prod1, prod1.getDescription(), price1, weight1);
		
		StringBuilder priceSB = new StringBuilder();
		priceSB.append(String.format("$%.2f", prod1.getPrice() / 100d));
		priceSB.append("\n");
		String priceString = priceSB.toString();
		
		assertEquals(priceString, cartController.getPriceString());
	}
	
	/*
	 * Test for getting the product description as a string.
	 */
	@Test 
	public void getProductString() {
		cartController.addItem(prod1, prod1.getDescription(), price1, weight1);
		
		StringBuilder productSB = new StringBuilder();
		
		productSB.append(prod1.getDescription());
		productSB.append("\n");
		String productString = productSB.toString();
		assertEquals(productString, cartController.getProductString());
	}
	
	/*
	 * Test for getting the information of a product.
	 */
	@Test
	public void testGetProductInfo() {
		cartController.productList.add(prod1, prod1.getDescription(), price1, weight1);
		ProductInfo[] information = new ProductInfo[cartController.productList.size()];
		
		information[0] = cartController.productList.get(0);
		assertEquals(information[0], cartController.getProductInfo());
	}
   	
	/*
	 * Test for registering a listener.
	 */
	@Test
	public void testRegisterListener() {
		assertTrue(cartController.register(listener));
		assertFalse(cartController.register(listener));
	}
	
	/*
	 * Test for deregistering a listener.
	 */
	@Test
	public void testDeregisterListener() {
		cartController.register(listener);
		assertTrue(cartController.deregister(listener));
		CartListener listener2 = new CL();
		assertFalse(cartController.deregister(listener2));
	}
	
	/*
	 * Test for getting invalid items in the product list.
	 */
	@Test
	public void testGetProductInvalidIndex() {
		cartController.addItem(prod1, prod1.getDescription(), price1, weight1);
		assertNull(cartController.productList.get(-1));
		assertNull(cartController.productList.get(2));
	}
	
	/*
	 * Test for adding a null product.
	 */
   	@Test (expected = NullPointerException.class)
   	public void testAddingNullProduct() {
   		cartController.addItem(null, "Null Product", price1, weight1);
   		
   	}
   	
   	/*
   	 * Test for adding a product with a null description.
   	 */
   	@Test (expected = NullPointerException.class)
   	public void testAddingProductWithNullDesciption() {
   		cartController.addItem(prod1, null, 0, 0);
   	}
   	
   	
   	int found = 0;
   	public class CL implements CartListener {

		@Override
		public void notifyItemAdded(Product product, long price, double weightInGrams) {
			found++;
			
		}

		@Override
		public void notifyItemRemoved(Product product, long price, double weightInGrams) {
			found++;
			
		}
   		
   	}
   	
   boolean enabled = false;
   boolean isOn = false;
   int scans = 0;
   	public class BSL implements BarcodeScannerListener {

		@Override
		public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
			enabled = true;
		}

		@Override
		public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
			enabled = false;
		}

		@Override
		public void turnedOn(AbstractDevice<? extends AbstractDeviceListener> device) {
			isOn = true;
		}

		@Override
		public void turnedOff(AbstractDevice<? extends AbstractDeviceListener> device) {
			isOn = false;
		}

		@Override
		public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
			scans++;
		}
   		
   	}
}
