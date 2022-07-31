package secao_18_Interfaces.interfaces;

public interface InterestService {
	
	//Este método nao pode ser default pois "Interfaces" não podem conter variáveis e nem serem instanciadas
	double getInterestRate();
	
	//Aqui chamamos o metodo acima (getInterestRate()) pois ele estará implementado nas classes e pegar o valor da classe que o implementou
	default double payment(double amount, int month) {
		return amount * Math.pow(1 + (getInterestRate() / 100), month);
	}
	
}
