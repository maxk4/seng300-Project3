package util;
import java.util.HashMap;
import java.util.Map;
/**
 Represents a set of databases that the simulation can interact with.
 The databases have to be populated in order to be usable.

 New Class Added in Iteration 3 @Simrat
 */

public class MembershipDatabase {

	public MembershipDatabase() {
		//empty constructor
		//accessed as static variable
	}

	/**
	 * Accessed as static variable
	 * To fill the data, add the following into the 'setup method'
	 * Adding data to the membership database
	 * 		MembershipDatabase.MEMBER_DATABASE.put(555,"J");
	 * 		MembershipDatabase.MEMBER_DATABASE.put(1234,"John Doe");
	 * 		MembershipDatabase.MEMBER_DATABASE.put(5555,"John Doe 2");
	 */
	public static final Map<Integer, String> MEMBER_DATABASE = new HashMap<>();
	//comes with its own methods of a hashmap, can search for key, and it will return a string of customer name

}

