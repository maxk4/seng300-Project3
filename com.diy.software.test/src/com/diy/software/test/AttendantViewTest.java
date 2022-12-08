package com.diy.software.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.AttendantStation;

import ui.AttendantUI;
import ui.views.util.AttendantView;

public class AttendantViewTest {

	public class DummyAttendantView extends AttendantView{

		protected DummyAttendantView(AttendantUI controller, JFrame frame) {
			super(controller, frame);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	DummyAttendantView view;
	AttendantUI ui;
	AttendantStation station;
	JFrame frame; 
	
	@Before
	public void setup() {
		station = new AttendantStation();
		ui = new AttendantUI(station, 1);
		view = new DummyAttendantView(ui, frame);
	}
	
	@Test 
	public void testGetTitle() {
		String s = view.getTitle();
		assertNull(s);
	}
}
