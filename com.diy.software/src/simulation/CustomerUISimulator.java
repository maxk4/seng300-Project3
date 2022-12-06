package simulation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.diy.hardware.DoItYourselfStation;
import com.diy.simulation.Customer;
import com.jimmyselectronics.Item;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.opeechee.Card;
import com.unitedbankingservices.DisabledException;
import com.unitedbankingservices.TooMuchCashException;
import com.unitedbankingservices.banknote.Banknote;
import com.unitedbankingservices.coin.Coin;

import ca.powerutility.NoPowerException;
import ca.ucalgary.seng300.simulation.InvalidArgumentSimulationException;
import util.IntegerOnlyDocument;
import util.MembershipDatabase;

public class CustomerUISimulator{

	//Added in Iteration 3 @Simrat (Starts)
	public static Customer currentCustomer;
	//Added In iteration 3 @simrat (ends)
	
	private Item scanWeightItem;
	private double scanWeight;
	
	public CustomerUISimulator(DoItYourselfStation station, Customer customer, String title) {
		
		JDialog customerSim = new JDialog();
		customerSim.setLocation(0, 400);
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
		
		JLabel scanningAreaLabel = new JLabel("Enter Weight on Scanning Area Scale (grams):");
		JTextField scanningWeight = new JTextField();
		scanningWeight.setDocument(new IntegerOnlyDocument(() -> {
			try {
				if (scanWeightItem != null)
					station.scanningArea.remove(scanWeightItem);
			} catch(InvalidArgumentSimulationException | NoPowerException e) {
				System.out.println("Failed" + e.toString());
			} try {

				int weight = 0;
				if (scanningWeight.getText().length() != 0) weight = Integer.parseInt(scanningWeight.getText());
				scanWeightItem = new Item(weight) {};
				scanWeight = weight;
				System.out.println(weight);
				station.scanningArea.add(scanWeightItem);
			} catch(InvalidArgumentSimulationException | NoPowerException e) {
				System.out.println("Failed" + e.toString());
			}
		}));
		scanningWeight.setColumns(10);
		
		JButton addScanningToBagging = new JButton("Add Weight to Bagging Area:");
		addScanningToBagging.addActionListener(e -> {
			station.baggingArea.add(new Item(scanWeight) {});
		});
		
		scanningArea.add(scanningAreaLabel);
		scanningArea.add(scanningWeight);
		scanningArea.add(addScanningToBagging);
		
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
		for (BigDecimal denom : station.coinDenominations) {
			JButton coin = new JButton(String.format("c%d", denom.longValue()));
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
		

		JPanel membership = new JPanel();
		membership.setLayout(new BoxLayout(membership, BoxLayout.PAGE_AXIS));
		
		//Labels used
		JLabel label_MemberCards_Text = new JLabel("Current Membership Cards in Customer Wallet:");
		label_MemberCards_Text.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		JLabel label_Barcode_Text = new JLabel("Current Barcodes in Customer Wallet:");
		label_Barcode_Text.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		JLabel label_separator1 = new JLabel("=====================================");
		label_separator1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		JLabel label_separator2 = new JLabel("=====================================");
		label_separator2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		JLabel label_separator3 = new JLabel("=====================================");
		label_separator3.setFont(new Font("Lucida Grande", Font.BOLD, 14));

		//Setting the combo boxes for membership cards, membership barcodes (start)
		//get the card from the customer wallet
		ArrayList<String> memCardsList = new ArrayList<>();
		for (Card card : customer.wallet.cards) {
			if(card.kind.equals("Membership"))
			{
				memCardsList.add(card.cardholder + " , " + card.number);
			}
			//Loaded the list with the membership cards
		}
		//Display the cards in a GUI
		JComboBox<String> comboBox_MemberCardsInWallet = new JComboBox<String>(memCardsList.toArray(new String[memCardsList.size()]));
		comboBox_MemberCardsInWallet.setFont(new Font("Lucida Grande", Font.BOLD, 15));

		//Barcodes for membership cards
		ArrayList<String> barcodes = new ArrayList<>();
		for (Barcode memberBarcode : MembershipDatabase.MEMBER_BARCODES.keySet())
		{
			barcodes.add(memberBarcode.toString());
		}
		JComboBox<String> comboBox_MemberCard_Barcodes = new JComboBox<String>(barcodes.toArray(new String[memCardsList.size()]));
		comboBox_MemberCard_Barcodes.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		//Added in 3rd Iteration ends
				//Cancel Button (ends)

				//Scan Card Button (starts)
				/*
				 * Scan Card button, once pressed, gets the value from the combo box field, gets the barcode from the barcode list from Simulation class, scans that using the barcode scanner using the scan() method, then checks if that exists in database and display the result on scan GUI
				 */
		JButton button_ScanCard = new JButton("Scan Card");
		button_ScanCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedBarcodeString = (String) comboBox_MemberCard_Barcodes.getSelectedItem();
				Barcode selectedMemberBarcode = null;
				System.out.println("(EnterMemberNumber GUI) selected barcode " + selectedBarcodeString);
				//Find this barcode in the list given in Simulation Class (as static list)
				for (Barcode memberBarcode : MembershipDatabase.MEMBER_BARCODES.keySet())
				{
					if(memberBarcode.toString().equals(selectedBarcodeString))
					{
						selectedMemberBarcode = memberBarcode;
						break;
					}
				}
				//Scan the selected member barcode with the scanner of the diy system
				if (station.mainScanner.scan(new BarcodedItem(selectedMemberBarcode, 0.1))) {
					//scan successfully
					System.out.println("(EnterMemberNumberGUI) Scan successfully");
					} else {
					//scan Failed
					System.out.println("(EnterMemberNumberGUI) Scan fail");
				}
			}});
		button_ScanCard.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		//Scan Card Button (ends)

		//Swipe Card Button (starts)
		/*
		 * Swipe Card button, once pressed, gets the value from the combo box field, gets the cards from the customer wallet from Simulation class, swipes that card into card reader and display the card-holder name onto the scan GUI.
		 *
		 * We are getting the name of customer from the database after swiping the card at the card-reader
		 * Card reader swipes the card, if the kind of card is "Membership" then we get the data without putting any hold
		 * in CardReaderListener,
		 * Swipe method in Card Reader returns the card data, and we get the card-holder name from it
		 */
		JButton button_SwipeCard = new JButton("Swipe Card");
		button_SwipeCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Make customer select the card
				String selectedCard = (String) comboBox_MemberCardsInWallet.getSelectedItem();
				System.out.println("(EnterMemberNumberGUI) Card selected " + selectedCard);
				//John Member-Card , 99999999
				//Split this to get card number
				String[] selectedCardSplit = selectedCard.split(" , ");
				String selectedCardNumber  = selectedCardSplit[1];
				//System.out.println("Card selected Numberrr" + selectedCardNumber);
				Card card = new Card("Membership", selectedCardNumber, "Membership Holder", "000", "000", true, true);
				try {
					station.cardReader.swipe(card);
					System.out.println("Swipe successful");
				} catch (IOException e1) {
					e1.printStackTrace();
					System.out.println("Swipe failed");
				}
			}
		});
		button_SwipeCard.setFont(new Font("Lucida Grande", Font.BOLD, 19));
				
		membership.add(label_MemberCards_Text);
		membership.add(label_separator1);
		membership.add(comboBox_MemberCardsInWallet);
		membership.add(button_SwipeCard);
		membership.add(label_separator2);
		membership.add(label_Barcode_Text);
		membership.add(label_separator3);
		membership.add(comboBox_MemberCard_Barcodes);
		membership.add(button_ScanCard);
		
		
		container.add(cart);
		container.add(placeList);
		container.add(scales);
		container.add(cash);
		container.add(wallet);
		container.add(membership);
		container.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		
		customerSim.getContentPane().add(container);
		customerSim.setTitle("Customer Simulator");
		customerSim.pack();
		customerSim.setVisible(true);
		
	}
	
}
