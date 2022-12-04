package ui.views.util;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.diy.hardware.Product;

public class ProductButton extends JButton {
	private static final long serialVersionUID = -6966276679344423613L;
	
	private Product product;
	public String name;
	
	public ProductButton(Product product, String name) {
		super(name);
		
		init(product, name);
	}
	
	public ProductButton(Product product, String name, ActionListener listener) {
		super(name);
		
		init(product, name);
		
		addActionListener(listener);
	}
	
	private void init(Product product, String name) {
		this.product = product;
		this.name = name;
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		setMinimumSize(new Dimension(0, 100));
		setPreferredSize(new Dimension(100, 100));
		
		try {
			setIcon(new ImageIcon(ProductButton.class.getResource("/resources/products/icons8-" + name.toLowerCase() + "-100.png")));
		} catch(Exception e) {}
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
