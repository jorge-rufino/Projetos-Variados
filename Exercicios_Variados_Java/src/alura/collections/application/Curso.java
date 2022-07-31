package alura.collections.application;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import alura.collections.entities.Aula;

public class Curso {
	private String nome;
	private String instrutor;
	List<Aula> aulas = new LinkedList<>();
	
	public Curso(String nome, String instrutor) {
		this.nome = nome;
		this.instrutor = instrutor;
	}
	
	public List<Aula> getAulas(){
//	Devolve uma cópia da Lista e que não pode ser modificada, ou seja, nao se pode chamar o metodo "add()" através dele	
		return Collections.unmodifiableList(aulas);
	}
	
	public void addAula(Aula aula) {
		aulas.add(aula);
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setInstrutor(String instrutor) {
		this.instrutor = instrutor;
	}
	
	public String getInstrutor() {
		return instrutor;
	}
}
