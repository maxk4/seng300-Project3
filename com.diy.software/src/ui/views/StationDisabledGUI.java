package ui.views;

import ui.CustomerUI;
import ui.views.util.CustomerView;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StationDisabledGUI extends CustomerView {

	private static final long serialVersionUID = 8489647036255738889L;

	/**
	 * Create the frame.
	 */
	public StationDisabledGUI(CustomerUI customer) {
		super(customer);
		title = "Station Disabled";
		setBounds(100, 100, 554, 431);
		setBackground(new Color(137, 150, 161));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel label_StationLocked_Text = new JLabel("Station Disabled");
		label_StationLocked_Text.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		
		JLabel label_LockIcon = new JLabel("");
		label_LockIcon.setIcon(new ImageIcon(StationDisabledGUI.class.getResource("/resources/icons8-lock-200.png")));
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(183)
					.addComponent(label_LockIcon)
					.addContainerGap(200, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(63)
					.addComponent(label_StationLocked_Text, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
					.addGap(65))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(label_LockIcon, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addGap(58)
					.addComponent(label_StationLocked_Text, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(103, Short.MAX_VALUE))
		);
		setLayout(gl_contentPane);
	}

}
