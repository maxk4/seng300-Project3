
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ca.powerutility.PowerGrid;
import com.jimmyselectronics.necchi.BarcodeScanner;
import com.jimmyselectronics.opeechee.CardReader;
import membership.MembershipCardListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.diy.hardware.DoItYourselfStation;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
import com.jimmyselectronics.opeechee.Card;
import util.MembershipDatabase;
import membership.MembershipController;
import membership.MembershipListener;


// authors Charvi and Simrat

public class MembershipTestUnit {
	private MembershipControllerStub membershipController;

	Card membership_card1;
	Card membership_card2;
	Card membership_card3;
	Barcode membership_card_barcode1;
	Barcode membership_card_barcode2;
	Barcode membership_card_barcode3;
	DoItYourselfStation station;
	BarcodedItem item1;
	BarcodedItem item2;
	BarcodedItem item3;

	//Counts used in the stubs to make sure card is scanned or swiped right amount of times
	static int barcodedScannedCount = 0;
	static int cardDataRead = 0;
	static int count = 0;

	/**
	 * Set up of each test cases
	 */
	@Before
	public void setup() {

		//Setting up the Membership Database
		MembershipDatabase.MEMBER_DATABASE.put(123456789,"John Doe Customer");
		MembershipDatabase.MEMBER_DATABASE.put(1234567891,"John Doe");
		MembershipDatabase.MEMBER_DATABASE.put(1234567892,"John Doe 2");

		//Create 2 membership card
		membership_card1 = new Card("Membership","99999999", "John Member-Card", "000", "000", false, false);
		membership_card2 = new Card("Membership","88888888", "John OG-Card", "000", "000", false, false);
		membership_card3 = new Card("Membership","88888887", "John Not Member", "000", "000", false, false);

		///Add the membership cards into the membership database
		MembershipDatabase.MEMBER_DATABASE.put(99999999,"John Member-Card");
		MembershipDatabase.MEMBER_DATABASE.put(88888888,"John OG-Card");
		//membership_card3 is not a membership card

		//Create a barcodedItem, so that it can be scanned by barcodeScanner
		//Added in Iteration 3 @Simrat (Start)
		membership_card_barcode1 = new Barcode(new Numeral[]{Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine});
		item1 = new BarcodedItem(membership_card_barcode1, 0.1);
		membership_card_barcode2 = new Barcode(new Numeral[]{Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight});
		item2 = new BarcodedItem(membership_card_barcode2, 0.1);
		membership_card_barcode3 = new Barcode(new Numeral[]{Numeral.four, Numeral.four, Numeral.four, Numeral.four});
		item3 = new BarcodedItem(membership_card_barcode3, 0.1);

		MembershipDatabase.MEMBER_BARCODES.put(membership_card_barcode1, 99999999);
		MembershipDatabase.MEMBER_BARCODES.put(membership_card_barcode2, 88888888);
		//barcode 3 is not added in the database (not a member)

		station = new DoItYourselfStation();
		PowerGrid.disconnect();
		PowerGrid.engageUninterruptiblePowerSource();
		membershipController = new MembershipControllerStub(station);
		membershipController.register(new MembershipListener() {
			@Override
			public void notifyMembershipCardRead(int memberId) {
				//customerUI.useMemberName(memberId);
			}
		});
		station.plugIn();
		station.turnOn();
		station.cardReader.enable();
		station.mainScanner.enable();
		station.handheldScanner.enable();

		barcodedScannedCount = 0;
		cardDataRead = 0;
		count = 0;

	}

	/**
	 * Teardown after each test
	 */
	@After
	public void after()
	{
		station.cardReader.disable();
		station.mainScanner.disable();
		station.handheldScanner.disable();
		station.turnOff();
	}

	/**
	 * Test checks if the member exists
	 */
	@Test
	public void testMemberExist(){
		assertTrue(MembershipDatabase.MEMBER_DATABASE.containsKey(99999999));
	}
	/**
	 * Test checks if the member not exists
	 */
	@Test
	public void testMemberNotExist(){
		assertFalse(MembershipDatabase.MEMBER_DATABASE.containsKey(133));
	}


	/**
	 * uses card reader to scan a membership card, uses listener stub to make sure that card was read and correct data is read
	 * Expected Value = from the database (used to initialise in the setup() method)
	 * Actual Value = taken from the card reader after it swiped the card
	 * Card that is a member is swiped, so the cardDataRead counter will be increased
	 */
	@Test
	public void swipeCardGood(){
		while(true) {
			try {
				cardDataRead = 0;
				Card.CardData data = station.cardReader.swipe(membership_card1);
				assertEquals(membership_card1.cardholder, data.getCardholder());
				assertEquals(1, cardDataRead);
				break;
			} catch (IOException e) {
				//throw new RuntimeException(e);
			}
		}
	}

	/**
	 * uses card reader to scan a membership card, uses listener stub to make sure that card was read and correct data is read
	 * Expected Value = from the database (used to initialise in the setup() method)
	 * Actual Value = taken from the card reader after it swiped the card
	 * Card that is a not a member is swiped, so the cardDataRead counter will not be increased
	 */
	@Test
	public void swipeCardBad(){
		while(true) {
			try {
				cardDataRead = 0;
				Card.CardData data = station.cardReader.swipe(membership_card3);
				//not exists in database
				assertEquals(membership_card3.cardholder, data.getCardholder());
				//we will still read the card but in stub below counter will not increment
				assertEquals(0, cardDataRead);
				break;
			} catch (IOException e) {
				//throw new RuntimeException(e);
			}
		}
	}


	/**
	 * uses mainscanner to scan a membership card, uses listener stub to make sure that card was read and correct data is read
	 */
	@Test
	public void barcodeScannedGood(){
		while(true) {
			if (station.mainScanner.scan(new BarcodedItem(membership_card_barcode1, 0.1)))
			{
				assertEquals(1, barcodedScannedCount);
				//in the stub below, if the scanned barcode is a member, this counter will increment by 1 (expected value)
				break;
			}
			//otherwise scan was not success, do it again
		}
	}
	/**
	 *
	 */
	@Test
	public void barcodeScannedBad(){
		while(true) {
			barcodedScannedCount = 0;
			if (station.mainScanner.scan(new BarcodedItem(membership_card_barcode3, 0.1)))
			{
				//assertEquals(1, count);
				assertEquals(0, barcodedScannedCount);
				//in the stub below, if the scanned barcode is a not member, this counter will not increment (expected value)
				break;
			}


			//otherwise scan was not success, do it again
		}
	}

}
class MembershipControllerStub extends MembershipController
{
	private List<MembershipListener> listeners = new ArrayList<MembershipListener>();

	public MembershipControllerStub(DoItYourselfStation station)
	{
		super(station);
		listeners.clear();
		//this(station);

		MembershipCardListenerStub listener = new MembershipCardListenerStub(this);
		station.cardReader.register(listener);
		station.mainScanner.register(listener);
		station.handheldScanner.register(listener);

	}
}
class MembershipCardListenerStub extends MembershipCardListener
{
	private MembershipController controller;
	public MembershipCardListenerStub(MembershipController controller) {
		super(controller);
		this.controller = controller;
	}
	@Override
	public void cardDataRead(CardReader reader, Card.CardData data) {
		if (!MembershipDatabase.MEMBER_DATABASE.containsKey(Integer.valueOf(data.getNumber()))) return;

		//we reach here if and only if the database contains the card that is swipped
		controller.notifyMemberCardRead(Integer.valueOf(data.getNumber()));
		MembershipTestUnit.cardDataRead++;
	}

	/**
	 * An event announcing that the indicated barcode has been successfully scanned.
	 *
	 * @param barcodeScanner The device on which the event occurred.
	 * @param barcode        The barcode that was read by the scanner.
	 */
	@Override
	public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
		if (!MembershipDatabase.MEMBER_BARCODES.containsKey(barcode)) return;

		//we reach here if and only if barcode is a member of the database
		controller.notifyMemberCardRead(MembershipDatabase.MEMBER_BARCODES.get(barcode));
		System.out.println("Membership card listener: barcode scanned");
		MembershipTestUnit.barcodedScannedCount++;
	}

}



