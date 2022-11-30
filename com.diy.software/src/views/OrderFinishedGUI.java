package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.LayoutStyle.ComponentPlacement;

public class OrderFinishedGUI extends JFrame {
	private static final long serialVersionUID = 960506723715532997L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public OrderFinishedGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(50, 126, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel label_CheckMarkIcon = new JLabel("");
		label_CheckMarkIcon.setIcon(new ImageIcon(OrderFinishedGUI.class.getResource("/resources/icons8-ok-100.png")));
		
		JLabel label_Finished_Text = new JLabel("Finished! Please take your bags and receipt");
		label_Finished_Text.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
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
		contentPane.setLayout(gl_contentPane);
	}

}
