package cart;

import com.diy.hardware.Product;

public interface ItemAddedListener {
	public abstract void notifyItemAdded(Product product, long price, double weightInGrams);
}
