import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import athourization.AttendantDatabase;

public class AttendantDatabaseTest {
	
	private AttendantDatabase db;	
	private String username;
	private String newUsername;
	private String pswd;
	private String newpswd;
	private static int counter = 0; // for making non-duplicated new username for each test
	
	@Before
	public void setup() {
		db = new AttendantDatabase();
		username = "username";
		pswd = "000000";
		newpswd = pswd + "0";
		db.add(username, pswd);
		// make non-duplicated new username
		counter++;
		newUsername = username + Integer.toString(counter); 
	}
	
	@Test
	public void testValidLogin() {
		boolean isValid = db.validate(username, pswd);
		assertTrue(isValid);
	}
	
	@Test
	public void testIncorrectUsernameLogin() {
		boolean isValid = db.validate(newUsername, pswd);
		assertFalse(isValid);
	}
	
	@Test
	public void testIncorrectPasswordLogin() {
		boolean isValid = db.validate(username, newpswd);
		assertFalse(isValid);
	}
	
	@Test
	public void testChangePasswordCorrectUsernameAndPassword() {
		boolean isChanged = db.changePassword(username, pswd, newpswd);
		assertTrue(isChanged);
		boolean isValid = db.validate(username, pswd);
		assertFalse(isValid);
		isValid = db.validate(username, newpswd);
		assertTrue(isValid);
	}
	
	@Test
	public void testChangePasswordIncorrectUsername() {
		boolean isChanged = db.changePassword(newUsername, pswd, newpswd);
		assertFalse(isChanged);
		boolean isValid = db.validate(username, pswd);
		assertTrue(isValid);
		isValid = db.validate(username, newpswd);
		assertFalse(isValid);
	}
	
	@Test
	public void testChangePasswordIncorrectPassword() {
		boolean isChanged = db.changePassword(username, newpswd, newpswd);
		assertFalse(isChanged);
		boolean isValid = db.validate(username, pswd);
		assertTrue(isValid);
		isValid = db.validate(username, newpswd);
		assertFalse(isValid);
	}
	
	@Test
	public void testAlphabeticPassword() {
		String alphabeticpswd = "abcdefg";
		db.add(newUsername, alphabeticpswd);
		boolean isValid = db.validate(newUsername, alphabeticpswd);
		assertTrue(isValid);
		isValid = db.validate(newUsername, alphabeticpswd.substring(1));
		assertFalse(isValid);
	}
	
	@Test
	public void testAlphaNumericPassword() {
		String alphaNumericpswd = "abcdefg123";
		db.add(newUsername, alphaNumericpswd);
		boolean isValid = db.validate(newUsername, alphaNumericpswd);
		assertTrue(isValid);
		isValid = db.validate(newUsername, alphaNumericpswd.substring(1));
		assertFalse(isValid);
	}
}
