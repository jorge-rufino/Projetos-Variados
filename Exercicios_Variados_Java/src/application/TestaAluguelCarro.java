package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.AluguelCarro;
import entities.Carro;
import entities.Util;
import services.ServicoAluguel;
import services.ServicoImpostoBrazil;

public class TestaAluguelCarro {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println("Entre com os dados do aluguel:");
		String modelo = Util.inputString("Modelo do carro:");
		Date dataInicio = sdf.parse(Util.inputString("Inicio aluguel:"));
		Date dataFim = sdf.parse(Util.inputString("Fim aluguel:"));
		double precoPorHora = Util.inputDouble("Preço por hora:");
		double precoPorDia = Util.inputDouble("Preço por dia:");
		
		AluguelCarro aluguelCarro = new AluguelCarro(dataInicio, dataFim, new Carro(modelo));
		ServicoAluguel servicoAluguel = new ServicoAluguel(precoPorHora, precoPorDia, new ServicoImpostoBrazil());
		servicoAluguel.processarNotaPagamento(aluguelCarro);
		
		System.out.println("NOTA DE PAGAMENTO:");
		System.out.println("Pagamento base:" + aluguelCarro.getNotaPagamento().getPagamentoBase());
		System.out.println("Imposto:" + aluguelCarro.getNotaPagamento().getImposto());
		System.out.println("Pagamento total:" + aluguelCarro.getNotaPagamento().pagamentoTotal());
	}

}
