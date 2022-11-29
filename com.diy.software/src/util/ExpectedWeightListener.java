package util;
import com.jimmyselectronics.AbstractDevice;
import com.jimmyselectronics.AbstractDeviceListener;
import com.jimmyselectronics.virgilio.ElectronicScale;
import com.jimmyselectronics.virgilio.ElectronicScaleListener;

/**
 * ElectronicScaleListener that checks for a discrepancy in the expected weight and current weight
 * @author Taylor Wong
 *
 */
public class ExpectedWeightListener implements ElectronicScaleListener {
	
	private double expectedWeight, sensitivity = 0, lastWeight;
	private CustomerUI customer;
	
	/**
	 * Create a new ExpectedWeightListener
	 * @param customer CustomerUI to attach the listener to
	 */
	public ExpectedWeightListener(CustomerUI customer) {
		this.customer = customer;
	}
	

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
		if (Math.abs(weightInGrams - expectedWeight) > sensitivity) 
			customer.alertWeightDiscrepancy();
		else customer.resolveWeightDiscrepancy();
		lastWeight = weightInGrams;
	}

	@Override
	public void overload(ElectronicScale scale) {}

	@Override
	public void outOfOverload(ElectronicScale scale) {}

	/**
	 * Set the weight this listener should expect
	 * If doing so will resolve a weight discrepancy the CustomerUI will be alerted such
	 * @param weightInGrams double weight to set the expected weight to in grams
	 */
	public void setExpectedWeight(double weightInGrams) {
		expectedWeight = weightInGrams;
		if (Math.abs(lastWeight - expectedWeight) > sensitivity) 
			customer.alertWeightDiscrepancy();
		else
			customer.resolveWeightDiscrepancy();
		
	}
	
	/**
	 * Increment the weight this listener should expect by the amount provided
	 * If doing so will resolve a weight discrepancy the CustomerUI will be alerted such
	 * @param inc double amount to increment the expected weight by
	 */
	public void updateExpectedWeight(double inc) {
		setExpectedWeight(expectedWeight + inc);
	}
	
	/**
	 * Set the sensitivity of this listener
	 * @param sensitivity double the new sensitivity
	 */
	public void setSensitivity(double sensitivity) {
		this.sensitivity = sensitivity;
	}
	
	/**
	 * Force resolve a weight discrepancy
	 * Note: only to be called by the attendant & related classes IMPORTANT!
	 */
	public void approveWeightDiscrepancy() {
		expectedWeight = lastWeight;
		customer.resolveWeightDiscrepancy();
	}
}
