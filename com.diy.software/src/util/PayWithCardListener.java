package util;


import com.diy.hardware.external.CardIssuer;
import com.jimmyselectronics.AbstractDevice;
import com.jimmyselectronics.AbstractDeviceListener;
import com.jimmyselectronics.opeechee.Card.CardData;
import com.jimmyselectronics.opeechee.CardReader;
import com.jimmyselectronics.opeechee.CardReaderListener;

import ca.ucalgary.seng300.simulation.InvalidArgumentSimulationException;

public class PayWithCardListener implements CardReaderListener {
	
	
	private boolean enabled = false;
	private CardData data; 
	public boolean cardInserted = false;
	private CustomerUI customer;
	
	
	public PayWithCardListener(CustomerUI customer) {
		this.customer = customer;
	}
	
//	public CardData getCardData() {
//		return data;
//	}
//	public boolean isCardInserted() {
//		return cardInserted;
//	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		enabled = true;
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		enabled = false;
	}

	@Override
	public void turnedOn(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void turnedOff(AbstractDevice<? extends AbstractDeviceListener> device) {}

	@Override
	public void cardInserted(CardReader reader) {
		cardInserted = true;
	}

	@Override
	public void cardRemoved(CardReader reader) {
		cardInserted = false;
	}

	@Override
	public void cardDataRead(CardReader reader, CardData data) {
		if (!enabled) return;
		this.data = data;
		transactionWithCreditCard(reader, data, Bank.CARD_ISSUER, customer.getBalance());
	}
	
	/**
	 * transactionWithCreditCard: Simulates a payment with a credit card.
	 * @param reader the card reader
	 * @param data the card's data
	 * @param bank the card's issuer
	 * @param total the payment total
	 * 
	 * @throws InvalidArgumentSimulationException
	 */
	public void transactionWithCreditCard(CardReader reader, CardData data, CardIssuer bank, long total) {
		//if the card is not a credit card and debit card
		if (!(data.getKind().equals("credit") || data.getKind().equals("debit"))) {
			throw new InvalidArgumentSimulationException("Card inserted is not of type credit or debit");
		}
		
		//get hold number on card, issued by the bank, if an error is encountered,
		//a hold number of -1 is found, and the system is notified that the hold failed
		long holdNumber = bank.authorizeHold(data.getNumber(), total);
		//If communication with the Bank is faulty or interrupted, prior to the hold being authorized, the transaction will fail.
		if (holdNumber == -1) {
			System.out.println("The hold failed");
			
		}

	     
		//otherwise, the transaction is posted, the amount of credit is reduced, and the 
		//system is notified that the transaction was successful
		else {
			boolean posted = bank.postTransaction(data.getNumber(), holdNumber, total);
			if (posted) {
				System.out.println("The transaction was successful");
				customer.notifyPayment(total);
				return;
			}
			//looping 5 tries
			for (int i = 0; i < 5 || (posted = bank.postTransaction(data.getNumber(), holdNumber, total)); i++){
				//delay for 20 seconds
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
			}
			if (posted) {
				System.out.println("The transaction was successful");
				customer.notifyPayment(total);
			} else {
				System.out.println("The transaction failed");
			}
		}
	}

	@Override
	public void cardTapped(CardReader reader) {
		//transactionWithCreditCard(reader, data, Bank.CARD_ISSUER, customer.getBalance());
		
	}

	@Override
	public void cardSwiped(CardReader reader) {
		//transactionWithCreditCard(reader, data, Bank.CARD_ISSUER, customer.getBalance());
		
	}
}


