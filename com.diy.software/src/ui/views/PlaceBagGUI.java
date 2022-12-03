package ui.views;

import javax.swing.border.EmptyBorder;

import ui.CustomerUI;
import ui.views.util.CustomerView;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class PlaceBagGUI extends CustomerView {
	private static final long serialVersionUID = 960506723715532997L;

	/**
	 * Create the frame.
	 */
	public PlaceBagGUI(CustomerUI customer) {
		super(customer);
		setBounds(100, 100, 548, 266);
		setBackground(new Color(50, 126, 192));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel label_PlaceItem_Icon = new JLabel("");
		label_PlaceItem_Icon.setIcon(new ImageIcon(PlaceBagGUI.class.getResource("/resources/icons8-weight-light-100.png")));
		
		JLabel label_PlaceItem_Text = new JLabel("Please place your bag in the bagging ares");
		label_PlaceItem_Text.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		JButton button_ItemPlaced = new JButton("Bag Placed");
		button_ItemPlaced.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		button_ItemPlaced.addActionListener(e -> {
			customer.requestPersonalBag();
		});
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(59)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(button_ItemPlaced, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_PlaceItem_Text, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(41))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(label_PlaceItem_Icon, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addGap(204)))
					.addGap(0, 0, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(label_PlaceItem_Icon)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_PlaceItem_Text)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button_ItemPlaced, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		setLayout(gl_contentPane);
	}

}
