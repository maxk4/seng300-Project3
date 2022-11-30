package util;

public class Octet {
	long a = 0, b = 0, c = 0, d = 0;
	
	public Octet(byte[] bytes) {
		for (int i = 0; i < 8; i++) {
			a <<= 8;
			a += ((int) bytes[i]) & 0xFF;
		}
		for (int i = 0; i < 8; i++) {
			b <<= 8;
			b += ((int) bytes[i + 8]) & 0xFF;
		}
		for (int i = 0; i < 8; i++) {
			c <<= 8;
			c += ((int) bytes[i + 16]) & 0xFF;
		}
		for (int i = 0; i < 8; i++) {
			d <<= 8;
			d += ((int) bytes[i + 24]) & 0xFF;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Octet)) return false;
		Octet o = (Octet) obj;
		return a == o.a && b == o.b && c == o.c && d == o.d;
	}
	
	@Override
	public String toString() {
		return String.format("%16x%16x%16x%16x", a, b, c, d).replaceAll(" ", "0");
	}
}