package secao_19_GenericsSetMap.application;

import java.util.HashSet;
import java.util.Set;

public class ExemploSet {

	public static void main(String[] args) {
		Set<String> setList = new HashSet<>();
		
		setList.add("TV");
		setList.add("Notebook");
		setList.add("Tablet");
		setList.add("Chair");
		
		setList.remove("Chair");
		//setList.removeIf(x -> x.length() >= 3); Remove todos que tem mais de 3 letras
		setList.removeIf(x -> x.charAt(0) == 'T'); //Remove todos que começam com T
		
		System.out.println(setList.contains("Notebook"));	//Contem "Notebook"?
		
		for (String string : setList) {
			System.out.println(string);
		}
	}

}
