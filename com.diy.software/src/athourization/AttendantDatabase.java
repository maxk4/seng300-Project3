package athourization;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import util.Octet;

public class AttendantDatabase {
	
	private static final Map<String, Octet> ATTENDANT_DATABASE = new HashMap<String, Octet>();
	private static final MessageDigest SHA256;
	static {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		SHA256 = md;
	}
	
	/**
	 * Check if the password matches the one in the database
	 * @param username String username of the attendant
	 * @param password String password to check
	 * @return boolean true if match false otherwise
	 */
	public static boolean validate(String username, String password) {
		if (username == null || password == null) return false;
		if (!ATTENDANT_DATABASE.containsKey(username)) return false;
		return ATTENDANT_DATABASE.get(username).equals(hash(password));
	}
	
	/**
	 * Add an attendant to the database
	 * @param username String username of the attendant
	 * @param password String their password
	 */
	public static boolean add(String username, String password) {
		if (username == null || password == null) return false;
		if (ATTENDANT_DATABASE.containsKey(username)) return false;
		ATTENDANT_DATABASE.put(username, hash(password));
		return true;
	}
	
	/**
	 * Hash a String to a 256 bit digest using SHA-256
	 * @param password String to hash
	 * @return Octet digest
	 */
	public static Octet hash(String password) {
		SHA256.update(password.getBytes());
		return new Octet(SHA256.digest());
	
	}
	
	/**
	 * Update the password of the attendant
	 * @param username String username of the attendant
	 * @param oldPassword String old password
	 * @param newPassword String new password
	 * @return boolean true if successful false otherwise
	 */
	public static boolean changePassword(String username, String oldPassword, String newPassword) {
		if (!ATTENDANT_DATABASE.containsKey(username)) return false;
		if (!validate(username, oldPassword)) return false;
		ATTENDANT_DATABASE.put(username, hash(newPassword));
		return true;
	}

	public static void clear() {
		ATTENDANT_DATABASE.clear();
	}
}
