package entities;

public class CurrencyConverter {
	public static final double IOF = 0.06;
	
	public static double conversor(double cotacaoDolar, double valorReal) {
		return (valorReal * cotacaoDolar) + (valorReal * cotacaoDolar * IOF);
	}
}
