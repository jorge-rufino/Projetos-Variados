package entities;

public class ClientePessoaFisica extends Cliente{
	private String cpf;
	private String sexo;
	
	public ClientePessoaFisica () {
		super();
	}

	public ClientePessoaFisica(String nome, String endereco, String telefone, String sexo, String cpf) {
		super(nome, endereco, telefone);
		this.cpf = cpf;
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Nome: " + super.getNome()
				+"\nCpf: "+ cpf
				+"\nEndereco: " + super.getEndereco()
				+"\nSexo: " + sexo
				+"\nTelefone: " + super.getTelefone();
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}	
	
}
