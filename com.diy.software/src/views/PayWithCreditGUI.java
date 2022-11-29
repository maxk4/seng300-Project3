package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.diy.hardware.DoItYourselfStationAR;

import util.CustomerUI;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class PayWithCreditGUI extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public PayWithCreditGUI(CustomerUI customer) {
		setTitle("Credit Payment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 266);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 139, 212));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Order Total: $");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JLabel lblNewLabel_1 = new JLabel("0.00");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JLabel lblNewLabel_2 = new JLabel("Select Card:");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JComboBox comboBox = new JComboBox();
		
		JButton btnNewButton = new JButton("Pay by Chip");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JButton btnPayByTap = new JButton("Pay by Tap");
		btnPayByTap.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JButton btnNewButton_1 = new JButton("Cancel Payment");
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(PayWithCreditGUI.class.getResource("/resources/icons8-mastercard-credit-card-100.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1))
						.addComponent(lblNewLabel_2)
						.addComponent(comboBox, 0, 227, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnPayByTap, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3))
					.addContainerGap())
				.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addGap(37)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPayByTap, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(5))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
