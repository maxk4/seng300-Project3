package com.diy.software.test;

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
		AttendantDatabase.clear();
		username = "username";
		pswd = "000000";
		newpswd = pswd + "0";
		// make non-duplicated new username
		counter++;
		newUsername = username + Integer.toString(counter); 
		AttendantDatabase.add(username, pswd);
	}
	
	@Test 
	public void testValidLogin() {
		boolean isValid = AttendantDatabase.validate(username, pswd);
		assertTrue(isValid);
	}
	
	@Test
	public void testIncorrectUsernameLogin() {
		boolean isValid = AttendantDatabase.validate(newUsername, pswd);
		assertFalse(isValid);
	}
	
	@Test
	public void testIncorrectPasswordLogin() {
		boolean isValid = AttendantDatabase.validate(username, newpswd);
		assertFalse(isValid);
	}
	
	@Test
	public void testAddExistingUsernameWithNewPassword() {
		AttendantDatabase.add(username, newpswd);
		boolean isValid = AttendantDatabase.validate(username, newpswd);
		assertFalse(isValid);
	}

	@Test
	public void testAddWithNullStringUsername() {
		AttendantDatabase.add(null, pswd);
		boolean isValid = AttendantDatabase.validate(null, pswd);
		assertFalse(isValid); 
	}
	
	@Test
	public void testChangePasswordCorrectUsernameAndPassword() {
		boolean isChanged = AttendantDatabase.changePassword(username, pswd, newpswd);
		assertTrue(isChanged);
		boolean isValid = AttendantDatabase.validate(username, pswd);
		assertFalse(isValid);
		isValid = AttendantDatabase.validate(username, newpswd);
		assertTrue(isValid);
	}
	
	@Test
	public void testChangePasswordIncorrectUsername() {
		boolean isChanged = AttendantDatabase.changePassword(newUsername, pswd, newpswd);
		assertFalse(isChanged);
		boolean isValid = AttendantDatabase.validate(username, pswd);
		assertTrue(isValid);
		isValid = AttendantDatabase.validate(username, newpswd);
		assertFalse(isValid);
	}
	
	@Test
	public void testChangePasswordIncorrectPassword() {
		boolean isChanged = AttendantDatabase.changePassword(username, newpswd, newpswd);
		assertFalse(isChanged);
		boolean isValid = AttendantDatabase.validate(username, pswd);
		assertTrue(isValid);
		isValid = AttendantDatabase.validate(username, newpswd);
		assertFalse(isValid);
	}
	
	@Test
	public void testAlphabeticPassword() {
		String alphabeticpswd = "abcdefg";
		AttendantDatabase.add(newUsername, alphabeticpswd);
		boolean isValid = AttendantDatabase.validate(newUsername, alphabeticpswd);
		assertTrue(isValid);
		isValid = AttendantDatabase.validate(newUsername, alphabeticpswd.substring(1));
		assertFalse(isValid);
	}
	
	@Test
	public void testAlphaNumericPassword() {
		String alphaNumericpswd = "abcdefg123";
		AttendantDatabase.add(newUsername, alphaNumericpswd);
		boolean isValid = AttendantDatabase.validate(newUsername, alphaNumericpswd);
		assertTrue(isValid);
		isValid = AttendantDatabase.validate(newUsername, alphaNumericpswd.substring(1));
		assertFalse(isValid);
	}
}
