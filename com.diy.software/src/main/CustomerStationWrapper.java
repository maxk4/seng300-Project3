package main;


import java.awt.event.WindowEvent;

import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.PLUCodedProduct;
import com.diy.hardware.Product;

import cart.CartController;
import cart.CartListener;
import membership.MembershipController;
import membership.MembershipListener;
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
import util.ProductInfo;

public class CustomerStationWrapper {
	
	private CartController cart;
	private PaymentController payment;
	private PrinterController print;
	private ScaleController scale;
	private CustomerUI customer;
	private MembershipController membership;
	private boolean inProgress = true;
	
	
	private Product waitingFor = null;
	private String waitingForDescription = null;
	
	private PaymentListener paymentListener;
	private CartListener cartListener;
	private ScaleListener scaleListener;
	private CustomerUIListener customerUIListener;
	private MembershipListener membershipListener;
	
	public CustomerStationWrapper(DoItYourselfStation diyStation, AttendantUI attendant) {
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

			// Disables the station, preventing customers from using it, until attendant re-enables it
			@Override
			public void disableStation(DoItYourselfStation station) {
				if (station == diyStation) {
					if(!inProgress){
						customer.setView(CustomerUI.DISABLED);
						return;
					}
					//Customer Session currently in progress
					customer.disable();
				}
			}

			//Enables the use of a station by customers, after it has been disabled
			@Override
			public void enableStation(DoItYourselfStation station) {
				if (station == diyStation) {
					if (inProgress)
						customer.enable();
					else
						customer.setView(CustomerUI.START);
				}
			}

			@Override
			public ProductInfo[] requestProductInfo(DoItYourselfStation station) {
				if (station == diyStation) return cart.getProductInfo();
				return null;
			}

			@Override
			public void addItem(DoItYourselfStation station, Product product, String description) {
				if (station != diyStation) return;
				waitingFor = product;
				waitingForDescription = description;
				customer.setView(CustomerUI.PLACE_ITEM);
			}

			@Override
			public void removeItem(DoItYourselfStation station, Product product, String description, long price, double weight) {
				if (station != diyStation) return;
				cart.removeItem(product, description, price, weight);
			}

			@Override
			public void startupStation(DoItYourselfStation station) {
				if(station != diyStation) return;
				station.turnOn();
				payment = new PaymentController(diyStation);
				cart = new CartController(diyStation);
				print = new PrinterController(diyStation);
				scale = new ScaleController(diyStation);
				customer = new CustomerUI(diyStation, "Customer Station");
				membership = new MembershipController(diyStation);
				
				paymentListener = new PaymentListener() {
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
				};
				payment.register(paymentListener);
				
				cartListener = new CartListener() {
					@Override
					public void notifyItemAdded(Product prod, long price, double weight) {
						payment.addCost(price);
						updateProductList();
						updateCashGUI();
						if (!(prod instanceof Bag)) {
							scale.updateExpectedWeight(weight);
						}
					}

					@Override
					public void notifyItemRemoved(Product prod, long price, double weight) {
						payment.addCost(-price);
						updateProductList();
						updateCashGUI();
						if (!(prod instanceof Bag))
							scale.updateExpectedWeight(-weight);
					}
					
				};
				cart.register(cartListener);
				
				customerUIListener = new CustomerUIListener() {
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
						payment.completeTransaction();
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
						if (!waitingFor.isPerUnit()) price *= weight / 1000d;

						customer.setView(CustomerUI.SCAN);
						cart.addItem(waitingFor, waitingForDescription, price, weight);
						waitingFor = null;
					}

					@Override
					public void requestUsePersonalBag() {
						boolean approved = attendant.approveOwnBagRequest(diyStation);
						customer.setView(CustomerUI.SCAN);
						if (approved) scale.approveWeight();
						
					}

					@Override
					public void requestNoBag() {
						if (attendant.requestNoBag(station)) {
							scale.approveWeight();
						}
					}
					
				};
				customer.register(customerUIListener);
				scaleListener = new ScaleListener() {
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
					
				};
				scale.register(scaleListener);
				
				membershipListener = new MembershipListener() {
					@Override
					public void notifyMembershipCardRead(int memberId) {
						customer.useMemberName(memberId);
					}
					
				};
				membership.register(membershipListener);
				
				customer.disable();
			}

			@Override
			public void shutdownStation(DoItYourselfStation station) {
				if(station != diyStation) return;
				payment.deregister(paymentListener);
				cart.deregister(cartListener);
				scale.deregister(scaleListener);
				customer.deregister(customerUIListener);
				//diyStation.screen.getFrame().dispose();
				System.out.println("Shutdown");
				diyStation.screen.setVisible(false);
				station.turnOff();
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