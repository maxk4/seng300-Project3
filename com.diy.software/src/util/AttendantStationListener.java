package util;
public class AttendantStationListener {
	
	private AttendantUI attendant;
	
	/**
	 * Create a new AttendantStationListener attached to the AttendantUI provided
	 * @param attendant AttendantUI to attach to
	 */
	public AttendantStationListener(AttendantUI attendant) {
		this.attendant = attendant;
	}
	
	/**
	 * Add the CustomerUI to the AttendantUI
	 * @param customer CustomerUI to add
	 */
	public void noftifyRegistered(CustomerUI customer) {
		attendant.addCustomerUI(customer);
	}
	
}
