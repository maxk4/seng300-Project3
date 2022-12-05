package simulation;

import java.awt.GridLayout;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.diy.hardware.DoItYourselfStation;
import com.unitedbankingservices.banknote.Banknote;
import com.unitedbankingservices.coin.Coin;

import ui.AttendantUI;

public class MaintenanceSimulator {
	
	private List<DoItYourselfStation> stations;
	private JFrame frame;

	public MaintenanceSimulator(AttendantUI attendant, List<DoItYourselfStation> stations) {
		this.stations = stations;
		
		frame = new JFrame();
		addWidgets();
		
		frame.setTitle("Maintanance Simulator");
		//Added in Iteration 3 @Simrat (Starts)
		frame.setLocation(800,150);
		//Added in Iteration 3 @Simrat (Starts)
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void addWidgets() {
		JPanel container = new JPanel(new GridLayout(2, 2));
		
		JPanel ink = new JPanel();
		ink.setLayout(new BoxLayout(ink, BoxLayout.PAGE_AXIS));
		JLabel inkLabel = new JLabel("Stations: Click to add Ink");
		JTextField inkAmmountField = new JTextField();
		inkAmmountField.setToolTipText("Enter ammount to add");
		ink.add(inkLabel);
		ink.add(inkAmmountField);
		ink.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 16));
		for (int i = 0; i < stations.size(); i++) {
			DoItYourselfStation station = stations.get(i);
			JButton inkBtn = new JButton("Station " + (i + 1));
			inkBtn.addActionListener(e -> {
				try {
					String parse = "";
					for (char c : inkAmmountField.getText().toCharArray())
						if (Character.isDigit(c)) parse += c;
					station.printer.addInk(Integer.parseInt(parse));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			ink.add(inkBtn);
		}
		

		JPanel paper = new JPanel();
		paper.setLayout(new BoxLayout(paper, BoxLayout.PAGE_AXIS));
		JLabel paperLabel = new JLabel("Stations: Click to add Paper");
		JTextField paperAmmountField = new JTextField(); //who misspelled this
		paperAmmountField.setToolTipText("Enter ammount to add");
		paper.add(paperLabel);
		paper.add(paperAmmountField);
		for (int i = 0; i < stations.size(); i++) {
			DoItYourselfStation station = stations.get(i);
			JButton paperBtn = new JButton("Station " + (i + 1));
			paperBtn.addActionListener(e -> {
				try {
					String parse = "";
					for (char c : paperAmmountField.getText().toCharArray())
						if (Character.isDigit(c)) parse += c;
					station.printer.addPaper(Integer.parseInt(parse));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			paper.add(paperBtn);
		}
		
		JPanel banknote = new JPanel();
		banknote.setLayout(new BoxLayout(banknote, BoxLayout.PAGE_AXIS));
		JLabel banknoteLabel = new JLabel("Stations: Click to add Banknotes");
		JTextField banknoteDenomField = new JTextField();
		banknoteDenomField.setToolTipText("Enter banknote denomination to add");
		JTextField banknoteAmountField = new JTextField();
		banknoteAmountField.setToolTipText("Enter amount to add");
		banknote.add(banknoteLabel);
		banknote.add(banknoteDenomField);
		banknote.add(banknoteAmountField);
		for (int i = 0; i < stations.size(); i++) {
			DoItYourselfStation station = stations.get(i);
			JButton banknoteBtn = new JButton("Station " + (i + 1));
			banknoteBtn.addActionListener(e -> {
				try {
					String parse = "";
					for (char c : banknoteDenomField.getText().toCharArray())
						if (Character.isDigit(c)) parse += c;
					int denomination = Integer.parseInt(parse);
					parse = "";
					for (char c : banknoteAmountField.getText().toCharArray())
						if (Character.isDigit(c)) parse += c;
					Banknote[] banknotes = new Banknote[Integer.parseInt(parse)];
					Arrays.fill(banknotes, new Banknote(Currency.getInstance(Locale.CANADA), denomination));
					station.banknoteDispensers.get(denomination).load(banknotes);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			banknote.add(banknoteBtn);
		}
		
		JPanel coin = new JPanel();
		coin.setLayout(new BoxLayout(coin, BoxLayout.PAGE_AXIS));
		JLabel coinLabel = new JLabel("Stations: Click to add Coins");
		JTextField coinDenomField = new JTextField();
		coinDenomField.setToolTipText("Enter coin denomination to add\n Available denominations:");
		JTextField coinAmountField = new JTextField();
		coinAmountField.setToolTipText("Enter amount to add");
		coin.add(coinLabel);
		coin.add(coinDenomField);
		coin.add(coinAmountField);
		for (int i = 0; i < stations.size(); i++) {
			DoItYourselfStation station = stations.get(i);
			JButton coinBtn = new JButton("Station " + (i + 1));
			coinBtn.addActionListener(e -> {
				try {
					String parse = "";
					for (char c : coinDenomField.getText().toCharArray())
						if (Character.isDigit(c)) parse += c;
					long denomination = Long.parseLong(parse);
					parse = "";
					for (char c : coinAmountField.getText().toCharArray())
						if (Character.isDigit(c)) parse += c;
					Coin[] coins = new Coin[Integer.parseInt(parse)];
					Arrays.fill(coins, new Coin(Currency.getInstance(Locale.CANADA), denomination));
					station.coinDispensers.get(denomination).load(coins);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			coin.add(coinBtn);
		}
		
		container.add(ink);
		container.add(paper);
		container.add(banknote);
		container.add(coin);
		
		container.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		frame.getContentPane().add(container);
	}

}
