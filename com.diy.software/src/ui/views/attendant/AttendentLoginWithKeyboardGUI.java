/**
 * Keyboard Class to allow users to input on a touch screen
 * 
 * @author Brody Wells
 */

package ui.views.attendant;


import javax.swing.border.EmptyBorder;

import athourization.AttendantDatabase;
import ui.AttendantUI;
import ui.views.attendant.AttendantSearchCatalogueGUI.KeyButton;
import ui.views.util.AttendantView;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class AttendentLoginWithKeyboardGUI extends AttendantView {
	
	private class FieldSelection implements MouseListener {
		JTextField field;
		
		public FieldSelection(JTextField field) {
			this.field = field;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			selected = field;
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}

	private static final long serialVersionUID = 6242487627854166656L;
	public JTextField textField_PasswordInput, textField_UsernameInput, selected;
	private boolean isShifted = false;
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
				selected.setText(selected.getText() + (!isShifted ? low : up));
				if (isShifted) button_Shift.doClick();
			});
		}
		
		public void update() {
			if (isShifted) setText(up);
			else setText(low);
		}
	}

	public List<KeyButton> keys = new ArrayList<KeyButton>();
	/**
	 * Create the keyboard frame.
	 * 
	 * @param controller		A CustomerUI object
	 */
	public AttendentLoginWithKeyboardGUI(AttendantUI controller, JFrame parent) {
		super(controller, parent);
		
		setBounds(100, 100, 653, 445);
		setBackground(new Color(94, 193, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		button_Shift = new JButton("Shift");
		
		KeyButton button_Q = new KeyButton("q", "Q");
		keys.add(button_Q);
		KeyButton button_W = new KeyButton("w", "W");
		keys.add(button_W);
		KeyButton button_E = new KeyButton("e", "E");
		keys.add(button_E);
		KeyButton button_R = new KeyButton("r", "R");
		keys.add(button_R);
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
		button_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				if(currValue.length() > 0) {
					currValue = currValue.substring(0, currValue.length()-1);
					selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
				}
			}
		});
		button_Del.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Del.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_Enter = new JButton("Enter");
		button_Enter.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Enter.setMargin(new Insets(0, 0, 0, 0));
		button_Enter.addActionListener(e -> {
			if (AttendantDatabase.validate(textField_UsernameInput.getText(), textField_PasswordInput.getText())) {
				controller.setView(AttendantUI.MAIN);
				clear();
			}
		});
		
		
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
				String currValue = selected.getText();
				currValue += " ";
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_Space.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Space.setMargin(new Insets(0, 0, 0, 0));
		
		
		textField_PasswordInput = new JTextField();
		textField_PasswordInput.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		textField_PasswordInput.addMouseListener(new FieldSelection(textField_PasswordInput));
		
		textField_PasswordInput.setColumns(10);
		
		textField_UsernameInput = new JTextField();
		textField_UsernameInput.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		textField_UsernameInput.addMouseListener(new FieldSelection(textField_UsernameInput));
		
		textField_UsernameInput.setColumns(10);
		
		JButton button_UsernameSelect = new JButton("Username");
		button_UsernameSelect.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		button_UsernameSelect.addActionListener(e -> {
			selected = textField_UsernameInput;
			textField_UsernameInput.requestFocusInWindow();
		});
		
		JButton button_PasswordSelect = new JButton("Password");
		button_PasswordSelect.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		button_PasswordSelect.addActionListener(e -> {
			selected = textField_PasswordInput;
			textField_PasswordInput.requestFocusInWindow();
		});
		
		JButton button_Login = new JButton("Login");
		button_Login.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		button_Login.addActionListener(e -> {
			if (AttendantDatabase.validate(textField_UsernameInput.getText(), textField_PasswordInput.getText())) {
				controller.setView(AttendantUI.MAIN);
				clear();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button_PasswordSelect, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_PasswordInput, GroupLayout.PREFERRED_SIZE, 489, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(17)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_Q, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_W, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_E, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_R, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_T, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_Y, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_U, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_I, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_O, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_P, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_LeftBracket, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_RightBracket, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_ForwardSlash, GroupLayout.PREFERRED_SIZE, 49, Short.MAX_VALUE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(15)
										.addComponent(button_A, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_S, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_D, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_F, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_G, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_H, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_J, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_K, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_L, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_SemiColon, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_Apostrophe, GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_Enter, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(32)
										.addComponent(button_Z, GroupLayout.PREFERRED_SIZE, 43, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_X, GroupLayout.PREFERRED_SIZE, 43, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_C, GroupLayout.PREFERRED_SIZE, 43, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_V, GroupLayout.PREFERRED_SIZE, 43, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_B, GroupLayout.PREFERRED_SIZE, 43, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_N, GroupLayout.PREFERRED_SIZE, 43, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_M, GroupLayout.PREFERRED_SIZE, 43, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_Comma, GroupLayout.PREFERRED_SIZE, 43, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_Period, GroupLayout.PREFERRED_SIZE, 43, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_BackSlash, GroupLayout.PREFERRED_SIZE, 43, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button_Shift, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_0, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_Dash, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_Equals, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_Del, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(134)
								.addComponent(button_Space, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
								.addGap(241)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button_UsernameSelect)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_UsernameInput, GroupLayout.PREFERRED_SIZE, 489, GroupLayout.PREFERRED_SIZE)))
					.addGap(15))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(button_Login, GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(button_Login, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(button_UsernameSelect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField_UsernameInput, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_PasswordInput, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_PasswordSelect, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(button_E, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_R, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_T, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_Y, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_U, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_I, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_O, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_P, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_LeftBracket, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_RightBracket, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_ForwardSlash, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_W, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(button_Q, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(button_A, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(button_S, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(button_D, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(button_F, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(button_G, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(button_H, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(button_J, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(button_K, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(button_L, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(button_SemiColon, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(button_Apostrophe, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(button_Enter, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(button_Z, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.addComponent(button_X, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.addComponent(button_C, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.addComponent(button_V, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.addComponent(button_B, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.addComponent(button_N, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.addComponent(button_M, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.addComponent(button_Comma, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.addComponent(button_Period, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.addComponent(button_BackSlash, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.addComponent(button_Shift, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_Space, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
					.addGap(24))
		);
		setLayout(gl_contentPane);
		
		selected = textField_UsernameInput;
	}
	
	private void clear() {
		textField_UsernameInput.setText("");
		textField_PasswordInput.setText("");
		selected = textField_UsernameInput;
		if (isShifted) button_Shift.doClick();
	}
}
