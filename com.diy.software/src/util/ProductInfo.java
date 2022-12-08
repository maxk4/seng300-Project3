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
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		else if (!(o instanceof ProductInfo))return false;
		ProductInfo p = (ProductInfo) o;
		if (!product.equals(p.product))return false;
		if (weight != p.weight)return false;
		if (!description.equals(p.description))return false;
		if (price != p.price)return false;
		return true;
	}
}
