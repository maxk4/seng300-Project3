package scale;

import com.jimmyselectronics.AbstractDevice;
import com.jimmyselectronics.AbstractDeviceListener;
import com.jimmyselectronics.virgilio.ElectronicScale;
import com.jimmyselectronics.virgilio.ElectronicScaleListener;

public class ScanningAreaListener implements ElectronicScaleListener {
	
	private double weight;

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void turnedOn(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void turnedOff(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void weightChanged(ElectronicScale scale, double weightInGrams) {
		weight = weightInGrams;
	}

	@Override
	public void overload(ElectronicScale scale) {}

	@Override
	public void outOfOverload(ElectronicScale scale) {}
	
	public double getWeight() {
		return weight;
	}

}
