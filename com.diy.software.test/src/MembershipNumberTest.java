
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import ca.powerutility.PowerGrid;
import com.diy.simulation.Customer;
import org.junit.Before;
import org.junit.Test;
import com.diy.hardware.DoItYourselfStation;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
import com.jimmyselectronics.opeechee.Card;
import simulation.CustomerUISimulator;
import ui.CustomerUI;
import ui.views.customer.EnterMemberNumberGUI;
import ui.views.customer.ScanScreenGUI;
import util.MembershipDatabase;
import membership.MembershipController;
import membership.MembershipListener;
import javax.swing.*;

// authors Charvi and Simrat 

public class MembershipNumberTest {

	private EnterMemberNumberGUI membershipGUI;
	private CustomerUISimulator customerSimulator;
	private MembershipController membershipController;
	private CustomerUI customerUI;
	private Customer customer;

	private static List<Card> cards = new ArrayList<Card>();


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

		cards.add(membership_card1);
		cards.add(membership_card2);
		cards.add(membership_card3);
		//membership card 3 is not a member.

		
		///Add the membership cards into the membership database
		MembershipDatabase.MEMBER_DATABASE.put(99999999,"John Member-Card");
		MembershipDatabase.MEMBER_DATABASE.put(88888888,"John OG-Card");

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
		MembershipDatabase.MEMBER_BARCODES.put(membership_card_barcode3, 4444);

		station = new DoItYourselfStation();
		PowerGrid.disconnect();
		PowerGrid.engageUninterruptiblePowerSource();
		station.plugIn();
		station.turnOn();
		customer = new Customer();
		customer.useStation(station);

		membershipController = new MembershipController(station);
		membershipController.register(new MembershipListener() {
			@Override
			public void notifyMembershipCardRead(int memberId) {
				customerUI.useMemberName(memberId);
			}
		});
		//creates listern and attaches that to scanner and barcodes automatically

		customerUI = new CustomerUI(station, "Cx Membership Test");
		membershipGUI = new EnterMemberNumberGUI(customerUI);
		customerSimulator = new CustomerUISimulator(station, customer, "Simulator");

	}

	/**
	 * Test using the text box, checks if the member exists, simulates the customer typing in text box, this member exist in the database
	 */
    @Test
    public void testTypingMemberExists(){
    
    	membershipGUI.textField_MemberNumber.setText("123456789");
		membershipGUI.button_Enter.doClick();
		System.out.println(customerUI.getCurrMem());
	    assertTrue(MembershipDatabase.MEMBER_DATABASE.containsKey(123456789));
		assertEquals("Cx: John Doe Customer", customerUI.getCurrMem());

    }
	/**
	 * Test using the text box, checks if the member exists, simulates the customer typing in text box, this member do not exist
	 */
	@Test
	public void testTypingMemberNotExists(){

		membershipGUI.textField_MemberNumber.setText("1234");
		membershipGUI.button_Enter.doClick();
		System.out.println(customerUI.getCurrMem());
		assertEquals("Invalid Membership Number", customerUI.getCurrMem());
		assertFalse(MembershipDatabase.MEMBER_DATABASE.containsKey(1234));
	}

	/**
	 * Test using the text box, checks if the member exists, simulates the customer typing in text box, this customer enters alphabets in the text field
	 */
	@Test
	public void testTypingMemberAlphabets(){

		String expectedResult = "No alphabets are allowed";
		membershipGUI.textField_MemberNumber.setText("123AA12");
		membershipGUI.button_Enter.doClick();
		System.out.println(customerUI.getCurrMem());
		assertEquals(expectedResult, customerUI.getCurrMem());
		//assertFalse(MembershipDatabase.MEMBER_DATABASE.containsKey(123AA12));
	}
	//===================================================================
    @Test
    public void testScanningExist(){

	    //Barcodes for membership cards
	    ArrayList<String> barcodes = new ArrayList<>();
	    for (Barcode memberBarcode : MembershipDatabase.MEMBER_BARCODES.keySet())
	    {
		    barcodes.add(memberBarcode.toString());
	    }
	    customerSimulator.comboBox_MemberCard_Barcodes = new JComboBox<String>(barcodes.toArray(new String[barcodes.size()]));
		String selectedMemberID = null;
		while (!customerSimulator.isMemberShipScanSuccess)
		{
			customerSimulator.comboBox_MemberCard_Barcodes.setSelectedIndex(1);
			selectedMemberID = customerSimulator.comboBox_MemberCard_Barcodes.getItemAt(1);

			customerSimulator.button_ScanCard.doClick();

			//if the scan failed for fault scanner, it will be scanned again
		}
	    //current member will be set
		//expected value for the current set customer, will be taken from barcode database
	    int key = Integer.parseInt(selectedMemberID);
	    String expectedValue = MembershipDatabase.MEMBER_DATABASE.get(key);
		System.out.println("Expected value = " + expectedValue);
		System.out.println("Actual value = " + customerUI.getCurrMem());
		assertEquals(expectedValue, customerUI.getCurrMem());
		    }


	//===================================================================
	@Test
	public void testScanningNotExist(){

		//Barcodes for membership cards
		ArrayList<String> barcodes = new ArrayList<>();
		for (Barcode memberBarcode : MembershipDatabase.MEMBER_BARCODES.keySet())
		{
			barcodes.add(memberBarcode.toString());
		}
		customerSimulator.comboBox_MemberCard_Barcodes = new JComboBox<String>(barcodes.toArray(new String[barcodes.size()]));
		String selectedMemberID = null;
		while (!customerSimulator.isMemberShipScanSuccess)
		{
			customerSimulator.comboBox_MemberCard_Barcodes.setSelectedIndex(0);
			selectedMemberID = customerSimulator.comboBox_MemberCard_Barcodes.getItemAt(0);
				//it should be 4444, whihc is not in mdatabase.
			customerSimulator.button_ScanCard.doClick();

			//if the scan failed for fault scanner, it will be scanned again
		}
		//current member will be set
		//expected value for the current set customer, will be taken from barcode database
		int key = Integer.parseInt(selectedMemberID);
		String expectedValue = MembershipDatabase.MEMBER_DATABASE.get(key);
		System.out.println("Expected value = " + expectedValue);
		System.out.println("Actual value = " + customerUI.getCurrMem());
		assertEquals(expectedValue, customerUI.getCurrMem());
	}

	//===================================================================
	@Test
	public void testSwipeExists(){

		//Membership cards
		ArrayList<String> memCardsList = new ArrayList<>();
		for (Card card : cards) {
			if(card.kind.equals("Membership"))
			{
				memCardsList.add(card.cardholder + " , " + card.number);
			}
			//Loaded the list with the membership cards
		}
		//Display the cards in a GUI
		customerSimulator.comboBox_MemberCardsInWallet = new JComboBox<String>(memCardsList.toArray(new String[memCardsList.size()]));

		String selectedMemberCard = null;
		while (!customerSimulator.isMemberShipSwipeSuccess)
		{
			customerSimulator.comboBox_MemberCardsInWallet.setSelectedIndex(0);
			selectedMemberCard = customerSimulator.comboBox_MemberCardsInWallet.getItemAt(0);
			//it should be 4444, whihc is not in mdatabase.
			customerSimulator.button_SwipeCard.doClick();

			//if the scan failed for fault scanner, it will be scanned again
		}
		//current member will be set
		//John Member-Card , 99999999
		//Split this to get card number
		String[] selectedCardSplit = selectedMemberCard.split(" , ");
		String selectedCardNumber  = selectedCardSplit[1];
		int key = Integer.parseInt(selectedCardNumber);
		String expectedValue = MembershipDatabase.MEMBER_DATABASE.get(key);
		System.out.println("Expected value = " + expectedValue);
		System.out.println("Actual value = " + customerUI.getCurrMem());
		assertEquals(expectedValue, customerUI.getCurrMem());
	}


	//===================================================================
	@Test
	public void testSwipeNotExists(){

		//Membership cards
		ArrayList<String> memCardsList = new ArrayList<>();
		for (Card card : cards) {
			if(card.kind.equals("Membership"))
			{
				memCardsList.add(card.cardholder + " , " + card.number);
			}
			//Loaded the list with the membership cards
		}
		memCardsList.add(membership_card3.cardholder + " , " + membership_card3.number);
		//Display the cards in a GUI
		customerSimulator.comboBox_MemberCardsInWallet = new JComboBox<String>(memCardsList.toArray(new String[memCardsList.size()]));

		String selectedMemberCard = null;
		while (!customerSimulator.isMemberShipSwipeSuccess)
		{
			customerSimulator.comboBox_MemberCardsInWallet.setSelectedIndex(2);
			selectedMemberCard = customerSimulator.comboBox_MemberCardsInWallet.getItemAt(2);
			//it should be 4444, whihc is not in mdatabase.
			customerSimulator.button_SwipeCard.doClick();

			//if the scan failed for fault scanner, it will be scanned again
		}
		//current member will be set
		//John Member-Card , 99999999
		//Split this to get card number
		String[] selectedCardSplit = selectedMemberCard.split(" , ");
		String selectedCardNumber  = selectedCardSplit[1];
		int key = Integer.parseInt(selectedCardNumber);
		String expectedValue = MembershipDatabase.MEMBER_DATABASE.get(key);
		System.out.println("Expected value = " + expectedValue);
		System.out.println("Actual value = " + customerUI.getCurrMem());
		assertEquals(expectedValue, customerUI.getCurrMem());
	}

}

