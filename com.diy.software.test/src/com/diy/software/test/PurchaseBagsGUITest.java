package com.diy.software.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.DoItYourselfStation;

import simulation.Simulation;
import ui.CustomerUI;
import ui.views.customer.PurchaseBagsGUI;

public class PurchaseBagsGUITest {
	
	PurchaseBagsGUI gui;
	CustomerUI customerUI;
	DoItYourselfStation station;
	
	@Before
	public void setup() {
		station = new DoItYourselfStation();
		station.plugIn();
		station.turnOn();
		customerUI = new CustomerUI(station, "station");
		gui = new PurchaseBagsGUI(customerUI);

	}
	
	@Test
	public void testButtons() {
		gui.button_1.doClick();
		gui.button_2.doClick(); 
		gui.button_3.doClick();
		gui.button_4.doClick();
		gui.button_5.doClick();
		gui.button_6.doClick();
		gui.button_7.doClick();
		gui.button_8.doClick();
		gui.button_9.doClick();
		gui.button_Decimal.doClick();
		gui.button_0.doClick();
		assertTrue("123456789.0".equals(gui.textField_NumberOfBags.getText()));		
		for (int i = 0; i < 3; i++) {
			gui.button_Del.doClick();			
		}
		assertTrue("12345678".equals(gui.textField_NumberOfBags.getText()));		
		gui.button_Enter.doClick();
	}
	
}
