package alura.collections.application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import alura.collections.entities.Aula;

public class TestaCurso {

	public static void main(String[] args) {
		List<Curso> cursos = new ArrayList<>();

		Curso javaColecoes = new Curso("Dominando as coleções do Java", "Paulo Silveira");
		javaColecoes.addAula(new Aula("Trabalhando com ArrayList", 21));
		javaColecoes.addAula(new Aula("Criando uma Aula", 20));
		javaColecoes.addAula(new Aula("Modelando com coleções", 24));
		
		Curso ensinoFundamental = new Curso("Ensino Fundamental", "Albert Einstein");
		ensinoFundamental.addAula(new Aula("Matematica", 21));
		ensinoFundamental.addAula(new Aula("Geografia", 20));
		ensinoFundamental.addAula(new Aula("Portugues", 24));
		ensinoFundamental.addAula(new Aula("Historia", 20));
		
		cursos.add(javaColecoes);
		cursos.add(ensinoFundamental);

//        Isso não e mais possivel devido a alteracao feita no metodo "getAulas()"
//        javaColecoes.getAulas().add(new Aula("TESTESTSETSETESTEST", 21));

		System.out.println(cursos.get(0).getAulas());
		System.out.println(cursos.get(1).getAulas());
		System.out.println("----------------");
		
		List<Aula> aulas = cursos.stream().map(curso -> curso.getAulas()).flatMap(List::stream).collect(Collectors.toList());
		aulas.forEach(aula -> System.out.println(aula));
	}

}
