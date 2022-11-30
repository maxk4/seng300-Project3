package cart;

import java.util.ArrayList;
import java.util.List;


import com.diy.hardware.Product;

public class ProductList {
	private List<Product> products;
	private List<String> descriptions;
	private List<Long> prices;
	
	/**
	 * A strategy that operates on a product, description, price triple
	 *	
	 */
	public static interface Consumer {
		public void consume(Product product, String desc, long price);
	}
	
	public ProductList() {
		products = new ArrayList<Product>();
		descriptions = new ArrayList<String>();
		prices = new ArrayList<Long>();
	}
	
	/**
	 * Add a product, description, price triple to the list
	 * @param product Product the product
	 * @param desc String description of the product to show
	 * @param price long price of the product
	 * @throws NullPointerException when one or more of the arguments is null
	 */
	public void add(Product product, String desc, long price) {
		if (product == null) throw new NullPointerException();
		if (desc == null) throw new NullPointerException();
		products.add(product);
		descriptions.add(desc);
		prices.add(price);
	}
	
	/**
	 * Iterate over the entries of the list performing an action on each entry
	 * @param consumer Consumer action to perform on each entry
	 */
	public void forEach(Consumer consumer) {
		for (int i = 0; i < products.size(); i++) {
			consumer.consume(products.get(i), descriptions.get(i), prices.get(i));
		}
	}
	
	/**
	 * Check if a product is in the list
	 * @param product Product to check for
	 * @return true if the product is in the list false otherwise
	 */
	public boolean containsProduct(Product product) {
		for (Product p : products) {
			if (p.equals(product)) return true;
		}
		return false;
	}
	
	/**
	 * Get the size of the list
	 * @return int size of the list
	 */
	public int size() {
		return products.size();
	}

	public void clear() {
		products.clear();
		descriptions.clear();
		prices.clear();
	}
}
