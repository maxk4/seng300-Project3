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
import simulation.Simulation;

public class PrinterController implements ReceiptPrinterListener {
	
	private List<PrinterListener> listeners;
	private DoItYourselfStation station;
	
	
	boolean lowPaper;
	boolean lowInk;
	boolean noPaper;
	boolean noInk;
	boolean isOverload;
	boolean isEmpty;
	
	//Constructor
	public PrinterController(DoItYourselfStation station) {
		this.lowPaper = false;
		this.lowInk = false;
		this.noPaper = false;
		this.noInk = false;
		this.isOverload = false;
		this.isEmpty = false;
		this.station = station;
		this.listeners = new ArrayList<PrinterListener>();

		//Added In Iteration 3 @Simrat (Starts)
		listeners.add(new PrintListener());
		//Added In Iteration 3 @Simrat (Ends)

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
		notifyLowPaper();
		notifyNoPaper();
		lowPaper(printer);
		noPaper = true;
		lowPaper = true;
	}

	@Override
	public void outOfInk(IReceiptPrinter printer) {
		abortPrinting();
		notifyLowInk();
		notifyNoInk();
		lowInk(printer);
		noInk = true;
		lowInk = true;
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
		System.out.println("(Printer Controller) Paper Added");
		lowPaper = false;
		noPaper = false;
	}

	@Override
	public void inkAdded(IReceiptPrinter printer) {
		for (PrinterListener listener : listeners) listener.notifyInkRefilled(station);
		System.out.println("(Printer Controller) Ink Added");
		lowInk = false;
		noInk = false;
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
	public boolean getIsOverload() {
		return isOverload;
	}
	public boolean getIsEmpty() {
		return isEmpty;
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
		System.out.println("(Printer Controller) we got low Ink, damm it again");
	}
	
	public void notifyLowPaper() {
		for (PrinterListener listener : listeners) listener.notifyLowPaper(station);
		System.out.println("(Printer Controller) we got low Paper, damm it again");
	}

	public void notifyNoInk() {
		for (PrinterListener listener : listeners) listener.notifyNoInk(station);
		System.out.println("(Printer Controller) we got NO Ink");
	}

	public void notifyNoPaper() {
		for (PrinterListener listener : listeners) listener.notifyNoInk(station);
		System.out.println("(Printer Controller) we got NO Paper");
	}


	public void print(String receipt)  {
		for (char c : receipt.toCharArray()) {
			try {
				station.printer.print(c);
			} catch (EmptyException e) {
				// Notify attendant and stop printing, otherwise it will try to print every character and will show dialog box
				//Added in Iteration 3 @Simrat (Starts)
				System.out.println("Empty exception in print");
				isEmpty = true;
				break;
				//Added in Iteration 3 @Simrat (ends)
			} catch (OverloadException e) {
				//e.printStackTrace();
				System.out.println("Overload exception in print");
				isOverload = true;
				break;
			}
		}
		station.printer.cutPaper();
	}
}
