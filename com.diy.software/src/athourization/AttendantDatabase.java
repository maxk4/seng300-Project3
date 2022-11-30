package athourization;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import util.Octet;

public class AttendantDatabase {
	
	private static final Map<Integer, Octet> ATTENDANT_DATABASE = new HashMap<Integer, Octet>();
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
	 * @param id int id of the attendant
	 * @param password String password to check
	 * @return boolean true if match false otherwise
	 */
	public static boolean validate(int id, String password) {
		if (!ATTENDANT_DATABASE.containsKey(id)) return false;
		return ATTENDANT_DATABASE.get(id).equals(hash(password));
	}
	
	/**
	 * Add an attendant to the database
	 * @param id int id of the attendant
	 * @param password String their password
	 */
	public static void add(int id, String password) {
		ATTENDANT_DATABASE.put(id, hash(password));
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
	 * @param id int id of the attendant
	 * @param oldPassword String old password
	 * @param newPassword String new password
	 * @return boolean true if successful false otherwise
	 */
	public static boolean changePassword(int id, String oldPassword, String newPassword) {
		if (!ATTENDANT_DATABASE.containsKey(id)) return false;
		if (!validate(id, oldPassword)) return false;
		ATTENDANT_DATABASE.put(id, hash(newPassword));
		return true;
	}
}
