package printing;

import com.diy.hardware.DoItYourselfStation;

public interface PrinterListener {
	public abstract void notifyLowInk(DoItYourselfStation station);
	public abstract void notifyLowPaper(DoItYourselfStation station);
	public abstract void notifyInkRefilled(DoItYourselfStation station);
	public abstract void notifyPaperRefilled(DoItYourselfStation station);
	public abstract void empty(DoItYourselfStation station);
}
