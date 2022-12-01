package simulation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.AttendantStation;
import com.diy.hardware.external.ProductDatabases;
import com.diy.simulation.Customer;
import com.jimmyselectronics.OverloadException;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
import com.jimmyselectronics.opeechee.Card;

import ca.powerutility.PowerGrid;
import main.CustomerStationWrapper;
import ui.AttendantUI;
import util.Bank;
import util.MembershipDatabase;
import util.MembershipNumber;

public class Simulation {
	
	public static final Barcode[] barcodes = new Barcode[] {
		new Barcode(new Numeral[] {Numeral.one}),
		new Barcode(new Numeral[] {Numeral.two}),
		new Barcode(new Numeral[] {Numeral.three}),
		new Barcode(new Numeral[] {Numeral.four}),
		new Barcode(new Numeral[] {Numeral.five}),
		new Barcode(new Numeral[] {Numeral.six}),
		new Barcode(new Numeral[] {Numeral.seven}),
		new Barcode(new Numeral[] {Numeral.eight}),
		new Barcode(new Numeral[] {Numeral.nine}),
		new Barcode(new Numeral[] {Numeral.one, Numeral.two})
	};
	
	public static List<Card> cards = new ArrayList<Card>();
	//Added in Iteration 3 @Simrat (Start)
	public static BarcodedItem membership_card_barcode;
	public static Customer currentCustomer;
	//Added in Iteration 3 @Simrat (end)
	
	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("Missing arguments: <stations>");
			return;
		}
		setup();
		
		// Get number of stations
		int diyStations = Integer.parseInt(args[0]);
		
		// Initialize attendant station and ui
		AttendantStation aStation = new AttendantStation();
		aStation.plugIn();
		aStation.turnOn();
		AttendantUI attendant = new AttendantUI(aStation, diyStations);
		
		for (int i = 0; i < diyStations; i++) {
			DoItYourselfStation station = new DoItYourselfStation();
			attendant.addStation(station);
		}
		
		// Initialize diy stations
		for (DoItYourselfStation station : aStation.attendedStations()) {
			Customer customer = genCustomer();

			currentCustomer = customer;
			customer.useStation(station);
			station.plugIn();
			station.turnOn();
			
			CustomerStationWrapper customerStation = new CustomerStationWrapper(station, attendant);
			new CustomerUISimulator(station, customer, "Customer Simulator");
			try {
				station.printer.addInk(100);
				station.printer.addPaper(100);
			} catch (OverloadException e) {
				e.printStackTrace();
			}
		}
//		
//		// Register diy stations with the attendant station
//		for (CustomerUI cStation : uis) aStation.add(cStation);
		
		MaintenanceSimulator ms = new MaintenanceSimulator(attendant, aStation.attendedStations());
		
		
	}
	
	private static void setup() {
		int[] banknoteDenominations = {5000,2000,1000,500};
		long[] coinDenominations = {200, 100, 25, 10, 5};
		DoItYourselfStation.configureBanknoteDenominations(banknoteDenominations);
		DoItYourselfStation.configureCoinDenominations(coinDenominations);
		PowerGrid.engageUninterruptiblePowerSource();
		
		for (int i = 0; i < barcodes.length; i++)
			ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcodes[i], new BarcodedProduct(barcodes[i], "Product " + (i + 1), (i + 1) * 100, 2.3));
		
		for (int i = 0; i < 10; i++) {
			Card card = new Card(i % 2 == 0 ? "credit" : "debit", "841799260331897" + i, "Sir Fakeman", "564", "0000".intern(), true, true);
			Calendar expiry = Calendar.getInstance();
			expiry.set(2025, 1, 1);
			Bank.CARD_ISSUER.addCardData(card.number, card.cardholder, expiry, card.cvv, Double.MAX_VALUE);
			cards.add(card);
		}

		//Updated code in Iteration 3 @Simrat (Start)
		//members.add(12345678);
		//members.add(23456789);
		//members.add(34567890);
		//Setting up the Membership Database
		MembershipDatabase.MEMBER_DATABASE.put(123456789,"John Doe Customer");
		MembershipDatabase.MEMBER_DATABASE.put(1234567891,"John Doe");
		MembershipDatabase.MEMBER_DATABASE.put(1234567892,"John Doe 2");
		//Data added in MEMBER_DATABASE

		//Create 2 membership card
		Card membership_card1 = new Card("Membership","99999999", "John Member-Card", "000", "000", false, false);
		Card membership_card2 = new Card("Membership","88888888", "John OG-Card", "000", "000", false, false);
		Card membership_card3 = new Card("Membership","88888887", "John Not Member", "000", "000", false, false);

		//Add these to customer's wallet
		//adding into cards Array which will be added into wallet in genCustomer() method
		cards.add(membership_card1);
		cards.add(membership_card2);
		cards.add(membership_card3);

		///Add the membership cards into the membership database
		MembershipDatabase.MEMBER_DATABASE.put(99999999,"John Member-Card");
		MembershipDatabase.MEMBER_DATABASE.put(88888888,"John OG-Card");

		//Create a barcodedItem, so that it can be scanned by barcodeScanner
		membership_card_barcode = new BarcodedItem(new Barcode(new Numeral[]{Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine}), 0.1);

		//MembershipNumber.MEMBER_NUMBERS.add(12345678);
		//MembershipNumber.MEMBER_NUMBERS.add(23456789);
		//MembershipNumber.MEMBER_NUMBERS.add(34567890);
		//Updated code in Iteration 3 Ends @Simrat (Ends)

	}

	private static Customer genCustomer() {
		Customer customer = new Customer();
		
		for (int i = 0; i < 10; i++) {
			BarcodedItem item = new BarcodedItem(barcodes[i], 2.3);
			customer.shoppingCart.add(item);
		}
		customer.wallet.cards.addAll(cards);
		return customer;
	}

}
