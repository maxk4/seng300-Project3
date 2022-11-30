package cart;

public interface ItemAddedListener {
	public abstract void notifyItemAdded(long price, double weightInGrams);
}
