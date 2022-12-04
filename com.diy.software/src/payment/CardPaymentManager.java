package payment;


import java.util.LinkedList;
import java.util.Queue;

import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.external.CardIssuer;


import util.Bank;
import util.GiftCardIssuer;

public class CardPaymentManager extends PaymentManager {
	
	public CardPaymentManager(PaymentController controller, DoItYourselfStation station) {
		super(controller);
		station.cardReader.register(new CardReadListener(this, station));
	}

	private Queue<HoldData> holds = new LinkedList<HoldData>();
	private class HoldData {
		public String cardNumber;
		public long id;
		public double amount;
		// the instution that issued the card
		public CardIssuer issuer;
		
		public HoldData(String cardNumber, long id, double amount, CardIssuer issuer) {
			this.cardNumber = cardNumber;
			this.id = id;
			this.amount = amount;
			this.issuer = issuer;
		}
	}
	
	@Override
	public long returnFunds(long amount) {
		long returned = 0;
		
		while (returned < amount && !holds.isEmpty()) {
			HoldData hold = holds.poll();
			hold.issuer.releaseHold(hold.cardNumber, hold.id);
			funds -= hold.amount;
			returned += hold.amount;
		}
		return returned;
	}

	@Override
	public long pay(long amount) {
		double rem = amount / 100d;
		
		while (rem > 0 && !holds.isEmpty()) {
			HoldData hold = holds.poll();
			double payment = Math.min(hold.amount, rem);
			hold.issuer.postTransaction(hold.cardNumber, hold.id, payment);
			rem -= payment;
			funds -= payment;
		}
		while (!holds.isEmpty()) {
			HoldData hold = holds.poll();
			hold.issuer.releaseHold(hold.cardNumber, hold.id);
			funds -= hold.amount * 100;
		}
		return (long) ((amount - rem) * 100);
	}
	
	public void placeHold(String cardNumber, long amount, String cardType) throws HoldException {
		System.out.println(cardNumber + " $" + amount / 100d);
		// for gift cards
        if (cardType.equals("Gift Card")) {
			long hold = GiftCardIssuer.CARD_ISSUER.authorizeHold(cardNumber, amount / 100d);
		    if (hold < 0) throw new HoldException();
			holds.add(new HoldData(cardNumber, hold, amount / 100d, GiftCardIssuer.CARD_ISSUER));

		// for credit cards, and debit cards
		} else {
			long hold = Bank.CARD_ISSUER.authorizeHold(cardNumber, amount / 100d);
		    if (hold < 0) throw new HoldException(); 
			holds.add(new HoldData(cardNumber, hold, amount / 100d, Bank.CARD_ISSUER));
		}
		funds += amount;
		controller.holdSuccessful();
	}

}
