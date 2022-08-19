package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import model.entities.Pessoa;

public class Teste {

	public static void main(String[] args) {
		List<Pessoa> teste = new ArrayList<>();
		teste.add(new Pessoa(null, "Jorge", null, null, null, null, null, null, null));
		teste.add(new Pessoa(null, "Mario", null, null, null, null, null, null, null));
		teste.add(new Pessoa(null, "Ana", null, null, null, null, null, null, null));
		
		System.out.println(teste);
		
		teste.sort(Comparator.comparing(Pessoa::getNome));
		
		System.out.println("\n"+teste);
	}

}
