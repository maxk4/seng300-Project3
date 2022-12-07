package ui.views.attendant;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.diy.hardware.DoItYourselfStation;

import ui.AttendantUI;
import ui.CustomerUI;
import ui.views.util.AttendantView;
import ui.views.util.StationComponent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class AttendantGUI extends AttendantView {

	private List<StationComponent> stationComponents;
	private Map<DoItYourselfStation, StationComponent> componentMap = new HashMap<DoItYourselfStation, StationComponent>();
	private Map<StationComponent, DoItYourselfStation> stationMap = new HashMap<StationComponent, DoItYourselfStation>();
	private JPanel stationPanel;
	private AttendantUI attendant;
	
	private JFrame parent;

	/**
	 * Create the frame.
	 */
	public AttendantGUI(AttendantUI attendant, JFrame frame) {
		super(attendant, frame);
		this.attendant = attendant;
		this.parent = frame;
		stationComponents = new ArrayList<StationComponent>();
		title = "Attendant GUI";
		setBounds(100, 100, 593, 298);
		setBackground(new Color(65, 139, 212));
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		container.setBackground(new Color(65, 139, 212));
		
		JButton logout = new JButton("Logout");
		logout.addActionListener(e -> {
			attendant.setView(AttendantUI.LOGIN);
		});
		
		stationPanel = new JPanel();
		stationPanel.setLayout(new BoxLayout(stationPanel, BoxLayout.PAGE_AXIS));
		stationPanel.setAlignmentX(LEFT_ALIGNMENT);
		stationPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		stationPanel.setBackground(new Color(65, 139, 212));
		
		container.add(logout);
		container.add(stationPanel);

		add(container);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	
	public void update() {
		stationPanel.removeAll();
		for (StationComponent sc : stationComponents) stationPanel.add(sc);
		parent.revalidate();
		parent.repaint();
		parent.pack();
	}
	
	public void addStation(DoItYourselfStation station) {
		StationComponent sc = new StationComponent(stationComponents.size() + 1, station, this, attendant);
		stationComponents.add(sc);
		componentMap.put(station, sc);
		stationMap.put(sc, station);
		update();
	}
	
	public void requestOpenStation(DoItYourselfStation station) {
		controller.setView(new CustomerCart(station, attendant, parent, controller.requestProductList(station)));
	}
	
	public void notifyWeightDiscrepancyDetected(DoItYourselfStation station) {
		StationComponent sc = componentMap.get(station);
		sc.notifyDectected(StationComponent.WEIGHT_DISCREPANCY, new StationComponent.Action() {
			@Override
			public void deny() {
				sc.notifyResolved(StationComponent.WEIGHT_DISCREPANCY);
			}
			
			@Override
			public void approve() {
				attendant.approveWeight(station);
				sc.notifyResolved(StationComponent.WEIGHT_DISCREPANCY);
			}
		});
	}

	public void notifyWeightDiscrepancyResolved(DoItYourselfStation station) {
		StationComponent sc = componentMap.get(station);
		sc.notifyResolved(StationComponent.WEIGHT_DISCREPANCY);
	}
	
	public void notifyLowInkDetected(DoItYourselfStation station) {
		StationComponent sc = componentMap.get(station);
		sc.alert(StationComponent.LOW_INK, false);
	}
	
	public void notifyLowInkResolved(DoItYourselfStation station) {
		StationComponent sc = componentMap.get(station);
		sc.alert(StationComponent.LOW_INK, true);
	}
	
	public void notifyLowPaperDetected(DoItYourselfStation station) {
		StationComponent sc = componentMap.get(station);
		sc.alert(StationComponent.LOW_PAPER, false);
	}
	
	public void notifyLowPaperResolved(DoItYourselfStation station) {
		StationComponent sc = componentMap.get(station);
		sc.alert(StationComponent.LOW_PAPER, true);
	}
	
	public void notifyOutOfChangeDetected(DoItYourselfStation station) {
		StationComponent sc = componentMap.get(station);
		sc.alert(StationComponent.OUT_OF_CHANGE, false);
	}
	
	public void notifyOutOfChangeResolved(DoItYourselfStation station) {
		StationComponent sc = componentMap.get(station);
		sc.alert(StationComponent.OUT_OF_CHANGE, true);
	}

	public void notifyAddOwnBag(DoItYourselfStation station) {
		StationComponent sc = componentMap.get(station);
		sc.notifyDectected(StationComponent.ADD_OWN_BAG, new StationComponent.Action() {
			@Override
			public void deny() {
				attendant.denyOwnBag(station);
				sc.notifyResolved(StationComponent.ADD_OWN_BAG);
			}
			
			@Override
			public void approve() {
				attendant.approveOwnBag(station);
				sc.notifyResolved(StationComponent.ADD_OWN_BAG);
			}
		});
	}

	public void notifyDisableCustomerMachine(StationComponent sc){
		DoItYourselfStation station = stationMap.get(sc);
		attendant.disableStation(station);
	}
	public void notifyEnableCustomerMachine(StationComponent sc){
		DoItYourselfStation station = stationMap.get(sc);
		attendant.enableStation(station);
	}

	public void notifyPrintFailure(DoItYourselfStation station) {
		StationComponent sc = componentMap.get(station);
		sc.notifyDectected(StationComponent.PRINT_FAILURE, new StationComponent.Action() {
			@Override
			public void deny() {
				attendant.denyOwnBag(station);
				sc.notifyResolved(StationComponent.PRINT_FAILURE);
			}
			
			@Override
			public void approve() {
				attendant.approveOwnBag(station);
				sc.notifyResolved(StationComponent.PRINT_FAILURE);
			}
		});
	}

}
