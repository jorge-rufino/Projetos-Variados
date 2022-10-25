package injecao_de_dependencia.notificacao;

import injecao_de_dependencia.Cliente;

public interface Notificador {
	
	void notificar(Cliente cliente, String messagem);
}
