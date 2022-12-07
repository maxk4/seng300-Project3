package membership;

import java.util.ArrayList;
import java.util.List;

import com.diy.hardware.DoItYourselfStation;

public class MembershipController {
	
	private List<MembershipListener> listeners = new ArrayList<MembershipListener>();
	
	public MembershipController(DoItYourselfStation station) {
		MembershipCardListener listener = new MembershipCardListener(this);
		station.cardReader.register(listener);
		station.mainScanner.register(listener);
		station.handheldScanner.register(listener);
	}
	
	public boolean register(MembershipListener listener) {
		if (listeners.contains(listener)) return false;
		listeners.add(listener);
		return true;
	}
	
	public void notifyMemberCardRead(int memberId) {
		for (MembershipListener listener : listeners) listener.notifyMembershipCardRead(memberId);
	}
}
