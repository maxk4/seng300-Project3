import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.AttendantStation;

import ui.AttendantUI;
import ui.views.util.AttendantView;

public class AttendantViewTest {

	public class DummyAttendantView extends AttendantView{

		protected DummyAttendantView(AttendantUI controller) {
			super(controller);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	DummyAttendantView view;
	AttendantUI ui;
	AttendantStation station;
	
	@Before
	public void setup() {
		station = new AttendantStation();
		ui = new AttendantUI(station, 1);
		view = new DummyAttendantView(ui);
	}
	
	@Test 
	public void testGetTitle() {
		String s = view.getTitle();
		assertTrue(null == s);
	}
}
