package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.Aluno;

public class TesteListArrayList {

	public static void main(String[] args) {
		Aluno a1 = new Aluno ("João");
		Aluno a2 = new Aluno ("Maria");
		Aluno a3 = new Aluno ("Jose");
		Aluno a4 = new Aluno ("Pedro");
		
		//ArrayList as referencias aos objetos ficam na ordem que são adicionados e podem ser repetidas
		List<Aluno> alunos = new ArrayList<>();
		
		alunos.add(a1);
		alunos.add(a2);
		alunos.add(a3);
		alunos.add(a4);
		alunos.add(a4);
		alunos.add(a4);
		
		imprimirAlunos(alunos);
		System.out.println();
		
		//HashSet não mantém a ordem de adição e não pode repetir
		Set<Aluno> alunosSet = new HashSet<>();
		
		alunosSet.add(a1);
		alunosSet.add(a2);
		alunosSet.add(a3);
		alunosSet.add(a4);
		alunosSet.add(a4);
		alunosSet.add(a4);		
		
		imprimirAlunos(alunosSet);

	}
	
	public static void imprimirAlunos(List<Aluno> alunos) {
		for(Aluno obj : alunos) {
			System.out.println(obj.getNome());
		}
	}
	
	public static void imprimirAlunos(Set<Aluno> alunos) {		
		for(Aluno obj : alunos) {
			System.out.println(obj.getNome());
		}
	}

}

