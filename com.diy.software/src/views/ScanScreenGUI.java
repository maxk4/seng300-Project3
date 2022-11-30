package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.CustomerUI;
import util.ProductList;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import java.awt.Font;

public class ScanScreenGUI extends JFrame {

	private static final long serialVersionUID = 6049492754371953479L;
	private JPanel contentPane;
	private JTextField textField_OrderTotal_Number;
	private JTextField textField_MemberNumber;
	private JTextArea scannedPricesArea;
	private JTextArea scannedItemsArea;

	/**
	 * Create the frame.
	 */
	public ScanScreenGUI(CustomerUI customer) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ScanScreenGUI.class.getResource("/resources/icons8-pc-on-desk-100.png")));
		setTitle("-- DoItYourselfStation --");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 794);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(14, 144, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel label_DollarSignForTotal_Text = new JLabel("$");
		label_DollarSignForTotal_Text.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		textField_OrderTotal_Number = new JTextField();
		textField_OrderTotal_Number.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		textField_OrderTotal_Number.setColumns(10);
		textField_OrderTotal_Number.setEditable(false);
		
		textField_MemberNumber = new JTextField();
		textField_MemberNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		textField_MemberNumber.setColumns(10);
		textField_MemberNumber.setEditable(false);
		
		JButton button_PayWithDebit = new JButton("Debit");
		button_PayWithDebit.addActionListener(e -> {
			customer.payWithDebit();
		});
		button_PayWithDebit.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-debit-card-100.png")));
		
		JButton button_CompleteAndPrintReceipt = new JButton("Complete/Print Receipt");
		button_CompleteAndPrintReceipt.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-receipt-100.png")));
		button_CompleteAndPrintReceipt.addActionListener(e -> {
			try {
				customer.endSession();
			} catch (IllegalStateException err) {
				// Alert customer that they have a balance remaining
			}
		});
		
		JButton button_PayWithCredit = new JButton("Credit");
		button_PayWithCredit.addActionListener(e -> {
			customer.payWithCredit();
		});
		button_PayWithCredit.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-mastercard-credit-card-100.png")));
		
		JButton button_PayWithCash = new JButton("Cash");
		button_PayWithCash.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-cash-100.png")));
		button_PayWithCash.addActionListener(e -> {
			customer.payWithCash();
		});
		
		JButton button_Attendant = new JButton("Attendant");
		
		JButton button_EnterMemberNum = new JButton("Enter Member #");
		button_EnterMemberNum.addActionListener(e -> {
			customer.enterMemNum();
		});
		
		JButton button_UsePersonalBags = new JButton("Use Personal Bags");
		button_UsePersonalBags.addActionListener(e -> {
			customer.addBag();
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label_Pay_Text = new JLabel("PAY");
		label_Pay_Text.setFont(new Font("Telugu MN", Font.BOLD, 23));
		
		JButton button_BuyBags = new JButton("Buy Bags");
		button_BuyBags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer.purchageBags();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_DollarSignForTotal_Text)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_OrderTotal_Number, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(button_CompleteAndPrintReceipt, GroupLayout.PREFERRED_SIZE, 271, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(100)
									.addComponent(button_Attendant)
									.addContainerGap())))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(58)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(button_PayWithCredit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
								.addComponent(button_PayWithCash, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 167, Short.MAX_VALUE)
								.addComponent(button_PayWithDebit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
							.addGap(52))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(112)
							.addComponent(label_Pay_Text, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(112))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(button_EnterMemberNum, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
								.addComponent(textField_MemberNumber, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_UsePersonalBags, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
								.addComponent(button_BuyBags, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
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
							.addComponent(textField_MemberNumber)
							.addGap(18)
							.addComponent(button_BuyBags)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_UsePersonalBags)
							.addGap(102)
							.addComponent(label_Pay_Text)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button_PayWithCash, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_PayWithCredit, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_PayWithDebit, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(button_CompleteAndPrintReceipt))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_DollarSignForTotal_Text)
						.addComponent(textField_OrderTotal_Number, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(63))
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
		contentPane.setLayout(gl_contentPane);
	}
	
	public void update(long balance, ProductList products) {
		StringBuilder productSB = new StringBuilder();
		StringBuilder priceSB = new StringBuilder();
		products.forEach((prod, desc, price) -> {
			productSB.append(desc);
			productSB.append("\n");
			priceSB.append(String.format("$%.2f", price / 100d));
			priceSB.append("\n");
		});
		scannedItemsArea.setText(priceSB.toString());
		scannedPricesArea.setText(productSB.toString());
		textField_OrderTotal_Number.setText(String.format("$%.2f", balance / 100d));
	}

	public void updateMember(Integer number) {
		if (number == 8) 
			textField_MemberNumber.setText("Invalid #");
		if (number == 0) 
			textField_MemberNumber.setText("Not a Member");
		if (number == 1) 
			textField_MemberNumber.setText("A Member # already in use");
		if (number != 8 && number != 0 && number != 1)
		textField_MemberNumber.setText(number + "");
	}
}
