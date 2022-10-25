package injecao_de_dependencia.service;

import injecao_de_dependencia.Cliente;
import injecao_de_dependencia.Produto;
import injecao_de_dependencia.notificacao.Notificador;

public class EmissaoNotaFiscalService {
	
	private Notificador notificador;

	//Aqui ele pode receber tanto um notificador de Email quanto de SMS
	public EmissaoNotaFiscalService(Notificador notificador) {
		this.notificador = notificador;
	}
	
	public void emitir(Cliente cliente, Produto produto) {
		
		notificador.notificar(cliente, "Nota fiscal do produto " + produto.getNome() + " no valor de R$ " +produto.getValorTotal()
							  + " foi emitida!");
	}
}
