package secao_20_Prog_Funcional_Lambda.util;

import java.util.function.Consumer;

import secao_20_Prog_Funcional_Lambda.entities.Product;

public class PriceUpdateConsumer implements Consumer<Product>{

	@Override
	public void accept(Product product) {
		product.setPrice(product.getPrice() * 1.1);	//Aumenta o price em 10%
	}

}
