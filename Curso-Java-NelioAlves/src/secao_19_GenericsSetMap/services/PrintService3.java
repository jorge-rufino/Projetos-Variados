package secao_19_GenericsSetMap.services;

import java.util.ArrayList;
import java.util.List;
				
//a letra "T" que será o tipo, pode ser qualquer nome que o programador quiser
public class PrintService3<T> {
	private List<T> lista = new ArrayList<>();
	
	public void addValue(T value) {
		lista.add(value);
	}
	
	public T first() {
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
