package simulation;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Currency;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.diy.hardware.DoItYourselfStation;
import com.diy.simulation.Customer;
import com.jimmyselectronics.Item;
import com.jimmyselectronics.opeechee.Card;
import com.unitedbankingservices.DisabledException;
import com.unitedbankingservices.TooMuchCashException;
import com.unitedbankingservices.banknote.Banknote;
import com.unitedbankingservices.coin.Coin;

import util.IntegerOnlyDocument;

public class CustomerUISimulator{

	//Added in Iteration 3 @Simrat (Starts)
	public static Customer currentCustomer;
	//Added In iteration 3 @simrat (ends)
	
	private Item scanWeightItem;
	
	public CustomerUISimulator(DoItYourselfStation station, Customer customer, String title) {
		
		JDialog customerSim = new JDialog();
		customerSim.setLocationRelativeTo(null);
		//Added in Iteration 3 @Simrat (Starts)
		currentCustomer = customer;
		//Added In iteration 3 @simrat (ends)
		JPanel container = new JPanel();
		//container.setLayout(new GridLayout(1, 5));
		
		JPanel cart = new JPanel();
		cart.setLayout(new BoxLayout(cart, BoxLayout.PAGE_AXIS));
		
		int i = 0;
		JLabel cartLabel = new JLabel("Cart: Click to Scan");
		cart.add(cartLabel);
		for (Item item : customer.shoppingCart) {
			JButton button = new JButton(String.format("Item %d: Weight: %.2fkg", ++i, item.getWeight()));
			button.addActionListener(e -> {
				if (station.mainScanner.isDisabled()) return;
				customer.shoppingCart.add(item);
				customer.selectNextItem();
				customer.scanItem(false);
			});
			cart.add(button);
		}
		
		JPanel scales = new JPanel();
		scales.setLayout(new BoxLayout(scales, BoxLayout.PAGE_AXIS));
		
		JPanel remove = new JPanel();
		remove.setLayout(new BoxLayout(remove, BoxLayout.PAGE_AXIS));
		JLabel removeLabel = new JLabel("Items: Click to remove from Bagging Area");
		remove.add(removeLabel);
		
		JLabel placeLabel = new JLabel("Items: Click to add to Bagging Area");
		JPanel placeList = new JPanel();
		placeList.setLayout(new BoxLayout(placeList, BoxLayout.PAGE_AXIS));
		placeList.add(placeLabel);
		i = 0;
		for (Item item : customer.shoppingCart) {
			JButton button = new JButton(String.format("Item %d: Weight: %.2fkg", ++i, item.getWeight()));
			JButton removeBtn = new JButton(String.format("Item %d: Weight: %.2fkg", i, item.getWeight()));
			removeBtn.addActionListener(e -> {
				remove.remove(removeBtn);
				customerSim.revalidate();
				customerSim.repaint();
				station.baggingArea.remove(item);
			});
			button.addActionListener(e -> {
				if (station.baggingArea.isDisabled()) return;
				customer.shoppingCart.add(item);
				customer.selectNextItem();
				customer.placeItemInBaggingArea();
				remove.add(removeBtn);
				customerSim.validate();
				customerSim.revalidate();
				customerSim.repaint();
			});
			placeList.add(button);
		}
		
		JPanel scanningArea = new JPanel();
		scanningArea.setLayout(new BoxLayout(scanningArea, BoxLayout.PAGE_AXIS));
		
		JLabel scanningAreaLabel = new JLabel("Enter Weight on Scanning Area Scale:");
		JTextField scanningWeight = new JTextField();
		scanningWeight.setDocument(new IntegerOnlyDocument(() -> {
			if (scanWeightItem != null)
				station.scanningArea.remove(scanWeightItem);
			int weight = 0;
			if (scanningWeight.getText().length() != 0) weight = Integer.parseInt(scanningWeight.getText());
			scanWeightItem = new Item(weight) {};
			station.scanningArea.add(scanWeightItem);
		}));
		scanningWeight.setColumns(10);
		
		scanningArea.add(scanningAreaLabel);
		scanningArea.add(scanningWeight);
		
		scales.add(remove);
		scales.add(scanningArea);
		
		
		JPanel wallet = new JPanel();
		wallet.setLayout(new BoxLayout(wallet, BoxLayout.PAGE_AXIS));
		
		JLabel walletLabel = new JLabel("Wallet: Click to select card pin 0000 will be used");
		wallet.add(walletLabel);
		
		for (Card card : customer.wallet.cards) {
			JButton button = new JButton(String.format("(%s): %s", card.number, card.kind));
			button.addActionListener(e -> {
				customer.wallet.cards.add(card);
				try {
					customer.selectCard(card.kind);
					customer.insertCard("0000".intern());
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				customer.wallet.cards.remove(customer.wallet.cards.size() - 1);
			});
			wallet.add(button);
		}
		
		JPanel cash = new JPanel();
		cash.setLayout(new BoxLayout(cash, BoxLayout.PAGE_AXIS));
		
		JLabel billLabel = new JLabel("Bills: Click to insert");
		cash.add(billLabel);
		for (int denom : station.banknoteDenominations) {
			JButton bill = new JButton(String.format("$%.2f", denom / 100d));
			bill.addActionListener(e -> {
				try {
					station.banknoteInput.receive(new Banknote(Currency.getInstance(Locale.CANADA), denom));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			cash.add(bill);
		}
		
		JLabel coinLabel = new JLabel("Coins: Click to insert");
		cash.add(coinLabel);
		for (long denom : station.coinDenominations) {
			JButton coin = new JButton(String.format("�%d", denom));
			coin.addActionListener(e -> {
				try {
					station.coinSlot.receive(new Coin(Currency.getInstance(Locale.CANADA), denom));
				} catch (DisabledException e1) {
					
				} catch (TooMuchCashException e1) {
					// Alert attendant
				}
			});
			cash.add(coin);
		}
		
		container.add(cart);
		container.add(placeList);
		container.add(scales);
		container.add(cash);
		container.add(wallet);
		container.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		
		customerSim.getContentPane().add(container);
		customerSim.setTitle("Customer Simulator");
		customerSim.pack();
		customerSim.setVisible(true);
		
	}
	
}
