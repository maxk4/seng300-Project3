package util;
import com.diy.hardware.external.CardIssuer;

public class Bank {
	public static final CardIssuer CARD_ISSUER;
	
	static {
		CARD_ISSUER = new CardIssuer("United Banking Services", 100);
	}
}
