package alura.collections.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import alura.collections.entities.Aula;

public class TestaListaDeAula {

	public static void main(String[] args) {
		List<Aula> aulas = new ArrayList<>();
		
		aulas.add(new Aula("Revistando as ArrayLists", 21));
		aulas.add(new Aula("Listas de objetos", 20));
		aulas.add(new Aula("Relacionamento de listas e objetos", 15));
		
		 // antes de ordenar:
        System.out.println(aulas);

        Collections.sort(aulas);

        // depois de ordenar por aulta:
        System.out.println(aulas);
        
//        As duas linhas abaixo fazem o mesmo
        
//        Collections.sort(aulas, Comparator.comparing(Aula::getTempo)); 
        aulas.sort(Comparator.comparing(Aula::getTempo));
        
     // depois de ordenar por tempo:
        System.out.println(aulas);
	}

}
