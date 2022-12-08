package com.diy.software.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simulation.Simulation;

public class SimulationTest {
	Simulation simulation;
	@Before
	public void setUp() {
		simulation = new Simulation();
	}
	@After
	public void tearDown() {
		simulation = null;
	}
	@Test
	public void testSimulation() {
		String [] args = new String[] {};
		//Test fail cases
		simulation.main(args);
		assertNull(simulation.attendant);
		assertNull(simulation.currentCustomer);
	}
	@Test
	public void properSimulationTest() {
		String [] args = new String[] {"1"};
		simulation.main(args);
		assertFalse(simulation.attendant == null);
		assertFalse(simulation.currentCustomer == null);
	}
}
