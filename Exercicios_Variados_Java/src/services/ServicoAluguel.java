package services;

import entities.AluguelCarro;
import entities.NotaPagamento;

public class ServicoAluguel {
	private Double precoPorHora;
	private Double precoPorDia;
	
	private ServicoImpostoBrazil impostoBrazil;
	
	public ServicoAluguel(Double precoPorHora, Double precoPorDia, ServicoImpostoBrazil impostoBrazil) {
		this.precoPorHora = precoPorHora;
		this.precoPorDia = precoPorDia;
		this.impostoBrazil = impostoBrazil;
	}
	
	public void processarNotaPagamento (AluguelCarro aluguelCarro) {
		long t1 = aluguelCarro.getInicioAluguel().getTime();
		long t2 = aluguelCarro.getFimAluguel().getTime();
		
		double horasLocadas = (double) (t2 - t1) / 1000 / 60 / 60;
		
		double pagamentoBase;
		if(horasLocadas <= 12) {
			pagamentoBase = Math.ceil(horasLocadas) * precoPorHora; 
		}
		else {
			pagamentoBase = Math.ceil(horasLocadas / 24) * precoPorDia;
		}
		
		double imposto = impostoBrazil.imposto(pagamentoBase);
		
		aluguelCarro.setNotaPagamento(new NotaPagamento(pagamentoBase, imposto));
	}

	public Double getPrecoPorHora() {
		return precoPorHora;
	}

	public void setPrecoPorHora(Double precoPorHora) {
		this.precoPorHora = precoPorHora;
	}

	public Double getPrecoPorDia() {
		return precoPorDia;
	}

	public void setPrecoPorDia(Double precoPorDia) {
		this.precoPorDia = precoPorDia;
	}

	public ServicoImpostoBrazil getImpostoBrazil() {
		return impostoBrazil;
	}

	public void setImpostoBrazil(ServicoImpostoBrazil impostoBrazil) {
		this.impostoBrazil = impostoBrazil;
	}
}
