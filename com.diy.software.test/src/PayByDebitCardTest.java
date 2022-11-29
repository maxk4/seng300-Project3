
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.DoItYourselfStationAR;
import com.diy.hardware.external.CardIssuer;

import com.jimmyselectronics.opeechee.Card;
import com.jimmyselectronics.opeechee.Card.CardData;
import com.jimmyselectronics.opeechee.CardReader;
import com.jimmyselectronics.opeechee.CardReaderListener;

import ca.powerutility.PowerGrid;
import ca.ucalgary.seng300.simulation.InvalidArgumentSimulationException;
import util.Bank;
import util.CustomerUI;
import util.ExpectedWeightListener;
import util.PayWithCardListener;

public class PayByDebitCardTest {
	
	Card debitCard;
	Card creditCard;
	Card invalidCard;
	CustomerUI customer;
	DoItYourselfStationAR station;
	PayWithCardListener payWithCard;
	CardReader reader;
	CardIssuer bank;
	ExpectedWeightListener expectedWeightListener;
	CardData data;
	Calendar expiry = Calendar.getInstance();
	
	private final ByteArrayOutputStream content = new ByteArrayOutputStream();
	private final PrintStream original = System.out;
	
	@Before
	public void setup() {
		PowerGrid.engageUninterruptiblePowerSource();
		expiry.set(2023, 8, 23);
		bank = Bank.CARD_ISSUER;
		debitCard = new Card("debit", "1234567890123456", "John Smith", "123", "0000", true, true);
		creditCard = new Card("credit", "2234567890123456", "John Smith", "111", "0000", true, true);
		invalidCard = new Card("invalid", "1234567890123456", "John Smith", "222", "0000", true, true);
		
		expectedWeightListener = new ExpectedWeightListener(customer);
		station = new DoItYourselfStationAR();
		
		station.plugIn();
		station.turnOn();
		customer = new CustomerUI(station);
		payWithCard = new PayWithCardListener(customer);
		station.cardReader.register(payWithCard);
		reader = station.cardReader;
		System.setOut(new PrintStream(content));
		
		reader.plugIn();
		reader.turnOn();
		reader.enable();
	}
	
	@After
	public void teardown() {
		System.setOut(original);
	}
	
	/*
	 * Tests when the kind of card is an invalid kind.
	 */
	@Test (expected = InvalidArgumentSimulationException.class)
	public void testInvalidCard() throws IOException {
		data = reader.insert(invalidCard, "0000");
		payWithCard.transactionWithCreditCard(reader, data, bank, 25);
	}
	
	/*
	 * Tests for a successful transaction with a debit card.
	 */
	@Test
	public void testSuccessfulTransactionWithDebit() throws IOException {
		bank.addCardData("1234567890123456", "John Smith", expiry, "123", 150);
		String expected = "The transaction was successful\r\n";
		customer.payWithDebit();
		customer.setBalance(10);
		data = reader.insert(debitCard, "0000");
		//payWithCard.transactionWithCreditCard(reader, data, bank, 25);
		
		assertEquals(expected, content.toString());
	}
	
	/*
	 * Tests for a successful transaction with a credit card.
	 */
	@Test
	public void testSuccessfulTransactionWithCredit() throws IOException {
		bank.addCardData("2234567890123456", "John Smith", expiry, "111", 150);
		String expected = "The transaction was successful\r\n";
		customer.payWithCredit();
		customer.setBalance(10);
		data = reader.insert(creditCard, "0000");
		
		//payWithCard.transactionWithCreditCard(reader, data, bank, 45);
		
		assertEquals(expected, content.toString());
	}
	
	/*
	 * Tests for a failed transaction with a credit card.
	 */
	@Test
	public void testFailedTransactionWithCredit() throws IOException {
		String expected = "The hold failed\r\n";
		data = reader.insert(creditCard, "0000");
		//payWithCard.transactionWithCreditCard(reader, data, bank, 300);
		assertEquals(expected, content.toString());
	}
	
	/*
	 * Tests for a failed transaction with a debit card.
	 */
	@Test 
	public void testFailedTransactionWithDebit() throws IOException {
		String expected = "The hold failed\r\n";
		data = reader.insert(debitCard, "0000");
		//payWithCard.transactionWithCreditCard(reader, data, bank, 200);
		assertEquals(expected, content.toString());
	}
	
	/*
	 * Tests the listener for when a card is tapped.
	 */
	@Test
	public void testCardTapped() throws IOException {
		String expected = "The hold failed\r\n";
		reader.tap(creditCard);
		//payWithCard.cardTapped(reader);
		assertEquals(expected, content.toString());
	}
	
	/*
	 * Tests the listener for when a device is enabled.
	 */
	@Test
	public void testEnabled() {
		payWithCard.enabled(reader);
		assertFalse(reader.isDisabled());
	}
	
	/*
	 * Tests the listener for when a device is disabled.
	 */
	@Test
	public void testDisabled() {
		reader.disable();
		payWithCard.disabled(reader);
		assertTrue(reader.isDisabled());
	}
	
	/*
	 * Tests the listener for when a card is inserted.
	 */
	@Test
	public void testCardInserted() throws IOException {
		payWithCard.cardInserted(reader);
		assertTrue(payWithCard.cardInserted);
	}
	
	/*
	 * Tests the listener for when a card is removed.
	 */
	@Test
	public void testCardRemoved() {
		payWithCard.cardRemoved(reader);
		assertFalse(payWithCard.cardInserted);
	}
}
