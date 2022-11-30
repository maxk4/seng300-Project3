package ui;

public interface CustomerUIListener {
	public abstract void purchaseBags(int amount);
	public abstract void endSession();
	public abstract void beginSession();
}
