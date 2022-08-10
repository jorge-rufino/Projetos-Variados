package model.entities;

import java.io.Serializable;

public class Telefone implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String ddd;
	private String numero;
	private String tipo;
	
	private Pessoa pessoa;

	public Telefone () {}
	
	public Telefone(Integer id, String ddd, String numero, String tipo, Pessoa pessoa) {	
		this.id = id;
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
		this.pessoa = pessoa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", ddd=" + ddd + ", numero=" + numero + ", tipo=" + tipo + ", pessoa=" + pessoa.getNome() +"]";
	}
	
}
