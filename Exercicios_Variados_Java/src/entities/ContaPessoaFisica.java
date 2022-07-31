package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContaPessoaFisica extends Conta{
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private double investimentos;
	private final double TAXA_REDIMENTOS = 0.05;
	
	public ContaPessoaFisica () {
		super();
	}
	@Override
	public String toString() {
		return "Tipo de Conta: " + getTipoConta()
		+"\nTitular: " + super.getTitular().getNome()
		+"\nConta nº: " + super.getNumero()
		+"\nSaldo: "+ String.format("%.2f", super.getSaldo())
		+"\nLimite: " + String.format("%.2f", super.getLimite())
		+"\nInvestimentos: " + String.format("%.2f", getInvestimentos())
		+"\nData de abertura:" + sdf.format(super.getDataAbertura());
	}
	
	public ContaPessoaFisica(Cliente titular, int numero, double saldo, double limite, Date dataAbertura,
			double investimentos) {
		super(titular, numero, saldo, limite, dataAbertura);
		this.investimentos = investimentos;
	}

	public double getInvestimentos() {
		return investimentos + (investimentos * TAXA_REDIMENTOS);
	}

	public void setInvestimentos(double investimentos) {
		this.investimentos = investimentos;
	}	
	
	@Override
	public String getTipoConta() {
		return "Conta corrente";
	}
}
