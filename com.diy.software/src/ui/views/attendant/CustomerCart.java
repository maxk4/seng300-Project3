package ui.views.attendant;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.diy.hardware.DoItYourselfStation;

import ui.AttendantUI;
import ui.views.util.AttendantView;
import util.ProductInfo;

public class CustomerCart extends AttendantView {
	
	private static final long serialVersionUID = -695860933154295335L;

	private class RemoveButton extends JButton {
		private static final long serialVersionUID = 2196974853723525767L;

		public RemoveButton() {
			super("remove");
			setAlignmentX(Component.LEFT_ALIGNMENT);
			setMargin(new Insets(0, 0, 0, 0));
			setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
			setMinimumSize(new Dimension(0, 50));
			setPreferredSize(new Dimension(100, 50));
		}
	}
	
	public CustomerCart(DoItYourselfStation station, AttendantUI attendant, JFrame parent, ProductInfo ... products) {
		super(attendant, parent);
		
		JScrollPane productListContainer = new JScrollPane();
		
		JPanel productList = new JPanel();
		productList.setLayout(new BoxLayout(productList, BoxLayout.PAGE_AXIS));
		productList.setPreferredSize(new Dimension(400, 600));
		
		for (ProductInfo info : products) {
			JPanel row = new JPanel(new GridLayout(1, 2));
			row.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
			row.setMinimumSize(new Dimension(0, 0));
			row.setPreferredSize(new Dimension(100, 50));
			row.setAlignmentX(JPanel.LEFT_ALIGNMENT);
			
			JLabel desc = new JLabel(info.description);
			desc.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
			desc.setMinimumSize(new Dimension(0, 0));
			desc.setPreferredSize(new Dimension(100, 50));
			desc.setAlignmentX(JPanel.LEFT_ALIGNMENT);
			desc.setHorizontalTextPosition(JLabel.CENTER);
			
			RemoveButton remove = new RemoveButton();
			remove.addActionListener(e -> {
				attendant.forceRemove(station, info.product, info.description, info.price, info.weight);
				productList.remove(row);
				this.revalidate();
				this.repaint();
			});
			row.add(desc);
			row.add(remove);
			productList.add(row);
		}
		
		productListContainer.setViewportView(productList);
		
		JButton add = new JButton("Add an Item");
		add.addActionListener(e -> {
			attendant.setView(new AttendantSearchCatalogueGUI(attendant, this, station, parent));
		});
		add.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
		add.setMinimumSize(new Dimension(0, 50));
		add.setPreferredSize(new Dimension(400, 50));
		add.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		JButton refresh = new JButton("Refresh");
		refresh.addActionListener(e -> {
			attendant.setView(new CustomerCart(station, attendant, parent, controller.requestProductList(station)));
		});
		refresh.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
		refresh.setMinimumSize(new Dimension(0, 50));
		refresh.setPreferredSize(new Dimension(400, 50));
		refresh.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		JButton cancel = new JButton("Return");
		cancel.addActionListener(e -> {
			attendant.setView(AttendantUI.MAIN);
		});
		cancel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
		cancel.setMinimumSize(new Dimension(0, 50));
		cancel.setPreferredSize(new Dimension(400, 50));
		cancel.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(productListContainer);
		add(add);
		add(refresh);
		add(cancel);
		setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		setBackground(new Color(14, 144, 215));
	}
}
