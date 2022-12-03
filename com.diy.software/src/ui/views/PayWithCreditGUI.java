package ui.views;

import javax.swing.border.EmptyBorder;

import com.diy.hardware.DoItYourselfStation;

import ui.CustomerUI;
import ui.views.util.CustomerView;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class PayWithCreditGUI extends CustomerView {

	private static final long serialVersionUID = -6094043016720414433L;


	/**
	 * Create the frame.
	 */
	public PayWithCreditGUI(CustomerUI customer, DoItYourselfStation station) {
		super(customer);
		
		this.title = "Credit Payment";
		
		setBackground(new Color(65, 139, 212));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel label_OrderTotal_Text = new JLabel("Order Total: $");
		label_OrderTotal_Text.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JLabel label_OrderTotal_Number = new JLabel("0.00");
		label_OrderTotal_Number.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JLabel label_SelectCard_Text = new JLabel("Select Card:");
		label_SelectCard_Text.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JComboBox<?> comboBox_SelectCardType = new JComboBox<Object>();
		
		JButton button_PayByChip = new JButton("Pay by Chip");
		button_PayByChip.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JButton button_PayByTap = new JButton("Pay by Tap");
		button_PayByTap.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		

		JButton button_CancelPayment = new JButton("Cancel Payment");
		button_CancelPayment.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_CancelPayment.addActionListener(e -> {
			controller.setView(CustomerUI.SCAN);
		});
		
		JLabel labe_CreditCard_Icon = new JLabel("");
		labe_CreditCard_Icon.setIcon(new ImageIcon(PayWithCreditGUI.class.getResource("/resources/icons8-mastercard-credit-card-100.png")));
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_OrderTotal_Text)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_OrderTotal_Number))
						.addComponent(label_SelectCard_Text)
						.addComponent(comboBox_SelectCardType, 0, 227, Short.MAX_VALUE)
						.addComponent(button_PayByChip, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(button_PayByTap, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
						.addComponent(labe_CreditCard_Icon))
					.addContainerGap())
				.addComponent(button_CancelPayment, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_OrderTotal_Text)
								.addComponent(label_OrderTotal_Number))
							.addGap(37)
							.addComponent(label_SelectCard_Text)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_SelectCardType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(labe_CreditCard_Icon))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(button_PayByTap, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button_PayByChip, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_CancelPayment, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(5))
		);
		setLayout(gl_contentPane);
	}
}
