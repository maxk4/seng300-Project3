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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class PayWithCreditGUI extends CustomerView {

	private static final long serialVersionUID = -6094043016720414433L;

	private JLabel label_OrderTotal_Number;

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
		
		label_OrderTotal_Number = new JLabel("0.00");
		label_OrderTotal_Number.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		

		JButton button_CancelPayment = new JButton("Cancel Payment / Return to Order");
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
							.addComponent(label_OrderTotal_Number)
							.addPreferredGap(ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
							.addComponent(labe_CreditCard_Icon))
						.addComponent(button_CancelPayment, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(labe_CreditCard_Icon))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_CancelPayment, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(111, Short.MAX_VALUE))
		);
		setLayout(gl_contentPane);
	}
	
	public void update(long paid, long balance) {
		label_OrderTotal_Number.setText(String.format("%.2f", (balance - paid) / 100d));
	}
}
