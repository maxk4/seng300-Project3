package ui.views;

import javax.swing.border.EmptyBorder;

import com.diy.hardware.DoItYourselfStation;

import ui.CustomerUI;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PayWithCashGUI extends CustomerView {
	private static final long serialVersionUID = -7710462478741797757L;

	private final JLabel label_OrderTotal_Number, label_MoneyInserted_Number;
	
	/**
	 * Create the frame.
	 */
	public PayWithCashGUI(CustomerUI customer, DoItYourselfStation station) {
		super(customer);
		setBounds(100, 100, 656, 393);
		setBackground(new Color(65, 139, 212));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel label_OrderTotal_Text = new JLabel("Order Total: $");
		label_OrderTotal_Text.setIcon(new ImageIcon(PayWithCashGUI.class.getResource("/resources/icons8-dollar-coin-100.png")));
		label_OrderTotal_Text.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		label_OrderTotal_Number = new JLabel("0.00");
		label_OrderTotal_Number.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JLabel label_MoneyInserted_Text = new JLabel("Inserted: $");
		label_MoneyInserted_Text.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		label_MoneyInserted_Number = new JLabel("0.00");
		label_MoneyInserted_Number.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JLabel label_Cash_Icon = new JLabel("");
		label_Cash_Icon.setIcon(new ImageIcon(PayWithCashGUI.class.getResource("/resources/icons8-cash-100.png")));
		
		
		GroupLayout gl_contentPane = new GroupLayout(this);

		JButton button_CancelPayment = new JButton("Cancel Payment / Return to Order");
		button_CancelPayment.setBackground(new Color(94, 193, 255));
		button_CancelPayment.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		button_CancelPayment.addActionListener(e -> {
			controller.setView(CustomerUI.SCAN);
			station.coinSlot.disable();
			station.banknoteInput.disable();
			station.banknoteOutput.disable();
		});
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(label_MoneyInserted_Text, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_MoneyInserted_Number, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(464, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_OrderTotal_Text)
					.addGap(35)
					.addComponent(label_OrderTotal_Number)
					.addPreferredGap(ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
					.addComponent(label_Cash_Icon))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(button_CancelPayment, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label_Cash_Icon)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_OrderTotal_Text)
								.addComponent(label_OrderTotal_Number))))
					.addGap(52)
					.addComponent(button_CancelPayment, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(55)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_MoneyInserted_Text, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_MoneyInserted_Number, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(60))
		);
		setLayout(gl_contentPane);
	}
	
	public void update(long paid, long balance) {
		label_OrderTotal_Number.setText(String.format("%.2f", balance / 100d));
		label_MoneyInserted_Number.setText(String.format("%.2f", paid / 100d));
	}
}
