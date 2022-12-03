package printing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.diy.hardware.DoItYourselfStation;
import com.jimmyselectronics.AbstractDevice;
import com.jimmyselectronics.AbstractDeviceListener;
import com.jimmyselectronics.EmptyException;
import com.jimmyselectronics.OverloadException;
import com.jimmyselectronics.abagnale.IReceiptPrinter;
import com.jimmyselectronics.abagnale.ReceiptPrinterListener;

public class PrinterController implements ReceiptPrinterListener {
	
	private List<PrinterListener> listeners;
	private DoItYourselfStation station;
	
	
	boolean lowPaper;
	boolean lowInk;
	boolean noPaper;
	boolean noInk;
	
	//Constructor
	public PrinterController(DoItYourselfStation station) {
		this.lowPaper = false;
		this.lowInk = false;
		this.noPaper = false;
		this.noInk = false;
		this.station = station;
		this.listeners = new ArrayList<PrinterListener>();
		station.printer.register(this);
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void turnedOn(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void turnedOff(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void outOfPaper(IReceiptPrinter printer) {
		abortPrinting();
		noPaper = true;
	}

	@Override
	public void outOfInk(IReceiptPrinter printer) {
		abortPrinting();
		noInk = true;
	}

	@Override
	public void lowInk(IReceiptPrinter printer) {
		notifyLowInk();
		lowInk = true;
	}

	@Override
	public void lowPaper(IReceiptPrinter printer) {
		notifyLowPaper();
		lowPaper = true;
	}

	@Override
	public void paperAdded(IReceiptPrinter printer) {
		for (PrinterListener listener : listeners) listener.notifyPaperRefilled(station);
	}

	@Override
	public void inkAdded(IReceiptPrinter printer) {
		for (PrinterListener listener : listeners) listener.notifyInkRefilled(station);
	}
	
	public boolean getLowInk() {
		return lowInk;
	}
	
	public boolean getLowPaper() {
		return lowPaper;
	}
	
	public boolean getNoInk() {
		return noInk;
	}
	
	public boolean getNoPaper() {
		 return noPaper;
	 }
	
	
	/** If printer runs of ink/paper while printing
	 *  Abort printing, suspend station, and inform attendant that duplicate receipt must be printed and station needs maintenance
	 */ 
	public void abortPrinting() {
		JOptionPane maintenanceWarning = new JOptionPane();
		JOptionPane.showMessageDialog(maintenanceWarning,"Duplicate receipt must printed. This Station needs maintenance. (No Ink/Paper)");
	}
	
	public void notifyLowInk() {
		for (PrinterListener listener : listeners) listener.notifyLowInk(station);
	}
	
	public void notifyLowPaper() {
		for (PrinterListener listener : listeners) listener.notifyLowPaper(station);
	}
	
	public void print(String receipt) {
		for (char c : receipt.toCharArray()) {
			try {
				station.printer.print(c);
			} catch (EmptyException e) {
				// Notify attendant
			} catch (OverloadException e) {
				e.printStackTrace();
			}
		}
		station.printer.cutPaper();
	}
	
	public void addPaper() {
		noPaper = false;
		paperAdded(this.station.printer);
	}
	
	public void addInk() {
		noInk = false;
		inkAdded(this.station.printer);
	}
	
}
