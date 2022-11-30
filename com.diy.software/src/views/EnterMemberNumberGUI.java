package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.CustomerUI;
import util.MembershipDatabase;
import util.MembershipListener;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class EnterMemberNumberGUI extends JFrame {
	private static final long serialVersionUID = -2878096921110787780L;
	private JPanel contentPane;
	private JTextField txtMemberNumber;

	private MembershipListener membership_listener = new MembershipListener();

	/**
	 * Create the Membership GUI, for customer to type, scan, swipe the membership cards
	 */
	public EnterMemberNumberGUI(CustomerUI customer) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 126, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
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

		//Updated in 3rd Iteration @author Simrat Benipal (start)
		//Added in 3rd Iteration @author Simrat Benipal
		JButton btnNewButton_Cancel = new JButton("Cancel");
		btnNewButton_Cancel.setBounds(254, 81, 100, 60);
		btnNewButton_Cancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			txtMemberNumber.setText("");
			customer.startScanning();
		}
		});
		btnNewButton_Cancel.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		//Added in 3rd Iteration ends

		JButton btnNewButton_Enter = new JButton("Enter");
		btnNewButton_Enter.setBounds(254, 155, 100, 120);
		btnNewButton_Enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				if(Objects.equals(currValue, ""))
				{
					//Try again
					currValue = "-1";
				}
				Integer memberNumber = Integer.valueOf(currValue);
				//Signal the Listener
				membership_listener.cardTyped(memberNumber);
				if(MembershipDatabase.MEMBER_DATABASE.containsKey(memberNumber))
				{
					String customerName = MembershipDatabase.MEMBER_DATABASE.get(memberNumber);
					customer.useMemberNumber("Cx: " + customerName);

				}
				else {
					//Member Do not exist in the database
					customer.useMemberNumber("Invalid Membership Number");
				}
				txtMemberNumber.setText("");
				customer.startScanning();
			}
		});
		btnNewButton_Enter.setFont(new Font("Lucida Grande", Font.BOLD, 19));

		//Add Membership Number by Scanning
		JButton btnNewButton_Scan = new JButton("Scan Card");
		btnNewButton_Scan.setBounds(11, 354, 156, 64);
		btnNewButton_Scan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Signal the listener, that a card is scanned
				membership_listener.cardScanned(null);
				txtMemberNumber.setText("");
				customer.startScanning();
			}
		});
		btnNewButton_Scan.setFont(new Font("Lucida Grande", Font.BOLD, 19));

		//Add membership number by Swiping
		JButton btnNewButton_Swipe = new JButton("Swipe Card");
		btnNewButton_Swipe.setBounds(173, 354, 156, 64);
		btnNewButton_Swipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Signal the listener, that a card has been swiped
				membership_listener.cardSwiped(null);
				txtMemberNumber.setText("");
				customer.startScanning();
			}
		});
		btnNewButton_Swipe.setFont(new Font("Lucida Grande", Font.BOLD, 19));


		//Added Iteration 3 Code @Simrat (ends)

		JButton btnNewButton_Del = new JButton("Delete");
		btnNewButton_Del.setBounds(254, 11, 100, 64);
		btnNewButton_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = txtMemberNumber.getText();
				currValue = currValue.substring(0, currValue.length() - 1);
				txtMemberNumber.setText(currValue);
			}
		});
		btnNewButton_Del.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		contentPane.setLayout(null);
		
		txtMemberNumber = new JTextField();
		txtMemberNumber.setBounds(11, 292, 342, 57);
		txtMemberNumber.setEditable(false);
		txtMemberNumber.setText("");
		txtMemberNumber.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		txtMemberNumber.setColumns(10);
		contentPane.add(txtMemberNumber);
		contentPane.add(btnNewButton_4);
		contentPane.add(btnNewButton_5);
		contentPane.add(btnNewButton_6);
		contentPane.add(btnNewButton_7);
		contentPane.add(btnNewButton_8);
		contentPane.add(btnNewButton_9);
		contentPane.add(btnNewButton_0);
		contentPane.add(btnNewButton_1);
		contentPane.add(btnNewButton_2);
		contentPane.add(btnNewButton_decimal);
		contentPane.add(btnNewButton_3);
		contentPane.add(btnNewButton_Enter);
		contentPane.add(btnNewButton_Del);
		contentPane.add(btnNewButton_Cancel);
		contentPane.add(btnNewButton_Scan);
		contentPane.add(btnNewButton_Swipe);
	}
}
