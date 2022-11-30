package cart;

import java.util.ArrayList;
import java.util.List;

import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.Product;


public class CartController {
	public ProductList productList;
	
	private List<ItemAddedListener> listeners;
	private ScanItemListener handheldScannerListener, mainScannerListener;
	
	public CartController(DoItYourselfStation station) {
		productList = new ProductList();
		listeners = new ArrayList<ItemAddedListener>();
		handheldScannerListener = new ScanItemListener(station.handheldScanner, this);
		mainScannerListener = new ScanItemListener(station.mainScanner, this);
	}
	
	/**
	 * Register an ItemAddedListener to this
	 * @param listener ItemAddedListener to add
	 * @return boolean true if successful false otherwise
	 */
	public boolean register(ItemAddedListener listener) {
		if (listeners.contains(listener)) return false;
		listeners.add(listener);
		return true;
	}
	
	/**
	 * Unregister an ItemAddedListener to this
	 * @param listener ItemAddedListener to remove
	 * @return boolean true if successful false otherwise
	 */
	public boolean deregister(ItemAddedListener listener) {
		if (!listeners.contains(listener)) return false;
		listeners.remove(listener);
		return true;
	}
	
	public void clear() {
		productList.clear();
	}
	
	private long total;
	public String getReceipt() {
		total = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("Transaction Complete\n");
		sb.append("--------------------\n");
		productList.forEach((prod, desc, price) -> {
			sb.append(String.format("%s\t\t$%.2f\n", desc, price / 100d));
			total += price;
		});
		sb.append("--------------------\n");
		sb.append(String.format("\t\tTotal:\t$%.2f", total / 100d));
		return sb.toString();
	}
	
	public String getPriceString() {
		StringBuilder priceSB = new StringBuilder();
		productList.forEach((prod, desc, price) -> {
			priceSB.append(String.format("$%.2f", price / 100d));
			priceSB.append("\n");
		});
		return priceSB.toString();
	}
	
	public String getProductString() {
		StringBuilder productSB = new StringBuilder();
		productList.forEach((prod, desc, price) -> {
			productSB.append(desc);
			productSB.append("\n");
		});
		return productSB.toString();
	}
	
	public void addItem(Product product, String description, long price, double weightInGrams) {
		productList.add(product, description, price);
		for (ItemAddedListener listener : listeners) listener.notifyItemAdded(product, price, weightInGrams);
	}
}
