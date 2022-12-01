package util;
import com.diy.hardware.external.CardIssuer;

public class GiftCardIssuer {
	public static final CardIssuer CARD_ISSUER;
	
	static {
		CARD_ISSUER = new CardIssuer("Gift Card Services", 100);
	}
}
