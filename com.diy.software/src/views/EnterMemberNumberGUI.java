package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.CustomerUI;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class EnterMemberNumberGUI extends JFrame {
	private static final long serialVersionUID = -2878096921110787780L;
	private JPanel contentPane;
	private JTextField textField_MemberNumber;

	/**
	 * Create the 
	 */
	public EnterMemberNumberGUI(CustomerUI customer) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Set default close operation so that customer is returned to the Scan Screen
		WindowListener exitListener = new WindowAdapter() {

		    @Override
		    public void windowClosing(WindowEvent e) {
				customer.startScanning();
		    }
		};
		addWindowListener(exitListener);
		
		
		setBounds(100, 100, 342, 388);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 126, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton button_1 = new JButton("1");
		button_1.setBounds(11, 152, 75, 64);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "1";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_2 = new JButton("2");
		button_2.setBounds(92, 152, 75, 64);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "2";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_2.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_7 = new JButton("7");
		button_7.setBounds(11, 11, 75, 64);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "7";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_7.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_8 = new JButton("8");
		button_8.setBounds(92, 11, 75, 64);
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "8";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_8.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_9 = new JButton("9");
		button_9.setBounds(173, 11, 75, 64);
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "9";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_9.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_4 = new JButton("4");
		button_4.setBounds(11, 82, 75, 64);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "4";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_4.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_6 = new JButton("6");
		button_6.setBounds(173, 81, 75, 64);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "6";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_6.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_5 = new JButton("5");
		button_5.setBounds(92, 82, 75, 64);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "5";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_5.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_3 = new JButton("3");
		button_3.setBounds(173, 151, 75, 64);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "3";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_3.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_0 = new JButton("0");
		button_0.setBounds(11, 222, 156, 64);
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "0";
				textField_MemberNumber.setText(currValue);
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
		
		JButton button_Enter = new JButton("Enter");
		button_Enter.setBounds(254, 81, 75, 205);
		button_Enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				Integer memberNumber = Integer.valueOf(currValue);
				memberNumber = customer.checkMemberNumber(memberNumber);
				customer.useMemberNumber(memberNumber);
				textField_MemberNumber.setText("");
				customer.startScanning();
			}
		});
		button_Enter.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_Del = new JButton("Del");
		button_Del.setBounds(254, 11, 75, 64);
		button_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue = currValue.substring(0, currValue.length() - 1);
				textField_MemberNumber.setText(currValue);
			}
		});
		button_Del.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		contentPane.setLayout(null);
		
		textField_MemberNumber = new JTextField();
		textField_MemberNumber.setBounds(11, 292, 318, 57);
		textField_MemberNumber.setEditable(false);
		textField_MemberNumber.setText("");
		textField_MemberNumber.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		textField_MemberNumber.setColumns(10);
		contentPane.add(textField_MemberNumber);
		contentPane.add(button_4);
		contentPane.add(button_5);
		contentPane.add(button_6);
		contentPane.add(button_7);
		contentPane.add(button_8);
		contentPane.add(button_9);
		contentPane.add(button_0);
		contentPane.add(button_1);
		contentPane.add(button_2);
		contentPane.add(button_decimal);
		contentPane.add(button_3);
		contentPane.add(button_Enter);
		contentPane.add(button_Del);
	}
}
