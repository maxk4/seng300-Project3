package com.diy.software.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.external.ProductDatabases;
import com.diy.simulation.Customer;
import com.jimmyselectronics.AbstractDevice;
import com.jimmyselectronics.AbstractDeviceListener;
import com.jimmyselectronics.OverloadException;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
import com.jimmyselectronics.virgilio.ElectronicScale;
import com.jimmyselectronics.virgilio.ElectronicScaleListener;

import ca.powerutility.PowerGrid;
import scale.ExpectedWeightListener;
import scale.ScaleController;
import scale.ScaleListener;
import scale.ScanningAreaListener;
import ui.CustomerUI;

/**
 * Test suite for the scale package.
 * @author Matthias Kee
 */
public class ScaleTests {
	
	Customer customer;
	CustomerUI ui;
	DoItYourselfStation station;
	Barcode bc1, bc2, bc3, nullBarcode;
	BarcodedItem lightItem, normalItem, heavyItem, nullItem;
	BarcodedProduct lightProduct, normalProduct, heavyProduct;
	ScaleController controller;
	ScanningAreaListener sal;
	ExpectedWeightListener ewl;
	ScaleListener scaleListener;
	ElectronicScaleListener ESLListener;
	long price1 = 10L, price2 = 15L;
	double lightWeight = 0.2, normalWeight = 4.0, heavyWeight = 10.0;
	
	/*
	 * Setup for the test suite.
	 */
	@Before 
	public void setup() {
		ProductDatabases.BARCODED_PRODUCT_DATABASE.clear();
		
	    bc1 = new Barcode(new Numeral[] {Numeral.one});
	    bc2 = new Barcode(new Numeral[] {Numeral.one, Numeral.two});
	    bc3 = new Barcode(new Numeral[] {Numeral.three});
	       
	    lightItem = new BarcodedItem(bc1, lightWeight);
	    normalItem = new BarcodedItem(bc2, normalWeight);
	    heavyItem = new BarcodedItem(bc3, heavyWeight);
	       
	    lightProduct = new BarcodedProduct(bc1, "Light", price1, lightWeight);
	    normalProduct = new BarcodedProduct(bc2, "Normal", price2, normalWeight);
	    heavyProduct = new BarcodedProduct(bc3, "Heavy", price2, heavyWeight);
	       
	    ProductDatabases.BARCODED_PRODUCT_DATABASE.put(bc1, lightProduct);
	    ProductDatabases.BARCODED_PRODUCT_DATABASE.put(bc2, normalProduct);
	    ProductDatabases.BARCODED_PRODUCT_DATABASE.put(bc3, heavyProduct);
	    
	    scaleListener = new SL();
	    ESLListener = new ESL();
	   
	    station = new DoItYourselfStation();
	    DoItYourselfStation.configureScaleMaximumWeight(5);
	    station.plugIn();
	    station.turnOn();
	    PowerGrid.instance().forcePowerRestore();
		PowerGrid.engageUninterruptiblePowerSource();
		
	    ui = new CustomerUI(station, "Station 1");
	    ui.beginSession();
	    
	    controller = new ScaleController(station);
	    
	    sal = new ScanningAreaListener();
	    ewl = new ExpectedWeightListener(controller);
	    
	    station.baggingArea.register(ewl);
	    station.baggingArea.register(sal);
	    
	    customer = new Customer();
	    customer.useStation(station);
	}
	
	/*
	 * Test when the weight on the scale has changed.
	 */
	@Test
	public void testWeightChanged() {
		station.baggingArea.enable();
		
		station.baggingArea.add(normalItem);
		
		assertEquals(normalWeight, sal.getWeight(), 0);
		
	}
	
	/*
	 * Test when the scale is disabled.
	 */
	@Test
	public void testDisabledScale() {
		station.baggingArea.disable();
		station.baggingArea.add(normalItem);
		
		assertEquals(0, sal.getWeight(), 0);
	}
	
	/*
	 * Test when a weight discrepancy is detected.
	 */
	@Test
	public void testWeightDiscrepancyDetected() {
		controller.register(scaleListener);
		station.baggingArea.enable();
		
		station.baggingArea.add(heavyItem);
		
		assertEquals(heavyWeight, sal.getWeight(), 0.01);
		assertEquals(2, found);
	}
	
	/*
	 * Test when the scale enters the overloaded state.
	 */
	@Test
	public void testOverload() {
		station.baggingArea.register(ESLListener);
		station.baggingArea.enable();
		station.baggingArea.add(heavyItem);
		assertEquals(2, found);
	}
	
	/*
	 * Test when the scale stops being in the overload state.
	 */
	@Test
	public void testOutOfOverload() {
		station.baggingArea.register(ESLListener);
		station.baggingArea.enable();
		station.baggingArea.add(heavyItem);
		station.baggingArea.remove(heavyItem);
		
		assertEquals(3, found);
	}
	
	/*
	 * Test when a force resolve of a weight discrepancy happens.
	 */
	@Test
	public void testApproveWeightDiscrepancy() {
		controller.register(scaleListener);
		station.baggingArea.enable();
		station.baggingArea.add(heavyItem);
		
		controller.approveWeight();
		assertEquals(3, found);
	}
	
	/*
	 * Test when setting expected weight results in the total weight being greater than the sensitivity.
	 */
	@Test 
	public void testSetExpectedWeightGreaterThanSensitivity() {
		controller.register(scaleListener);
		station.baggingArea.enable();
		controller.setExpectedWeight(normalWeight);
		
		station.baggingArea.add(normalItem);
		assertEquals(3, found);
	}
	
	/*
	 * Test when setting the expected weight results in the total weight being less than the sensitivity.
	 */
	@Test
	public void testSetExpectedWeightLessThanSensitivity() {
		controller.register(scaleListener);
		station.baggingArea.enable();
		station.baggingArea.add(normalItem);
		ewl.setSensitivity(2);
	
		ewl.setExpectedWeight(3);
		assertEquals(3, found);
	}
	
	/*
	 * Test for updating the expected weight.
	 */
	@Test 
	public void testUpdateExpectedWeight() {
		controller.register(scaleListener);
		station.baggingArea.enable();
		controller.updateExpectedWeight(normalWeight);
		station.baggingArea.add(normalItem);
		
		assertEquals(3, found);
	}
	
	/*
	 * Test removing last item weight.
	 */
	@Test 
	public void testRemoveLastItemWeight() throws OverloadException {
		station.baggingArea.add(normalItem);
		
		controller.removeLastItemWeight();
		
	}
	
	/*
	 * Test for registering a listner.
	 */
	@Test
	public void testRegisterListener() {
		assertTrue(controller.register(scaleListener));
		assertFalse(controller.register(scaleListener));
		
	}
	
	/*
	 * Test for deregistering a listener.
	 */
	@Test
	public void testDeregisterListener() {
		controller.register(scaleListener);
		assertTrue(controller.deregister(scaleListener));
		SL listener2 = new SL();
		assertFalse(controller.deregister(listener2));
	}
	
	int found = 0;
	boolean weightChanged = false;
	boolean enabled = false;
	boolean isOn = false;
	
	public class SL implements ScaleListener {

		@Override
		public void notifyWeightDiscrepancyDetected() {
			found++;
			
		}

		@Override
		public void notifyWeightDiscrepancyResolved() {
			found++;
			
		}
		
	}
	
	public class ESL implements ElectronicScaleListener {

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
		public void weightChanged(ElectronicScale scale, double weightInGrams) {
			found++;
			
		}

		@Override
		public void overload(ElectronicScale scale) {
			found++;
			
		}

		@Override
		public void outOfOverload(ElectronicScale scale) {
			found++;
			
		}
		
	}

}
