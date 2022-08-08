package model.entities;

import java.io.Serializable;

public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String rua;
	private String bairro;
	private String complemento;
	private String cidade;
	
	public Endereco() {}
	
	public Endereco(Integer id, String rua, String bairro, String complemento, String cidade) {
		this.id = id;
		this.rua = rua;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cidade = cidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", rua=" + rua + ", bairro=" + bairro + ", complemento=" + complemento
				+ ", cidade=" + cidade + "]";
	}
		
}
