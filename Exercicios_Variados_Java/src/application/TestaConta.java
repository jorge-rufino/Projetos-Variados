package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;

import entities.Cliente;
import entities.ClientePessoaFisica;
import entities.ClientePessoaJuridica;
import entities.Conta;
import entities.ContaPessoaFisica;
import entities.ContaPessoaJuridica;
import entities.Util;
import enums.OpcoesPainel;
import exceptions.DomainExceptions;

public class TestaConta {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);		
					
		List<Conta> listaContas = new ArrayList<Conta>();
		iniciarAplicacao(listaContas);
		
	}
	
	public static void iniciarAplicacao(List<Conta> listaContas) {
		OpcoesPainel opcoesPainel; 
		do {
			opcoesPainel = painel();
			switch (opcoesPainel) {
			case SAIR: {
				System.exit(0);				
			}break;
			case CRIAR_CONTA: {
				criarConta(listaContas);
			}break;
			case LISTAR_CONTAS: {
				listarContas(listaContas);
			}break;
			case LISTAR_CLIENTES: {
				listarClientes(listaContas);				
			}break;
			default:
				JOptionPane.showMessageDialog(null, "Opçao inválida! Tente novamente!");
			}
			
		}while (opcoesPainel != OpcoesPainel.SAIR);
	}
	
	public static OpcoesPainel painel() {
		String listaOpcoes = "";		
		for (OpcoesPainel opcoes : OpcoesPainel.values()) {
			if (opcoes.getCode() >= 0) {
				listaOpcoes += opcoes.getCode() + " - " + opcoes + "\n";
			}			
		}
		try {
			String opcaoUsuario = JOptionPane.showInputDialog(listaOpcoes);
			if (opcaoUsuario == null) {		//Quando clicar no botao Cancelar
				return OpcoesPainel.SAIR;
			}
			return OpcoesPainel.getOption(Integer.parseInt(opcaoUsuario));
		}
		catch (NumberFormatException e) {			
		}
		return OpcoesPainel.ERRO;
		
	}
	
	public static void criarConta(List<Conta> listaContas) {
		String tipoConta = Util.inputString("Qual o tipo de Conta(F/J)?");
		Cliente cliente = new Cliente();
		if (tipoConta.equals("f")) {
			String titular = Util.inputString("Nome do titular da conta:");
			String cpf = Util.inputString("CPF do titular:");
			String endereco = Util.inputString("Endereço do titular:");
			String telefone = Util.inputString("Telefone do titular:");
			String sexo = Util.inputString("Sexo do titular:");
			cliente = new ClientePessoaFisica(titular, endereco, telefone, sexo, cpf);
		}
		else if (tipoConta.equals("j")) {
			String titular = Util.inputString("Nome da empresa titular da conta:");
			String cnpj = Util.inputString("CNPJ do titular:");
			String endereco = Util.inputString("Endereço do titular:");
			String telefone = Util.inputString("Telefone do titular:");
			cliente = new ClientePessoaJuridica(titular, endereco, telefone, cnpj);
		}
		
		int numConta = Util.inputInt("Numero da conta:");		
		double saldo = Util.inputDouble("Saldo da conta:");		
		double limite = Util.inputDouble("Limite da conta:");
		
		Date dataAbertura = new Date();
		if (tipoConta.equals("f")) {
			listaContas.add(new ContaPessoaFisica(cliente, numConta, saldo, limite, dataAbertura, 0.0));
		}
		else if (tipoConta.equals("j")) {
			listaContas.add(new ContaPessoaJuridica(cliente, numConta, saldo, limite, dataAbertura, 0.0));
		}
	}
	
	public static void listarContas(List<Conta> listaContas) {
		try {
			if (listaContas.size() == 0) {
				throw new DomainExceptions("Ainda não foram criadas contas!");
			}
			String lista = "Lista de Contas:\n";
			for (Conta conta : listaContas) {
				lista += conta + "\n\n";
			}
			Util.outputString(lista, 0);
		}catch (DomainExceptions e) {
			Util.outputString(e.getMessage(), 0);
		}
		
	}
	
	public static void listarClientes(List<Conta> listaContas) {
		try {
			if (listaContas.size() == 0) {
				throw new DomainExceptions("Ainda não foram criadas contas!");
			}
			String lista = "Lista de Clientes:\n";
			for (Conta conta : listaContas) {
				lista += conta.getTitular() + "\nTitular da conta nº: " + conta.getNumero()+"\n\n";
			}
			Util.outputString(lista, 0);
		}catch (DomainExceptions e) {
			Util.outputString(e.getMessage(), 0);
		}		
	}
}
