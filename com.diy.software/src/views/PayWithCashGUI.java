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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PayWithCashGUI extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public PayWithCashGUI(CustomerUI customer) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 393);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 139, 212));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Order Total: $");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JLabel lblNewLabel_2 = new JLabel("0.00");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JButton btnNewButton_1_1 = new JButton("5");
		btnNewButton_1_1.setIcon(new ImageIcon(PayWithCashGUI.class.getResource("/resources/icons8-cent-100.png")));
		btnNewButton_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JButton btnNewButton_3 = new JButton("10");
		btnNewButton_3.setIcon(new ImageIcon(PayWithCashGUI.class.getResource("/resources/icons8-cent-100.png")));
		btnNewButton_3.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JButton btnNewButton_2_2 = new JButton("25");
		btnNewButton_2_2.setIcon(new ImageIcon(PayWithCashGUI.class.getResource("/resources/icons8-cent-100.png")));
		btnNewButton_2_2.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JButton btnNewButton_2_1_1 = new JButton("1");
		btnNewButton_2_1_1.setIcon(new ImageIcon(PayWithCashGUI.class.getResource("/resources/icons8-dollar-coin-100.png")));
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JLabel lblNewLabel_1_1 = new JLabel("Inserted: $");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JButton btnNewButton_1_1_1 = new JButton("5");
		btnNewButton_1_1_1.setIcon(new ImageIcon(PayWithCashGUI.class.getResource("/resources/icons8-dollar-coin-100.png")));
		btnNewButton_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JButton btnNewButton_3_1 = new JButton("10");
		btnNewButton_3_1.setIcon(new ImageIcon(PayWithCashGUI.class.getResource("/resources/icons8-dollar-coin-100.png")));
		btnNewButton_3_1.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JButton btnNewButton_2_2_1 = new JButton("20");
		btnNewButton_2_2_1.setIcon(new ImageIcon(PayWithCashGUI.class.getResource("/resources/icons8-dollar-coin-100.png")));
		btnNewButton_2_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JButton btnNewButton_2_1_1_1 = new JButton("50");
		btnNewButton_2_1_1_1.setIcon(new ImageIcon(PayWithCashGUI.class.getResource("/resources/icons8-dollar-coin-100.png")));
		btnNewButton_2_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JLabel lblNewLabel_2_1 = new JLabel("0.00");
		lblNewLabel_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PayWithCashGUI.class.getResource("/resources/icons8-cash-100.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_2))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton_1_1, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2_2, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2_1_1, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
					.addGap(8))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1_1_1, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_3_1, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_2_2_1, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(btnNewButton_2_1_1_1, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(460, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(542, Short.MAX_VALUE)
					.addComponent(lblNewLabel))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2)))
						.addComponent(lblNewLabel))
					.addGap(61)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2_2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 48, Short.MAX_VALUE)
						.addComponent(btnNewButton_2_1_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_3_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2_2_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2_1_1_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(60))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
