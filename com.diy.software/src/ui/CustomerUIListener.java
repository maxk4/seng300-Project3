package ui;

import com.diy.hardware.Product;

public interface CustomerUIListener {
	public abstract void purchaseBags(int amount);
	public abstract void endSession();
	public abstract void beginSession();
	public abstract void selectItem(Product product, String description);
	public abstract void itemPlaced();
}
