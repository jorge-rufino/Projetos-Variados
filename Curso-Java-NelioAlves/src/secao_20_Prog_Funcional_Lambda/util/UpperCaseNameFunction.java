package secao_20_Prog_Funcional_Lambda.util;

import java.util.function.Function;

import secao_20_Prog_Funcional_Lambda.entities.Product;

//Recebe o primeiro argumento e retorna o segundo, neste caso vamos receber um Product e retorna o nome em MAISCULO
public class UpperCaseNameFunction implements Function<Product, String>{

	@Override
	public String apply(Product p) {
		return p.getName().toUpperCase();
	}

}
