package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContaPessoaJuridica  extends Conta{
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private double emprestimo;
	
	public ContaPessoaJuridica() {
		super();
	}
	
	public ContaPessoaJuridica(Cliente titular, int numero, double saldo, double limite, Date dataAbertura,
			double emprestimo) {
		super(titular, numero, saldo, limite, dataAbertura);
		this.emprestimo = emprestimo;
	}
	@Override
	public String toString() {
		return "Tipo de Conta: " + getTipoConta()
		+"\nTitular: " + super.getTitular().getNome()
		+"\nConta nº: " + super.getNumero()
		+"\nSaldo: "+ String.format("%.2f", super.getSaldo())
		+"\nLimite: " + String.format("%.2f", super.getLimite())
		+"\nEmprestimo: " + String.format("%.2f", getEmprestimo())
		+"\nData de abertura:" + sdf.format(super.getDataAbertura());
	}
	
	public double getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(double emprestimo) {
		this.emprestimo = emprestimo;
	}
	public String getTipoConta() {
		return "Conta Pessoa Jurídica";
	}
}
