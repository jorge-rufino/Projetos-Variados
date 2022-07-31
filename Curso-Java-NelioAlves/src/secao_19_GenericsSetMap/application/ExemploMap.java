package secao_19_GenericsSetMap.application;

import java.util.Map;
import java.util.TreeMap;

public class ExemploMap {

	public static void main(String[] args) {
		//TreeMap assim como o TreeSet, ordena automaticamente os elementos de acordo com o equals e hashcode da chave/key
		Map<String, String> cookies = new TreeMap<>();
		
		//Adiciona elementos no Map (Como o "add()" de Listas e Sets)
		cookies.put("username", "Maria");
		cookies.put("email", "maria@gmail.com");
		cookies.put("phone", "985697143");
		
		
		System.out.println("ALL COOKIES:");
		
		//Metodo "keySet()" retorna um Set com todas as keys
		for (String key : cookies.keySet()) {
			System.out.println(key + " : " + cookies.get(key));
		}
		
		//Como já existe uma key "phone", ele irá sobrescrever o valor 
		cookies.put("phone", "985697144");
		System.out.println();
		mostrarMap(cookies);
		
		//Verifica se existe a key "phone"
		System.out.println("\nContains 'phone' key: " + cookies.containsKey("phone"));
		//Busca uma key/chave
		System.out.println("\nPhone number: " + cookies.get("phone"));
		//Traz nulo se buscar uma chave que nao existe
		System.out.println("\nPhone number: " + cookies.get("teste"));
		
		System.out.println("\nSize: " + cookies.size());
		System.out.println();
		cookies.remove("phone");
		mostrarMap(cookies);
	}
	
	public static void mostrarMap (Map<String, String> map) {
		for (String key : map.keySet()) {
			System.out.println(key + " : " + map.get(key));
		}
	}
}
