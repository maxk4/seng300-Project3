package ui;

import com.diy.hardware.DoItYourselfStation;

public interface AttendantUIListener {
	public void approveWeight(DoItYourselfStation station);
	public void approveOwnBag(DoItYourselfStation station);
	public void denyOwnBag(DoItYourselfStation station);
	public void approveNoBag(DoItYourselfStation station);
}
