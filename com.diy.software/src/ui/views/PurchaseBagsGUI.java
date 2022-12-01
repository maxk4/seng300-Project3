package ui.views;


import ui.CustomerUI;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PurchaseBagsGUI extends CustomerView {
	private static final long serialVersionUID = -2878096921110787780L;
	private JTextField textField_NumberOfBags;

	/**
	 * Create the 
	 */
	public PurchaseBagsGUI(CustomerUI customer) {
		super(customer);
		setBackground(new Color(94, 193, 255));
		
		
		JButton button_0 = new JButton("0");
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "0";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_0.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_1 = new JButton("1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "1";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_2 = new JButton("2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "2";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_2.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_3 = new JButton("3");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "3";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_3.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_4 = new JButton("4");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "4";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_4.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_5 = new JButton("5");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "5";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_5.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_6 = new JButton("6");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "6";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_6.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_7 = new JButton("7");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "7";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_7.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_8 = new JButton("8");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "8";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_8.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_9 = new JButton("9");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "9";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_9.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_Decimal = new JButton(".");
		button_Decimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += ".";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_Decimal.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_Enter = new JButton("Enter");
		button_Enter.addActionListener(e -> {
			controller.setView(CustomerUI.SCAN);
			controller.purchaseBags(Integer.parseInt(textField_NumberOfBags.getText()));
		});
		button_Enter.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_Del = new JButton("Del");
		button_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue = currValue.substring(0, currValue.length() - 1);
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_Del.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		textField_NumberOfBags = new JTextField();
		textField_NumberOfBags.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_NumberOfBags)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(button_0, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_Decimal, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(button_Enter, 0, 0, Short.MAX_VALUE)
								.addComponent(button_Del, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(173, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_Del, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_Decimal, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_0, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
						.addComponent(button_Enter, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_NumberOfBags, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(175))
		);
		setLayout(groupLayout);
	}
}
