
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import com.diy.hardware.DoItYourselfStationAR;
import com.diy.simulation.Customer;
import com.diy.hardware.external.CardIssuer;
import ca.ucalgary.seng300.simulation.SimulationException;
import util.AttendantStation;
import util.AttendantUI;
import util.CustomerUI;
import util.PayWithCardListener;

import com.jimmyselectronics.opeechee.Card;
import com.jimmyselectronics.opeechee.CardReader;

public class PayByCreditCardTest {
	
	Card creditCard;
	Card notIssuedCredit;
	AttendantUI attendant;
	Customer customer;
	AttendantStation astation;
	DoItYourselfStationAR station;
	CustomerUI ui;
	PayWithCardListener pwcl = new PayWithCardListener(ui);
	CardIssuer cardIssuer;
	CardReader reader;
	CardReaderListenerStub listener;


	
	
	/**
	 * Initializes credit card
	 */
	@Before
	public void setup() {
		creditCard = new Card("credit", "8417992603318971", "Isaacs", "276", "0000", true, true);
		listener = new CardReaderListenerStub();
		
	}
	
	//Checks that null credit card throws exception
	@Test
	public void NullCreditCard() {
		try {
			creditCard = new Card(null, "8417992603318971", "Isaacs", "276", "0000", true, true);
			fail("Expected exception: Credit Card is null.");
		}
		catch (Exception e){
			assertTrue(e instanceof SimulationException);
		}
		
	}
	//Checks that a valid credit card can be tapped
	@Test
	public void TapCreditCard() {
		creditCard = new Card("credit", "8417992603318971", "Isaacs", "276", "0000", true, true);
		assertTrue(creditCard.isTapEnabled);
	}
	//Checks that a valid credit card can be inserted
	@Test
	public void InsertCreditCard() {
		creditCard = new Card("credit", "8417992603318971", "Isaacs", "276", "0000", true, true);
		// Try inserting the card into a card reader stub
		reader = new CardReader();
		reader.plugIn();
		reader.turnOn();
		reader.enable();
		reader.register(listener);
		try {
			reader.insert(creditCard, "0000");
			assertTrue(listener.inserted);
		} catch (IOException e) {
			fail("Cound not insert card");
		}
	}
	//Checks that a valid credit card can be swiped
	@Test
	public void SwipeCreditCard() {
		creditCard = new Card("credit", "8417992603318971", "Isaacs", "276", "0000", true, true);
		//assertTrue(creditCard);
		// Is this necessary?
	}
	
	//Checks that a credit card with incorrect pin throws exception when inserted
	@Test
	public void InvalidPinCreditCard() {
		try {
			creditCard = new Card(null, "8417992603318971", "Isaacs", "276", "1111", true, true);
			fail("Invalid Pin");
		}
		catch (Exception e){
			assertTrue("Invalid Pin", e instanceof SimulationException);
		}
		
	}
	
	
}

