package cart;

import java.util.ArrayList;
import java.util.List;


import com.diy.hardware.Product;

import util.ProductInfo;

public class ProductList {
	private List<Product> products;
	private List<String> descriptions;
	private List<Long> prices;
	private List<Double> weights;
	
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
		weights = new ArrayList<Double>();
	}
	
	/**
	 * Add a product, description, price triple to the list
	 * @param product Product the product
	 * @param desc String description of the product to show
	 * @param price long price of the product
	 * @throws NullPointerException when one or more of the arguments is null
	 */
	public void add(Product product, String desc, long price, double weight) {
		if (product == null) throw new NullPointerException();
		if (desc == null) throw new NullPointerException();
		products.add(product);
		descriptions.add(desc);
		prices.add(price);
		weights.add(weight);
	}
	
	public boolean remove(Product product, String desc, long price, double weight) {
		for (int i = 0; i < size(); i++) {
			if (!products.get(i).equals(product)) continue;
			if (!descriptions.get(i).equals(desc)) continue;
			if (price != prices.get(i)) continue;
			if (weight != weights.get(i)) continue;
			
			products.remove(i);
			descriptions.remove(i);
			prices.remove(i);
			weights.remove(i);
			
			return true;
		}
		return false;
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
	
	public ProductInfo get(int index) {
		if (index < 0 || index >= size()) return null;
		return new ProductInfo(products.get(index), descriptions.get(index), prices.get(index), weights.get(index));
	}
	
	public ProductInfo[] toArray() {
		ProductInfo[] res = new ProductInfo[size()];
		for (int i = 0; i < size(); i++) res[i] = get(i);
		return res;
	}

	public void clear() {
		products.clear();
		descriptions.clear();
		prices.clear();
		weights.clear();
	}
}
