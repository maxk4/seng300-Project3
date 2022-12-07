package com.diy.software.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.diy.hardware.BarcodedProduct;
import com.diy.hardware.Product;
import com.diy.hardware.external.ProductDatabases;
import com.jimmyselectronics.necchi.Barcode;
import com.jimmyselectronics.necchi.BarcodedItem;
import com.jimmyselectronics.necchi.Numeral;

import util.ProductList;
import util.ProductList.Consumer;

public class ProductListTests {
	
	ProductList list;
	Consumer consumer;
	
	Barcode bc1, bc2;
	BarcodedItem item1, item2;
	BarcodedProduct prod1, prod2;
	
	double weight1 = 5.0;
	long price1 = 10L;

	@Before
	public void setup() {
		bc1 = new Barcode(new Numeral[] {Numeral.one});
		bc2 = new Barcode(new Numeral[] {Numeral.one, Numeral.two});
		
		item1 = new BarcodedItem(bc1, weight1);
		item2 = new BarcodedItem(bc2, weight1);
		
		prod1 = new BarcodedProduct(bc1, "Product 1", price1, weight1);
		prod2 = new BarcodedProduct(bc2, "Product 2", price1, weight1);
		
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(bc1, prod1);
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(bc2, prod2);
		
		consumer = new CL();
		
		list = new ProductList();
	}
	
	/*
	 * Test for adding a valid product.
	 */
	@Test
	public void testAddValidProduct() {
		list.add(prod1, prod1.getDescription(), price1);
		assertTrue(list.containsProduct(prod1));
		
		assertFalse(list.containsProduct(prod2));
		list.add(prod2, prod2.getDescription(), price1);
		
		assertEquals(2, list.size());
	}
	
	/*
	 * Test for verifying an action is performed for each item in the list.
	 */
	@Test
	public void testForEach() {
		list.add(prod1, prod1.getDescription(), price1);
		list.add(prod2, prod2.getDescription(), price1);
		
		list.forEach(consumer);
		
		assertEquals(2, found);
	}
	
	/*
	 * Test for adding a null product.
	 */
	@Test (expected = NullPointerException.class)
	public void testAddNullProduct() {
		list.add(null, prod1.getDescription(), price1);
	}
	
	/*
	 * Test for adding a product with a null description.
	 */
	@Test (expected = NullPointerException.class)
	public void testAddProductNullDescription() {
		list.add(prod1, null, price1);
	}
	
	int found = 0;
	public class CL implements Consumer {

		@Override
		public void consume(Product product, String desc, long price) {
			found++;
		}
		
	}
}
