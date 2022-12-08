package com.diy.software.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.AttendantStation;
import com.diy.hardware.DoItYourselfStation;
import com.diy.simulation.Customer;
import ca.powerutility.PowerGrid;
import printing.PrinterController;
import simulation.MaintenanceSimulator;
import simulation.Simulation;
import ui.AttendantUI;

public class MaintenanceSimulatorTest {
	MaintenanceSimulator maintenanceSim;
	Simulation sim;
	List<DoItYourselfStation> stations;
	DoItYourselfStation station;
	Customer customer;
	AttendantUI attendantUI;
	AttendantStation attendantStation;
	PrinterController printListener;
	
	@Before
	public void setUp() {
		int[] banknoteDenominations = {5000,2000,1000,500};
		BigDecimal[] coinDenominations = {BigDecimal.valueOf(200), BigDecimal.valueOf(100), BigDecimal.valueOf(25), BigDecimal.valueOf(10), BigDecimal.valueOf(5)};
		DoItYourselfStation.configureBanknoteDenominations(banknoteDenominations);
		DoItYourselfStation.configureCoinDenominations(coinDenominations);
		DoItYourselfStation.configureBanknoteDispensationSlot(5);
		PowerGrid.engageUninterruptiblePowerSource();
		
		
		//customer = new Customer();
		sim = new Simulation();
		station = new DoItYourselfStation();
		station.plugIn();
		station.turnOn();
		printListener = new PrinterController(station);
		
		attendantStation = new AttendantStation();
		stations = new ArrayList<DoItYourselfStation>();
		stations.add(station);
		attendantUI = new AttendantUI(attendantStation, 1);
		maintenanceSim = new MaintenanceSimulator(attendantUI, stations);
		
		
		
	}
	@After
	public void tearDown() {
		customer = null;
		station = null;
		maintenanceSim = null;
		
	}
	
	@Test
	public void testMaintenance() {
		String[] args = new String[] {"2"};
		//station.banknoteDispensers.put
		sim.main(args);
		//Test maintenance of coin
		maintenanceSim.coinDenomField.setText("500");
		maintenanceSim.coinAmountField.setText("5");
		maintenanceSim.coinBtn.doClick();
		assertNull(station.coinDispensers.get("500"));

		//Test maintenance of banknote
		maintenanceSim.banknoteDenomField.setText("500");
		maintenanceSim.banknoteAmountField.setText("5");
		maintenanceSim.banknoteBtn.doClick();
		assertNull(station.banknoteDispensers.get("500"));
		
		//Test maintenance of ink
		maintenanceSim.inkAmmountField.setText("20");
		maintenanceSim.inkBtn.doClick();
		assertFalse(printListener.lowInk);
		assertFalse(printListener.noInk);
		
		//Test maintenance of paper
		maintenanceSim.paperAmmountField.setText("10");
		maintenanceSim.paperBtn.doClick();
		assertFalse(printListener.lowPaper);
		assertFalse(printListener.noPaper);
	}
}
	
