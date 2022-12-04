package cart;

import com.diy.hardware.Product;

public interface CartListener {
	public abstract void notifyItemAdded(Product product, long price, double weightInGrams);
	public abstract void notifyItemRemoved(Product product, long price, double weightInGrams);
}
