package views;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import util.AttendantUI;
import util.CustomerUI;

@SuppressWarnings("serial")
public class AttendantGUI extends JFrame {

	private List<StationComponent> stationComponents;
	private JPanel contentPane;
	private JPanel stationPanel;

	/**
	 * Create the frame.
	 */
	public AttendantGUI(AttendantUI attendant) {
		super();
		stationComponents = new ArrayList<StationComponent>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Attendant GUI");
		setBounds(100, 100, 593, 298);
		contentPane = new JPanel();
		
		stationPanel = new JPanel();
		stationPanel.setLayout(new BoxLayout(stationPanel, BoxLayout.PAGE_AXIS));
		stationPanel.setAlignmentX(LEFT_ALIGNMENT);
		stationPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		
		contentPane.add(stationPanel);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		
		
		getContentPane().add(contentPane);
		pack();
		setVisible(true);
	}
	
	public void update() {
		stationPanel.removeAll();
		for (StationComponent sc : stationComponents) stationPanel.add(sc);
		revalidate();
		repaint();
		pack();
	}
	
	public void addStation(CustomerUI customer) {
		stationComponents.add(new StationComponent(customer, "Station " + (stationComponents.size() + 1)));
		update();
	}
	
	public void notifyWeightDiscrepancyDetected(CustomerUI customer) {
		int index = indexOf(customer);
		if (index < 0) return;
		StationComponent sc = stationComponents.get(index);
		sc.notifyDectected(StationComponent.WEIGHT_DISCREPANCY, new StationComponent.Action() {
			@Override
			public void deny() {
				sc.notifyResolved(StationComponent.WEIGHT_DISCREPANCY);
			}
			
			@Override
			public void approve() {
				customer.notifyWeightApproved();
				sc.notifyResolved(StationComponent.WEIGHT_DISCREPANCY);
			}
		});
	}
	
	public void notifyWeightDiscrepancyResolved(CustomerUI customer) {
		int index = indexOf(customer);
		if (index < 0) return;
		StationComponent sc = stationComponents.get(index);
		sc.notifyResolved(StationComponent.WEIGHT_DISCREPANCY);
	}
	
	public void notifyLowInkDetected(CustomerUI customer) {
		int index = indexOf(customer);
		if (index < 0) return;
		StationComponent sc = stationComponents.get(index);
		sc.alert(StationComponent.LOW_INK, false);
	}
	
	public void notifyLowInkResolved(CustomerUI customer) {
		int index = indexOf(customer);
		if (index < 0) return;
		StationComponent sc = stationComponents.get(index);
		sc.alert(StationComponent.LOW_INK, true);
	}
	
	public void notifyLowPaperDetected(CustomerUI customer) {
		int index = indexOf(customer);
		if (index < 0) return;
		StationComponent sc = stationComponents.get(index);
		sc.alert(StationComponent.LOW_PAPER, false);
	}
	
	public void notifyLowPaperResolved(CustomerUI customer) {
		int index = indexOf(customer);
		if (index < 0) return;
		StationComponent sc = stationComponents.get(index);
		sc.alert(StationComponent.LOW_PAPER, true);
	}
	
	public void notifyOutOfChangeDetected(CustomerUI customer) {
		int index = indexOf(customer);
		if (index < 0) return;
		StationComponent sc = stationComponents.get(index);
		sc.alert(StationComponent.OUT_OF_CHANGE, false);
	}
	
	public void notifyOutOfChangeResolved(CustomerUI customer) {
		int index = indexOf(customer);
		if (index < 0) return;
		StationComponent sc = stationComponents.get(index);
		sc.alert(StationComponent.OUT_OF_CHANGE, true);
	}

	public void notifyAddOwnBag(CustomerUI customer) {
		int index = indexOf(customer);
		if (index < 0) return;
		StationComponent sc = stationComponents.get(index);
		sc.notifyDectected(StationComponent.ADD_OWN_BAG, new StationComponent.Action() {
			@Override
			public void deny() {
				customer.denyBag();
				sc.notifyResolved(StationComponent.ADD_OWN_BAG);
			}
			
			@Override
			public void approve() {
				customer.approveBag();
				sc.notifyResolved(StationComponent.ADD_OWN_BAG);
			}
		});
	}
	
	private int indexOf(CustomerUI customer) {
		for (int i = 0; i < stationComponents.size(); i++) if (stationComponents.get(i).customer == customer) return i;
		return -1;
	}
	
}
