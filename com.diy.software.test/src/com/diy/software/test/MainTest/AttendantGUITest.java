package com.diy.software.test.MainTest;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.AttendantStation;
import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.Product;

import ca.powerutility.PowerGrid;
import main.CustomerStationWrapper;
import simulation.Simulation;
import ui.AttendantUI;
import ui.AttendantUIListener;
import ui.views.attendant.AttendantSearchCatalogueGUI;
import ui.views.attendant.AttendantSearchCatalogueGUI.KeyButton;
import ui.views.attendant.CustomerCart;
import util.ProductInfo;

public class AttendantGUITest {
	AttendantStation attendantStation;
	DoItYourselfStation station;
	List<DoItYourselfStation> stations;
	AttendantUI attendantUI;
	ProductInfo[] products;
	
	@Before
	public void setUp() {
		PowerGrid.engageUninterruptiblePowerSource();
		station = new DoItYourselfStation();
		station.plugIn();
		station.turnOn();
		
		attendantStation = new AttendantStation();
		attendantStation.add(station);
		stations = new ArrayList<DoItYourselfStation>();
		stations.add(station);
		attendantUI = new AttendantUI(attendantStation, 1);
		
		attendantUI.addCustomerUI(station);
		attendantUI.startupStation(station);
		
		products = new ProductInfo[] {new ProductInfo(null, "null", 2, 2.3)};
		
		attendantUI.register(new AttendantUIListener() {

			@Override
			public boolean approveWeight(DoItYourselfStation station) {return false;}

			@Override
			public void approveNoBag(DoItYourselfStation station) {}

			@Override
			public ProductInfo[] requestProductInfo(DoItYourselfStation station) {

				return products;
			}

			@Override
			public void disableStation(DoItYourselfStation station) {}

			@Override
			public void enableStation(DoItYourselfStation station) {}

			@Override
			public void addItem(DoItYourselfStation station, Product product, String description) {}

			@Override
			public void removeItem(DoItYourselfStation station, Product product, String description, long price,
					double weightInGrams) {}

			@Override
			public void startupStation(DoItYourselfStation station) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void shutdownStation(DoItYourselfStation station) {}
			
		});
		
		Simulation.main(new String[] {"1"});
	}
	@Test
	public void testOpenCart() {
		attendantUI.gui.requestOpenStation(station);
		assertFalse(attendantUI.gui == null); //the gui opens a new scene
		CustomerCart cart = new CustomerCart(station, attendantUI, attendantStation.screen.getFrame(), products);
		AttendantSearchCatalogueGUI search = new AttendantSearchCatalogueGUI(attendantUI, cart, station, attendantStation.screen.getFrame());
		for (KeyButton key : search.keys) key.doClick();
		attendantUI.setView(search);
		assertFalse(attendantUI.gui == null);
	}
}
