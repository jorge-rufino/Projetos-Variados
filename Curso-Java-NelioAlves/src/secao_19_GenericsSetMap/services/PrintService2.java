package secao_19_GenericsSetMap.services;

import java.util.ArrayList;
import java.util.List;
				
public class PrintService2 {
	private List<Object> lista = new ArrayList<>();
	
	public void addValue(Object value) {
		lista.add(value);
	}
	
	public Object first() {
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
