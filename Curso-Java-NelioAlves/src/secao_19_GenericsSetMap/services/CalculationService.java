package secao_19_GenericsSetMap.services;

import java.util.List;

public class CalculationService {
	//Metodo generico que recebe uma lista de qualquer tipo e retorna o maior da lista
	public static <Tipo extends Comparable<Tipo>> Tipo max (List<Tipo> lista) {
		if (lista.isEmpty()) {
			throw new IllegalStateException("List is empty");
		}
		
		Tipo max = lista.get(0);
		
		for (Tipo obj : lista) {
//Lembrando que se o "obj" for maior que o "max" ele retorna um "inteiro positivo", se for menor um "inteiro negativo" e se for igual retorna 0
			if (obj.compareTo(max) > 0) {
				max = obj;
			}
		}
		return max;
	}
}
