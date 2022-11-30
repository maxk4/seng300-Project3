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
	private JTextField textField;
	private JTextField memberField;
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
		
		JLabel lblNewLabel = new JLabel("$");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		textField.setColumns(10);
		textField.setEditable(false);
		
		memberField = new JTextField();
		memberField.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		memberField.setColumns(10);
		memberField.setEditable(false);
		
		JButton btnNewButton = new JButton("Debit");
		btnNewButton.addActionListener(e -> {
			customer.payWithDebit();
		});
		btnNewButton.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-debit-card-100.png")));
		
		JButton btnNewButton_1 = new JButton("Complete/Print Receipt");
		btnNewButton_1.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-receipt-100.png")));
		btnNewButton_1.addActionListener(e -> {
			try {
				customer.endSession();
			} catch (IllegalStateException err) {
				// Alert customer that they have a balance remaining
			}
		});
		
		JButton btnNewButton_2 = new JButton("Credit");
		btnNewButton_2.addActionListener(e -> {
			customer.payWithCredit();
		});
		btnNewButton_2.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-mastercard-credit-card-100.png")));
		
		JButton btnNewButton_3 = new JButton("Cash");
		btnNewButton_3.setIcon(new ImageIcon(ScanScreenGUI.class.getResource("/resources/icons8-cash-100.png")));
		btnNewButton_3.addActionListener(e -> {
			customer.payWithCash();
		});
		
		JButton btnNewButton_5 = new JButton("Attendant");
		
		JButton btnNewButton_6 = new JButton("Enter Member #");
		btnNewButton_6.addActionListener(e -> {
			customer.enterMemNum();
		});
		
		JButton btnNewButton_7 = new JButton("Use Personal Bags");
		btnNewButton_7.addActionListener(e -> {
			customer.addBag();
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("PAY");
		lblNewLabel_1.setFont(new Font("Telugu MN", Font.BOLD, 23));
		
		JButton btnNewButton_6_1 = new JButton("Buy Bags");
		btnNewButton_6_1.addActionListener(new ActionListener() {
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
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 271, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(100)
									.addComponent(btnNewButton_5)
									.addContainerGap())))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(58)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
								.addComponent(btnNewButton_3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 167, Short.MAX_VALUE)
								.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
							.addGap(52))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(112)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(112))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_6, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
								.addComponent(memberField, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_7, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
								.addComponent(btnNewButton_6_1, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(btnNewButton_5)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_6)
							.addComponent(memberField)
							.addGap(18)
							.addComponent(btnNewButton_6_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_7)
							.addGap(102)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(btnNewButton_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
		textField.setText(String.format("$%.2f", balance / 100d));
	}

	//Updated in Iteration 3 @Simrat (Starts)
	public void updateMember(String customerName) {
		/*
		if (number == 8)
			memberField.setText("Invalid #");
		if (number == 0) 
			memberField.setText("Not a Member");
		if (number == 1) 
			memberField.setText("A Member # already in use");
		if (number != 8 && number != 0 && number != 1)

		 */
		memberField.setText( customerName + "");
	}
	//Updated in Iteration 3 @Simrat (Ends)
}
