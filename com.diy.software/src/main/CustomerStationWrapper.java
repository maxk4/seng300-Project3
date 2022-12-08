package main;


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
import printing.PrinterListener;
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
	public boolean inProgress = true;
	
	
	private Product waitingFor = null;
	private String waitingForDescription = null;
	
	public PaymentListener paymentListener;
	public CartListener cartListener;
	public ScaleListener scaleListener;
	public CustomerUIListener customerUIListener;
	public MembershipListener membershipListener;

	
	public CustomerStationWrapper(DoItYourselfStation diyStation, AttendantUI attendant) {
		attendant.register(new AttendantUIListener() {
			@Override
			public boolean approveWeight(DoItYourselfStation station) {
				if (station == diyStation) {
					scale.approveWeight();
				}
				return true;
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
					station.screen.disable();
				}
				
			}

			//Enables the use of a station by customers, after it has been disabled
			@Override
			public void enableStation(DoItYourselfStation station) {
				if (station == diyStation) {
					station.screen.enable();
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

					@Override
					public void notifyNotEnoughCash() {
						attendant.notifyOutOfChange(diyStation);
					}

					@Override
					public void notifyRequiresAdditionalBanknote(int req) {
						
					}
					
					@Override
					public void notifyRequiresAdditionalCoins(long neededCoinDenomination) {
						// TODO Auto-generated method stub
						
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
					public String notifyMembershipCardRead(int memberId) {
						return customer.useMemberName(memberId);
					}
					
				};
				membership.register(membershipListener);
				print.register(new PrinterListener() {

					@Override
					public void notifyLowInk(DoItYourselfStation station) {
						attendant.notifyLowInkDetected(diyStation);
					}

					@Override
					public void notifyLowPaper(DoItYourselfStation station) {
						attendant.notifyLowPaperDetected(diyStation);
					}

					@Override
					public void notifyInkRefilled(DoItYourselfStation station) {
						attendant.notifyLowInkResolved(diyStation);
					}

					@Override
					public void notifyPaperRefilled(DoItYourselfStation station) {
						attendant.notifyLowPaperResolved(diyStation);
					}

					@Override
					public void empty(DoItYourselfStation station) {
						attendant.notifyPrintFailure(diyStation);
					}
					
				});
				
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
	public long getCurrentDue() {
		return payment.getBalance() - payment.getAvailableFunds();
	}
	private void updateProductList() {
		customer.updateProductList(payment.getBalance(), payment.getAvailableFunds(), cart.getProductString(), cart.getPriceString());
	}
	
	private void updateCashGUI() {
		customer.updateCashGUI(payment.availableCash(), payment.getBalance());
	}
}