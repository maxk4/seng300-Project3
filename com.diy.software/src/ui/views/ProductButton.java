package ui.views;


import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.diy.hardware.Product;

import ui.CustomerUI;

public class ProductButton extends JButton {
	private static final long serialVersionUID = -6966276679344423613L;
	
	private final Product product;
	public final String name;
	
	public ProductButton(CustomerUI controller, Product product, String name) {
		super(name);
		
		this.product = product;
		this.name = name;
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		setMinimumSize(new Dimension(0, 100));
		setPreferredSize(new Dimension(100, 100));
		
		try {
			setIcon(new ImageIcon(ProductButton.class.getResource("/resources/products/icons8-" + name.toLowerCase() + "-100.png")));
		} catch(Exception e) {}
		
		addActionListener(e -> {
			controller.selectItem(product, name);
		});
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof ProductButton)) return false;
		ProductButton p = (ProductButton) obj;
		if (!product.equals(p.product)) return false;
		if (!name.equals(p.name)) return false;
		return true;
	}
	
}
