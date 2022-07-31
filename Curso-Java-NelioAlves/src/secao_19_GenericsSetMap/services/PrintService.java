package secao_19_GenericsSetMap.services;

import java.util.ArrayList;
import java.util.List;
				/*ESTA CLASSE SO FUNCIONA PARA "INTEIROS"... E SE NÓS QUISESSEMOS PARA STRING?
				 * TERIAMOS QUE MUDAR O TIPO PARA STRING OU FAZER OUTRA CLASSE
				 * PARA TENTAR REUTILIZAR A CLASSE PARA ESTES CASOS, VIDE CLASSE "PrinService2"*/
public class PrintService {
	private List<Integer> lista = new ArrayList<>();
	
	public void addValue(int value) {
		lista.add(value);
	}
	
	public int first() {
		if (lista.isEmpty()) {
			throw new IllegalStateException("Lista is empty!");
		}
		return lista.get(0);
	}
	
	public void print() {
		String result ="[";
		
		if (!lista.isEmpty()) {
			result += lista.get(0);
		}
		for (int i = 1; i < lista.size(); i++) {
			result += ", " + lista.get(i);
		}		
		result +="]";
		System.out.println(result);
	}
}
