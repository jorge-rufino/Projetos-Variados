package secao_19_GenericsSetMap.application;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import application.Util;

public class Exercicio02 {

	public static void main(String[] args) {
		Set<Integer> codigosA = new TreeSet<Integer>();
		Set<Integer> codigosB = new TreeSet<Integer>();
		Set<Integer> codigosC = new TreeSet<Integer>();
		
		
		int n = Util.inputInt("How many students for course A? ");
		for (int i = 0; i < n; i++) {
			int codigo = Util.inputInt("Código do aluno:");
			codigosA.add(codigo);
		}
		
	 	n = Util.inputInt("How many students for course B? ");
	 	for (int i = 0; i < n; i++) {
	 		int codigo = Util.inputInt("Código do aluno:");
	 		codigosB.add(codigo);
		}
	 	
		n = Util.inputInt("How many students for course C? ");
		for (int i = 0; i < n; i++) {
			int codigo = Util.inputInt("Código do aluno:");
			codigosC.add(codigo);
		}
		
		Set<Integer>  total = new HashSet<Integer>(codigosA);
		total.addAll(codigosB);
		total.addAll(codigosC);
		
		for (Integer integer : total) {
			System.out.println(integer);
		}
	}

}

