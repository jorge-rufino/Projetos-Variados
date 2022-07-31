package secao_19_GenericsSetMap.application;

import application.Util;
import secao_19_GenericsSetMap.services.PrintService3;

public class TestaPrintService {

	public static void main(String[] args) {
		/*Com objeto da classe "PrintService2" ele aceita tanto "int" quanto "string", na verdade ele está aceitando 
		 * QUALQUER TIPO DE OBJETO, então o que acontece se misturarmos int com string ou outro objeto? 
		 * Teremos o problema de "Type Safety"...
		 * para contornamos este problema criamos a "PrintService3"*/ 
		 
		//PrintService ps = new PrintService();
		//PrintService2 ps = new PrintService2();
		
		//Agora basta colocar o tipo que precisar entre <> que o a classe está implementada para receber qualquer tipo
		PrintService3<Integer> ps = new PrintService3<>();
		
		int n = Util.inputInt("How many values?");
		
		for (int i =0; i < n; i++) {
			int value = Util.inputInt("Value:");
			ps.addValue(value);
		}
		
		ps.print();
		System.out.println("First: " + ps.first());
	}

}
