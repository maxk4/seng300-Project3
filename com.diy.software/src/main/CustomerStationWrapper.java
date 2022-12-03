package main;

import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.Product;

import cart.CartController;
import payment.PaymentController;
import payment.PaymentListener;
import printing.PrinterController;
import scale.ScaleController;
import scale.ScaleListener;
import ui.AttendantUI;
import ui.AttendantUIListener;
import ui.CustomerUI;
import ui.CustomerUIListener;
import util.Bag;

public class CustomerStationWrapper {
	private CartController cart;
	private PaymentController payment;
	private PrinterController print;
	private ScaleController scale;
	private CustomerUI customer;
	private boolean inProgress = true;
	
	public CustomerStationWrapper(DoItYourselfStation diySstation, AttendantUI attendant) {
		
		payment = new PaymentController(diySstation);
		cart = new CartController(diySstation);
		print = new PrinterController(diySstation);
		scale = new ScaleController(diySstation);
		customer = new CustomerUI(diySstation, "Customer Station");
		
		payment.register(new PaymentListener() {
			@Override
			public void cardPaymentSucceeded() {
				customer.setView(CustomerUI.SCAN);
				updateProductList();
			}

			@Override
			public void cashInserted() {
				updateCashGUI();
				updateProductList();
			}
		});
		
		cart.register((prod, price, weight) -> {
			payment.addCost(price);
			updateProductList();
			updateCashGUI();
			if (!(prod instanceof Bag))
				scale.updateExpectedWeight(weight);
		});
		
		customer.register(new CustomerUIListener() {
			@Override
			public void purchaseBags(int amount) {
				for (int i = 0; i < amount; i++) {
					//add bag
					Bag bag = new Bag();
					cart.addItem(bag, Bag.DESCRIPTION, Bag.PRICE, Bag.WEIGHT);
				}
				updateProductList();
			}

			@Override
			public void endSession() {
				if (payment.hasRemainingBalance()) return;
				customer.setView(CustomerUI.END);
				inProgress = false;
				scale.setExpectedWeight(0);
				System.out.println("Finish");
				String receipt = cart.getReceipt();
				print.print(receipt);
				cart.clear();
				updateProductList();
			}

			@Override
			public void beginSession() {
				inProgress = true;
			}

			@Override
			public void selectItem(Product product, String description) {
				// Notify ScanningArea scale to look for weight
			}
			
		});
		
		attendant.register(new AttendantUIListener() {
			@Override
			public void approveWeight(DoItYourselfStation station) {
				if (station == diySstation) {
					scale.approveWeight();
				}
			}

			@Override
			public void approveOwnBag(DoItYourselfStation station) {
				// TODO Auto-generated method stub
			}

			@Override
			public void denyOwnBag(DoItYourselfStation station) {
				// TODO Auto-generated method stub
				
			}

			// Do Not Place Item in Bagging Area Use Case
			@Override
			public void approveNoBag(DoItYourselfStation station) {
			// Reduces the expected weight in the Bagging Area by the expected weight of the item
				if (station == diySstation) {
					scale.removeLastItemWeight();
				}
			}

			@Override
			public void disableStation(DoItYourselfStation station) {
			// Reduces the expected weight in the Bagging Area by the expected weight of the item
				if (station == diySstation) {
					customer.setView(CustomerUI.DISABLED);
					System.out.println("SYSTEM DISABLED");
				}
			}

			@Override
			public void enableStation(DoItYourselfStation station) {
				// Reduces the expected weight in the Bagging Area by the expected weight of the item
				if (station == diySstation) {
					customer.setView(CustomerUI.SCAN);
					System.out.println("SYSTEM ENABLED");
				}
			}
		});
		
		scale.register(new ScaleListener() {
			@Override
			public void notifyWeightDiscrepancyDetected() {
				if (inProgress) {
					customer.setView(CustomerUI.WEIGHT_DISCREPANCY);
					attendant.notifyWeightDiscrepancyDetected(diySstation);
				}
			}

			@Override
			public void notifyWeightDiscrepancyResolved() {
				if (inProgress) {
					customer.setView(CustomerUI.SCAN);
					attendant.notifyWeightDiscrepancyResolved(diySstation);
				} else {
					customer.setView(CustomerUI.START);
					inProgress = true;
				}
			}
			
		});
	}
	
	private void updateProductList() {
		customer.updateProductList(payment.getBalance(), payment.getAvailableFunds(), cart.getProductString(), cart.getPriceString());
	}
	
	private void updateCashGUI() {
		customer.updateCashGUI(payment.availableCash(), payment.getBalance());
	}
}
