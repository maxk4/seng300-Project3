/*Author: Yulin Yan UCID:30126391*/
 
import static org.junit.Assert.*;
 
import org.junit.Before;
import org.junit.Test;
 
import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.DoItYourselfStationAR;
import com.diy.hardware.Product;
import com.diy.hardware.external.ProductDatabases;
import com.diy.simulation.Customer;
import com.jimmyselectronics.Item;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
 
import ca.ucalgary.seng300.simulation.NullPointerSimulationException;
import util.AttendantStation;
import util.AttendantStationListener;
import util.AttendantUI;
import util.CustomerUI;
import util.CustomerStationListener;
import util.ExpectedWeightListener;
import util.ScanItemListener;
 
public class AddItemByScanningTests {
  
   AttendantUI attendant;
   Customer customer;
   AttendantStation astation;
   DoItYourselfStationAR station;
   CustomerUI ui;
   ScanItemListener sil = new ScanItemListener(ui);
   Barcode bc1, bc2, bc3;
   BarcodedItem item1, item2, item3;
   BarcodedProduct prod1, prod2;
   long price1 = 10L, price2 = 15L;
   double weight1 = 1.3, weight2 = 5.2, weight3 = 3.2;
   
   /**
    * Sets up an attendant linked to one station and a single customer that is using that station
    */
   @Before
   public void setup() {
	   ProductDatabases.BARCODED_PRODUCT_DATABASE.clear();
	   
       bc1 = new Barcode(new Numeral[] {Numeral.one});
       bc2 = new Barcode(new Numeral[] {Numeral.one, Numeral.two});
       bc3 = new Barcode(new Numeral[] {Numeral.three});
       
       item1 = new BarcodedItem(bc1, weight1);
       item2 = new BarcodedItem(bc2, weight2);
       item3 = new BarcodedItem(bc3, weight3);
       
       prod1 = new BarcodedProduct(bc1, "Product 1", price1, weight1);
       prod2 = new BarcodedProduct(bc2, "Product 2", price2, weight2);
       
       ProductDatabases.BARCODED_PRODUCT_DATABASE.put(bc1, prod1);
       ProductDatabases.BARCODED_PRODUCT_DATABASE.put(bc2, prod2);
       
       AttendantStation aStation = new AttendantStation();
      
       attendant = new AttendantUI(aStation, 10);
      
       AttendantStationListener listener = new AttendantStationListener(attendant);
       aStation.registerListener(listener);
      
       station = new DoItYourselfStationAR();
      
       station.plugIn();
       station.turnOn();
       station.scanner.enable();
       ui = new CustomerUI(station);
       ui.startScanning();
      
       sil = new ScanItemListener(ui);
       station.scanner.register(sil);
  
       ExpectedWeightListener ewl = new ExpectedWeightListener(ui);
       station.scale.register(ewl);
       ui.setWeightListener(ewl);
          
       CustomerStationListener dl = new CustomerStationListener(attendant);
       ui.registerDiscrepancyListener(dl);
  
          
       aStation.registerStation(ui);
      
       customer = new Customer();
       customer.useStation(station);
   }
   
   @Test
   public void testScanItemScannerDisabled() {

       // Setup Station
	   station.scanner.disable();
       customer.shoppingCart.add(item1);
       customer.selectNextItem();
       
       customer.scanItem();
       customer.placeItemInBaggingArea();
       
       assertFalse(ui.checkProductList(prod1));
       assertEquals(ui.productCount(), 0);
   }
   
   @Test
   public void testScanItemValidBarcode() { 
      
       // Setup Station
       ui.startScanning();
       customer.shoppingCart.add(item1);
       customer.shoppingCart.add(item2);
       customer.selectNextItem();
       
       // Scan first Item
       while(true) {
           customer.scanItem();
           if (sil.getSuccessfulScan() == 1)
        	   break;
       }
       customer.placeItemInBaggingArea();
       
       assertTrue(ui.checkProductList(prod2));
       assertFalse(ui.checkProductList(prod1));
       
       assertEquals(ui.productCount(), 1);
   }
   
   @Test
   public void testScanItemNoBarcode() {

       // Setup Station
       station.scanner.enable();
       customer.shoppingCart.add(item3);
       customer.selectNextItem();
       
       customer.scanItem();
       customer.placeItemInBaggingArea();
       
       assertEquals(ui.productCount(), 0);
   }
}
 
