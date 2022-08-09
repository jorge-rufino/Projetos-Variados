package model.entities;

import java.io.Serializable;

public class Telefone implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String ddd;
	private String numero;
	private String tipo;
	
	private Integer idPessoa;

	public Telefone () {}
	
	public Telefone(Integer id, String ddd, String numero, String tipo, Integer idPessoa) {	
		this.id = id;
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
		this.idPessoa = idPessoa;
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

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", ddd=" + ddd + ", numero=" + numero + ", tipo=" + tipo + ", idPessoa=" + idPessoa
				+ "]";
	}
	
}
