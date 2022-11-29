import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.DoItYourselfStation;
import com.jimmyselectronics.EmptyException;
import com.jimmyselectronics.OverloadException;
import com.jimmyselectronics.abagnale.ReceiptPrinterND;

import util.AttendantStation;
import util.AttendantUI;
import util.CustomerUI;
import util.LowInkLowPaper;


public class LowInkPaperTest {
	
	public ReceiptPrinterND printer = new ReceiptPrinterND();
	
	public static final int MAXIMUM_PAPER = 10;
	public static final int MAXIMUM_INK = 20;

	LowInkLowPaper listener;
	DoItYourselfStation station;
	CustomerUI customer;
	
	java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();
	
	@Before
	public void setup() {
		station = new DoItYourselfStation();
		station.plugIn();
		station.turnOn();
		customer = new CustomerUI(station);
		AttendantUI attendant = new AttendantUI(new AttendantStation(), 1);
		
		listener = new LowInkLowPaper(customer, attendant) {
			@Override
			public void abortPrinting() {}
		};
		printer.plugIn();
		printer.turnOn();
		printer.register(listener);
		
		System.setOut(new java.io.PrintStream(output));
		
	}
	
	@After
	public void finish() {
		try {
			customer.endSession();
		} catch(Exception e) {
			
		}
	}
	
	/**
	 * Test that when printing with low ink, the listener notices
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testLowInk() throws EmptyException, OverloadException {
		printer.addPaper(MAXIMUM_PAPER);
		printer.addInk(20);
		printer.print('A');
		
		assertTrue(listener.getLowInk());
	}
	
	/**
	 * Test that when printing with low paper, the listener notices
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testLowPaper() throws EmptyException, OverloadException {
		printer.addInk(MAXIMUM_INK);
		printer.addPaper(2);
		printer.print('A');
		
		assertTrue(listener.getLowPaper());
	}
	
	/**
	 * Test that an empty exception is thrown when printer prints with no ink
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testOutOfInkThrowsException() throws EmptyException, OverloadException {
		printer.addPaper(MAXIMUM_PAPER);
		try {
			printer.print('A');	
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
		printer.addInk(MAXIMUM_INK);
		try {
			printer.print('A');
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
		printer.addInk(MAXIMUM_INK);
		printer.addPaper(1);
		printer.print('\n');

		assertTrue(listener.getNoPaper());
	}
	
	/**
	 * Test that the listener notices when printer runs out of ink
	 * 
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	@Test
	public void testOutOfInkTriggersListener() throws EmptyException, OverloadException {
		printer.addPaper(MAXIMUM_PAPER);
		printer.addInk(1);
		printer.print('A');
		
		assertTrue(listener.getNoInk());
	}
}
