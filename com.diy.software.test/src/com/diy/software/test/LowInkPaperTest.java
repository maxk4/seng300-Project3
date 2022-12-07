package com.diy.software.test;

import static org.junit.Assert.assertTrue;

import ca.powerutility.NoPowerException;
import ca.powerutility.PowerGrid;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.DoItYourselfStation;
import com.jimmyselectronics.EmptyException;
import com.jimmyselectronics.OverloadException;
import com.jimmyselectronics.abagnale.ReceiptPrinterND;

import ui.CustomerUI;
import printing.PrinterController;

/** @authors Simrat and Charvi
 * Test suite for the Printer Controller (Low Ink and Low Paper Use Cases)
 */


public class LowInkPaperTest {
	
	public ReceiptPrinterND printer = new ReceiptPrinterND();
	
	public static final int MAXIMUM_PAPER = 10;
	public static final int MAXIMUM_INK = 20;

	PrinterController printController; 
	DoItYourselfStation station;
	CustomerUI customer;
	String title = "Low Ink/ Low Paper Tests";

	
	@Before
	public void setup() {
		// Initialize the station object
		station = new DoItYourselfStation();

		// Turn the station on
		PowerGrid.disconnect();
		PowerGrid.engageUninterruptiblePowerSource();

		station.plugIn();
		station.turnOn();

		// Initialize the customer
		customer = new CustomerUI(station, title);

		printController = new PrinterController(station) {
			@Override
			public void abortPrinting() {
				System.out.println("Abort Printing");
			}
		};
		station.printer.register(printController);
		// Turn the printer on
		station.printer.enable();
		station.printer.turnOn();
		
	}
	
	@After
	public void finish() {
		try {
			// End the customer session and turn the station off
			customer.endSession();
			station.printer.disable();
			station.printer.turnOff();
			station.turnOff();

		} catch(Exception e) {
			
		}
	}


	/**
	 * Test that when printing is turned off, an exception is thrown
	 * @throws NoPowerException
	 */
	@Test (expected = NoPowerException.class)
	public void testNoPower() throws NoPowerException, OverloadException {
		//Adding Paper to the printer hardware, should result in an exception
		station.printer.turnOff();
		station.printer.addPaper(MAXIMUM_PAPER);
	}

	
	/**
	 * Test that an empty exception is thrown when printer prints with no ink
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testOutOfInkThrowsException() throws EmptyException, OverloadException {
		//Adding Paper to the printer hardware
		station.printer.addPaper(MAXIMUM_PAPER);
		try {
			printController.print("\n");
		} catch (Exception e) {
			//Exception expected
			assertTrue(e instanceof EmptyException);
		}
	}
	
	/**
	 * Test that an empty exception is thrown when printer prints with no paper
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testOutOfPaperThrowsException() throws EmptyException, OverloadException {
		//Adding Ink to the printer hardware
		station.printer.addInk(MAXIMUM_INK);
		try {
			printController.print("A\n");
		} catch (Exception e) {
			//Exception expected
			assertTrue(e instanceof EmptyException);
		}
	}
	
	/**
	 * Test that the listener notices when printer runs of of paper
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testOutOfPaperTriggersListener() throws EmptyException, OverloadException {
		//Adding Paper and Ink to the printer hardware
		station.printer.addInk(MAXIMUM_INK);
		station.printer.addPaper(1);
		printController.print("A\n");

		//Returns true if no paper
		assertTrue(printController.getNoPaper());
	}
	
	/**
	 * Test that the listener notices when printer runs out of ink
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testOutOfInkTriggersListener() throws EmptyException, OverloadException {
		//Adding Paper and Ink to the printer hardware
		station.printer.addPaper(MAXIMUM_PAPER);
		station.printer.addInk(1);
		printController.print("A");
		//Returns true if no ink	
		assertTrue(printController.getNoInk());
	}
	/**
	 * Test overload of printer
	 * @throws OverloadException
	 */
	@Test
	public void testOverload() throws NoPowerException, OverloadException {
		//adding more lines in paper, will thorw overload exception
		station.printer.addPaper(MAXIMUM_PAPER*2);
		station.printer.addInk(ReceiptPrinterND.CHARACTERS_PER_LINE + 5);
		try
		{
			printController.print(String.valueOf("A" .repeat(ReceiptPrinterND.CHARACTERS_PER_LINE + 1)));
			//Returns true if overloaded
		}catch (Exception e){
			assertTrue(e instanceof OverloadException);
		}


	}
}
