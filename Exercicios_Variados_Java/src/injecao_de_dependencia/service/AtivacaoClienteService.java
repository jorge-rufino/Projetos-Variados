package injecao_de_dependencia.service;

import injecao_de_dependencia.Cliente;
import injecao_de_dependencia.notificacao.Notificador;

public class AtivacaoClienteService {
	
	private Notificador notificador;
	
	//Aqui ele pode receber tanto um notificador de Email quanto de SMS
	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
				
		this.notificador.notificar(cliente, "Seu cadastro foi ativado!");
	}
}
