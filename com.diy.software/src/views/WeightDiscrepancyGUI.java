package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;

public class WeightDiscrepancyGUI extends JFrame {

	private static final long serialVersionUID = 8489647036255738889L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public WeightDiscrepancyGUI() {
		setTitle("Weight Descrepancy");
		setBackground(new Color(206, 103, 94));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		setBounds(100, 100, 554, 431);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(206, 103, 94));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel label_StationLocked_Text = new JLabel("Station Locked for Weight Descrepancy");
		label_StationLocked_Text.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		
		JLabel label_LockIcon = new JLabel("");
		label_LockIcon.setIcon(new ImageIcon(WeightDiscrepancyGUI.class.getResource("/resources/icons8-lock-200.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(183)
					.addComponent(label_LockIcon)
					.addContainerGap(200, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(63)
					.addComponent(label_StationLocked_Text, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
					.addGap(65))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(label_LockIcon, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addGap(58)
					.addComponent(label_StationLocked_Text, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(103, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
