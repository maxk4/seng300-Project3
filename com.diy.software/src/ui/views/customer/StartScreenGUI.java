package ui.views.customer;

import javax.swing.border.EmptyBorder;

import com.diy.hardware.DoItYourselfStation;

import ui.CustomerUI;
import ui.views.util.CustomerView;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class StartScreenGUI extends CustomerView {

	private static final long serialVersionUID = 6812935065908490923L;

	/**
	 * Create the frame.
	 */
	public StartScreenGUI(CustomerUI customer, DoItYourselfStation station) {
		super(customer);
		setBounds(100, 100, 355, 302);
		setBackground(new Color(50, 126, 192));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JButton button_StartScanning = new JButton("Start Scanning");
		button_StartScanning.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-barcode-100.png")));
		button_StartScanning.addActionListener(e -> {
			controller.beginSession();
			controller.setView(CustomerUI.SCAN);
			station.mainScanner.enable();
			station.handheldScanner.enable();
		});
		
		JLabel label_DIYStation_Text = new JLabel("DIYourselfStation");
		label_DIYStation_Text.setFont(new Font("Lucida Grande", Font.BOLD, 23));
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(button_StartScanning, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
						.addComponent(label_DIYStation_Text))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_DIYStation_Text)
					.addGap(42)
					.addComponent(button_StartScanning, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(gl_contentPane);
	}

}
