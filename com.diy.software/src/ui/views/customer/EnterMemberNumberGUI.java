package ui.views.customer;


import cart.ScanItemListener;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.opeechee.Card;
import com.jimmyselectronics.opeechee.CardReaderListener;
import simulation.Simulation;
import ui.CustomerUI;
import ui.views.util.CustomerView;
import util.MembershipDatabase;
import membership.MembershipCardListener;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class EnterMemberNumberGUI extends CustomerView {
	private static final long serialVersionUID = -2878096921110787780L;
	public JTextField textField_MemberNumber;
	public JButton button_Enter;
	//private MembershipListener membership_listener = new MembershipListener();
	JComboBox comboBox_MemberCardsInWallet = new JComboBox();
	JComboBox comboBox_MemberCard_Barcodes = new JComboBox();
	//Creating a combo box for Customer to select the membership card

	/**
	 * Create the Membership GUI, for customer to type, scan, swipe the membership cards
	 */
	public EnterMemberNumberGUI(CustomerUI customer) {
		super(customer);
		setBackground(new Color(94, 193, 255));

		//Setting the combo boxes for membership cards, membership barcodes (Ends)

		//Text Field
		textField_MemberNumber = new JTextField();
		textField_MemberNumber.setColumns(10);

		//Creating the keypad buttons (Starts)
		JButton button_0 = new JButton("0");
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "0";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_0.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_1 = new JButton("1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "1";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_2 = new JButton("2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "2";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_2.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_3 = new JButton("3");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "3";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_3.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_4 = new JButton("4");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "4";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_4.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_5 = new JButton("5");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "5";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_5.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_6 = new JButton("6");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "6";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_6.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_7 = new JButton("7");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "7";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_7.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_8 = new JButton("8");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "8";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_8.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_9 = new JButton("9");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += "9";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_9.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_Decimal = new JButton(".");
		button_Decimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				currValue += ".";
				textField_MemberNumber.setText(currValue);
			}
		});
		button_Decimal.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		//Creating the keypad buttons (Ends)
		
		//Enter Button (starts)
		/*
		 * Enter button, once pressed, gets the value from the text field, converts that to integer value, checks if that exists in membership database and display on the scan GUI (main GUI)
		 */
		button_Enter = new JButton("Enter");
		button_Enter.addActionListener(e -> {
			String currValue = textField_MemberNumber.getText();
			boolean dataGood = true;
			if(Objects.equals(currValue, "")) //nothing added on GUI
			{
				//Try again
				customer.useMemberName("Enter a valid value");
				dataGood = false;
			}//else if some alphabet is entered, reject
			else
			{
				//there is something entered in the text field, check if that is digit or alphabet
				//if alphabet is found, display error message and exit.
				char [] chars = currValue.toCharArray();
				for (char c : chars)
				{
					if(!Character.isDigit(c))
					{
						dataGood = false;
						customer.useMemberName("No alphabets are allowed");
						break;
					}
				}
			}
			//if the data is good only then we proceed otherwise do nothing, reset the text field
			if(dataGood)
			{
				//that means data is good (not contains any alphabets, so we can test
				Integer memberNumber = Integer.valueOf(currValue);
				if (MembershipDatabase.MEMBER_DATABASE.containsKey(memberNumber)) {
					String customerName = MembershipDatabase.MEMBER_DATABASE.get(memberNumber);
					customer.useMemberName("Cx: " + customerName);

				} else {
					//Member Do not exist in the database
					customer.useMemberName("Invalid Membership Number");
				}
			}
			textField_MemberNumber.setText("");
			//customer.startScanning();
			controller.setView(CustomerUI.SCAN);

		});
		button_Enter.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		//Enter Button (ends)

		//Delete Button (starts)
		JButton button_Del = new JButton("Delete");
		button_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_MemberNumber.getText();
				if(Objects.equals(currValue, ""))
				{
					//Try again
					currValue = " ";
				}
				currValue = currValue.substring(0, currValue.length() - 1);
				textField_MemberNumber.setText(currValue);
			}
		});
		button_Del.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		//Delete Button (Ends)

		//Updated in 3rd Iteration @author Simrat Benipal (start)
		//Added in 3rd Iteration @author Simrat Benipal
		//Cancel Button (starts)
		/*
		 * Cancel button, once pressed, cancels the operation and returns to main scan GUI
		 */
		JButton btnNewButton_Cancel = new JButton("Cancel");
		//btnNewButton_Cancel.setBounds(254, 81, 100, 60);
		btnNewButton_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MemberNumber.setText("");
				//	customer.startScanning();
				customer.useMemberName("Operation Canceled");
				controller.setView(CustomerUI.SCAN);
			}
		});
		btnNewButton_Cancel.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		//Swipe Card Button (Ends)

		//Setting the Layout of the GUI Interface, adding buttons and labels onto GUI
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(textField_MemberNumber)
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
									.addComponent(button_Del, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
					.addContainerGap(62, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
						.addComponent(button_Del, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
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
					.addComponent(textField_MemberNumber, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(30))
		);
		setLayout(groupLayout);
	}
}
