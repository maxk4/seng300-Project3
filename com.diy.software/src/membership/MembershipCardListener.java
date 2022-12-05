package membership;
/*
	Created in Iteration 3
	@author Simrat
	Listens for Membership card scanned and swiped
	Implements CardReaderListener and BarcodeScannerListener
 */
import com.jimmyselectronics.AbstractDevice;
import com.jimmyselectronics.AbstractDeviceListener;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodeScanner;
import com.jimmyselectronics.necchi.BarcodeScannerListener;
import com.jimmyselectronics.opeechee.Card;
import com.jimmyselectronics.opeechee.CardReader;
import com.jimmyselectronics.opeechee.CardReaderListener;

import util.MembershipDatabase;

public class MembershipCardListener implements CardReaderListener, BarcodeScannerListener {

	private MembershipController controller;
	
	public MembershipCardListener(MembershipController controller) {
		this.controller = controller;
	}
	
	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) { }

	@Override
	public void turnedOn(AbstractDevice<? extends AbstractDeviceListener> device) {	}

	@Override
	public void turnedOff(AbstractDevice<? extends AbstractDeviceListener> device) { }

	@Override
	public void cardInserted(CardReader reader) { }

	@Override
	public void cardRemoved(CardReader reader) { }

	@Override
	public void cardTapped(CardReader reader) {	}

	@Override
	public void cardDataRead(CardReader reader, Card.CardData data) {
		if (!MembershipDatabase.MEMBER_DATABASE.containsKey(Integer.valueOf(data.getNumber()))) return;
		
		controller.notifyMemberCardRead(Integer.valueOf(data.getNumber()));
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
		controller.notifyMemberCardRead(MembershipDatabase.MEMBER_BARCODES.get(barcode));
		System.out.println("Membership card listener: barcode scanned");
	}
	/**
	 * Announces that a card has swiped on the indicated card reader.
	 *
	 * @param reader The reader where the event occurred.
	 */
	@Override
	public void cardSwiped(CardReader reader) {
		System.out.println("Listener: Membership Card swipedpped");
	}
}