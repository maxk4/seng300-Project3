package ui.views.util;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.AttendantUI;

public abstract class AttendantView extends JPanel {
	private static final long serialVersionUID = 755704336014907219L;
	protected AttendantUI controller;
	private JFrame parent;
	
	protected AttendantView(AttendantUI controller, JFrame parent) {
		this.controller = controller;
		this.parent = parent;
	}
}
