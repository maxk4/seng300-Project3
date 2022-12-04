package printing;

import com.diy.hardware.AttendantStation;
import com.diy.hardware.DoItYourselfStation;
import simulation.Simulation;
import ui.AttendantUI;
import ui.CustomerUI;
import ui.views.StationComponent;

public class PrintListener implements PrinterListener {
	@Override
	public void notifyLowInk(DoItYourselfStation station) {
		System.out.println("(Print Listener) Low Ink");
		Simulation.attendant.notifyLowInkDetected(station);
		StationComponent.disable.doClick();

	}

	public void notifyLowPaper(DoItYourselfStation station) {
		System.out.println("(Print Listener) Low Paper");
		Simulation.attendant.notifyLowPaperDetected(station);
		StationComponent.disable.doClick();
	}

	@Override
	public void notifyInkRefilled(DoItYourselfStation station) {
		System.out.println("(Print Listener) notifyInkRefilled, enable the station");
		Simulation.attendant.notifyLowInkResolved(station);
		//StationComponent.enable.doClick();

	}

	@Override
	public void notifyPaperRefilled(DoItYourselfStation station) {
		System.out.println("(Print Listener) notifyPaperRefilled, enable the station");
		Simulation.attendant.notifyLowPaperResolved(station);
		//StationComponent.enable.doClick();

	}

	@Override
	public void empty(DoItYourselfStation station) {

	}
}
