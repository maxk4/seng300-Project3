package ui.views;

import javax.swing.border.EmptyBorder;

import ui.CustomerUI;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PurchaseBagsGUI extends CustomerView {
	
	private static final long serialVersionUID = -511473493557072211L;
	private JTextField textField_NumberOfBags;


	/**
	 * Create the 
	 */
	public PurchaseBagsGUI(CustomerUI customer) {
		super(customer);
		setBounds(100, 100, 342, 388);
		setBackground(new Color(50, 126, 192));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JButton button_1 = new JButton("1");
		button_1.setBounds(11, 152, 75, 64);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "1";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_2 = new JButton("2");
		button_2.setBounds(92, 152, 75, 64);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "2";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_2.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_7 = new JButton("7");
		button_7.setBounds(11, 11, 75, 64);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "7";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_7.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_8 = new JButton("8");
		button_8.setBounds(92, 11, 75, 64);
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "8";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_8.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_9 = new JButton("9");
		button_9.setBounds(173, 11, 75, 64);
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "9";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_9.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_4 = new JButton("4");
		button_4.setBounds(11, 82, 75, 64);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "4";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_4.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_6 = new JButton("6");
		button_6.setBounds(173, 81, 75, 64);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "6";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_6.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_5 = new JButton("5");
		button_5.setBounds(92, 82, 75, 64);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "5";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_5.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_3 = new JButton("3");
		button_3.setBounds(173, 151, 75, 64);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "3";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_3.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_0 = new JButton("0");
		button_0.setBounds(11, 222, 156, 64);
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += "0";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_0.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_decimal = new JButton(".");
		button_decimal.setBounds(173, 222, 75, 64);
		button_decimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_NumberOfBags.getText();
				currValue += ".";
				textField_NumberOfBags.setText(currValue);
			}
		});
		button_decimal.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_Enter = new JButton("Enter");
		button_Enter.setBounds(254, 81, 75, 205);
		button_Enter.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		button_Enter.addActionListener(e -> {
			controller.setView(CustomerUI.SCAN);
			controller.purchaseBags(Integer.parseInt(textField_NumberOfBags.getText()));
		});
		
		JButton button_Del = new JButton("Del");
		button_Del.setBounds(254, 11, 75, 64);
		button_Del.addActionListener(e -> {
			String curValue = textField_NumberOfBags.getText();
			if (curValue.length() > 0)
				textField_NumberOfBags.setText(curValue.substring(0, curValue.length() - 1));
		});
		button_Del.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		textField_NumberOfBags = new JTextField();
		textField_NumberOfBags.setBounds(11, 292, 318, 57);
		textField_NumberOfBags.setEditable(false);
		textField_NumberOfBags.setText("");
		textField_NumberOfBags.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		textField_NumberOfBags.setColumns(10);
		add(textField_NumberOfBags);
		add(button_4);
		add(button_5);
		add(button_6);
		add(button_7);
		add(button_8);
		add(button_9);
		add(button_0);
		add(button_1);
		add(button_2);
		add(button_decimal);
		add(button_3);
		add(button_Enter);
		add(button_Del);
	}

}