package ui.views;

import javax.swing.JPanel;

import ui.AttendantUI;

public abstract class AttendantView extends JPanel {
	private static final long serialVersionUID = 755704336014907219L;
	protected String title;
	protected AttendantUI controller;
	
	
	protected AttendantView(AttendantUI controller) {
		this.controller = controller;
	}
	
	public String getTitle() {
		return title;
	}
}
