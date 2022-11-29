package simulation;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.diy.hardware.DoItYourselfStationAR;
import com.jimmyselectronics.OverloadException;

import util.AttendantUI;

public class MaintenanceSimulator {
	
	private AttendantUI attendant;
	private List<DoItYourselfStationAR> stations;
	private JFrame frame;

	public MaintenanceSimulator(AttendantUI attendant, List<DoItYourselfStationAR> stations) {
		this.attendant = attendant;
		this.stations = stations;
		
		frame = new JFrame();
		addWidgets();
		
		frame.setTitle("Maintanance Simulator");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void addWidgets() {
		JPanel container = new JPanel(new GridLayout(1, 2));
		
		JPanel ink = new JPanel();
		ink.setLayout(new BoxLayout(ink, BoxLayout.PAGE_AXIS));
		JLabel inkLabel = new JLabel("Stations: Click to add Ink");
		JTextField inkAmmountField = new JTextField();
		inkAmmountField.setToolTipText("Enter ammount to add");
		ink.add(inkLabel);
		ink.add(inkAmmountField);
		ink.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 16));
		for (int i = 0; i < stations.size(); i++) {
			DoItYourselfStationAR station = stations.get(i);
			JButton inkBtn = new JButton("Station " + (i + 1));
			inkBtn.addActionListener(e -> {
				try {
					String parse = "";
					for (char c : inkAmmountField.getText().toCharArray())
						if (Character.isDigit(c)) parse += c;
					station.printer.addInk(Integer.parseInt(parse));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			ink.add(inkBtn);
		}
		

		JPanel paper = new JPanel();
		paper.setLayout(new BoxLayout(paper, BoxLayout.PAGE_AXIS));
		JLabel paperLabel = new JLabel("Stations: Click to add Paper");
		JTextField paperAmmountField = new JTextField();
		paperAmmountField.setToolTipText("Enter ammount to add");
		paper.add(paperLabel);
		paper.add(paperAmmountField);
		for (int i = 0; i < stations.size(); i++) {
			DoItYourselfStationAR station = stations.get(i);
			JButton paperBtn = new JButton("Station " + (i + 1));
			paperBtn.addActionListener(e -> {
				try {
					String parse = "";
					for (char c : paperAmmountField.getText().toCharArray())
						if (Character.isDigit(c)) parse += c;
					station.printer.addPaper(Integer.parseInt(parse));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			paper.add(paperBtn);
		}
		
		container.add(ink);
		container.add(paper);
		
		container.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		frame.getContentPane().add(container);
	}

}
