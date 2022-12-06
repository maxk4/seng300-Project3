package com.diy.software.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.AttendantStation;
import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.PLUCodedProduct;
import com.diy.hardware.PriceLookUpCode;
import com.diy.hardware.Product;
import com.diy.hardware.external.ProductDatabases;
import com.diy.simulation.Customer;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
import com.jimmyselectronics.opeechee.Card;

import ca.powerutility.PowerGrid;
import cart.CartController;
import cart.CartListener;
import cart.ProductList;
import cart.ScanItemListener;
import main.CustomerStationWrapper;
import ui.AttendantUI;
import ui.CustomerUI;
import ui.CustomerUIListener;
import util.MembershipDatabase;

public class AddItemTests {
	
	Customer customer;
	CustomerUI ui;
	CustomerStationWrapper cswrapper;
	AttendantUI attendantUI;
	AttendantStation attendantStation;
	DoItYourselfStation station;
	CartController cartController;
	ScanItemListener sil;
	Barcode bc1, bc2, bc3, barcodeNullProduct;
	BarcodedItem item1, item2, item3, itemNullProduct;
	BarcodedProduct prod1, prod2, nullProduct;
	PLUCodedProduct productPLU;
	
	public List<Card> cards = new ArrayList<Card>();
	long price1 = 10L, price2 = 15L, priceNullProduct= 5L;
	double weight1 = 1.3, weight2 = 5.2, weight3 = 3.2, weightNullProduct = 3.0;
	int found;
	
	@Before
	public void setup() {
		ProductDatabases.BARCODED_PRODUCT_DATABASE.clear();
		
		found = 0;
		   
	    bc1 = new Barcode(new Numeral[] {Numeral.one});
	    bc2 = new Barcode(new Numeral[] {Numeral.one, Numeral.two});
	    bc3 = new Barcode(new Numeral[] {Numeral.three});
	    barcodeNullProduct = new Barcode(new Numeral[] {Numeral.one, Numeral.two, Numeral.three});
	       
	    item1 = new BarcodedItem(bc1, weight1);
	    item2 = new BarcodedItem(bc2, weight2);
	    item3 = new BarcodedItem(bc3, weight3);
	    itemNullProduct = new BarcodedItem(barcodeNullProduct, weightNullProduct);
	       
	    prod1 = new BarcodedProduct(bc1, "Product 1", price1, weight1);
	    prod2 = new BarcodedProduct(bc2, "Product 2", price2, weight2);
	    productPLU = new PLUCodedProduct(new PriceLookUpCode("1111"), "PLU Product", price1);
	  //  nullProduct = new BarcodedProduct(barcodeNullProduct, "Null Product", priceNullProduct, weightNullProduct);
	    
	    
	    ProductDatabases.BARCODED_PRODUCT_DATABASE.put(bc1, prod1);
	    ProductDatabases.BARCODED_PRODUCT_DATABASE.put(bc2, prod2);
	    //ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcodeNullProduct, );
	    
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
	    
	    attendantStation = new AttendantStation();
	    attendantUI = new AttendantUI(attendantStation, 1);
	    
	    customer = new Customer();
	    customer.useStation(station);
	    cswrapper = new CustomerStationWrapper(station, attendantUI);
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
	 * 
	 * Current Bug: Product list size is expected to contain 1 item, but it contains 3 of the same items.
	 */
   	@Test
	public void testScanItemValidBarcode() { 
   		station.mainScanner.enable();
   		
   		customer.shoppingCart.add(item1);
   		customer.shoppingCart.add(item2);
   		customer.selectNextItem();
   		
   			//causing infinite loop, because sil.getSuccessfulScan() returns 2, when it should return 1.
//   		while(true) {
   			customer.scanItem(false);
//   			if (sil.getSuccessfulScan() == 1) {
//   				break;
//   			}
//   		}
  
   		customer.placeItemInBaggingArea();
   		
   		assertTrue(cartController.productList.containsProduct(prod2));
   		assertFalse(cartController.productList.containsProduct(prod1));
   		assertEquals(1, cartController.productList.size());
   	}
   	
   	/*
   	 * Test for the number of successful scans.
   	 * 
   	 * Current Bug: should return successfulScan to be 1, but it is returning 2.
   	 */
   	@Test
   	public void testNumberOfSuccessfulScans() {
   		station.mainScanner.enable();
   		
   		customer.shoppingCart.add(item1);
   		customer.selectNextItem();
   		customer.scanItem(false);
   		
   		int actual = sil.getSuccessfulScan();
   		
   		assertEquals(1, actual);
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
   
   	@Test
   	public void testAddItemThenRemove() {
   		station.mainScanner.enable();
   		
   		cartController.register(new CartListener() {
			@Override
			public void notifyItemAdded(Product product, long price, double weightInGrams) {
				found++;
			}
			@Override
			public void notifyItemRemoved(Product product, long price, double weightInGrams) {
				found++;
			}
   		});
   		
   		customer.shoppingCart.add(item2);
   		customer.selectNextItem();
   		customer.scanItem(false);
   		customer.placeItemInBaggingArea();
   		
   		assertTrue(cartController.productList.containsProduct(prod2));
   		
   		station.baggingArea.remove(item2);
   		cartController.removeItem(prod2, prod2.getDescription(), prod2.getPrice(), prod2.getExpectedWeight());
   		
   		assertFalse(cartController.productList.containsProduct(prod2));
   		assertEquals(2, found);
   		
   		
   	}
 
	@Test
   	public void testClearProductList() {
   		station.mainScanner.enable();
   		
   		customer.shoppingCart.add(item1);
   		customer.selectNextItem();
   		customer.scanItem(false);
   		customer.placeItemInBaggingArea();
   		
   		assertTrue(cartController.productList.containsProduct(prod1));
   		
   		cartController.clear();
   		
   		assertFalse(cartController.productList.containsProduct(prod1));
   		
   	}
   	
   	@Test (expected = NullPointerException.class)
   	public void testAddingNullProduct() {
   		cartController.addItem(null, "Null Product", price1, weight1);
   		
   	}
   	
   	@Test (expected = NullPointerException.class)
   	public void testAddingProductWithNullDesciption() {
   		cartController.addItem(prod1, null, 0, 0);
   	}
   	
   	@Test 
   	public void testAddByPLUCode() {
   		cartController.register(new CartListener() {
			@Override
			public void notifyItemAdded(Product product, long price, double weightInGrams) {
				found++;	
			}
			@Override
			public void notifyItemRemoved(Product product, long price, double weightInGrams) {
				fail();	
			}
   		});
   		ui.addPLUProduct(productPLU);
   		assertEquals(1, found);
   	}
}
