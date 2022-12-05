import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import ca.powerutility.NoPowerException;
import ca.powerutility.PowerGrid;
import ca.powerutility.PowerSurge;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.AttendantStation;
import com.diy.hardware.DoItYourselfStation;
import com.jimmyselectronics.EmptyException;
import com.jimmyselectronics.OverloadException;
import com.jimmyselectronics.abagnale.ReceiptPrinterND;


import ui.AttendantUI;
import ui.CustomerUI;
import printing.PrinterController;
import printing.PrinterListener;

import javax.swing.*;


public class LowInkPaperTest {
	
	private List<PrinterListener> listeners = new ArrayList<PrinterListener>();
	public ReceiptPrinterND printer = new ReceiptPrinterND();
	
	public static final int MAXIMUM_PAPER = 10;
	public static final int MAXIMUM_INK = 20;

	PrinterController printController; //previously lowinklowpaper, middle between customer and actual printer
	DoItYourselfStation station;
	CustomerUI customer;
	String title = "Low Ink/ Low Paper Tests";

	//listeners.add(new PrintListener());
	
	//java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();
	
	@Before
	public void setup() {
		station = new DoItYourselfStation();

		PowerGrid.disconnect();
		PowerGrid.engageUninterruptiblePowerSource();

		station.plugIn();
		station.turnOn();

		//station.printer.enable();




		customer = new CustomerUI(station, title);
		AttendantUI attendant = new AttendantUI(new AttendantStation(), 1);



		printController = new PrinterController(station) {
			@Override
			public void abortPrinting() {
				System.out.println("Abort Printing");
			}
		};
		printController.enabled(station.printer);
		printController.turnedOn(station.printer);
		//register the print listener
		station.printer.register(printController);
		//System.setOut(new java.io.PrintStream(output));
		
	}
	
	@After
	public void finish() {
		try {
			customer.endSession();
			station.turnOff();
			printController.disabled(station.printer);
			printController.turnedOff(station.printer);
		} catch(Exception e) {
			
		}
	}


	/**
	 * Test that when printing is turned off, should throw an exception
	 * @throws NoPowerException
	 */
	@Test (expected = NoPowerException.class)
	public void testNoPower() throws NoPowerException, OverloadException {
		//Adding Paper and ink to the printer hardware, should result in an exception
		station.printer.turnOff();
		station.printer.addPaper(MAXIMUM_PAPER);
	}

	/**
	 * Test that when printing is turned off, should throw an exception
	 * @throws OverloadException
	 */
	@Test
	public void testOverload() throws NoPowerException, OverloadException {
		//Adding Paper and ink to the printer hardware, should result in an exception
		station.printer.addPaper(MAXIMUM_PAPER*2);
		station.printer.addInk(ReceiptPrinterND.CHARACTERS_PER_LINE + 5);
		printController.print(String.valueOf("AA" + "A".repeat(ReceiptPrinterND.CHARACTERS_PER_LINE + 1)));
		assertTrue(printController.getIsOverload());
	}


	/**
	 * Test that when printing with low ink, the listener notices
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testLowInk() throws EmptyException, OverloadException {
		//Adding Paper and ink to the printer hardware
		station.printer.addPaper(MAXIMUM_PAPER);
		station.printer.addInk(1);

		//Trying to print
		printController.print("A");
		
		assertTrue(printController.getLowInk());

	}
	
	/**
	 * Test that when printing with low paper, the listener notices
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testLowPaper() throws EmptyException, OverloadException {
		station.printer.addInk(MAXIMUM_INK);
		station.printer.addPaper(1);
		//printing one line on paper
		printController.print("A\nA");
		
		assertTrue(printController.getLowPaper());
	}
	
	/**
	 * Test that an empty exception is thrown when printer prints with no ink
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testOutOfInkThrowsException() throws EmptyException, OverloadException {
		station.printer.addPaper(MAXIMUM_PAPER);
		try {
			//printer.print('A');
			printController.print("\n");
		} catch (Exception e) {
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
		station.printer.addInk(MAXIMUM_INK);
		try {
			//printer.print('A');
			printController.print("A\n");
		} catch (Exception e) {
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
		station.printer.addInk(MAXIMUM_INK);
		station.printer.addPaper(1);
		//printer.print('\n');
		printController.print("A\n");

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
		station.printer.addPaper(MAXIMUM_PAPER);
		station.printer.addInk(1);
		//printer.print('A');
		printController.print("A");
		
		assertTrue(printController.getNoInk());
	}
}
