package secao_20_Prog_Funcional_Lambda.entities;

public class Product {
	private String name;
	private Double price;

	public Product(String name, Double price){
		
		this.name = name;
		this.price = price;
	}

	public static String staticUpperCaseNameFunction(Product p ) {
		return p.getName().toUpperCase();
	}
	
	public String nonStaticUpperCaseNameFunction() {
		return name.toUpperCase();
	}
	
	public static void staticUpdatePrice (Product p) {
		p.setPrice(p.getPrice() * 1.1);
	}
	
	public void nonStaticUpdatePrice () {
		price *=  1.1;
	}
	
	public static boolean staticProductPredicate(Product p) {
		return p.getPrice() >= 100;
	}
	
//	Como é um metodo nao estatico, ele vai trabalhar com o proprio objeto, por isso nao recebe argumentos
	public boolean nonStaticProductPredicate() {
		return getPrice() >= 100;
	}
	
	@Override
	public String toString() {
		return name + ", " + String.format("%.2f", price);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
