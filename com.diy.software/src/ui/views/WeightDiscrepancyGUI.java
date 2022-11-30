package ui.views;

import javax.swing.border.EmptyBorder;

import ui.CustomerUI;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class WeightDiscrepancyGUI extends CustomerView {

	private static final long serialVersionUID = 8489647036255738889L;

	/**
	 * Create the frame.
	 */
	public WeightDiscrepancyGUI(CustomerUI customer) {
		super(customer);
		title = "Weight Descrepancy";
		setBounds(100, 100, 554, 431);
		setBackground(new Color(206, 103, 94));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Station Locked for Weight Descrepancy");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(WeightDiscrepancyGUI.class.getResource("/resources/icons8-lock-200.png")));
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(183)
					.addComponent(lblNewLabel_1)
					.addContainerGap(200, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(63)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
					.addGap(65))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addGap(58)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(103, Short.MAX_VALUE))
		);
		setLayout(gl_contentPane);
	}

}
