package entities;

public class ClientePessoaJuridica extends Cliente{
	private String cnpj;
	
	public ClientePessoaJuridica() {
		super();
	}

	public ClientePessoaJuridica(String nome, String endereco, String telefone, String cnpj) {
		super(nome, endereco, telefone);
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {		
		return "Nome: " + super.getNome()
				+"\nCnpj: "+ cnpj
				+"\nEndereco: " + super.getEndereco()				
				+"\nTelefone: " + super.getTelefone();
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}
