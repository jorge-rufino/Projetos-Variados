package entities;

import java.util.Date;

import exceptions.DomainExceptions;

public class Conta {
	private Cliente titular;
	private int numero;
	private double saldo, limite;
	private Date dataAbertura;
	
	public Conta() {}

	public Conta(Cliente titular, int numero, double saldo, double limite, Date dataAbertura) {		
		this.titular = titular;
		this.numero = numero;
		this.saldo = saldo;
		this.limite = limite;
		this.dataAbertura = dataAbertura;
	}
	
	public void saque (double valor) {
		try {
			if (valor > limite) {
				throw new DomainExceptions("Saque maior que o limite permitido");
			}
			if (valor > saldo) {
				throw new DomainExceptions("Saldo Insuficiente!");
			}			
			saldo -= valor;
			System.out.println("Saque realizado com sucesso!");
		}
		catch (DomainExceptions e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void tranferePara(Conta contaDestino, double valor) {
		try {
			if (valor > limite) {
				throw new DomainExceptions("Transferencia maior que o limite permitido");
			}
			if (valor > saldo) {
				throw new DomainExceptions("Saldo Insuficiente para transferencia!");
			}			
		} catch (DomainExceptions e) {
			System.out.println(e.getMessage());
		}
		saldo -= valor;
		contaDestino.deposito(valor);
		System.out.println("Transferido R$"+valor+" para a conta nº "+ contaDestino.getNumero() + ", do titular: " + contaDestino.getTitular().getNome());
	}
	
	public void deposito(double valor) {
		saldo += valor;
	}
	
	public Cliente getTitular() {
		return titular;
	}

	public void addTitular(Cliente titular) {
		this.titular = titular;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public String getTipoConta() {
		return "SuperClasse";
	}
}
