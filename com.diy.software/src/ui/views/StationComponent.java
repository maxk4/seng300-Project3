package ui.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class StationComponent extends JPanel {
	
	public static final String 
			WEIGHT_DISCREPANCY = "Weight Discrepancy",
			LOW_INK = "Low Ink",
			LOW_PAPER = "Low Paper",
			ADD_OWN_BAG = "Added Own Bag",
			OUT_OF_CHANGE = "Out of Change";
	
	public static interface Action {
		public void approve();
		public void deny();
	}
	
	private List<String> messages;
	private Map<String, Action> actions;
	private List<String> alerts;
	private JLabel msgField;
	private JLabel alertField;
	
	public StationComponent(String descriptor) {
		super();
		this.messages = new ArrayList<String>();
		this.alerts = new ArrayList<String>();
		this.actions = new HashMap<String, Action>();
		
		this.msgField = new JLabel();
		this.msgField.setMinimumSize(new Dimension(50, 20));
		
		this.alertField = new JLabel();
		this.alertField.setSize(new Dimension(50, 20));
		
		JLabel title = new JLabel(descriptor);
		JButton approve = new JButton("Approve");
		approve.addActionListener(e -> {
			if (messages.isEmpty()) return;
			actions.get(messages.get(0)).approve();
		});
		
		JButton approveAll = new JButton("Approve All");
		approveAll.addActionListener(e -> {
			for (Action a : actions.values()) a.approve();
		});
		
		JButton deny = new JButton("Deny");
		deny.addActionListener(e -> {
			if (messages.isEmpty()) return;
			actions.get(messages.get(0)).deny();
		});
		
		JButton denyAll = new JButton("Deny All");
		denyAll.addActionListener(e -> {
			for (Action a : actions.values()) a.deny();
		});
		
		this.setSize(400, 50);

		this.setLayout(new GridLayout(1, 7));
		this.add(title);
		this.add(alertField);
		this.add(msgField);
		this.add(approve);
		this.add(approveAll);
		this.add(deny);
		this.add(denyAll);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setBackground(new Color(0xFF, 0xFF, 0xFF));
	}

	public void update() {
		StringBuilder allMessagesBuilder = new StringBuilder();
		for (String msg : messages) allMessagesBuilder.append(msg + ", ");
		String allMessages = allMessagesBuilder.toString();
		if (allMessages.length() > 0) msgField.setText(allMessages.substring(0, allMessages.length() - 2));
		else msgField.setText("");
		
		StringBuilder allAlertsBuilder = new StringBuilder();
		for (String msg : alerts) allAlertsBuilder.append(msg + ", ");
		String allAlerts = allAlertsBuilder.toString();
		if (allAlerts.length() > 0) alertField.setText(allAlerts.substring(0, allAlerts.length() - 2));
		else alertField.setText("");
		
		this.revalidate();
		this.repaint();
	}
	
	public void notifyDectected(String eventMessage, Action action) {
		messages.add(eventMessage);
		actions.put(eventMessage, action);
		update();
	}
	
	public void notifyResolved(String eventMessage) {
		actions.remove(eventMessage);
		messages.remove(eventMessage);
		update();
	}
	
	public void alert(String alertMessage, boolean resolved) {
		if (resolved) alerts.remove(alertMessage);
		else alerts.add(alertMessage);
		update();
	}
}