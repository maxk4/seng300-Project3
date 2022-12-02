package ui.views;

import javax.swing.border.EmptyBorder;

import com.diy.hardware.DoItYourselfStation;

import ui.CustomerUI;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import javax.swing.ImageIcon;
import java.awt.Font;

public class ScanScreenGUI extends CustomerView {

	private static final long serialVersionUID = 6049492754371953479L;
	private JTextField textField_MemberNumber;
	private JTextArea scannedPricesArea;
	private JTextArea scannedItemsArea;
	private JTextField textField_OrderTotal_Number;
	private JTextField textField_Paid;
	private JTextField textField_Remaining;

	/**
	 * Create the frame.
	 */
	public ScanScreenGUI(CustomerUI controller, DoItYourselfStation station) {
		super(controller);
		title = "-- DoItYourselfStation --";
		setBackground(new Color(14, 144, 215));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lbl_Total_Text = new JLabel("Total $ ");
		lbl_Total_Text.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		textField_MemberNumber = new JTextField();
		textField_MemberNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		textField_MemberNumber.setColumns(10);
		textField_MemberNumber.setEditable(false);
		
		JButton button_PayWithDebit = new JButton("Debit");
		button_PayWithDebit.addActionListener(e -> {
			controller.setView(CustomerUI.PAY_WITH_DEBIT);
			station.cardReader.enable();
		});
		button_PayWithDebit.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-debit-card-100.png")));
		station.cardReader.enable();
		JButton button_CompleteAndPrintReceipt = new JButton("Complete/Print Receipt");
		button_CompleteAndPrintReceipt.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-receipt-100.png")));
		button_CompleteAndPrintReceipt.addActionListener(e -> {
			controller.endSession();
		});
		
		JButton button_PayWithCredit = new JButton("Credit");
		button_PayWithCredit.addActionListener(e -> {
			controller.setView(CustomerUI.PAY_WITH_CREDIT);
			station.cardReader.enable();
		});
		button_PayWithCredit.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-mastercard-credit-card-100.png")));

		JButton button_PayWithCash = new JButton("Cash");
		button_PayWithCash.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-cash-100.png")));
		button_PayWithCash.addActionListener(e -> {
			controller.setView(CustomerUI.PAY_WITH_CASH);
			station.coinSlot.enable();
			station.banknoteInput.enable();
			station.banknoteOutput.enable();
		});
		
		JButton button_PayWithGiftCard = new JButton("Gift Card");
		button_PayWithGiftCard.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-mastercard-credit-card-100.png")));
		button_PayWithGiftCard.addActionListener(e -> {
			controller.setView(CustomerUI.PAY_WITH_GIFT);
			station.cardReader.enable();
		});
		
		JButton button_Attendant = new JButton("Attendant");
		JButton button_EnterMemberNum = new JButton("Enter Member #");
		button_EnterMemberNum.addActionListener(e -> {
			controller.setView(CustomerUI.ENTER_MEMBERSHIP);
		});
		
		JButton button_UsePersonalBags = new JButton("Use Personal Bags");
		button_UsePersonalBags.addActionListener(e -> controller.setView(CustomerUI.ENTER_MEMBERSHIP));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label_Pay_Text = new JLabel("PAY");
		label_Pay_Text.setFont(new Font("Telugu MN", Font.BOLD, 23));
		
		JButton button_BuyBags = new JButton("Buy Bags");
		button_BuyBags.addActionListener(e -> controller.setView(CustomerUI.PURCHASE_BAGS));
		
		textField_OrderTotal_Number = new JTextField();
		textField_OrderTotal_Number.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		textField_OrderTotal_Number.setEditable(false);
		textField_OrderTotal_Number.setColumns(10);
		
		textField_Paid = new JTextField();
		textField_Paid.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		textField_Paid.setEditable(false);
		textField_Paid.setColumns(10);
		
		JLabel lbl_Paid_Text = new JLabel("Paid $ ");
		lbl_Paid_Text.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		textField_Remaining = new JTextField();
		textField_Remaining.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		textField_Remaining.setEditable(false);
		textField_Remaining.setColumns(10);
		
		JLabel lbl_Remaining_Text = new JLabel("Remaining $ ");
		lbl_Remaining_Text.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JButton button_AddPLUCodeButton = new JButton("Add Item by PLU Code");
		
		JButton button_SearchProductCatalogue = new JButton("Search Product Catalogue");
		button_SearchProductCatalogue.addActionListener(e -> {
			controller.setView(CustomerUI.CATALOGUE);
		});
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(61)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lbl_Total_Text)
								.addComponent(lbl_Paid_Text, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_Remaining_Text))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textField_Paid, 0, 0, Short.MAX_VALUE)
								.addComponent(textField_OrderTotal_Number, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
								.addComponent(textField_Remaining, Alignment.LEADING, 0, 0, Short.MAX_VALUE))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(button_CompleteAndPrintReceipt, GroupLayout.PREFERRED_SIZE, 328, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(100)
									.addComponent(button_Attendant)
									.addContainerGap())))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(58)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(button_PayWithCredit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
								.addComponent(button_PayWithCash, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 224, Short.MAX_VALUE)
								.addComponent(button_PayWithDebit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
								.addComponent(button_PayWithGiftCard, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 224, Short.MAX_VALUE))
							.addGap(52))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(112)
							.addComponent(label_Pay_Text, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(112))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(button_EnterMemberNum, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addComponent(button_BuyBags, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addComponent(button_UsePersonalBags, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(textField_MemberNumber, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))
								.addComponent(button_SearchProductCatalogue, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_AddPLUCodeButton, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(button_Attendant)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_EnterMemberNum)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_MemberNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_BuyBags)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_UsePersonalBags)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_SearchProductCatalogue)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_AddPLUCodeButton)
							.addGap(79)
							.addComponent(label_Pay_Text)
							.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
							.addComponent(button_PayWithCash, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_PayWithCredit, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_PayWithDebit, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_PayWithGiftCard, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(button_CompleteAndPrintReceipt))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lbl_Total_Text, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField_OrderTotal_Number, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_Paid, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_Paid_Text, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_Remaining_Text, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Remaining, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(12))
		);
		
		
		scannedPricesArea = new JTextArea();
		scannedPricesArea.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		scrollPane.setRowHeaderView(scannedPricesArea);
		scannedPricesArea.setColumns(10);
		scannedPricesArea.setEditable(false);
		
		scannedItemsArea = new JTextArea();
		scannedItemsArea.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		scannedItemsArea.setEditable(false);
		scrollPane.setViewportView(scannedItemsArea);
		setLayout(gl_contentPane);
	}
	
	public void update(long total, long paid, String productString, String priceString) {
		scannedItemsArea.setText(priceString);
		scannedPricesArea.setText(productString);
		textField_OrderTotal_Number.setText(String.format("$%.2f", total / 100d));
		textField_Paid.setText(String.format("$%.2f", paid / 100d));
		textField_Remaining.setText(String.format("$%.2f", (total - paid) / 100d));
	}

	public void updateMember(String customerName) {
		textField_MemberNumber.setText(customerName + "");
	}
}
