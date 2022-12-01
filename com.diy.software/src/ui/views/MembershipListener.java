package util;
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

public class MembershipListener implements CardReaderListener, BarcodeScannerListener {

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
	public void cardDataRead(CardReader reader, Card.CardData data) { }

	/**
	 * An event announcing that the indicated barcode has been successfully scanned.
	 *
	 * @param barcodeScanner The device on which the event occurred.
	 * @param barcode        The barcode that was read by the scanner.
	 */
	@Override
	public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
		System.out.print("Listener: Membership Card Scanned");
		System.out.println(barcode.toString());
	}
	/**
	 * Announces that a card has swiped on the indicated card reader.
	 *
	 * @param reader The reader where the event occurred.
	 */
	@Override
	public void cardSwiped(CardReader reader) {
		System.out.println("Listener: Membership Card swiped");
	}
	/**
	 * Announces that a card has scanned on the indicated card reader.
	 *
	 * @param barcodeScanner The reader where the event occurred.
	 */
	public void cardScanned(BarcodeScanner barcodeScanner) {
		System.out.println("Listener: Membership Card Scanned");
	}
	/**
	 * Announces that a card has typed on the indicated card reader.
	 */
	public void cardTyped(Integer memberNumber)
	{
		System.out.println("Listener: Membership Card Typed");
		System.out.println("Entered Data = " + memberNumber);
	}
}