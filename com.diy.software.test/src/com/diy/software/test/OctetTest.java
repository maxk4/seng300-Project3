package com.diy.software.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import util.Octet;

public class OctetTest {
	
	Octet o;
	
	@Before
	public void setup() {
		byte b = 0x0;
		
		// for the constructor, the byte[] passed in must have a size >= 32.
		byte[] bytes = new byte[32];
		for (int i = 0; i < 32; i++) {
			bytes[i] = b;
		}
		o = new Octet(bytes);
	}
	
	@Test
	public void testEqualsWithNullObject() {
		assertFalse(o.equals(null));
	}

	@Test
	public void testEqualsWithNonNullNonOctetObject() {
		Object obj = 3;
		assertFalse(o.equals(obj));
	}
	
	@Test
	public void testEqualsWithOctetObject() {
		assertTrue(o.equals(o));	
		System.out.println(o.toString());
	}
	
	@Test
	public void testToString() { 
		String s = "";
		for (int i = 0; i < 64; i++) {
			s += "0";
		}
		assertTrue(s.equals(o.toString()));
	}
}
