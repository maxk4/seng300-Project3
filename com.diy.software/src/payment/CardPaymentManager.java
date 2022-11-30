package payment;


import java.util.LinkedList;
import java.util.Queue;

import com.diy.hardware.DoItYourselfStation;

import util.Bank;

public class CardPaymentManager extends PaymentManager {
	
	public CardPaymentManager(PaymentController controller, DoItYourselfStation station) {
		super(controller);
		station.cardReader.register(new CardReadListener(this, station));
	}

	private Queue<HoldData> holds = new LinkedList<HoldData>();
	
	private class HoldData {
		String cardNumber;
		long id;
		double amount;
		
		public HoldData(String cardNumber, long id, double amount) {
			this.cardNumber = cardNumber;
			this.id = id;
			this.amount = amount;
		}
	}
	
	@Override
	public long returnFunds(long amount) {
		long returned = 0;
		
		while (returned < amount && !holds.isEmpty()) {
			HoldData hold = holds.poll();
			Bank.CARD_ISSUER.releaseHold(hold.cardNumber, hold.id);
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
			try {
				for (int i = 0; i < 5 && !Bank.CARD_ISSUER.postTransaction(hold.cardNumber, hold.id, payment); i++) Thread.sleep(20000);
				rem -= payment;
				funds -= payment;
				break;
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		while (!holds.isEmpty()) {
			HoldData hold = holds.poll();
			Bank.CARD_ISSUER.releaseHold(hold.cardNumber, hold.id);
			funds -= hold.amount * 100;
		}
		return (long) ((amount - rem) * 100);
	}
	
	public void placeHold(String cardNumber, long amount) throws HoldException {
		System.out.println(cardNumber + " $" + amount / 100d);
		long hold = Bank.CARD_ISSUER.authorizeHold(cardNumber, amount / 100d);
		if (hold < 0) throw new HoldException();
		holds.add(new HoldData(cardNumber, hold, amount / 100d));
		funds += amount;
		controller.holdSuccessful();
	}

}
