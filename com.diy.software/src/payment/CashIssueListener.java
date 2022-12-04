package payment;

public interface CashIssueListener {

	public abstract void notifyNotEnoughCash(); //not enough cash, we need the attendant to come and give the customer their change
	
	public abstract void notifyRequireAdditionalBanknotes(int neededBanknoteDenomination); //we need more banknotes in the change place
	
	public abstract void notifyRequireAdditionalCoins(long neededCoinDenomination); //we need more coins in the change place
}
