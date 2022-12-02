package cart;


import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.external.ProductDatabases;
import com.jimmyselectronics.AbstractDevice;
import com.jimmyselectronics.AbstractDeviceListener;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodeScanner;
import com.jimmyselectronics.necchi.BarcodeScannerListener;
import util.MembershipDatabase;

/**
 * A BarcodeScannerListener to track when an item is scanned then notify the attached CustomerUI
 * to add the item.
 * Note: only one ScanItemListener should be attached to any CustomerUI
 * @author Taylor Wong
 *
 */
public class ScanItemListener implements BarcodeScannerListener {
	
	private CartController controller;
	
	private boolean enabled = false;
	private int successfulScan;

	//Added Iteration 3 @Simrat (Starts)
	static String barcodeScanned;
	//Added Iteration 3 @Simrat (Ends)
	
	public ScanItemListener(BarcodeScanner scanner, CartController controller) {
		this.controller = controller;
		scanner.register(this);
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		enabled = true;
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		enabled = false;
	}

	@Override
	public void turnedOn(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void turnedOff(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
		if (!enabled) return;
		if (barcode == null) throw new NullPointerException("no barcode provided");

		//Added in Iteration 3 @Simrat (starts)
		System.out.println("(ScanItemListener) BarcodeScanned : " + barcode.toString());
		barcodeScanned = barcode.toString();
		//check if this barcode exists in membership database, if yes return, this is the call from membership gui
		if(MembershipDatabase.MEMBER_DATABASE.containsKey(Integer.valueOf(barcodeScanned)))
		{
			System.out.println("(ScanItemListener) barcode exists in membership database");
			return;
		}
		//Added in Iteration 3 @Simrat (ends)

		// Search database for product with matching barcode
		// if no such product exists the scan will be ignored
		if (!ProductDatabases.BARCODED_PRODUCT_DATABASE.containsKey(barcode)) return;
		BarcodedProduct product = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode);
		
		successfulScan++;
		controller.addItem(product, product.getDescription(), product.getPrice(), product.getExpectedWeight());
	}
	
	public int getSuccessfulScan() {
		return successfulScan;
	}

	//Added in Iteration 3 @Simrat (Starts)

	/**
	 * @author Simrat
	 * @return String of barcode currently scanned
	 */
	public static String getBarcodeScanned_String()
	{
		return barcodeScanned;
	}
	//Added in Iteration 3 @Simrat (ENDS)
}
