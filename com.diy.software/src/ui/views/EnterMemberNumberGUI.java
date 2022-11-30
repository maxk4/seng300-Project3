package ui.views;

import javax.swing.border.EmptyBorder;

import ui.CustomerUI;
import util.MembershipNumber;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class EnterMemberNumberGUI extends CustomerView {
	private static final long serialVersionUID = -2878096921110787780L;
	private JTextField txtMemberNumber;

	/**
	 * Create the 
	 */
	public EnterMemberNumberGUI(CustomerUI customer) {
		super(customer);
		setBackground(new Color(50, 126, 192));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JButton button_1 = new JButton("1");
		button_1.setBounds(11, 152, 75, 64);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "1";
				txtMemberNumber.setText(currValue);
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_2 = new JButton("2");
		button_2.setBounds(92, 152, 75, 64);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "2";
				txtMemberNumber.setText(currValue);
			}
		});
		button_2.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_7 = new JButton("7");
		button_7.setBounds(11, 11, 75, 64);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "7";
				txtMemberNumber.setText(currValue);
			}
		});
		button_7.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_8 = new JButton("8");
		button_8.setBounds(92, 11, 75, 64);
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "8";
				txtMemberNumber.setText(currValue);
			}
		});
		button_8.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_9 = new JButton("9");
		button_9.setBounds(173, 11, 75, 64);
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "9";
				txtMemberNumber.setText(currValue);
			}
		});
		button_9.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_4 = new JButton("4");
		button_4.setBounds(11, 82, 75, 64);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "4";
				txtMemberNumber.setText(currValue);
			}
		});
		button_4.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_6 = new JButton("6");
		button_6.setBounds(173, 81, 75, 64);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "6";
				txtMemberNumber.setText(currValue);
			}
		});
		button_6.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_5 = new JButton("5");
		button_5.setBounds(92, 82, 75, 64);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "5";
				txtMemberNumber.setText(currValue);
			}
		});
		button_5.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_3 = new JButton("3");
		button_3.setBounds(173, 151, 75, 64);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "3";
				txtMemberNumber.setText(currValue);
			}
		});
		button_3.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_0 = new JButton("0");
		button_0.setBounds(11, 222, 156, 64);
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "0";
				txtMemberNumber.setText(currValue);
			}
		});
		button_0.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_decimal = new JButton(".");
		button_decimal.setBounds(173, 222, 75, 64);
		button_decimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_decimal.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_Enter = new JButton("Enter");
		btnNewButton_Enter.setBounds(254, 81, 75, 205);
		btnNewButton_Enter.addActionListener(e -> {
			String currValue = txtMemberNumber.getText();
			int memberNumber = Integer.valueOf(currValue);
			if (MembershipNumber.checkMemNum(memberNumber)) {
				controller.setMember(memberNumber);
				controller.setView(CustomerUI.SCAN);
			}
		});
		btnNewButton_Enter.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_Del = new JButton("Del");
		button_Del.setBounds(254, 11, 75, 64);
		button_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue = currValue.substring(0, currValue.length() - 1);
				txtMemberNumber.setText(currValue);
			}
		});
		button_Del.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		setLayout(null);
		
		txtMemberNumber = new JTextField();
		txtMemberNumber.setBounds(11, 292, 318, 57);
		txtMemberNumber.setEditable(false);
		txtMemberNumber.setText("");
		txtMemberNumber.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		txtMemberNumber.setColumns(10);
		add(txtMemberNumber);
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
		add(btnNewButton_Enter);
		add(button_Del);
	}
}
