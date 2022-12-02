/**
 * Keyboard Class to allow users to input on a touch screen
 * 
 * @author Brody Wells
 */

package ui.views;


import javax.swing.border.EmptyBorder;

import athourization.AttendantDatabase;
import ui.AttendantUI;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

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
	private JTextField textField_PasswordInput, textField_UsernameInput, selected;;
	private boolean isShifted = false;
	private JButton button_Shift;

	/**
	 * Create the keyboard frame.
	 * 
	 * @param controller		A CustomerUI object
	 */
	public AttendentLoginWithKeyboardGUI(AttendantUI controller) {
		super(controller);
		
		setBounds(100, 100, 653, 445);
		setBackground(new Color(94, 193, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		button_Shift = new JButton("Shift");
		
		JButton button_Q = new JButton("q");
		button_Q.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_Q.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_Q.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Q.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_W = new JButton("w");
		button_W.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_W.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_W.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_W.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_E = new JButton("e");
		button_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_E.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_E.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_E.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_R = new JButton("r");
		button_R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_R.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_R.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_R.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_T = new JButton("t");
		button_T.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_T.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_T.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_T.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_Y = new JButton("y");
		button_Y.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_Y.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_Y.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Y.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_U = new JButton("u");
		button_U.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_U.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_U.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_U.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_I = new JButton("i");
		button_I.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_I.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_I.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_I.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_O = new JButton("o");
		button_O.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_O.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_O.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_O.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_P = new JButton("p");
		button_P.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_P.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_P.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_P.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_A = new JButton("a");
		button_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_A.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_A.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_A.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_S = new JButton("s");
		button_S.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_S.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_S.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_S.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_D = new JButton("d");
		button_D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_D.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_D.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_D.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_F = new JButton("f");
		button_F.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_F.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_F.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_F.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_G = new JButton("g");
		button_G.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_G.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_G.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_G.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_H = new JButton("h");
		button_H.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_H.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_H.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_H.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_J = new JButton("j");
		button_J.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_J.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_J.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_J.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_K = new JButton("k");
		button_K.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_K.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_K.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_K.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_L = new JButton("l");
		button_L.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_L.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_L.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_L.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_Z = new JButton("z");
		button_Z.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_Z.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_Z.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Z.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_X = new JButton("x");
		button_X.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_X.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_X.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_X.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_C = new JButton("c");
		button_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_C.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_C.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_C.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_V = new JButton("v");
		button_V.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_V.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_V.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_V.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_B = new JButton("b");
		button_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_B.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_B.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_B.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_N = new JButton("n");
		button_N.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_N.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_N.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_N.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_M = new JButton("m");
		button_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_M.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_M.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_M.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_Comma = new JButton(",");
		button_Comma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_Comma.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_Comma.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Comma.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_Period = new JButton(".");
		button_Period.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_Period.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_Period.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Period.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_BackSlash = new JButton("/");
		button_BackSlash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_BackSlash.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_BackSlash.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_BackSlash.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_SemiColon = new JButton(";");
		button_SemiColon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_SemiColon.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_SemiColon.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_SemiColon.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_Apostrophe = new JButton("'");
		button_Apostrophe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_Apostrophe.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_Apostrophe.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Apostrophe.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_LeftBracket = new JButton("[");
		button_LeftBracket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_LeftBracket.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_LeftBracket.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_LeftBracket.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_RightBracket = new JButton("]");
		button_RightBracket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_RightBracket.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_RightBracket.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_RightBracket.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_ForwardSlash = new JButton("\\");
		button_ForwardSlash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_ForwardSlash.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_ForwardSlash.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_ForwardSlash.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_1 = new JButton("1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_1.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_1.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_2 = new JButton("2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_2.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_2.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_2.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_3 = new JButton("3");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_3.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_3.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_3.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_4 = new JButton("4");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_4.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_4.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_4.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_5 = new JButton("5");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_5.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_5.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_5.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_6 = new JButton("6");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_6.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_6.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_6.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_7 = new JButton("7");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_7.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_7.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_7.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_8 = new JButton("8");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_8.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_8.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_8.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_9 = new JButton("9");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_9.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_9.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_9.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_0 = new JButton("0");
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_0.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_0.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_0.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_Dash = new JButton("-");
		button_Dash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_Dash.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_Dash.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Dash.setMargin(new Insets(0, 0, 0, 0));
		
		
		JButton button_Equals = new JButton("=");
		button_Equals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currValue = selected.getText();
				currValue += button_Equals.getText();
				selected.setText(currValue);
				if (isShifted) button_Shift.doClick();
			}
		});
		button_Equals.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		button_Equals.setMargin(new Insets(0, 0, 0, 0));
		
		
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
				if(isShifted) {
					isShifted = false;
					button_1.setText("1");
					button_2.setText("2");
					button_3.setText("3");
					button_4.setText("4");
					button_5.setText("5");
					button_6.setText("6");
					button_7.setText("7");
					button_8.setText("8");
					button_9.setText("9");
					button_0.setText("0");
					button_Q.setText("q");
					button_W.setText("w");
					button_E.setText("e");
					button_R.setText("r");
					button_T.setText("t");
					button_Y.setText("y");
					button_U.setText("u");
					button_I.setText("i");
					button_O.setText("o");
					button_P.setText("p");
					button_A.setText("a");
					button_S.setText("s");
					button_D.setText("d");
					button_F.setText("f");
					button_G.setText("g");
					button_H.setText("h");
					button_J.setText("j");
					button_K.setText("k");
					button_L.setText("l");
					button_Z.setText("z");
					button_X.setText("x");
					button_C.setText("c");
					button_V.setText("v");
					button_B.setText("b");
					button_N.setText("n");
					button_M.setText("m");
					button_Dash.setText("-");
					button_Equals.setText("=");
					button_RightBracket.setText("[");
					button_LeftBracket.setText("]");
					button_ForwardSlash.setText("\\");
					button_SemiColon.setText(";");
					button_Apostrophe.setText("'");
					button_Comma.setText(",");
					button_Period.setText(".");
					button_BackSlash.setText("/");
				}
				else {
					isShifted = true;
					button_1.setText("!");
					button_2.setText("@");
					button_3.setText("#");
					button_4.setText("$");
					button_5.setText("%");
					button_6.setText("^");
					button_7.setText("&");
					button_8.setText("*");
					button_9.setText("(");
					button_0.setText(")");
					button_Q.setText("Q");
					button_W.setText("W");
					button_E.setText("E");
					button_R.setText("R");
					button_T.setText("T");
					button_Y.setText("Y");
					button_U.setText("U");
					button_I.setText("I");
					button_O.setText("O");
					button_P.setText("P");
					button_A.setText("A");
					button_S.setText("S");
					button_D.setText("D");
					button_F.setText("F");
					button_G.setText("G");
					button_H.setText("H");
					button_J.setText("J");
					button_K.setText("K");
					button_L.setText("L");
					button_Z.setText("Z");
					button_X.setText("X");
					button_C.setText("C");
					button_V.setText("V");
					button_B.setText("B");
					button_N.setText("N");
					button_M.setText("M");
					button_Dash.setText("_");
					button_Equals.setText("+");
					button_RightBracket.setText("{");
					button_LeftBracket.setText("}");
					button_ForwardSlash.setText("|");
					button_SemiColon.setText(":");
					button_Apostrophe.setText("\"");
					button_Comma.setText("<");
					button_Period.setText(">");
					button_BackSlash.setText("?");
				}
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
		textField_UsernameInput.setText(" ");
		textField_PasswordInput.setText(" ");
		selected = textField_UsernameInput;
		if (isShifted) button_Shift.doClick();
	}
}
