package injecao_de_dependencia;

import java.math.BigDecimal;

import injecao_de_dependencia.notificacao.NotificadorEmail;
import injecao_de_dependencia.notificacao.NotificadorSMS;
import injecao_de_dependencia.service.AtivacaoClienteService;
import injecao_de_dependencia.service.EmissaoNotaFiscalService;

public class Main {
	
	public static void main (String args[]) {
		Cliente joao = new Cliente("Joao", "joao@gmail.com", "99999-9999");
		Cliente maria = new Cliente("Maria", "maria@gmail.com", "88888-8888");
		
		AtivacaoClienteService ativacaoService = new AtivacaoClienteService(new NotificadorSMS());
		ativacaoService.ativar(joao);
		ativacaoService.ativar(maria);
		
		System.out.println();
		
		EmissaoNotaFiscalService emissaoNotaFiscal = new EmissaoNotaFiscalService(new NotificadorEmail());
		emissaoNotaFiscal.emitir(joao, new Produto("TV", new BigDecimal("1000.00")));
	}
}
