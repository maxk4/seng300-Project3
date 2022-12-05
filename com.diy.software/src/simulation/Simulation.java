package simulation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.PLUCodedProduct;
import com.diy.hardware.PriceLookUpCode;
import com.diy.hardware.AttendantStation;
import com.diy.hardware.external.ProductDatabases;
import com.diy.simulation.Customer;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
import com.jimmyselectronics.opeechee.Card;

import athourization.AttendantDatabase;
import ca.powerutility.PowerGrid;
import main.CustomerStationWrapper;
import ui.AttendantUI;
import util.Bank;
import util.MembershipDatabase;

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
		new Barcode(new Numeral[] {Numeral.one, Numeral.zero}),
		new Barcode(new Numeral[] {Numeral.one, Numeral.one}),
		new Barcode(new Numeral[] {Numeral.one, Numeral.two}),
		new Barcode(new Numeral[] {Numeral.one, Numeral.three}),
		new Barcode(new Numeral[] {Numeral.one, Numeral.four}),
		new Barcode(new Numeral[] {Numeral.one, Numeral.five}),
		new Barcode(new Numeral[] {Numeral.one, Numeral.six}),
		new Barcode(new Numeral[] {Numeral.one, Numeral.seven}),
		new Barcode(new Numeral[] {Numeral.one, Numeral.eight}),
		new Barcode(new Numeral[] {Numeral.one, Numeral.nine}),
		new Barcode(new Numeral[] {Numeral.two, Numeral.zero})
	};
	
	public static List<Card> cards = new ArrayList<Card>();
	public static Customer currentCustomer;
	public static AttendantUI attendant;
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
		aStation.screen.getFrame().setLocation(0, 0);
		aStation.plugIn();
		aStation.turnOn();
		attendant = new AttendantUI(aStation, diyStations);
		
		for (int i = 0; i < diyStations; i++) {
			DoItYourselfStation station = new DoItYourselfStation();
			station.screen.getFrame().setLocation(0, 150);
			attendant.addStation(station);
		}
		
		// Initialize diy stations
		for (DoItYourselfStation station : aStation.attendedStations()) {
			Customer customer = genCustomer();

			currentCustomer = customer;
			customer.useStation(station);
			station.plugIn();
			
			 CustomerStationWrapper customerStation = new CustomerStationWrapper(station, attendant);
			new CustomerUISimulator(station, customer, "Customer Simulator");
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
		
		for (int i = 0; i < barcodes.length; i++) {
			ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcodes[i], new BarcodedProduct(barcodes[i], "Product " + (i + 1), (i + 1) * 100, 2.3));
		}
		
		PriceLookUpCode blueberryCode = new PriceLookUpCode("4240");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(blueberryCode, new PLUCodedProduct(blueberryCode, "Blueberry", 3 * 100));

		PriceLookUpCode appleCode = new PriceLookUpCode("3283");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(appleCode, new PLUCodedProduct(appleCode, "Apple", 4 * 100));
		
		PriceLookUpCode bananaCode = new PriceLookUpCode("4011");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(bananaCode, new PLUCodedProduct(bananaCode, "Banana", 1 * 100));
		
		PriceLookUpCode papayaCode = new PriceLookUpCode("3303");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(papayaCode, new PLUCodedProduct(papayaCode, "Papaya", 10 * 100));
		
		PriceLookUpCode peachCode = new PriceLookUpCode("3113");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(peachCode, new PLUCodedProduct(peachCode, "Peach", 6 * 100));
		
		PriceLookUpCode mushroomCode = new PriceLookUpCode("4647");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(mushroomCode, new PLUCodedProduct(mushroomCode, "Mushrooms", 8 * 100));
		
		PriceLookUpCode mangoCode = new PriceLookUpCode("3363");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(mangoCode, new PLUCodedProduct(mangoCode, "Mango", 6 * 100));
		
		PriceLookUpCode strawberryCode = new PriceLookUpCode("7000");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(strawberryCode, new PLUCodedProduct(strawberryCode, "Strawberry", 6 * 100));
		
		PriceLookUpCode broccoliCode = new PriceLookUpCode("7001");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(broccoliCode, new PLUCodedProduct(broccoliCode, "Broccoli", 6 * 100));

		PriceLookUpCode cabbageCode = new PriceLookUpCode("7002");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(cabbageCode, new PLUCodedProduct(cabbageCode, "Cabbage", 6 * 100));

		PriceLookUpCode carrotCode = new PriceLookUpCode("7003");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(carrotCode, new PLUCodedProduct(carrotCode, "Carrot", 6 * 100));

		PriceLookUpCode cherryCode = new PriceLookUpCode("7004");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(cherryCode, new PLUCodedProduct(cherryCode, "Cherry", 6 * 100));

		PriceLookUpCode cornCode = new PriceLookUpCode("7005");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(cornCode, new PLUCodedProduct(cornCode, "Corn", 6 * 100));

		PriceLookUpCode cucumberCode = new PriceLookUpCode("7006");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(cucumberCode, new PLUCodedProduct(cucumberCode, "Cucumber", 6 * 100));

		PriceLookUpCode raspberryCode = new PriceLookUpCode("7007");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(raspberryCode, new PLUCodedProduct(raspberryCode, "Raspberry", 6 * 100));

		PriceLookUpCode watermelonCode = new PriceLookUpCode("7008");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(watermelonCode, new PLUCodedProduct(watermelonCode, "Watermelon", 6 * 100));

		PriceLookUpCode kiwiCode = new PriceLookUpCode("7009");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(kiwiCode, new PLUCodedProduct(kiwiCode, "Kiwi", 6 * 100));

		PriceLookUpCode lettuceCode = new PriceLookUpCode("7010");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(lettuceCode, new PLUCodedProduct(lettuceCode, "Lettuce", 6 * 100));

		PriceLookUpCode onionCode = new PriceLookUpCode("7011");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(onionCode, new PLUCodedProduct(onionCode, "Onion", 6 * 100));

		PriceLookUpCode peasCode = new PriceLookUpCode("7012");
		ProductDatabases.PLU_PRODUCT_DATABASE.put(peasCode, new PLUCodedProduct(peasCode, "Lettuce", 6 * 100));
		
		
		System.out.println("Successfully populated Barcoded Product Database and PLU Product Database");
		
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
		//Added in Iteration 3 @Simrat (Start)
		Barcode membership_card_barcode1 = new Barcode(new Numeral[]{Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine, Numeral.nine});
		Barcode membership_card_barcode2 = new Barcode(new Numeral[]{Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight});
		Barcode membership_card_barcode3 = new Barcode(new Numeral[]{Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.eight, Numeral.seven});

		MembershipDatabase.MEMBER_BARCODES.put(membership_card_barcode1, 99999999);
		MembershipDatabase.MEMBER_BARCODES.put(membership_card_barcode2, 88888888);
		//MembershipNumber.MEMBER_NUMBERS.add(12345678);
		//MembershipNumber.MEMBER_NUMBERS.add(23456789);
		//MembershipNumber.MEMBER_NUMBERS.add(34567890);
		//Updated code in Iteration 3 Ends @Simrat (Ends)

		// Add a attendant to the attendant database
		AttendantDatabase.add("John Doe", "Password123");
		AttendantDatabase.add("", "");
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
