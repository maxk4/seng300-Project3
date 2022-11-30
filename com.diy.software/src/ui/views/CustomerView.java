package ui.views;

import javax.swing.JPanel;

import ui.CustomerUI;

public abstract class CustomerView extends JPanel {
	private static final long serialVersionUID = 755704336014907219L;
	protected String title;
	protected CustomerUI controller;
	
	
	protected CustomerView(CustomerUI controller) {
		super();
		this.controller = controller;
	}
	
	public String getTitle() {
		return title;
	}
}
