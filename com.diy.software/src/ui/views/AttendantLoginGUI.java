package ui.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.CustomerUI;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class AttendantLoginGUI extends CustomerView {

	private static final long serialVersionUID = -2046572931109545669L;
	private JTextField textField_Username;
	private JTextField textField;

	
	/**
	 * Create the frame.
	 */
	public AttendantLoginGUI(CustomerUI customer) {
		super(customer);
		
		setBounds(100, 100, 438, 199);
		setBackground(new Color(94, 193, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel label_Username = new JLabel("Username: ");
		label_Username.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		textField_Username = new JTextField();
		textField_Username.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		textField_Username.setColumns(10);
		
		JLabel label_Password = new JLabel("Password: ");
		label_Password.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		textField.setColumns(10);
		
		JButton button_Login = new JButton("Login");
		button_Login.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(button_Login, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_Username)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(label_Password, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_Username, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_Username)
						.addComponent(textField_Username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_Password, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(button_Login, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(122, Short.MAX_VALUE))
		);
		setLayout(gl_contentPane);
	}

}
