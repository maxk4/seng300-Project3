package util;
/**
 * A listener allowing the state of a weight discrepancy to be passed from 
 * the customer ui to and from the attendant ui
 * @author Taylor Wong
 *
 */
public class CustomerStationListener {
	
	private AttendantUI attendant;
	
	/**
	 * Make a new DiscrepancyListener attached to the provided AttendantUI
	 * @param attendant AttendantUI to attach the new listener to
	 */
	public CustomerStationListener(AttendantUI attendant) {
		this.attendant = attendant;
	}
	
	/**
	 * Notify the attendant that a weight discrepancy has been detected
	 * @param customer CustomerUI that has the weight discrepancy
	 */
	public void notifyWeightDiscrepancyDetected(CustomerUI customer) {
		attendant.notifyWeightDiscrepancyDetected(customer);
	}
	
	/**
	 * Notify the attendant that a weight discrepancy has been resolved
	 * @param customer CustomerUI that had the weight discrepancy
	 */
	public void notifyWeightDiscrepancyResolved(CustomerUI customer) {
		attendant.notifyWeightDiscrepancyResolved(customer);
	}
	
	public void notifyOutOfChange(CustomerUI customer) {
		attendant.notifyOutOfChange(customer);
	}
	
	public void nofityAddBag(CustomerUI customer) {
		attendant.notifyAddBag(customer);
	}
}
