package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;


import util.CustomerUI;

import javax.swing.ImageIcon;

public class PayWithDebitGUI extends JFrame {
	private static final long serialVersionUID = 7114333266530075624L;
	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public PayWithDebitGUI(CustomerUI customer) {
		setTitle("Debit Payment");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 453, 238);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 139, 212));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel label_OrderTotal_Text = new JLabel("Order Total: $");
		label_OrderTotal_Text.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JLabel label_OrderTotal_Number = new JLabel("0.00");
		label_OrderTotal_Number.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JButton button_PayByChip = new JButton("Pay by Chip");
		button_PayByChip.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JButton button_PayByTap = new JButton("Pay by Tap");
		button_PayByTap.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JButton button_CancelPayment = new JButton("Cancel Payment");
		button_CancelPayment.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PayWithDebitGUI.class.getResource("/resources/icons8-debit-card-100.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button_PayByChip, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_PayByTap, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
								.addComponent(button_CancelPayment, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_OrderTotal_Text)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_OrderTotal_Number)
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
								.addComponent(label_OrderTotal_Text)
								.addComponent(label_OrderTotal_Number)))
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(button_PayByChip, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button_PayByTap, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_CancelPayment, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(6))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
