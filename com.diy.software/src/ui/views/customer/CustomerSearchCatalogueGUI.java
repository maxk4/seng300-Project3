/**
 * Keyboard Class to allow users to input on a touch screen
 * 
 * @author Brody Wells
 */

package ui.views.customer;

import javax.swing.border.EmptyBorder;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.PLUCodedProduct;
import com.diy.hardware.external.ProductDatabases;

import ui.CustomerUI;
import ui.views.attendant.AttendantSearchCatalogueGUI.KeyButton;
import ui.views.util.CustomerView;
import ui.views.util.ProductButton;
import util.ActionDocument;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class CustomerSearchCatalogueGUI extends CustomerView {

	private static final long serialVersionUID = 6242487627854166656L;
	private JTextField textField_Input;
	private boolean isShifted = false;
	private JPanel product_panel;
	private List<ProductButton> productButtons = new ArrayList<ProductButton>();
	public List<KeyButton> keys = new ArrayList<KeyButton>();
	private JButton button_Shift;
	
	public class KeyButton extends JButton {
		private static final long serialVersionUID = 679785136510997134L;
		String low, up;
		public KeyButton(String low, String up) {
			super(low);
			this.low = low;
			this.up = up;
			setFont(new Font("Lucida Grande", Font.BOLD, 15));
			setMargin(new Insets(0, 0, 0, 0));
			addActionListener(e -> {
				textField_Input.setText(textField_Input.getText() + (!isShifted ? low : up));
				if (isShifted) button_Shift.doClick();
			});
		}
		
		public void update() {
			if (isShifted) setText(up);
			else setText(low);
		}
	}

	/**
	 * Create the keyboard frame.
	 * 
	 * @param controller		A CustomerUI object
	 */
	public CustomerSearchCatalogueGUI(CustomerUI controller) {
		super(controller);
		setBounds(100, 100, 657, 659);
		setBackground(new Color(94, 193, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		

		KeyButton button_Q = new KeyButton("q", "Q");
		keys.add(button_Q);
		KeyButton button_W = new KeyButton("w", "W");
		keys.add(button_W);
		KeyButton button_E = new KeyButton("e", "E");
		keys.add(button_E);
		KeyButton button_R = new KeyButton("r", "R");
		keys.add(button_E);
		KeyButton button_T = new KeyButton("t", "T");
		keys.add(button_T);
		KeyButton button_Y = new KeyButton("y", "Y");
		keys.add(button_Y);
		KeyButton button_U = new KeyButton("u", "U");
		keys.add(button_U);
		KeyButton button_I = new KeyButton("i", "I");
		keys.add(button_I);
		KeyButton button_O = new KeyButton("o", "O");
		keys.add(button_O);
		KeyButton button_P = new KeyButton("p", "P");
		keys.add(button_P);
		KeyButton button_A = new KeyButton("a", "A");
		keys.add(button_A);
		KeyButton button_S = new KeyButton("s", "S");
		keys.add(button_S);
		KeyButton button_D = new KeyButton("d", "D");
		keys.add(button_D);
		KeyButton button_F = new KeyButton("f", "F");
		keys.add(button_F);
		KeyButton button_G = new KeyButton("g", "G");
		keys.add(button_G);
		KeyButton button_H = new KeyButton("h", "H");
		keys.add(button_H);
		KeyButton button_J = new KeyButton("j", "J");
		keys.add(button_J);
		KeyButton button_K = new KeyButton("k", "K");
		keys.add(button_K);
		KeyButton button_L = new KeyButton("l", "L");
		keys.add(button_L);
		KeyButton button_Z = new KeyButton("z", "Z");
		keys.add(button_Z);
		KeyButton button_X = new KeyButton("x", "X");
		keys.add(button_X);
		KeyButton button_C = new KeyButton("c", "C");
		keys.add(button_C);
		KeyButton button_V = new KeyButton("v", "V");
		keys.add(button_V);
		KeyButton button_B = new KeyButton("b", "B");
		keys.add(button_B);
		KeyButton button_N = new KeyButton("n", "N");
		keys.add(button_N);
		KeyButton button_M = new KeyButton("m", "M");
		keys.add(button_M);
		KeyButton button_Comma = new KeyButton(",", "<");
		keys.add(button_Comma);
		KeyButton button_Period = new KeyButton(".", ">");
		keys.add(button_Period);
		KeyButton button_BackSlash = new KeyButton("/", "?");
		keys.add(button_BackSlash);
		KeyButton button_SemiColon = new KeyButton(";", ":");
		keys.add(button_SemiColon);
		KeyButton button_Apostrophe = new KeyButton("'", "\"");
		keys.add(button_Apostrophe);
		KeyButton button_LeftBracket = new KeyButton("[", "{");
		keys.add(button_LeftBracket);
		KeyButton button_RightBracket = new KeyButton("]", "}");
		keys.add(button_RightBracket);
		KeyButton button_ForwardSlash = new KeyButton("\\", "|");
		keys.add(button_ForwardSlash);
		KeyButton button_1 = new KeyButton("1", "!");
		keys.add(button_1);
		KeyButton button_2 = new KeyButton("2", "@");
		keys.add(button_2);
		KeyButton button_3 = new KeyButton("3", "#");
		keys.add(button_3);
		KeyButton button_4 = new KeyButton("4", "$");
		keys.add(button_4);
		KeyButton button_5 = new KeyButton("5", "%");
		keys.add(button_5);
		KeyButton button_6 = new KeyButton("6", "^");
		keys.add(button_6);
		KeyButton button_7 = new KeyButton("7", "&");
		keys.add(button_7);
		KeyButton button_8 = new KeyButton("8", "*");
		keys.add(button_8);
		KeyButton button_9 = new KeyButton("9", "(");
		keys.add(button_9);
		KeyButton button_0 = new KeyButton("0", ")");
		keys.add(button_0);
		KeyButton button_Dash = new KeyButton("-", "_");
		keys.add(button_Dash);
		KeyButton button_Equals = new KeyButton("=", "+");
		keys.add(button_Equals);
		JButton button_Del = new JButton("Del");
		button_Del.addActionListener(e -> {
			if (textField_Input.getText().length() > 0) 
				textField_Input.setText(textField_Input.getText().substring(0, textField_Input.getText().length() - 1));
		});
		button_Del.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Del.setMargin(new Insets(0, 0, 0, 0));
		
		JButton button_Enter = new JButton("Enter");
		button_Enter.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Enter.setMargin(new Insets(0, 0, 0, 0));
		
		button_Shift = new JButton("Shift");
		button_Shift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isShifted = !isShifted;
				for (KeyButton key : keys) key.update();
			}
		});
		button_Shift.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Shift.setMargin(new Insets(0, 0, 0, 0));
		
		JButton button_Space = new JButton("Space");
		button_Space.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = textField_Input.getText();
				currValue += " ";
				textField_Input.setText(currValue);
			}
		});
		button_Space.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Space.setMargin(new Insets(0, 0, 0, 0));
		
		textField_Input = new JTextField();
		textField_Input.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		textField_Input.setColumns(10);
		textField_Input.setDocument(new ActionDocument(() -> {
			updateList();
		}));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button_CancelSearch = new JButton("Cancel Search");
		button_CancelSearch.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_CancelSearch.addActionListener(e -> {
			textField_Input.setText("");
			controller.setView(CustomerUI.SCAN);
		});
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(17)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_Q, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_W, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_E, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_R, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_T, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_Y, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_U, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_I, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_O, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_P, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_LeftBracket, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_RightBracket, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_ForwardSlash, GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(15)
											.addComponent(button_A, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_S, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_D, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_F, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_G, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_H, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_J, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_K, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_L, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_SemiColon, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_Apostrophe, GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_Enter, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(32)
											.addComponent(button_Z, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_X, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_C, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_V, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_B, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_N, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_M, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_Comma, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_Period, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_BackSlash, GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(button_Shift, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_0, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_Dash, GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_Equals, GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_Del, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(134)
									.addComponent(button_Space, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
									.addGap(241)))
							.addGap(15))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField_Input, 626, 626, 626)
							.addContainerGap(15, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button_CancelSearch, GroupLayout.PREFERRED_SIZE, 623, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(18, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(button_CancelSearch, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_Input, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_2, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_3, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_4, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_5, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_6, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_7, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_8, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_9, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_0, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_Dash, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_Equals, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_Del, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
					.addGap(8)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(button_E, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_R, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_T, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_Y, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_U, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_I, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_O, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_P, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_LeftBracket, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_RightBracket, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_ForwardSlash, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_W, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_Q, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(button_A, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(button_S, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(button_D, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(button_F, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(button_G, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(button_H, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(button_J, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(button_K, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(button_L, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(button_SemiColon, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(button_Apostrophe, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(button_Enter, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(button_Z, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_X, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_C, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_V, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_B, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_N, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_M, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_Comma, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_Period, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_BackSlash, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(button_Shift, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_Space, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
					.addGap(24))
		);
		
		product_panel = new JPanel();
		scrollPane.setViewportView(product_panel);
		
		product_panel.setLayout(new BoxLayout(product_panel, BoxLayout.PAGE_AXIS));
		
		
		
		for (BarcodedProduct p : ProductDatabases.BARCODED_PRODUCT_DATABASE.values()) {
			ProductButton pb = new ProductButton(p, p.getDescription(), e -> {
				controller.selectItem(p, p.getDescription());
			});
			if (!productButtons.contains(pb)) productButtons.add(pb);
		}
		
		for (PLUCodedProduct p : ProductDatabases.PLU_PRODUCT_DATABASE.values()) {
			ProductButton pb = new ProductButton(p, p.getDescription(), e -> {
				controller.selectItem(p, p.getDescription());
			});
			if (!productButtons.contains(pb)) productButtons.add(pb);
		}
		productButtons.sort((a, b) -> {
			return a.name.compareTo(b.name);
		});
		updateList();
		setLayout(gl_contentPane);
	}
	
	public void updateList() {
		product_panel.removeAll();
		for (ProductButton pb : productButtons) {
			if (pb.name.toLowerCase().contains(textField_Input.getText().toLowerCase()))
				product_panel.add(pb);
			else if (textField_Input.getText().length() == 0)
				product_panel.add(pb);
		}
		product_panel.revalidate();
		product_panel.repaint();
	}
}
