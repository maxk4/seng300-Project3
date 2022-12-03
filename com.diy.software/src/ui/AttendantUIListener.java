package ui;

import com.diy.hardware.DoItYourselfStation;
import com.diy.hardware.Product;

import util.ProductInfo;

public interface AttendantUIListener {
	public void approveWeight(DoItYourselfStation station);
	public void approveOwnBag(DoItYourselfStation station);
	public void denyOwnBag(DoItYourselfStation station);
	public void approveNoBag(DoItYourselfStation station);
	public ProductInfo[] requestProductInfo(DoItYourselfStation station);
	public void disableStation(DoItYourselfStation station);
	public void enableStation(DoItYourselfStation station);
	public void addItem(DoItYourselfStation station, Product product, String description);
	public void removeItem(DoItYourselfStation station, Product product, String description, long price, double weightInGrams);

}
