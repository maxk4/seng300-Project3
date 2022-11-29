import com.jimmyselectronics.AbstractDevice;
import com.jimmyselectronics.AbstractDeviceListener;
import com.jimmyselectronics.opeechee.Card.CardData;
import com.jimmyselectronics.opeechee.CardReader;
import com.jimmyselectronics.opeechee.CardReaderListener;

public class CardReaderListenerStub implements CardReaderListener {
	
	public boolean inserted = false;

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void turnedOn(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void turnedOff(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void cardInserted(CardReader reader) {
		inserted = true;
	}

	@Override
	public void cardRemoved(CardReader reader) {
		inserted = false;
	}

	@Override
	public void cardDataRead(CardReader reader, CardData data) {}

	@Override
	public void cardTapped(CardReader reader) {}

	@Override
	public void cardSwiped(CardReader reader) {}

}
