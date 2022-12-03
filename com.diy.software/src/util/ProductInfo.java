package util;

import com.diy.hardware.Product;

public class ProductInfo {
	public Product product;
	public String description;
	public long price;
	public double weight;
	
	public ProductInfo(Product product, String description, long price, double weight) {
		this.product = product;
		this.description = description;
		this.price = price;
		this.weight = weight;
	}
}
