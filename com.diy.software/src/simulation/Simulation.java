package simulation;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.external.ProductDatabases;
import com.diy.simulation.Customer;
import com.jimmyselectronics.OverloadException;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
import com.jimmyselectronics.opeechee.Card;

import ca.powerutility.PowerGrid;
import util.AttendantStation;
import util.AttendantStationListener;
import util.AttendantUI;
import util.Bank;
import util.CashPayment;
import util.CustomerStationListener;
import util.CustomerUI;
import util.ExpectedWeightListener;
import util.LowInkLowPaper;
import util.NoBaggingRequestListener;
import util.PayWithCardListener;
import util.ScanItemListener;

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
	public static List<Integer> members = new ArrayList<>();
	
	
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
		AttendantUI attendant = new AttendantUI(aStation, diyStations);
		AttendantStationListener aStationListener = new AttendantStationListener(attendant);
		aStation.registerListener(aStationListener);
		NoBaggingRequestListener nbrListener = new NoBaggingRequestListener(attendant);
		
		// Initialize diy stations
		List<CustomerUI> uis = new ArrayList<CustomerUI>();
		List<DoItYourselfStation> stations = new ArrayList<DoItYourselfStation>();
		for (int i = 0; i < diyStations; i++) {
			Customer customer = genCustomer();
			
			DoItYourselfStation station = new DoItYourselfStation();
			station.plugIn();
			station.turnOn();
			CustomerUI ui = new CustomerUISimulator(station, customer, "Station " + (i + 1));
			
			ScanItemListener sil = new ScanItemListener(ui);
			station.mainScanner.register(sil);
			customer.useStation(station);
			
			station.cardReader.register(new PayWithCardListener(ui));
	
			ExpectedWeightListener ewl = new ExpectedWeightListener(ui);
			station.scanningArea.register(ewl);
			ui.setWeightListener(ewl);
			
			CustomerStationListener dl = new CustomerStationListener(attendant);
			ui.registerDiscrepancyListener(dl);
			ui.registerNoBaggingRequestListener(nbrListener);
			
			station.printer.register(new LowInkLowPaper(ui, attendant));
			try {
				station.printer.addInk(10);
				station.printer.addPaper(10);
			} catch (OverloadException e) {
				e.printStackTrace();
			}
			
			ui.setCashPaymentController(new CashPayment(ui, attendant, station));
			for (int j = 0; j < members.size(); j++) {
				ui.addMemberNumber(members.get(j));
			}
			uis.add(ui);
			stations.add(station);
			
			try {
				station.printer.addInk(100);
				station.printer.addPaper(100);
			} catch (OverloadException e) {
				e.printStackTrace();
			}
		}
		
		// Register diy stations with the attendant station
		for (CustomerUI cStation : uis) aStation.registerStation(cStation);
		
		MaintenanceSimulator ms = new MaintenanceSimulator(attendant, stations);
		
		
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
		
		members.add(12345678);
		members.add(23456789);
		members.add(34567890);
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
