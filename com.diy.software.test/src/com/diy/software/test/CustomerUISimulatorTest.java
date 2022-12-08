package com.diy.software.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.DoItYourselfStation;
import com.diy.simulation.Customer;

import ca.powerutility.NoPowerException;
import ca.powerutility.PowerGrid;
import ca.ucalgary.seng300.simulation.InvalidArgumentSimulationException;
import ca.ucalgary.seng300.simulation.NullPointerSimulationException;
import simulation.CustomerUISimulator;
import simulation.Simulation;
import util.MembershipDatabase;

public class CustomerUISimulatorTest {
	CustomerUISimulator customerSim;
	Simulation sim;
	DoItYourselfStation station;
	Customer customer;

	
	@Before
	public void setUp() {
		PowerGrid.engageUninterruptiblePowerSource();
		customer = new Customer();
		sim = new Simulation();
		station = new DoItYourselfStation();
		station.turnOn();
		customerSim = new CustomerUISimulator(station, customer, "Test");
		
	}
	@After
	public void tearDown() {
		customer = null;
		station = null;
		customerSim = null;
	}
	
	@Test (expected = NoPowerException.class)
	public void testScanCardButtonNullClick() {
		String[] args = new String[] {"1"};
		sim.main(args);
		station.turnOn();
		customerSim.button_ScanCard.doClick();
	}
	
	@Test (expected = NullPointerException.class)
	public void testButtonNullClickNoPower() {
		String[] args = new String[] {"1"};
		sim.main(args);
		customerSim.button.doClick();
	}
	
	/**
	@Test (expected = TooMuchCashException.class)
	public void testButtonNullClick() {
		String[] args = new String[] {"1"};
		sim.main(args);
		customerSim.button.doClick();
	}*/
	
}
	
