package ui.views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.diy.hardware.DoItYourselfStation;

import ui.CustomerUI;

import javax.swing.ImageIcon;

public class PayWithDebitGUI extends CustomerView {
	private static final long serialVersionUID = 7114333266530075624L;
	/**
	 * Create the frame.
	 */
	public PayWithDebitGUI(CustomerUI customer, DoItYourselfStation station) {
		super(customer);
		title = "Debit Payment";
		setBounds(100, 100, 453, 238);
		setBackground(new Color(65, 139, 212));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Order Total: $");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JLabel lblNewLabel_1 = new JLabel("0.00");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JButton btnNewButton = new JButton("Pay by Chip");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JButton btnPayByTap = new JButton("Pay by Tap");
		btnPayByTap.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JButton btnNewButton_1 = new JButton("Cancel Payment");
		btnNewButton_1.addActionListener(e -> {
			controller.setView(CustomerUI.SCAN);
			station.cardReader.disable();
		});
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PayWithDebitGUI.class.getResource("/resources/icons8-debit-card-100.png")));
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnPayByTap, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
							.addComponent(lblNewLabel_2)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1)))
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnPayByTap, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(6))
		);
		setLayout(gl_contentPane);
	}
}
