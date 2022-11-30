package scale;

public interface ScaleListener {
	public abstract void notifyWeightDiscrepancyDetected();
	public abstract void notifyWeightDiscrepancyResolved();
}
