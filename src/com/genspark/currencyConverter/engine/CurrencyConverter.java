package com.genspark.currencyConverter.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurrencyConverter {

	//Memebers
	private String currency;
	private double rate;
	
	//Constructor
	public CurrencyConverter(String currency, double rate) {
		super();
		this.currency = currency;
		this.rate = rate;
	}
	
	//Getter and Setter
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public static List<CurrencyConverter> global_currency = new ArrayList<CurrencyConverter> (
		Arrays.asList(
			new CurrencyConverter("USD", 1.D),
			new CurrencyConverter("EUR", 1.089088D),
			new CurrencyConverter("Indian Rupee", 0.013137D),
			new CurrencyConverter("AUD", 0.745390D),
			new CurrencyConverter("CAD", 00.795852D)
		)
	);
	
	public static String[] getCurrencyList() {
		return global_currency.stream().map(c->c.currency).toArray(String[]::new);
	}
	
	public static double getConvertRate(int fromIdx, int toIdx, double amount) throws Exception {
		if (fromIdx < 0 || fromIdx > global_currency.size() - 1) {
			throw new Exception("From Currency is out of index");
		}
		if (toIdx < 0 || toIdx > global_currency.size() - 1) {
			throw new Exception("To Currency is out of index");
		}
		return ((amount * global_currency.get(fromIdx).rate / global_currency.get(toIdx).rate));
	}
	
}
