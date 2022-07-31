package entities;

public class Rent {
	private double normal = 150.0, suite = 200.0;	
	private String name, email;
	private int dias, tipoQuarto;	
	
	public Rent(String name, String email, int dias, int tipoQuarto) {		
		this.name = name;
		this.email = email;
		this.dias = dias;
		this.tipoQuarto = tipoQuarto;
	}

	public Rent(String name, String email) {		
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		String quarto = "";
		if (tipoQuarto == 1) {
			quarto = "normal";
		}
		else {
			quarto = "suíte";
		}
		
		return name 
				+ ", " 
				+ email
				+", " 
				+ dias 
				+" dias"
				+", quarto "
				+ quarto
				+ ", total a pagar: "
				+"R$ "
				+String.format("%.2f", alguelTotal())
				;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}
	
	public double alguelTotal() {
		double diaria = 0.0;
		
		if(tipoQuarto == 1) {
			diaria = dias * normal;
		}			
		else {
			diaria = dias * suite;
		}
		return diaria;
	}
	
}
