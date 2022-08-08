package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String apelido;
	private String cpf;
	private String sexo;
	private String email;
	private Date data_cadastro;
	
	private Categoria categoria;
	
	private Endereco endereco;
	
	private List<Telefone> telefones = new ArrayList<>();
	
	public Pessoa () {}

	public Pessoa(Integer id, String nome, String apelido, String cpf, String sexo, String email, Date data_cadastro,
		Categoria categoria, Endereco endereco) {
		
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		this.cpf = cpf;
		this.sexo = sexo;
		this.email = email;
		this.data_cadastro = data_cadastro;
		this.categoria = categoria;
		this.endereco = endereco;
	}

	public void addTelefone(Telefone telefone) {
		telefones.add(telefone);
	}
	
	public List<Telefone>  getTelefone(){
		return telefones;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", apelido=" + apelido + ", cpf=" + cpf + ", sexo=" + sexo
				+ ", email=" + email + ", data_cadastro=" + data_cadastro + ", categoria=" + categoria + ", endereco="
				+ endereco + ", telefones=" + telefones + "]";
	}
	
}
