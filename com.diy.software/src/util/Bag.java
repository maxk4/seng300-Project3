package util;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.external.ProductDatabases;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.Numeral;

public class Bag extends BarcodedProduct {

	public static final Barcode BAGBARCODE = new Barcode(new Numeral[] { Numeral.zero, Numeral.zero, Numeral.zero, Numeral.zero, Numeral.one });
	public static final long PRICE = 1;
	public static final double WEIGHT = 0.01;
	
	static {
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(BAGBARCODE, new Bag());
	}
	
	public Bag() {
		super(BAGBARCODE, "Store Bag", PRICE, WEIGHT);
	}
}
