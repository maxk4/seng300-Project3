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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductInfo other = (ProductInfo) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (price != other.price)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}
}
