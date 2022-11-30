package cart;


import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.external.ProductDatabases;
import com.jimmyselectronics.AbstractDevice;
import com.jimmyselectronics.AbstractDeviceListener;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodeScanner;
import com.jimmyselectronics.necchi.BarcodeScannerListener;

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
		
		// Search database for product with matching barcode
		// if no such product exists the scan will be ignored
		if (!ProductDatabases.BARCODED_PRODUCT_DATABASE.containsKey(barcode)) return;
		BarcodedProduct product = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode);
		
		controller.productList.add(product, product.getDescription(), product.getPrice());
		successfulScan++;
		controller.itemAdded(product.getPrice(), product.getExpectedWeight());
	}
	
	public int getSuccessfulScan() {
		return successfulScan;
	}

}
