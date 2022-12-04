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
import ui.views.util.CustomerView;

import javax.swing.ImageIcon;

public class PayWithGiftCardGUI extends CustomerView {
	private static final long serialVersionUID = 7114333266530075624L;
	
	private JLabel label_OrderTotal_Number;
	
	/**
	 * Create the frame.
	 */
	public PayWithGiftCardGUI(CustomerUI customer, DoItYourselfStation station) {
		super(customer);
		title = "Gift Card Payment";
		setBounds(100, 100, 453, 238);
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
			station.cardReader.disable();
		});
		button_CancelPayment.addActionListener(e -> {
			controller.setView(CustomerUI.SCAN);
		});
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PayWithGiftCardGUI.class.getResource("/resources/icons8-mastercard-credit-card-100.png")));
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_OrderTotal_Text)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_OrderTotal_Number)
							.addPreferredGap(ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
							.addComponent(lblNewLabel_2))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(button_CancelPayment, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)))
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
					.addComponent(button_CancelPayment, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
					.addGap(73))
		);
		setLayout(gl_contentPane);
	}
	
	public void update(long paid, long balance) {
		label_OrderTotal_Number.setText(String.format("%.2f", (balance - paid) / 100d));
	}
}
