package com.diy.software.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import util.MembershipNumber;

/*
- test where input is less than 8 = fail
- test where input is greater than 8 = fail
- test where input is exactly 8 = pass
- test where input is null (empty) = fail
- test where input is exactly 8 but the number is wrong = fail
=======
import org.junit.Before;
import org.junit.Test;

import util.MembershipNumber;

/*
- test where input is less than 8 = fail
- test where input is greater than 8 = fail
- test where input is null (empty) = fail
- test where input is exactly 8 but the number is not in the members list = fail
- test where input is exactly 8 but the currentMember is not null = fail
- test where input is exactly 8 and the currentMember is null = pass
>>>>>>> origin/JC
 */

public class MembershipNumberTest {


	private MembershipNumber memberNum;
	private Integer expected;
	
	@Before
	public void setup() {
		memberNum = new MembershipNumber();
		memberNum.addMem(12345678);
		memberNum.addMem(23456789);
	}
	
    /*
    this test checks if the length of the number is equal to 8, which it is not.
    So, the test will fail and give since the membership number is less than 8 digits long.
     */
    @Test
    public void NumberIsLessThanEightDigits(){
    	expected = 8;
    	assertEquals(expected, memberNum.checkMemNum(1234));
    }

    /*
    this test checks if the length of the number is equal to 8, which it is not.
    So, the test will fail since the membership number is greater than 8 digits long.
     */
    @Test
    public void NumberIsGreaterThanEightDigits(){

    	expected = 8;
        assertEquals(expected, memberNum.checkMemNum(123456789));
    }


    /*
    this test checks if a number has been entered or not.
     */
    @Test
    public void NotMemberTest(){
    	expected = 0; 
        assertEquals(expected, memberNum.checkMemNum(12345679));
    }

    /*
    this test checks if the number is correct but the a memberNumber is already in use.
     */
    @Test
    public void CorrectLengthButWrongNumber(){
    	memberNum.checkMemNum(12345678);
    	expected = 1;
    	assertEquals(expected, memberNum.checkMemNum(23456789));
    }    
    
    /*
    this test checks if the length of the number is equal to 8, which it is.
    So, this test passes since the number is 8 digits long
     */
    @Test
    public void CorrectMembershipNumber(){
    	expected = 12345678;
        assertEquals(expected, memberNum.checkMemNum(12345678));
    }
    
}
