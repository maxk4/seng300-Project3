package printing;

import com.diy.hardware.AttendantStation;
import com.diy.hardware.DoItYourselfStation;
import simulation.Simulation;
import ui.AttendantUI;
import ui.CustomerUI;

public class PrintListener implements PrinterListener {
	@Override
	public void notifyLowInk(DoItYourselfStation station) {
		System.out.println("(Print Listener) Low Ink");
		//Simulation.attendant.notifyLowInkDetected(station);
	}

	@Override
	public void notifyNoInk(DoItYourselfStation station) {
		System.out.println("(Print Listener) No Paper");
	}

	@Override
	public void notifyNoPaper(DoItYourselfStation station) {
		System.out.println("(Print Listener) No Paper");
	}

	public void notifyLowPaper(DoItYourselfStation station) {
		System.out.println("(Print Listener) Low Paper");
		//Simulation.attendant.notifyLowPaperDetected(station);
	}

	@Override
	public void notifyInkRefilled(DoItYourselfStation station) {
		System.out.println("(Print Listener) notifyInkRefilled");
		//Simulation.attendant.notifyLowInkResolved(station);

	}

	@Override
	public void notifyPaperRefilled(DoItYourselfStation station) {
		System.out.println("(Print Listener) notifyPaperRefilled");
		//Simulation.attendant.notifyLowPaperResolved(station);

	}

}
