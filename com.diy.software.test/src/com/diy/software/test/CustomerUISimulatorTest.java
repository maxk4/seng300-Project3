package com.diy.software.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.DoItYourselfStation;
import com.diy.simulation.Customer;

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
		customer = new Customer();
		sim = new Simulation();
		station = new DoItYourselfStation();
		customerSim = new CustomerUISimulator(station, customer, "Test");
	}
	@After
	public void tearDown() {
		customer = null;
		station = null;
		customerSim = null;
	}
	
	@Test (expected = NullPointerSimulationException.class)
	public void testScanCardButtonNullClick() {
		String[] args = new String[] {"1"};
		sim.main(args);
		customerSim.button_ScanCard.doClick();
	}
	
	@Test (expected = InvalidArgumentSimulationException.class)
	public void testButtonNullClick() {
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
	
