package ui.views;


import cart.ScanItemListener;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.opeechee.Card;
import com.jimmyselectronics.opeechee.CardReaderListener;
import simulation.Simulation;
import ui.CustomerUI;
import util.MembershipDatabase;
import membership.MembershipListener;

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
	private JTextField textField_MemberNumber;
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
		
		JButton button_Enter = new JButton("Enter");
		button_Enter.addActionListener(e -> {
			String currValue = textField_MemberNumber.getText();
			if(Objects.equals(currValue, ""))
			{
				//Try again
				currValue = "-1";
			}
			Integer memberNumber = Integer.valueOf(currValue);
			//Signal the Listener
			//membership_listener.cardTyped(memberNumber);
			if(MembershipDatabase.MEMBER_DATABASE.containsKey(memberNumber))
			{
				String customerName = MembershipDatabase.MEMBER_DATABASE.get(memberNumber);
				customer.useMemberName("Cx: " + customerName);

			}
			else {
				//Member Do not exist in the database
				customer.useMemberName("Invalid Membership Number");
			}
			textField_MemberNumber.setText("");
			//customer.startScanning();
			controller.setView(CustomerUI.SCAN);

		});
		button_Enter.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
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

		//Updated in 3rd Iteration @author Simrat Benipal (start)
		//Added in 3rd Iteration @author Simrat Benipal
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
		//Added in 3rd Iteration ends

		
		textField_MemberNumber = new JTextField();
		textField_MemberNumber.setColumns(10);
		
		JLabel label_MemberCards_Text = new JLabel("Current Membership Cards in Customer Wallet:");
		label_MemberCards_Text.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		JLabel label_Barcode_Text = new JLabel("Current Barcodes in Customer Wallet:");
		label_Barcode_Text.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		JLabel label_separator1 = new JLabel("=====================================");
		label_separator1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		JLabel label_separator2 = new JLabel("=====================================");
		label_separator2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		JLabel label_separator3 = new JLabel("=====================================");
		label_separator3.setFont(new Font("Lucida Grande", Font.BOLD, 14));

		

		//get the card from the customer wallet
		ArrayList<String> memCardsList = new ArrayList<>();
		for (Card card : Simulation.currentCustomer.wallet.cards) {
			if(card.kind.equals("Membership"))
			{
				memCardsList.add(card.cardholder + " , " + card.number);
			}
			//Loaded the list with the membership cards
		}
		//Display the cards in a GUI
		comboBox_MemberCardsInWallet = new JComboBox<>(memCardsList.toArray());
		comboBox_MemberCardsInWallet.setFont(new Font("Lucida Grande", Font.BOLD, 15));

		//Barcodes for membership cards
		ArrayList<String> barcodes = new ArrayList<>();
		for (BarcodedItem memberBarcodes : Simulation.barcodesMember)
		{
			barcodes.add(memberBarcodes.getBarcode().toString());
		}
		comboBox_MemberCard_Barcodes = new JComboBox<>(barcodes.toArray());
		comboBox_MemberCard_Barcodes.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		
		JButton button_ScanCard = new JButton("Scan Card");
		button_ScanCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedBarcodeString = (String) comboBox_MemberCard_Barcodes.getSelectedItem();
				BarcodedItem selectedMemberBarcode = null;
				System.out.println("(EnterMemberNumber GUI) selected barcode " + selectedBarcodeString);
				//Find this barcode in the list
				for (BarcodedItem memberBarcodes : Simulation.barcodesMember)
				{
					if(memberBarcodes.getBarcode().toString().equals(selectedBarcodeString))
					{
						selectedMemberBarcode = memberBarcodes;
						break;
					}
				}
				//Scan the selected member barcode with the scanner of the diy system
				if (customer.getStation().mainScanner.scan(selectedMemberBarcode)) {
					//scan successfully
					System.out.println("(EnterMemberNumberGUI) Scan successfully");

					//get the barcode scanned from the scannerListener
					String cardNumber = ScanItemListener.getBarcodeScanned_String();
					Integer memberNumber = Integer.valueOf(cardNumber);
					if(MembershipDatabase.MEMBER_DATABASE.containsKey(memberNumber))
					{
						String customerName = MembershipDatabase.MEMBER_DATABASE.get(memberNumber);
						customer.useMemberName("Cx: " + customerName);
					}
					else {
						//Member Do not exist in the database
						customer.useMemberName("Invalid Membership Number");
					}

				} else {
					//scan Failed
					System.out.println("(EnterMemberNumberGUI) Scan fail");
					customer.useMemberName("Scan Failure, Try Again");
				}
				//Signal the listener, that a card is scanned
				//membership_listener.cardScanned(null);
				textField_MemberNumber.setText("");
				//customer.startScanning();
				controller.setView(CustomerUI.SCAN);
			}});
		button_ScanCard.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_SwipeCard = new JButton("Swipe Card");
		button_SwipeCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Make customer select the card
				String selectedCard = (String) comboBox_MemberCardsInWallet.getSelectedItem();
				System.out.println("(EnterMemberNumberGUI) Card selected " + selectedCard);
				//John Member-Card , 99999999
				//Split this to get card number
				String[] selectedCardSplit = selectedCard.split(" , ");
				String selectedCardNumber  = selectedCardSplit[1];
				//System.out.println("Card selected Numberrr" + selectedCardNumber);
				Integer memberNumber = Integer.valueOf(selectedCardNumber);
				if(MembershipDatabase.MEMBER_DATABASE.containsKey(memberNumber))
				{
					//from the wallet, get the card
					Card memberShipCard = null;
					for (Card card : Simulation.currentCustomer.wallet.cards) {
						if (card.number.equals(selectedCardNumber)) {
							memberShipCard = card;
							break;
						}
					}
					//Card selected, swipe the card at the card Reader to get the card holder name
					try {
						String customerName = customer.getStation().cardReader.swipe(memberShipCard).getCardholder();
						customer.useMemberName("Cx: " + customerName);
					} catch (IOException ex) { //scan failed
						customer.useMemberName("Swipe Failure, Try Again");
					}
				}
				else {
					//Member Do not exist in the database
					customer.useMemberName("Invalid Membership Number");
				}
				textField_MemberNumber.setText("");
				//customer.startScanning();
				controller.setView(CustomerUI.SCAN);
			}
		});
		button_SwipeCard.setFont(new Font("Lucida Grande", Font.BOLD, 19));
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
									.addComponent(button_Del, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(label_MemberCards_Text, Alignment.LEADING)
								.addComponent(label_Barcode_Text, Alignment.LEADING)
								.addComponent(label_separator1)
								.addComponent(label_separator2)
								.addComponent(label_separator3)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGap(6)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(button_ScanCard, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(groupLayout.createSequentialGroup()
										.addComponent(button_SwipeCard, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnNewButton_Cancel, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED))
									.addComponent(comboBox_MemberCard_Barcodes, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

									.addComponent(comboBox_MemberCardsInWallet, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
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
					.addGap(30)
					.addComponent(label_separator1)
					.addComponent(label_MemberCards_Text)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_MemberCardsInWallet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(button_SwipeCard, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(label_separator2)
						.addComponent(label_Barcode_Text)
					.addComponent(comboBox_MemberCard_Barcodes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)

					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						//.addComponent(button_ScanCard, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_ScanCard, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
						.addComponent(label_separator3)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(btnNewButton_Cancel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))

					.addGap(30))
		);
		setLayout(groupLayout);
	}
}
