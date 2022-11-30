package util;

import java.util.*;

/*
input will be stored in a HashMap, where:
- key: customer's name
- value: membership number


DO NOT USE THIS CLASS

 */

public class MembershipNumber {
	
	public static ArrayList<Integer> MEMBER_NUMBERS;
	private Integer currentMember;
	
	
	public MembershipNumber() {
		MEMBER_NUMBERS = new ArrayList<Integer>();
		currentMember = null;
	}
    /*
    public synchronized Integer checkMemNum(Integer num) {
    	String LengthString = num.toString();
    	int NumberLength = LengthString.length();
        if (NumberLength != 8) {
        	System.out.println("The number you entered is invalid");
        	return 8;}
        if (!(MEMBER_NUMBERS.contains(num))) {
        	System.out.println("The number you entered is not a member");
        	return 0;}
        if (currentMember != null) {
        	System.out.println("A member # is already in use : " + currentMember);
        	return 1;}
        else { 
        	System.out.println("The number you entered is valid");
            currentMember = num;
           	System.out.println("Current Member #: " + currentMember);
          	return currentMember;
        }
    }
    
    public void addMem(Integer num) {
    	MEMBER_NUMBERS.add(num);
    }
    
    public void removeMem(Integer num){
    	MEMBER_NUMBERS.remove(num);
    }
    
    public Integer getCurrentMember() {
    	return currentMember;
    }


     */
}
