package scale;

import java.util.ArrayList;
import java.util.List;

import com.diy.hardware.DoItYourselfStation;
import com.jimmyselectronics.virgilio.ElectronicScaleListener;

public class ScaleController {
	private List<ScaleListener> listeners;
	
	private ElectronicScaleListener scanningAreaListener;
	private ExpectedWeightListener baggingAreaListener;
	
	public ScaleController(DoItYourselfStation station) {
		listeners = new ArrayList<ScaleListener>();
		baggingAreaListener = new ExpectedWeightListener(this);
		scanningAreaListener = null;
		
		station.baggingArea.register(baggingAreaListener);
//		station.scanningArea.register(scanningAreaListener);
		station.baggingArea.enable();
		station.scanningArea.enable();
	}
	
	public void notifyWeightDiscrepancyDetected() {
		for (ScaleListener listener : listeners) listener.notifyWeightDiscrepancyDetected();
	}
	
	public void notifyWeightDiscrepancyResolved() {
		for (ScaleListener listener : listeners) listener.notifyWeightDiscrepancyResolved();
	}
	
	public void approveWeight() {
		baggingAreaListener.approveWeightDiscrepancy();
	}
	
	public void updateExpectedWeight(double weightInGrams) {
		baggingAreaListener.updateExpectedWeight(weightInGrams);
	}
	
	public void setExpectedWeight(double weightInGrams) {
		baggingAreaListener.setExpectedWeight(weightInGrams);
	}
	
	/**
	 * Register an ScaleListener to this
	 * @param listener ScaleListener to add
	 * @return boolean true if successful false otherwise
	 */
	public boolean register(ScaleListener listener) {
		if (listeners.contains(listener)) return false;
		listeners.add(listener);
		return true;
	}
	
	/**
	 * Unregister an ScaleListener to this
	 * @param listener ScaleListener to remove
	 * @return boolean true if successful false otherwise
	 */
	public boolean deregister(ScaleListener listener) {
		if (!listeners.contains(listener)) return false;
		listeners.remove(listener);
		return true;
	}
}