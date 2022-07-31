package entities;

import java.util.Date;

public class AluguelCarro {
	private Date inicioAluguel;
	private Date fimAluguel;
	
	private Carro carro;
	private NotaPagamento notaPagamento;
	
	public AluguelCarro(Date inicioAluguel, Date fimAluguel, Carro carro) {		
		this.inicioAluguel = inicioAluguel;
		this.fimAluguel = fimAluguel;
		this.carro = carro;
	}

	public Date getInicioAluguel() {
		return inicioAluguel;
	}

	public void setInicioAluguel(Date inicioAluguel) {
		this.inicioAluguel = inicioAluguel;
	}

	public Date getFimAluguel() {
		return fimAluguel;
	}

	public void setFimAluguel(Date fimAluguel) {
		this.fimAluguel = fimAluguel;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public NotaPagamento getNotaPagamento() {
		return notaPagamento;
	}

	public void setNotaPagamento(NotaPagamento notaPagamento) {
		this.notaPagamento = notaPagamento;
	}
		
}
