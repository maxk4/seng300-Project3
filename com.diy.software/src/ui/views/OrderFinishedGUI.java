package ui.views;

import javax.swing.border.EmptyBorder;

import ui.CustomerUI;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;

public class OrderFinishedGUI extends CustomerView {
	private static final long serialVersionUID = 960506723715532997L;

	/**
	 * Create the frame.
	 */
	public OrderFinishedGUI(CustomerUI customer) {
		super(customer);
		setBounds(100, 100, 450, 300);
		setBackground(new Color(50, 126, 192));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel label_CheckMarkIcon = new JLabel("");
		label_CheckMarkIcon.setIcon(new ImageIcon(OrderFinishedGUI.class.getResource("/resources/icons8-ok-100.png")));
		
		JLabel label_Finished_Text = new JLabel("Finished! Please take your bags and receipt");
		label_Finished_Text.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(17)
					.addComponent(label_Finished_Text, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(74))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(170)
					.addComponent(label_CheckMarkIcon, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(162, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_CheckMarkIcon)
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addComponent(label_Finished_Text)
					.addGap(101))
		);
		setLayout(gl_contentPane);
	}

}
