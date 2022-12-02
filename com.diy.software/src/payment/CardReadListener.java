package payment;


import com.diy.hardware.DoItYourselfStation;
import com.jimmyselectronics.AbstractDevice;
import com.jimmyselectronics.AbstractDeviceListener;
import com.jimmyselectronics.opeechee.Card.CardData;
import com.jimmyselectronics.opeechee.CardReader;
import com.jimmyselectronics.opeechee.CardReaderListener;

public class CardReadListener implements CardReaderListener {
	
	
	private boolean enabled = false;
	private CardData data; 
	public boolean cardInserted = false;
	private CardPaymentManager manager;
	private DoItYourselfStation station;
	
	public CardReadListener(CardPaymentManager manager, DoItYourselfStation station) {
		this.manager = manager;
		this.station = station;
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		enabled = true;
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		enabled = false;
	}

	@Override
	public void turnedOn(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void turnedOff(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void cardInserted(CardReader reader) {
		cardInserted = true;
	}

	@Override
	public void cardRemoved(CardReader reader) {
		cardInserted = false;
	}

	@Override
	public void cardDataRead(CardReader reader, CardData data) {
		if (!enabled) return;
		this.data = data;
		//Added in iteration 3 @Simrat (Starts)
		if(data.getType().equals("Membership")){
			//Scanning a membership card return, do not do anything, do not put any hold on it
			System.out.println("(CardReadListener): Membership card Scanned");
			return;
		}
		//Added in iteration 3 @Simrat (ends)
		if (manager.currentBalance() > 0) {
			try {
				manager.placeHold(data.getNumber(), manager.currentBalance(), data.getType());
			} catch (HoldException e) {
				e.printStackTrace();
			}
		}
		station.cardReader.remove();
	}

	@Override
	public void cardTapped(CardReader reader) {}

	@Override
	public void cardSwiped(CardReader reader) {}
}


