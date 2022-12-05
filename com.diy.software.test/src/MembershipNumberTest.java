
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import ca.powerutility.PowerGrid;
import com.diy.simulation.Customer;
import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.DoItYourselfStation;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodeScanner;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
import com.jimmyselectronics.opeechee.Card;
import com.jimmyselectronics.opeechee.CardReader;

import simulation.CustomerUISimulator;
import ui.CustomerUI;
import ui.views.customer.EnterMemberNumberGUI;
import util.MembershipDatabase;
import membership.MembershipController;
import membership.MembershipListener;
import membership.MembershipCardListener;

// authors Charvi and Simrat 

/*
- test where input is less than 8 = fail
- test where input is greater than 8 = fail
- test where input is exactly 8 = pass
- test where input is null (empty) = fail
- test where input is exactly 8 but the number is wrong = fail
=======
import org.junit.Before;
import org.junit.Test;

import util.MembershipNumber;

/*
 * -only test if it exists in the hashmap
- test where input is exactly 8 but the number is not in the members list = fail
- test where input is exactly 8 but the currentMember is not null = fail
- test where input is exactly 8 and the currentMember is null = pass
>>>>>>> origin/JC
 */

public class MembershipNumberTest {

	private Integer expected;
	private MembershipController membershipController;
	private EnterMemberNumberGUI membershipGUI;
	//private MembershipCardListener memCardListener = new MembershipCardListener(controller);
	private memListener memListener = new memListener();

	private CustomerUISimulator customerSimulator;
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
	CustomerUI customerUI;

	
	
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

		//Create a barcodedItem, so that it can be scanned by barcodeScanner
		//Added in Iteration 3 @Simrat (Start)
		membership_card_barcode1 = new Barcode(new Numeral[]{Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine});
		item1 = new BarcodedItem(membership_card_barcode1, 0.1);
		membership_card_barcode2 = new Barcode(new Numeral[]{Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight});
		item2 = new BarcodedItem(membership_card_barcode2, 0.1);
		membership_card_barcode3 = new Barcode(new Numeral[]{Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.seven});
		item3 = new BarcodedItem(membership_card_barcode3, 0.1);
		
		MembershipDatabase.MEMBER_BARCODES.put(membership_card_barcode1, 99999999);
		MembershipDatabase.MEMBER_BARCODES.put(membership_card_barcode2, 88888888);

		//MembershipController controller;
		MembershipCardListener membershipCardListener = new MembershipCardListener(membershipController);
		station = new DoItYourselfStation();
		station.cardReader.register(membershipCardListener);
		station.mainScanner.register(membershipCardListener);
		station.handheldScanner.register(membershipCardListener);
		PowerGrid.disconnect();
		PowerGrid.engageUninterruptiblePowerSource();
		station.plugIn();
		station.turnOn();

		membershipController = new MembershipController(station);

		customerUI = new CustomerUI(station, "Cx Membership Test");
		membershipGUI = new EnterMemberNumberGUI(customerUI);

		customerSimulator = new CustomerUISimulator(station, new Customer(), "Simulator");
		
	}

	/**
	 * Test using the text box, checks if the member exists, simulates the customer typing in text box, this member exist in the database
	 */
    @Test
    public void testTypingMemberExists(){
    
    	membershipGUI.textField_MemberNumber.setText("123456789");
		membershipGUI.button_Enter.doClick();
		System.out.println(customerUI.getCurrMem());
		assertEquals("Cx: John Doe Customer", customerUI.getCurrMem());
		assertTrue(MembershipDatabase.MEMBER_DATABASE.containsKey(123456789));
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
	
    @Test
    public void testSwipingExists(){
    	try {
			customerSimulator.button_ScanCard.doClick();
			station.cardReader.swipe(membership_card1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	assertTrue(MembershipDatabase.MEMBER_DATABASE.containsKey(99999999));
    	
    }
    
    @Test
    public void testScanning(){
    	station.mainScanner.scan(item1);
    	assertTrue(MembershipDatabase.MEMBER_BARCODES.containsKey(membership_card_barcode1));
    }
    
    @Test
    public void NotMemberTest(){
        assertFalse(MembershipDatabase.MEMBER_DATABASE.containsKey(88888887));
    }
    /*
    this test checks if the length of the number is equal to 8, which it is not.
    So, the test will fail and give since the membership number is less than 8 digits long.
     
    @Test
    public void NumberIsLessThanEightDigits(){
    	expected = 8;
    	assertEquals(expected, memberNum.checkMemNum(1234));
    }

    /*
    this test checks if the length of the number is equal to 8, which it is not.
    So, the test will fail since the membership number is greater than 8 digits long.
     
    @Test
    public void NumberIsGreaterThanEightDigits(){

    	expected = 8;
        assertEquals(expected, memberNum.checkMemNum(123456789));
    }


    /*
    this test checks if a number has been entered or not.
     
    @Test
    public void NotMemberTest(){
    	expected = 0; 
        assertEquals(expected, memberNum.checkMemNum(12345679));
    }

    /*
    this test checks if the number is correct but the a memberNumber is already in use.
     
    @Test
    public void CorrectLengthButWrongNumber(){
    	memberNum.checkMemNum(12345678);
    	expected = 1;
    	assertEquals(expected, memberNum.checkMemNum(23456789));
    }    
    
    /*
    this test checks if the length of the number is equal to 8, which it is.
    So, this test passes since the number is 8 digits long
     
    @Test
    public void CorrectMembershipNumber(){
    	expected = 12345678;
        assertEquals(expected, memberNum.checkMemNum(12345678));
       
    }
    */
    
   
}

class memListener implements MembershipListener 
{

	@Override
	public void notifyMembershipCardRead(int memberId) {
		// TODO Auto-generated method stub
		
	}
	
}
