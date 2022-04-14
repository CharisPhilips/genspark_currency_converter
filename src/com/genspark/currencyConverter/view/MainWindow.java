package com.genspark.currencyConverter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.genspark.currencyConverter.engine.CurrencyConverter;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 3097792305120242710L;

	//Members
	private JTextField textAmount;
	
	JComboBox<String> fromCurrency;
	JComboBox<String> toCurrency;
	
	private JButton btnConvert;
	private JTextField textConvert;
	private JLabel labelError;

	//Constructor
	public MainWindow() throws HeadlessException {
		super();
		initView();
		initActionListeners();
	}
	
	//Methods
	private void initView() {
		//make sure the program exits when the frame closes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Currency Converter");
		setSize(800, 150);

		//This will center the JFrame in the middle of the screen
		setLocationRelativeTo(null);
		
		BorderLayout layout = new BorderLayout();
		layout.setHgap(30);
		layout.setVgap(30);
		
		add(initAmount(), BorderLayout.LINE_START);
		add(initFromCurrency(), BorderLayout.CENTER);
		add(initToCurrency(), BorderLayout.LINE_END);
		add(initConvert(), BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private JPanel initAmount() {
		JPanel panelAmount = new JPanel();
		panelAmount.setSize(200, 100);
		JLabel labelAmount = new JLabel("Convert This Amount:");
		
		panelAmount.add(labelAmount, BorderLayout.NORTH);
		
		this.textAmount = new JTextField();
		this.textAmount.setPreferredSize(new Dimension(100, 25));
		
		panelAmount.add(textAmount, BorderLayout.SOUTH);
		
		return panelAmount;
	}

	private JPanel initFromCurrency() {
		JPanel panelFromCurrency = new JPanel();
		
		panelFromCurrency.setSize(200, 100);
		
		fromCurrency = new JComboBox<String>(CurrencyConverter.getCurrencyList());
		JLabel labelFromCurrency = new JLabel("From This Currency:");
		panelFromCurrency.add(labelFromCurrency, BorderLayout.NORTH);
		panelFromCurrency.add(fromCurrency, BorderLayout.SOUTH);
		return panelFromCurrency;
		
	}

	private JPanel initToCurrency() {
		
		JPanel panelToCurrency = new JPanel();
		panelToCurrency.setSize(200, 100);
		toCurrency = new JComboBox<String>(CurrencyConverter.getCurrencyList());
		JLabel labelToCurrency = new JLabel("To This Currency:");
		panelToCurrency.add(labelToCurrency, BorderLayout.NORTH);
		panelToCurrency.add(toCurrency, BorderLayout.SOUTH);
		return panelToCurrency;
	}

	private JPanel initConvert() {
		JPanel panelConvert = new JPanel();
		panelConvert.setSize(200, 100);
		
		BorderLayout layout = new BorderLayout();
		layout.setHgap(30);
		layout.setVgap(10);

		this.btnConvert = new JButton("Convert");
		
		this.textConvert = new JTextField();
		this.textConvert.setPreferredSize(new Dimension(100, 25));
		this.labelError = new JLabel();
		this.labelError.setVisible(true);
		this.labelError.setForeground(Color.RED);
		
		panelConvert.add(btnConvert);
		panelConvert.add(textConvert);
		panelConvert.add(labelError);
		return panelConvert;
	}
	
	private void initActionListeners() {
		btnConvert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String strAmount = MainWindow.this.textAmount.getText();
				try {
					double amount = Double.parseDouble(strAmount);
					int fromCurrencyIdx = MainWindow.this.fromCurrency.getSelectedIndex();
					int toCurrencyIdx = MainWindow.this.toCurrency.getSelectedIndex();
					
					double convert = CurrencyConverter.getConvertRate(fromCurrencyIdx, toCurrencyIdx, amount);
					
					MainWindow.this.textConvert.setText(String.format("%.5f", convert));
					MainWindow.this.labelError.setVisible(false);
				} catch(Exception e) {
					MainWindow.this.labelError.setText(e.getMessage());
					MainWindow.this.labelError.setVisible(true);
				}
			}
		});
	}
}
