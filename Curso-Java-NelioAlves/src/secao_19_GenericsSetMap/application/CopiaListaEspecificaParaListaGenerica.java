package secao_19_GenericsSetMap.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopiaListaEspecificaParaListaGenerica {

	public static void main(String[] args) {
		List<Integer> myInts = Arrays.asList(1, 2, 3, 4);
		List<Double> myDoubles = Arrays.asList(3.14, 6.28);
		List<Object> myObjs = new ArrayList<Object>();
		copy(myInts, myObjs);
		printList(myObjs);
		copy(myDoubles, myObjs); //Como "myObjs" já tinha recebido "myInts", aqui ele adicionou os valore de "myDoubles"
		printList(myObjs);
	}
	//Recebe uma lista mais especifica de qualquer tipo Numerico e copia pra uma lista mais generica 
	//Lembrando que Number é uma superclasse de Integer, Double, Float, Long, Short, Byte
	//E a a superclasse de Number é Object
	public static void copy(List<? extends Number> source, List<? super Number> destiny) {
		for (Number number : source) {
			destiny.add(number);
		}
	}

	//Recebe qualquer tipo de lista e imprime
	public static void printList(List<?> list) {
		for (Object obj : list) {
			System.out.print(obj + " ");
		}
		System.out.println();
	}

}
