package application;

import java.util.ArrayList;
import java.util.List;

public class ExemploLista2 {

	public static void main(String[] args) {
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(new Cliente(1L, "Jos�"));
		clientes.add(new Cliente(2L, "Maria"));
		clientes.add(new Cliente(3L, "Jo�o"));
		
		clientes.forEach(cliente -> System.out.printf("ID: %d, Nome: %s%n", cliente.getId(), cliente.getNome()));
	}

}
