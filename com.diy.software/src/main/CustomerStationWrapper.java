package main;


import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.PLUCodedProduct;
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
	
	
	private Product waitingFor = null;
	private String waitingForDescription = null;
	
	
	
	public CustomerStationWrapper(DoItYourselfStation diyStation, AttendantUI attendant) {
		
		payment = new PaymentController(diyStation);
		cart = new CartController(diyStation);
		print = new PrinterController(diyStation);
		scale = new ScaleController(diyStation);
		customer = new CustomerUI(diyStation, "Customer Station");
		
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
			public void addPLUProduct(PLUCodedProduct product) {
				waitingFor = product;
				waitingForDescription = product.getDescription();
				customer.setView(CustomerUI.PLACE_ITEM);
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
				waitingFor = product;
				waitingForDescription = description;
				customer.setView(CustomerUI.PLACE_ITEM);
			}

			@Override
			public void itemPlaced() {
				if (waitingFor == null) return;
				
				long price = waitingFor.getPrice();
				double weight = scale.getScanningAreaWeight();
				if (!waitingFor.isPerUnit()) price *= weight;
				
				cart.addItem(waitingFor, waitingForDescription, price, weight);
				customer.setView(CustomerUI.SCAN);
				waitingFor = null;
			}

			@Override
			public void requestUsePersonalBag() {
				boolean approved = attendant.approveOwnBagRequest(diyStation);
				customer.setView(CustomerUI.SCAN);
				if (approved) scale.approveWeight();
				
			}
			
		});
		
		attendant.register(new AttendantUIListener() {
			@Override
			public void approveWeight(DoItYourselfStation station) {
				if (station == diyStation) {
					scale.approveWeight();
				}
			}

			@Override
			public void approveOwnBag(DoItYourselfStation station) {
			}

			@Override
			public void denyOwnBag(DoItYourselfStation station) {
				// TODO Auto-generated method stub
				
			}

			// Do Not Place Item in Bagging Area Use Case
			@Override
			public void approveNoBag(DoItYourselfStation station) {
			// Reduces the expected weight in the Bagging Area by the expected weight of the item
				if (station == diyStation) {
					scale.removeLastItemWeight();
				}
			}
		});
		
		scale.register(new ScaleListener() {
			@Override
			public void notifyWeightDiscrepancyDetected() {
				if (inProgress) {
					customer.setView(CustomerUI.WEIGHT_DISCREPANCY);
					attendant.notifyWeightDiscrepancyDetected(diyStation);
				}
			}

			@Override
			public void notifyWeightDiscrepancyResolved() {
				if (inProgress) {
					customer.setView(CustomerUI.SCAN);
					attendant.notifyWeightDiscrepancyResolved(diyStation);
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
