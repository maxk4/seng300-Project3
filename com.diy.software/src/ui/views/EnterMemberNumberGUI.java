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

		
		JButton btnNewButton_1 = new JButton("1");
		btnNewButton_1.setBounds(11, 152, 75, 64);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "1";
				txtMemberNumber.setText(currValue);
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_2 = new JButton("2");
		btnNewButton_2.setBounds(92, 152, 75, 64);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "2";
				txtMemberNumber.setText(currValue);
			}
		});
		btnNewButton_2.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_7 = new JButton("7");
		btnNewButton_7.setBounds(11, 11, 75, 64);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "7";
				txtMemberNumber.setText(currValue);
			}
		});
		btnNewButton_7.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_8 = new JButton("8");
		btnNewButton_8.setBounds(92, 11, 75, 64);
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "8";
				txtMemberNumber.setText(currValue);
			}
		});
		btnNewButton_8.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_9 = new JButton("9");
		btnNewButton_9.setBounds(173, 11, 75, 64);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "9";
				txtMemberNumber.setText(currValue);
			}
		});
		btnNewButton_9.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_4 = new JButton("4");
		btnNewButton_4.setBounds(11, 82, 75, 64);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "4";
				txtMemberNumber.setText(currValue);
			}
		});
		btnNewButton_4.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_6 = new JButton("6");
		btnNewButton_6.setBounds(173, 81, 75, 64);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "6";
				txtMemberNumber.setText(currValue);
			}
		});
		btnNewButton_6.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_5 = new JButton("5");
		btnNewButton_5.setBounds(92, 82, 75, 64);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "5";
				txtMemberNumber.setText(currValue);
			}
		});
		btnNewButton_5.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_3 = new JButton("3");
		btnNewButton_3.setBounds(173, 151, 75, 64);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "3";
				txtMemberNumber.setText(currValue);
			}
		});
		btnNewButton_3.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_0 = new JButton("0");
		btnNewButton_0.setBounds(11, 222, 156, 64);
		btnNewButton_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue += "0";
				txtMemberNumber.setText(currValue);
			}
		});
		btnNewButton_0.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton btnNewButton_decimal = new JButton(".");
		btnNewButton_decimal.setBounds(173, 222, 75, 64);
		btnNewButton_decimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_decimal.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
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
		
		JButton btnNewButton_Del = new JButton("Del");
		btnNewButton_Del.setBounds(254, 11, 75, 64);
		btnNewButton_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue = currValue.substring(0, currValue.length() - 1);
				txtMemberNumber.setText(currValue);
			}
		});
		btnNewButton_Del.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		setLayout(null);
		
		txtMemberNumber = new JTextField();
		txtMemberNumber.setBounds(11, 292, 318, 57);
		txtMemberNumber.setEditable(false);
		txtMemberNumber.setText("");
		txtMemberNumber.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		txtMemberNumber.setColumns(10);
		add(txtMemberNumber);
		add(btnNewButton_4);
		add(btnNewButton_5);
		add(btnNewButton_6);
		add(btnNewButton_7);
		add(btnNewButton_8);
		add(btnNewButton_9);
		add(btnNewButton_0);
		add(btnNewButton_1);
		add(btnNewButton_2);
		add(btnNewButton_decimal);
		add(btnNewButton_3);
		add(btnNewButton_Enter);
		add(btnNewButton_Del);
	}
}
