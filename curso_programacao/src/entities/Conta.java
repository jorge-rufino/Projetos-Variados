package entities;

public class Conta {
	private final double TAXA = 5.0;
	
	private final int numero;
	private String nomeTitular;
	private double saldoConta;
	
	public Conta(int numero, String nomeTitular, double depositoInicial) {
		this.numero = numero;
		this.nomeTitular = nomeTitular;
		deposito(depositoInicial);
	}
	public Conta(int numero, String nomeTitular) {
		this.numero = numero;
		this.nomeTitular = nomeTitular;		
	}
	
	public String getNomeTitular() {
		return nomeTitular;
	}
	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}
	public double getSaldoConta() {
		return saldoConta;
	}	
	public int getNumero() {
		return numero;
	}
	
	public void saque(double valor) {
		saldoConta -= valor + TAXA;
	}
	
	public void deposito(double valor) {
		saldoConta += valor;
	}
	
	public String toString() {
		return "Account " 
				+ numero 
				+ ", Holder: "
				+ nomeTitular
				+", Balance: $" 
				+ String.format("%.2f", saldoConta); 
	}
}
